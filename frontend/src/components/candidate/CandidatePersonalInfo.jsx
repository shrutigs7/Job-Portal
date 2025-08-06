// src/components/candidate/CandidatePersonalInfo.jsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8081';

function CandidatePersonalInfo() {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user"));
  const userId = user?.userId;

  const [formData, setFormData] = useState({
    name: '',
    dateOfBirth: '',
    mobileNo: '',
    linkedIn: '',
    gitHub: ''
  });

  const [errors, setErrors] = useState({});

  const handleChange = e => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const validate = () => {
    const newErrors = {};
    if (!formData.name.trim()) newErrors.name = "Name is required";
    if (!formData.dateOfBirth) newErrors.dateOfBirth = "Date of birth is required";
    if (!formData.mobileNo.trim()) newErrors.mobileNo = "Mobile number is required";
    if (!formData.linkedIn.trim()) newErrors.linkedIn = "LinkedIn profile is required";
    return newErrors;
  };

  const handleSubmit = e => {
    e.preventDefault();
    const validationErrors = validate();
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }

    axios.post(`${API_BASE_URL}/candidate/${userId}`, formData)
      .then(() => {
        alert("Personal information saved successfully!");
        navigate(`/candidate/register/education`);
      })
      .catch(err => {
        console.error("Error saving personal information:", err);
        alert("Failed to save personal information");
      });
  };

  return (
    <div className="container mt-4">
      <h3>Candidate Registration</h3>
      <form onSubmit={handleSubmit}>

        <div className="mb-3">
          <label className="form-label">Full Name *</label>
          <input type="text" name="name" className="form-control" value={formData.name} onChange={handleChange} />
          {errors.name && <div className="text-danger">{errors.name}</div>}
        </div>

        <div className="mb-3">
          <label className="form-label">Date of Birth *</label>
          <input type="date" name="dateOfBirth" className="form-control" value={formData.dateOfBirth} onChange={handleChange} />
          {errors.dateOfBirth && <div className="text-danger">{errors.dateOfBirth}</div>}
        </div>

        <div className="mb-3">
          <label className="form-label">Mobile Number *</label>
          <input type="text" name="mobileNo" className="form-control" value={formData.mobileNo} onChange={handleChange} />
          {errors.mobileNo && <div className="text-danger">{errors.mobileNo}</div>}
        </div>

        <div className="mb-3">
          <label className="form-label">LinkedIn Profile *</label>
          <input type="text" name="linkedIn" className="form-control" value={formData.linkedIn} onChange={handleChange} />
          {errors.linkedIn && <div className="text-danger">{errors.linkedIn}</div>}
        </div>

        <div className="mb-3">
          <label className="form-label">GitHub (optional)</label>
          <input type="text" name="gitHub" className="form-control" value={formData.gitHub} onChange={handleChange} />
        </div>

        <button type="submit" className="btn btn-primary">Next</button>
      </form>
    </div>
  );
}

export default CandidatePersonalInfo;
