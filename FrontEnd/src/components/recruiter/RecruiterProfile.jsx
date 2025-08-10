import React, { useEffect, useState } from 'react';
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8081';

function RecruiterProfile() {
  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);

  const user = JSON.parse(localStorage.getItem('user'));
  const userId = user?.userId;
  const [error, setError] = useState(null);

  const token = JSON.parse(localStorage.getItem('user'))?.token;

  useEffect(() => {

    const headers = {
      Authorization: `Bearer ${token}`,
    };
    axios.get(`${API_BASE_URL}/recruiter/profile/${user.userId}`, { headers })
      .then(res => {
        setProfile(res.data)
        console.log(res.data)
      })
      .catch(err => setError(err.message))
      .finally(() => setLoading(false));
  }, [userId]);

  if (loading) return <div className="p-4">Loading profile...</div>;
  if (!profile) return <div className="p-4 text-danger">Profile not found.</div>;

  const { name, mobNumber, title, company } = profile;

  return (
    <div className="container mt-4">
      {error && (
        <div style={{ color: 'red', marginBottom: '1rem' }}>
          {error}
        </div>
      )}
      <div className="card shadow rounded-4 p-4">
        <h2 className="mb-3">Recruiter Profile</h2>
        <hr />
        <div className="mb-3">
          <h5 className="fw-bold mb-1">{name}</h5>
          <p className="mb-1"><strong>Title:</strong> {title}</p>
          <p><strong>Mobile:</strong> {mobNumber}</p>
        </div>

        <div className="bg-light rounded-4 p-3">
          <h4 className="mb-3">Company Details</h4>
          <p><strong>Name:</strong> {company.companyName}</p>
          <p><strong>Location:</strong> {company.location}</p>
          <p><strong>Industry:</strong> {company.industry}</p>
          <p><strong>Size:</strong> {company.size}</p>
          <p><strong>Founded:</strong> {company.foundedYear}</p>
          <p><strong>Website:</strong> <a href={company.website} target="_blank" rel="noreferrer">{company.website}</a></p>
          <p><strong>Description:</strong> {company.description}</p>
        </div>
      </div>
    </div>
  );
}

export default RecruiterProfile;
