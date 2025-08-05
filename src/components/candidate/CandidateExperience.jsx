// src/components/candidate/CandidateExperience.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const API_BASE_URL = 'http://localhost:8081';

function CandidateExperience() {
  const user = JSON.parse(localStorage.getItem("user"));
  const userId = user?.userId;
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    role: '',
    companyName: '',
    startDate: '',
    endDate: '',
    expInYears: '',
    description: ''
  });

  const [experienceList, setExperienceList] = useState([]);
  const [errors, setErrors] = useState({});

  const handleChange = e => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const validate = () => {
    const errs = {};
    if (!formData.role.trim()) errs.role = "Role is required";
    if (!formData.companyName.trim()) errs.companyName = "Company Name is required";
    if (!formData.startDate) errs.startDate = "Start Date is required";
    if (!formData.description.trim()) errs.description = "Description is required";
    return errs;
  };

  const handleSubmit = e => {
    e.preventDefault();
    const validationErrors = validate();
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }

    axios.post(`${API_BASE_URL}/candidate/experience/${userId}`, formData)
      .then(() => {
        fetchExperiences();
        setFormData({ role: '', companyName: '', startDate: '', endDate: '', expInYears: '', description: '' });
        setErrors({});
      })
      .catch(err => {
        console.error("Failed to add experience:", err);
        alert("Error adding experience");
      });
  };

  const fetchExperiences = () => {
    axios.get(`${API_BASE_URL}/candidate/${userId}`)
      .then(res => {
        const { experienceList } = res.data;
        setExperienceList(experienceList || []);
      })
      .catch(err => {
        console.error("Failed to load experience list", err);
      });
  };

  useEffect(() => {
    fetchExperiences();
  }, []);

  return (
    <div className="container mt-4">
      <h3>Candidate Registration - Step 3: Experience</h3>

      <form className="row g-3 mb-4" onSubmit={handleSubmit}>
        <div className="col-md-4">
          <label className="form-label">Role *</label>
          <input type="text" name="role" className="form-control" value={formData.role} onChange={handleChange} />
          {errors.role && <div className="text-danger">{errors.role}</div>}
        </div>

        <div className="col-md-4">
          <label className="form-label">Company Name *</label>
          <input type="text" name="companyName" className="form-control" value={formData.companyName} onChange={handleChange} />
          {errors.companyName && <div className="text-danger">{errors.companyName}</div>}
        </div>

        <div className="col-md-2">
          <label className="form-label">Start Date *</label>
          <input type="date" name="startDate" className="form-control" value={formData.startDate} onChange={handleChange} />
          {errors.startDate && <div className="text-danger">{errors.startDate}</div>}
        </div>

        <div className="col-md-2">
          <label className="form-label">End Date</label>
          <input type="date" name="endDate" className="form-control" value={formData.endDate} onChange={handleChange} />
        </div>

        <div className="col-md-2">
          <label className="form-label">Experience (in years)</label>
          <input type="number" step="0.1" name="expInYears" className="form-control" value={formData.expInYears} onChange={handleChange} />
        </div>

        <div className="col-md-10">
          <label className="form-label">Description *</label>
          <textarea name="description" className="form-control" rows="3" value={formData.description} onChange={handleChange}></textarea>
          {errors.description && <div className="text-danger">{errors.description}</div>}
        </div>

        <div className="col-md-12">
          <button type="submit" className="btn btn-success">Add Experience</button>
        </div>
      </form>

      <h5>All Added Experience</h5>
      {experienceList.length === 0 ? (
        <p>No experience added yet.</p>
      ) : (
        <table className="table table-bordered">
          <thead>
            <tr>
              <th>Role</th>
              <th>Company</th>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Years</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            {experienceList.map((exp, index) => (
              <tr key={index}>
                <td>{exp.role}</td>
                <td>{exp.companyName}</td>
                <td>{exp.startDate}</td>
                <td>{exp.endDate}</td>
                <td>{exp.expInYears}</td>
                <td>{exp.description}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      <div className="d-flex justify-content-between mt-4">
        <button className="btn btn-secondary" onClick={() => navigate('/candidate/register/education')}>Back</button>
        <button className="btn btn-primary" onClick={() => navigate('/candidate/register/skills')}>Next</button>
      </div>
    </div>
  );
}

export default CandidateExperience;
