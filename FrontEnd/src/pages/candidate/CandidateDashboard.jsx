// src/pages/candidate/CandidateDashboard.jsx

import React from 'react';
import CandidateNavbar from '../../components/candidate/CandidateNavbar';
import { Routes, Route, Outlet } from 'react-router-dom';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
function CandidateDashboard() {
  const navigate = useNavigate();
  useEffect(() => {
    const token = JSON.parse(localStorage.getItem('user'))?.token;
    if (!token) {
      navigate('/');  // Redirect to login
    }
  }, []);


  return (
    <div>
      <CandidateNavbar />
      <div className="container mt-3">
        <Outlet /> {/* This will render the matched child route */}
      </div>
    </div>
  )
}

export default CandidateDashboard;
