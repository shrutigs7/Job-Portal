import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8081';
const jobTypeOptions = ['INTERN', 'TRAINEE', 'JUNIOR', 'ASSOCIATE', 'MID_LEVEL', 'SENIOR', 'LEAD', 'MANAGER', 'EXECUTIVE'];

function UpdateJob() {
  const { jobId } = useParams();
  const navigate = useNavigate();
  const [skillsError, setSkillsError] = useState('');


  const [formData, setFormData] = useState({
    title: '',
    companyName: '',
    yearOfExperience: '',
    type: '',
    description: '',
    location: '',
    jskills: [],
  });

  const [allSkills, setAllSkills] = useState([]);

  useEffect(() => {
    // Fetch all skills
    axios.get(`${API_BASE_URL}/skill/get`)
      .then(res => setAllSkills(res.data))
      .catch(err => console.error('Error loading skills:', err));

    // Fetch job details
    axios.get(`${API_BASE_URL}/recruiter/job/${jobId}`)
      .then(res => {
        const job = res.data;
        setFormData({
          title: job.title || '',
          companyName: job.companyName || '',
          yearOfExperience: job.yearOfExperience || '',
          location: job.location || '',
          type: job.type || '',
          description: job.description || '',
          jskills: job.jskills?.map(skill => Number(skill.skillId)) || [], // Assuming job.skills is a list of skill objects
        });
      })
      .catch(err => console.error('Error fetching job:', err));
  }, [jobId]);

  const handleChange = e => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSkillChange = skillId => {
    setFormData(prev => {
      const updatedSkills = prev.jskills.includes(skillId)
        ? prev.jskills.filter(id => id !== skillId) // remove if already selected
        : [...prev.jskills, skillId]; // add if not selected

      return {
        ...prev,
        jskills: updatedSkills,
      };
    });
  };


  const handleSubmit = e => {
    e.preventDefault();

    // Validation: At least one skill must be selected
    if (formData.jskills.length === 0) {
      setSkillsError('Please select at least one skill.');
      return;
    }

    setSkillsError(''); // Clear previous error if validation passes

    const user = JSON.parse(localStorage.getItem("user"));
    const payload = {
      ...formData,
      recruiter: user,
    };

    axios.put(`${API_BASE_URL}/recruiter/job/${jobId}`, payload)
      .then(() => {
        alert("Job updated successfully!");
        navigate("/recruiter/all-jobs");
      })
      .catch(err => console.error("Update failed:", err));
  };


  return (
    <div className="container mt-4">
      <h3>Update Job</h3>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Title</label>
          <input type="text" name="title" className="form-control" value={formData.title} onChange={handleChange} required />
        </div>

        <div className="mb-3">
          <label className="form-label">Company Name</label>
          <input type="text" name="companyName" className="form-control" value={formData.companyName} onChange={handleChange} required />
        </div>

        <div className="mb-3">
          <label className="form-label">Location</label>
          <input type="text" name="location" className="form-control" value={formData.location} onChange={handleChange} required />
        </div>


        <div className="mb-3">
          <label className="form-label">Years of Experience</label>
          <input type="number" name="yearOfExperience" className="form-control" value={formData.yearOfExperience} onChange={handleChange} required />
        </div>

        <div className="mb-3">
          <label className="form-label">Job Type</label>
          <select name="type" className="form-select" value={formData.type} onChange={handleChange} required>
            <option value="">-- Select Type --</option>
            {jobTypeOptions.map(type => (
              <option key={type} value={type}>{type.replace('_', ' ')}</option>
            ))}
          </select>
        </div>

        <div className="mb-3">
          <label className="form-label">Description</label>
          <textarea name="description" className="form-control" rows="4" value={formData.description} onChange={handleChange} required></textarea>
        </div>

        <div className="mb-3">
          <label className="form-label">Skills</label>
          <div className="form-check">
            {allSkills.map(skill => (
              <div key={skill.skillId} className="form-check">
                <input
                  type="checkbox"
                  className="form-check-input"
                  id={`skill-${skill.skillId}`}
                  value={Number(skill.skillId)} // Ensure value is a number
                  checked={formData.jskills.includes(skill.skillId)} // Compare with numbers
                  onChange={() => handleSkillChange(skill.skillId)}
                />
                <label className="form-check-label" htmlFor={`skill-${skill.skillId}`}>
                  {skill.skillName}
                </label>
              </div>
            ))}
          </div>
          {skillsError && <div className="text-danger mt-1">{skillsError}</div>}
        </div>


        <button type="submit" className="btn btn-success">Update</button>
      </form>
    </div>
  );
}

export default UpdateJob;
