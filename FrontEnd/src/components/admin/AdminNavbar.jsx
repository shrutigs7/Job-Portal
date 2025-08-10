import React from 'react';
import { NavLink } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
function AdminNavbar() {


  const navigate = useNavigate();
  const handleLogout = () => {

    localStorage.removeItem('jwt');       // Remove JWT
    localStorage.removeItem('user');      // Optional: clear user info
    localStorage.clear();                 // Optional: clear all localStorage

    // Navigate to login page
    navigate('/');

    // Optional: reload to force state reset
    window.location.reload();
  };

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

            <li className="nav-item order-2">
              <button className="nav-link custom-dark-link" onClick={handleLogout}>
                Logout
              </button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default AdminNavbar;
