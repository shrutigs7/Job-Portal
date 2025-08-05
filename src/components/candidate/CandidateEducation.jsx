// src/components/candidate/CandidateEducation.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const API_BASE_URL = 'http://localhost:8081';

const degrees = [
  'BE', 'BTECH', 'ME', 'MTECH', 'BCS', 'BSC', 'MCS', 'MSC', 'DIPLOMA', 'TENTH', 'TWELTH'
];

function CandidateEducation() {
  const user = JSON.parse(localStorage.getItem("user"));
  const userId = user?.userId;

  const [formData, setFormData] = useState({
    degree: '',
    grade: '',
    startYear: '',
    endYear: '',
    university: ''
  });

  const [educations, setEducations] = useState([]);
  const [errors, setErrors] = useState({});
  const navigate = useNavigate();


  const handleChange = e => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const validate = () => {
    const errs = {};
    if (!formData.degree) errs.degree = "Degree is required";
    if (!formData.grade.trim()) errs.grade = "Grade is required";
    if (!formData.startYear) errs.startYear = "Start year is required";
    if (!formData.endYear) errs.endYear = "End year is required";
    if (!formData.university.trim()) errs.university = "University is required";
    return errs;
  };

  const handleSubmit = e => {
    e.preventDefault();
    const validationErrors = validate();
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }

    axios.post(`${API_BASE_URL}/candidate/education/${userId}`, formData)
      .then(() => {
        fetchEducations();
        setFormData({ degree: '', grade: '', startYear: '', endYear: '', university: '' });
        setErrors({});
      })
      .catch(err => {
        console.error("Failed to add education:", err);
        alert("Error adding education");
      });
  };

  const fetchEducations = () => {
    axios.get(`${API_BASE_URL}/candidate/${userId}`)
      .then(res => {
        const { educationList } = res.data;
        setEducations(educationList || []);
      })
      .catch(err => {
        console.error("Failed to load education list", err);
      });
  };

  useEffect(() => {
    fetchEducations();
  }, []);

  return (
    <div className="container mt-4">
      <h3>Candidate Registration - Step 2: Education</h3>

      <form className="row g-3 mb-4" onSubmit={handleSubmit}>
        <div className="col-md-4">
          <label className="form-label">Degree *</label>
          <select className="form-select" name="degree" value={formData.degree} onChange={handleChange}>
            <option value="">-- Select Degree --</option>
            {degrees.map(degree => (
              <option key={degree} value={degree}>{degree}</option>
            ))}
          </select>
          {errors.degree && <div className="text-danger">{errors.degree}</div>}
        </div>

        <div className="col-md-4">
          <label className="form-label">Grade *</label>
          <input type="text" name="grade" className="form-control" value={formData.grade} onChange={handleChange} />
          {errors.grade && <div className="text-danger">{errors.grade}</div>}
        </div>

        <div className="col-md-2">
          <label className="form-label">Start Year *</label>
          <input type="date" name="startYear" className="form-control" value={formData.startYear} onChange={handleChange} />
          {errors.startYear && <div className="text-danger">{errors.startYear}</div>}
        </div>

        <div className="col-md-2">
          <label className="form-label">End Year *</label>
          <input type="date" name="endYear" className="form-control" value={formData.endYear} onChange={handleChange} />
          {errors.endYear && <div className="text-danger">{errors.endYear}</div>}
        </div>

        <div className="col-md-6">
          <label className="form-label">University *</label>
          <input type="text" name="university" className="form-control" value={formData.university} onChange={handleChange} />
          {errors.university && <div className="text-danger">{errors.university}</div>}
        </div>

        <div className="col-md-12">
          <button type="submit" className="btn btn-success">Add Education</button>
        </div>
      </form>

      <h5>All Added Education</h5>
      {educations.length === 0 ? (
        <p>No education added yet.</p>
      ) : (
        <table className="table table-bordered">
          <thead>
            <tr>
              <th>Degree</th>
              <th>Grade</th>
              <th>Start Year</th>
              <th>End Year</th>
              <th>University</th>
            </tr>
          </thead>
          <tbody>
            {educations.map((edu, index) => (
              <tr key={index}>
                <td>{edu.degree}</td>
                <td>{edu.grade}</td>
                <td>{edu.startYear}</td>
                <td>{edu.endYear}</td>
                <td>{edu.university}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
         <div className="d-flex justify-content-between mt-4">
        {/* <button className="btn btn-secondary" onClick={() => navigate('/candidate/register/personal')}>Back</button> */}
        <button className="btn btn-primary" onClick={() => navigate('/candidate/register/experience')}>Next</button>
      </div>
    </div>
  );
}

export default CandidateEducation;
