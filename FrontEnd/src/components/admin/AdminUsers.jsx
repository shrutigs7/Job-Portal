import React, { useState, useEffect } from 'react';
import axios from 'axios';

function AdminUsers() {
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState(null);
  const API_BASE_URL = 'http://localhost:8081';
  const token = JSON.parse(localStorage.getItem('user'))?.token;
  const [error, setError] = useState(null);

  useEffect(() => {
    const headers = {
      Authorization: `Bearer ${token}`,
    };

    axios.get(`${API_BASE_URL}/admin/users`, { headers })
      .then(async (res) => {
        const usersBasic = res.data; // array with userId and role

        // Fetch details for each user in parallel
        const usersWithNames = await Promise.all(
          usersBasic.map(async (user) => {
            try {
              const detailRes = await axios.get(`${API_BASE_URL}/admin/user-details/${user.userId}`, { headers });
              return {
                ...user,
                name: detailRes.data.name || 'N/A', // fallback if name missing
                profile: detailRes.data,
              };
            } catch (err) {
              console.error(`Error fetching details for user ${user.userId}`, err);
              setError(err.message);
              return { ...user, name: 'Unknown', profile: null };
            }

          })
        );
        console.log(users);

        setUsers(usersWithNames);
        if (usersWithNames.length > 0) setSelectedUser(usersWithNames[0]);
      })
      .catch(err => console.error('Error fetching users', err));
  }, []);

  const handleSelectUser = (user) => {
    setSelectedUser(user);
  };

  return (
    <div className="container mt-4">
      {error && (
        <div style={{ color: 'red', marginBottom: '1rem' }}>
          {error}
        </div>
      )}
      <div className="row">
        {/* User list */}
        <div className="col-md-5">
          <h5 className="mb-3">All Users</h5>
          {users.map(user => (
            <div
              key={user.userId}
              className="card mb-3 shadow-sm"
            >
              <div className="card-body d-flex justify-content-between align-items-center">
                <div>
                  <h6 className="mb-1">{user.name || 'Unknown User'}</h6>
                  <small className="text-muted text-capitalize">{user.role}</small>
                </div>
                <button
                  className="btn btn-outline-primary btn-sm"
                  onClick={() => handleSelectUser(user)}
                >
                  Details
                </button>
              </div>
            </div>
          ))}
        </div>

        {/* User Details */}
        <div className="col-md-7">
          {selectedUser ? (
            <div className="card shadow-sm">
              <div className="card-body">
                <h5 className="card-title">{selectedUser.name || 'N/A'}</h5>
                <p className="card-text">
                  <strong>Role:</strong> {selectedUser.role} <br />
                  <strong>Email:</strong> {selectedUser.profile?.email || 'N/A'} <br />
                  {selectedUser.role === 'candidate' && (
                    <>
                      <strong>Skills:</strong> {selectedUser.profile?.skills?.map(skill => skill.name).join(', ') || 'N/A'}
                    </>
                  )}
                  {selectedUser.role === 'recruiter' && (
                    <>
                      <strong>Company:</strong> {selectedUser.profile?.companyName || 'N/A'}
                    </>
                  )}
                </p>
              </div>
            </div>
          ) : (
            <div className="text-muted">Select a user to view details</div>
          )}
        </div>
      </div>
    </div>
  );
}

export default AdminUsers;
