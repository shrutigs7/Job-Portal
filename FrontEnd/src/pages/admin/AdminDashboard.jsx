import React from 'react';
import { Routes, Route, Outlet } from 'react-router-dom';
import AdminNavbar from '../../components/admin/AdminNavbar';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function AdminDashboard() {
  const navigate = useNavigate();
  useEffect(() => {
        const token = JSON.parse(localStorage.getItem('user'))?.token;
        if (!token) {
            navigate('/');  // Redirect to login
        }
    }, []);

  return (
    <div>
      <AdminNavbar />
      <div className="container mt-3">
        {/* <AdminHome></AdminHome> */}
        <Outlet /> {/* This will render the matched child route */}
      </div>
    </div>
  );
}

export default AdminDashboard;
