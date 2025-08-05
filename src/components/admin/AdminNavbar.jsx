import React from 'react';
import { NavLink } from 'react-router-dom';

function AdminNavbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <div className="container-fluid">
        <span className="navbar-brand">Admin Panel</span>

        <div className="collapse navbar-collapse">
          <ul className="navbar-nav w-100">
            <li className="nav-item">
              <NavLink to="/admin/home" className="nav-link custom-dark-link">Home</NavLink>
            </li>
            <li className="nav-item">
              <NavLink to="/admin/jobs" className="nav-link custom-dark-link">Jobs</NavLink>
            </li>
            <li className="nav-item">
              <NavLink to="/admin/users" className="nav-link custom-dark-link">Users</NavLink>
            </li>

            <li className="nav-item ms-auto">
              <NavLink to="/" className="nav-link custom-dark-link">Logout</NavLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default AdminNavbar;
