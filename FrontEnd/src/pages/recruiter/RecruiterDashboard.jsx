// src/pages/recruiter/RecruiterDashboard.jsx

import React from 'react';
import { Routes, Route, Outlet } from 'react-router-dom';
import RecruiterNavbar from '../../components/recruiter/RecruiterNavbar';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function RecruiterDashboard() {

const navigate = useNavigate();
  useEffect(() => {
        const token = JSON.parse(localStorage.getItem('user'))?.token;
        if (!token) {
            navigate('/');  // Redirect to login
        }
    }, []);

  return (
    <div>
      <RecruiterNavbar />
      <div className="container mt-3">
        <Outlet /> {/* This will render the matched child route */}
      </div>
    </div>
  );
}

export default RecruiterDashboard;
