// src/components/recruiter/RecruiterJobsList.jsx
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function AllPostedJobs() {
  const [jobs, setJobs] = useState([]);
  const navigate = useNavigate();
  const API_BASE_URL = 'http://localhost:8081';

  const user = JSON.parse(localStorage.getItem("user"));
  const userId = user?.userId;
  const [error, setError] = useState(null);
  const token = JSON.parse(localStorage.getItem('user'))?.token;


  useEffect(() => {
    const headers = {
      Authorization: `Bearer ${token}`,
    };

    axios.get(`${API_BASE_URL}/recruiter/jobs/${userId}`, { headers })
      .then(response => setJobs(response.data))
      .catch(err => setError(err.message));
  }, []);

  const handleDelete = (jobId) => {
    const headers = {
      Authorization: `Bearer ${token}`,
    };

    if (window.confirm("Are you sure you want to delete this job?")) {
      axios.delete(`${API_BASE_URL}/recruiter/job/${jobId}`, { headers })
        .then(() => setJobs(prev => prev.filter(job => job.jobId !== jobId)))
        .catch(err => setError(err.message));
    }
  };

  const goToPostJob = () => navigate('/recruiter/post-job');

  return (
    <div className="container mt-4">
      {error && (
        <div style={{ color: 'red', marginBottom: '1rem' }}>
          {error}
        </div>
      )}
      <h3>Your Posted Jobs</h3>

      {jobs.length === 0 ? (
        <div className="text-center mt-5">
          <p>No jobs posted yet.</p>
          <button className="btn btn-primary" onClick={goToPostJob}>
            Post a Job
          </button>
        </div>
      ) : (
        <div className="container mt-4">
          {jobs.map(job => (
            <div
              key={job.jobId}
              className="card mb-3 shadow-sm"
              style={{ cursor: 'pointer' }}
              onClick={() => navigate(`/recruiter/all-jobs/${job.jobId}/candidates`)}
            >
              <div className="card-body d-flex justify-content-between align-items-center">
                <div>
                  <h5 className="card-title mb-1">{job.title}</h5>
                  <small className="text-muted">Posted on: {job.postedDate}</small>
                </div>

                <div className="btn-group" onClick={(e) => e.stopPropagation()}>
                  <button className="btn btn-sm btn-outline-primary" onClick={() => navigate(`/recruiter/all-jobs/${job.jobId}/update`)}>Update</button>
                  <button className="btn btn-sm btn-outline-danger" onClick={() => handleDelete(job.jobId)}>Delete</button>
                  <button className="btn btn-sm btn-outline-success" onClick={() => navigate(`/recruiter/all-jobs/${job.jobId}/candidates`)}>Applications</button>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div> // ✅ this line was missing
  );
}

export default AllPostedJobs;
