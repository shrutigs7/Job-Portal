import React from 'react';
import { NavLink } from 'react-router-dom';

function CandidateNavbar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
            <div className="container-fluid">
                <NavLink to="/candidate" className="navbar-brand">
                    Candidate Panel
                </NavLink>

                <div className="collapse navbar-collapse">
                    <ul className="navbar-nav w-100">
                        <li className="nav-item">
                            <NavLink to="/candidate/view-applications" className="nav-link custom-dark-link">
                                View Applications
                            </NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink to="/candidate/search-jobs" className="nav-link custom-dark-link">
                                Search Jobs
                            </NavLink>
                        </li>
                    </ul>

                    <ul className="navbar-nav ms-auto flex-row">
                        <li className="nav-item order-1 ms-2">
                            <NavLink to="/candidate/profile" className="nav-link custom-dark-link">
                                Profile
                            </NavLink>
                        </li>
                        <li className="nav-item order-2">
                            <NavLink to="/" className="nav-link custom-dark-link">
                                Logout
                            </NavLink>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
}

export default CandidateNavbar;
