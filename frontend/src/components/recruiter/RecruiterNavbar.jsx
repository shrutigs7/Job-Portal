import React from 'react';
import { NavLink } from 'react-router-dom';

function RecruiterNavbar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
            <div className="container-fluid">
                <NavLink to="/recruiter" className="navbar-brand">
                    Recruiter Panel
                </NavLink>


                <div className="collapse navbar-collapse">
                    <ul className="navbar-nav w-100">
                        <li className="nav-item">
                            <NavLink to="/recruiter/post-job" className="nav-link custom-dark-link">Post a Job</NavLink>
                        </li>
                        {/* <li className="nav-item">
              <NavLink to="/recruiter/profile" className="nav-link custom-dark-link">View Profile</NavLink>
            </li> */}
                        <li className="nav-item">
                            <NavLink to="/recruiter/all-jobs" className="nav-link custom-dark-link">Jobs</NavLink>
                        </li>
                    </ul>
                    <ul className="navbar-nav ms-auto flex-row">
                        <li className="nav-item order-1 ms-2">
                            <NavLink to="/recruiter/profile" className="nav-link custom-dark-link">Profile</NavLink>
                        </li>
                        <li className="nav-item order-2">
                            <NavLink to="/" className="nav-link custom-dark-link">Logout</NavLink>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
}

export default RecruiterNavbar;
