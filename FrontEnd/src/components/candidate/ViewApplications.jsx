import React, { useEffect, useState } from 'react';
import axios from 'axios';


const ViewApplications = () => {
  const [jobs, setJobs] = useState([]);
  const [statuses, setStatuses] = useState({});
  const user = JSON.parse(localStorage.getItem('user'));
  const [error, setError] = useState(null);
  const token = JSON.parse(localStorage.getItem('user'))?.token;

  useEffect(() => {
    if (!user || !user.userId) return;

    const headers = {
      Authorization: `Bearer ${token}`,
    };
    // 1. Fetch jobs applied by candidate
    axios.get(`http://localhost:8081/candidate/job/${user.userId}`, { headers })
      .then((res) => {
        const jobList = res.data;
        setJobs(jobList);
        console.log(jobList);
      })
      .catch(err => setError(err.message));
  }, [user?.userId]);

  // ✅ Step 2: Fetch status for each job after jobs are loaded
  useEffect(() => {
    if (!user || !user.userId || jobs.length === 0) return;

    const headers = {
      Authorization: `Bearer ${token}`,
    };

    jobs.forEach((job) => {
      if (!job.jobId) return;

      console.log(user.userId, job.jobId)
      axios.post('http://localhost:8081/candidate/job/status', {
        userId: user.userId,
        jobId: job.jobId
      }, { headers })
        .then((response) => {
          setStatuses((prev) => ({
            ...prev,
            [job.jobId]: response.data,
          }));
        })
        .catch((err) => setError(err.message));
    });
  }, [jobs]);

  return (
    <div className="container mt-4">
      {error && (
        <div style={{ color: 'red', marginBottom: '1rem' }}>
          {error}
        </div>
      )}
      <h3>My Applications</h3>
      <div className="row">
        {jobs.map((job, idx) => (
          <div className="col-md-6 mb-4" key={idx}>
            <div className="card shadow position-relative">
              {/* Status at top-right corner */}
              <span className="badge bg-info position-absolute top-0 end-0 m-2">
                {statuses[job.jobId] || 'Loading...'}
              </span>

              <div className="card-body">
                <h5 className="card-title">{job.title}</h5>
                <h6 className="card-subtitle mb-2 text-muted">{job.companyName}</h6>
                <p className="card-text">{job.description}</p>

                <ul className="list-group list-group-flush mt-2">
                  <li className="list-group-item"><strong>Location:</strong> {job.location}</li>
                  <li className="list-group-item"><strong>Experience:</strong> {job.yearOfExperience} yrs</li>
                  <li className="list-group-item"><strong>Type:</strong> {job.type}</li>
                  <li className="list-group-item"><strong>Skills:</strong> {Array.from(job.jskills || []).map(skill => skill.skillName).join(', ')}</li>
                </ul>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ViewApplications;
