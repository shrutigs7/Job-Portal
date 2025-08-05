import React from 'react';

function JobListCard({ job, onSelect }) {
  return (
    <div className="card mb-3 shadow-sm">
      <div className="card-body d-flex justify-content-between align-items-center">
        <div>
          <h5 className="mb-1">{job.title}</h5>
          <small className="text-muted">{job.companyName}</small>
        </div>
        <button className="btn btn-outline-primary btn-sm" onClick={onSelect}>
          Details
        </button>
      </div>
    </div>
  );
}

export default JobListCard;
