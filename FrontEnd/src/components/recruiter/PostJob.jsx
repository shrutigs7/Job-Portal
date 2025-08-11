import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const API_BASE_URL = 'http://localhost:8081';
const jobTypeOptions = ['INTERN', 'TRAINEE', 'JUNIOR', 'ASSOCIATE', 'MID_LEVEL', 'SENIOR', 'LEAD', 'MANAGER', 'EXECUTIVE'];

function PostJob() {
  const navigate = useNavigate();
  const [skillsError, setSkillsError] = useState('');
  const [error, setError] = useState(null);
  const token = JSON.parse(localStorage.getItem('user'))?.token;

  const [formData, setFormData] = useState({
    title: '',
    companyName: '',
    location: '',
    yearOfExperience: '',
    type: '',
    description: '',
    jskills: [],
  });

  const [allSkills, setAllSkills] = useState([]);

  useEffect(() => {
    const headers = {
      Authorization: `Bearer ${token}`,
    };

    axios.get(`${API_BASE_URL}/skill/get`, { headers })
      .then(res => setAllSkills(res.data))
      .catch(err => setError(err.message));
  }, []);

  const handleChange = e => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSkillChange = skillId => {
    setFormData(prev => {
      const updatedSkills = prev.jskills.includes(skillId)
        ? prev.jskills.filter(id => id !== skillId)
        : [...prev.jskills, skillId];

      return {
        ...prev,
        jskills: updatedSkills,
      };
    });
  };

  const handleSubmit = e => {

    const headers = {
      Authorization: `Bearer ${token}`,
    };
    e.preventDefault();

    if (formData.jskills.length === 0) {
      setSkillsError('Please select at least one skill.');
      return;
    }
    setSkillsError('');

    const user = JSON.parse(localStorage.getItem("user"));
    const userId = user?.userId;

    const payload = {
      ...formData,
      recruiter: user
    };

    //hardcoding
    axios.post(`${API_BASE_URL}/recruiter/job/${userId}`, payload , { headers })
      .then(() => {
        alert("Job posted successfully!");
        navigate("/recruiter/all-jobs");
      })
      .catch(err => {
        console.error("Failed to post job:", err);
        setError(err.message);
      });
  };

  return (
    <div className="container mt-4">
      {error && (
        <div style={{ color: 'red', marginBottom: '1rem' }}>
          {error}
        </div>
      )}
      <h3>Post New Job</h3>
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
                  value={skill.skillId}
                  checked={formData.jskills.includes(skill.skillId)}
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

        <button type="submit" className="btn btn-primary">Post Job</button>
      </form>
    </div>
  );
}

export default PostJob;
