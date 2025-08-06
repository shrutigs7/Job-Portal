// src/components/candidate/CandidateSkills.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const API_BASE_URL = 'http://localhost:8081';

function CandidateSkills() {
  const user = JSON.parse(localStorage.getItem("user"));
  const userId = user?.userId;

  const [skills, setSkills] = useState([]);
  const [selectedSkills, setSelectedSkills] = useState(new Set());

  const navigate = useNavigate();

  // Fetch skills from backend
  useEffect(() => {
    axios.get(`${API_BASE_URL}/skill/get`)
      .then(res => setSkills(res.data))
      .catch(err => {
        console.error("Error fetching skills:", err);
        alert("Failed to load skills");
      });
  }, []);

  const toggleSkill = (skillId) => {
    setSelectedSkills(prev => {
      const updated = new Set(prev);
      if (updated.has(skillId)) {
        updated.delete(skillId);
      } else {
        updated.add(skillId);
      }
      return updated;
    });
  };

  const handleFinish = () => {
  if (selectedSkills.size === 0) {
    alert("Please select at least one skill");
    return;
  }

  const payload = {
    skillIds: Array.from(selectedSkills)
  };

  axios.post(`${API_BASE_URL}/candidate/skills/${userId}`, payload)
    .then(() => {
      alert("Registration complete! Please log in.");
      localStorage.removeItem("user"); // optional
      navigate("/login");
    })
    .catch(err => {
      console.error("Error submitting skills:", err);
      alert("Failed to submit skills");
    });
};


  return (
    <div className="container mt-4">
      <h3>Candidate Registration - Step 4: Skills</h3>

      <div className="d-flex flex-wrap gap-2 my-4">
        {skills.map(skill => (
          <button
            key={skill.skillId}
            type="button"
            className={`btn ${selectedSkills.has(skill.skillId) ? 'btn-primary' : 'btn-outline-primary'}`}
            onClick={() => toggleSkill(skill.skillId)}
          >
            {skill.skillName}
          </button>
        ))}
      </div>

      <div className="d-flex justify-content-between mt-4">
        <button className="btn btn-secondary" onClick={() => navigate('/candidate/register/experience')}>Back</button>
        <button className="btn btn-success" onClick={handleFinish}>Finish</button>
      </div>
    </div>
  );
}

export default CandidateSkills;
