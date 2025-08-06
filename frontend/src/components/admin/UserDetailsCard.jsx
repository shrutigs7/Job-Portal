import React from 'react';

function UserDetailsCard({ user }) {
  return (
    <div className="card shadow">
      <div className="card-body">
        <h4>{user.name}</h4>
        <p><strong>Email:</strong> {user.email}</p>
        <p><strong>Role:</strong> {user.role}</p>

        {user.role === 'CANDIDATE' && (
          <>
            <p><strong>Education:</strong> {user.education}</p>
            <p><strong>Experience:</strong> {user.experience}</p>
            <p><strong>Skills:</strong> {user.skills?.map(s => s.name).join(', ')}</p>
          </>
        )}

        {user.role === 'RECRUITER' && (
          <>
            <p><strong>Company:</strong> {user.company}</p>
            <p><strong>Position:</strong> {user.position}</p>
            <p><strong>About:</strong> {user.about}</p>
          </>
        )}
      </div>
    </div>
  );
}

export default UserDetailsCard;
