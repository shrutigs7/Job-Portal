import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { getUserFromLocalStorage } from '../../layouts/localStorageUtils'; // helper to fetch local user

const ViewApplications = () => {
  const [jobs, setJobs] = useState([]);
  const [statuses, setStatuses] = useState({});
  const user = getUserFromLocalStorage();

  useEffect(() => {
    if (!user || !user.userId) return;

    // 1. Fetch jobs applied by candidate
    axios.get(`http://localhost:8081/candidate/job/${user.userId}`)
      .then((res) => {
        const jobList = res.data;
        setJobs(jobList);

        // 2. Fetch status for each job
        jobList.forEach(job => {
          axios.post('http://localhost:8081/candidate//job/application/status', {
            userId: user.userId,
            jobId: job.jobId
          })
            .then(response => {
              setStatuses(prev => ({ ...prev, [job.jobId]: response.data.status }));
            })
            .catch(err => console.error('Error fetching status', err));
        });
      })
      .catch(err => console.error('Error fetching jobs', err));
  }, [user]);

  return (
    <div className="container mt-4">
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
                  <li className="list-group-item"><strong>Skills:</strong> {Array.from(job.jskills || []).map(skill => skill.name).join(', ')}</li>
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
