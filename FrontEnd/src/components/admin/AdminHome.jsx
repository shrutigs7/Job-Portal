import React, { useEffect, useState } from 'react';
import axios from 'axios';
import CountUp from 'react-countup';
import { BsBriefcaseFill, BsPeopleFill } from 'react-icons/bs';


function AdminHome() {
  const [jobCount, setJobCount] = useState(0);
  const [userCount, setUserCount] = useState(0);
  const [error, setError] = useState(null);

  const API_BASE_URL = 'http://localhost:8081';
  const token = JSON.parse(localStorage.getItem('user'))?.token;

  useEffect(() => {
    const headers = {
      Authorization: `Bearer ${token}`,
    };

    axios.get(`${API_BASE_URL}/admin/job-count`, { headers })
      .then(res => setJobCount(res.data || 0))
      .catch(err =>
        setError(err.message)
      );

    axios.get(`${API_BASE_URL}/admin/users-count`, { headers })
      .then(res => setUserCount(res.data || 0))
      .catch(err => setError(err.message));
  }, [token]);

  return (
    <div className="row justify-content-center">
      {error && (
        <div style={{ color: 'red', marginBottom: '1rem' }}>
          {error}
        </div>
      )}

      {/* Job Count Card */}
      <div className="col-md-4 mb-4">
        <div className="card shadow text-center p-3">
          <div className="card-body">
            <BsBriefcaseFill size={40} className="text-primary mb-2" />
            <h5 className="card-title">Active Jobs</h5>
            <h2 className="text-primary">
              <CountUp end={jobCount} duration={1.5} />
            </h2>
          </div>
        </div>
      </div>

      {/* User Count Card */}
      <div className="col-md-4 mb-4">
        <div className="card shadow text-center p-3">
          <div className="card-body">
            <BsPeopleFill size={40} className="text-success mb-2" />
            <h5 className="card-title">Total Users</h5>
            <h2 className="text-success">
              <CountUp end={userCount} duration={1.5} />
            </h2>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminHome;
