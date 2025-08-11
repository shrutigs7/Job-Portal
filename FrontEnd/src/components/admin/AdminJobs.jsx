import React, { useEffect, useState } from 'react';
import axios from 'axios';
import JobListCard from './JobListCard';
import JobDetailsCard from './JobDetailsCard';

function AdminJobs() {
  const [jobs, setJobs] = useState([]);
  const [selectedJob, setSelectedJob] = useState(null);
  const token = JSON.parse(localStorage.getItem('user'))?.token;
  const [error, setError] = useState(null);
  useEffect(() => {

    const headers = {
      Authorization: `Bearer ${token}`,
    };

    axios.get('http://localhost:8081/admin/jobs', { headers })
      .then(res => {
        setJobs(res.data);
        setSelectedJob(res.data[0]); // default selection
      })
      .catch(err => setError(err.message));
  }, []);

  return (
    <div className="row">
      {error && (
        <div style={{ color: 'red', marginBottom: '1rem' }}>
          {error}
        </div>
      )}
      {/* Left: Job List */}
      <div className="col-md-7">
        {jobs.map(job => (
          <JobListCard
            key={job.id}
            job={job}
            onSelect={() => setSelectedJob(job)}
          />
        ))}
      </div>

      {/* Right: Selected Job Details */}
      <div className="col-md-5">
        {selectedJob && <JobDetailsCard job={selectedJob} />}
      </div>
    </div>
  );
}

export default AdminJobs;
