// src/pages/recruiter/RecruiterDashboard.jsx

import React from 'react';
import { Routes, Route, Outlet } from 'react-router-dom';
import RecruiterNavbar from '../../components/recruiter/RecruiterNavbar';

function RecruiterDashboard() {
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
