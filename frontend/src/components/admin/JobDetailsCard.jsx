import React from 'react';

function JobDetailsCard({ job }) {
  return (
    <div className="card shadow-sm">
      <div className="card-body">
        <h4>{job.title}</h4>
        <p><strong>Company:</strong> {job.companyName}</p>
        <p><strong>Experience:</strong> {job.yearOfExperience}</p>
        <p><strong>Location:</strong> {job.location}</p>
        <p><strong>Type:</strong> {job.type}</p>
        <p><strong>Skills:</strong> {job.jskills?.map(skill => skill.skillName).join(', ')}</p>
        <p><strong>Description:</strong> {job.description}</p>
      </div>
    </div>
  );
}

export default JobDetailsCard;
