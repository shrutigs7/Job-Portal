import React from 'react';
import { Routes, Route, Outlet } from 'react-router-dom';
import AdminNavbar from '../../components/admin/AdminNavbar';
import AdminHome from '../../components/admin/AdminHome';
import AdminJobs from '../../components/admin/AdminJobs';
import AdminUsers from '../../components/admin/AdminUsers';

function AdminDashboard() {
  return (
    <div>
      <AdminNavbar />
      <div className="container mt-3">
        <Outlet /> {/* This will render the matched child route */}
      </div>
    </div>
  );
}

export default AdminDashboard;
