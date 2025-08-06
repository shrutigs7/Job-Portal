import React, { useEffect, useState } from 'react';
import axios from 'axios';
import JobListCard from './JobListCard';
import JobDetailsCard from './JobDetailsCard';

function AdminJobs() {
  const [jobs, setJobs] = useState([]);
  const [selectedJob, setSelectedJob] = useState(null);

  useEffect(() => {
    axios.get('http://localhost:8081/admin/jobs')
      .then(res => {
        setJobs(res.data);
        setSelectedJob(res.data[0]); // default selection
      })
      .catch(err => console.error('Error fetching jobs:', err));
  }, []);

  return (
    <div className="row">
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
