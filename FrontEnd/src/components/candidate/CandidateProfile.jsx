import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { getUserFromLocalStorage } from '../../layouts/localStorageUtils';
import { Outlet } from 'react-router-dom';

function CandidateProfile() {
    const [candidate, setCandidate] = useState(null);
    const navigate = useNavigate();
    const userId = JSON.parse(localStorage.getItem('user'))?.userId;
    const [error, setError] = useState(null);
    const token = JSON.parse(localStorage.getItem('user'))?.token;


    useEffect(() => {
        const headers = {
            Authorization: `Bearer ${token}`,
        };
        axios.get(`http://localhost:8081/candidate/${userId}`, { headers })
            .then(res => setCandidate(res.data))
            .catch(err => setError(err.message));
    }, [userId]);

    if (!candidate) return <p>Loading...</p>;

    const {
        name,
        email,
        dateOfBirth,
        mobileNo,
        linkedIn,
        gitHub,
        educationList,
        experienceList,
        cskills
    } = candidate;


    function formatExperience(expInYears) {
        const years = Math.floor(expInYears);
        const months = Math.round((expInYears - years) * 12);
        return `${years} year${years !== 1 ? 's' : ''} ${months} month${months !== 1 ? 's' : ''}`;
    }


    return (
        <div className="container mt-4">
            {error && (
                <div style={{ color: 'red', marginBottom: '1rem' }}>
                    {error}
                </div>
            )}
            {/* Section 1: Personal Information */}
            <div className="card mb-4">
                <div className="card-header d-flex justify-content-between align-items-center">
                    <h4 className="mb-0">Personal Information</h4>
                    <button className="btn btn-primary btn-sm" onClick={() => navigate("/candidate/update/personal")}>
                        Update
                    </button>
                </div>
                <div className="card-body">
                    <h5><strong>{name}</strong></h5>
                    <p><strong>Email:</strong> {email}</p>
                    <p><strong>Date of Birth:</strong> {dateOfBirth}</p>
                    <p><strong>Mobile No:</strong> {mobileNo}</p>
                    <p><strong>LinkedIn:</strong> {linkedIn}</p>
                    <p><strong>GitHub:</strong> {gitHub || 'N/A'}</p>
                    <p><strong>Skills:</strong> {Array.from(cskills).map(skill => skill.skillName).join(', ')}</p>
                </div>
            </div>

            {/* Section 2: Education */}
            <div className="card mb-4">
                <div className="card-header d-flex justify-content-between align-items-center">
                    <h4 className="mb-0">Education</h4>
                    <button
                        className="btn btn-primary btn-sm"
                        onClick={() => navigate("/candidate/update/education")}
                    >
                        Update
                    </button>
                </div>
                <div className="card-body table-responsive">
                    {educationList.length > 0 ? (
                        <table className="table table-striped">
                            <thead>
                                <tr>
                                    <th>Degree</th>
                                    <th>Grade</th>
                                    <th>Start Year</th>
                                    <th>End Year</th>
                                    <th>University</th>
                                </tr>
                            </thead>
                            <tbody>
                                {educationList.map((edu, index) => (
                                    <tr key={index}>
                                        <td>{edu.degree}</td>
                                        <td>{edu.grade}</td>
                                        <td>{edu.startYear}</td>
                                        <td>{edu.endYear}</td>
                                        <td>{edu.university}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    ) : <p>No education records found.</p>}
                </div>
            </div>

            {/* Section 3: Experience */}
            <div className="card mb-4">
                <div className="card-header d-flex justify-content-between align-items-center">
                    <h4 className="mb-0">Experience</h4>
                    <button
                        className="btn btn-primary btn-sm"
                        onClick={() => navigate("/candidate/update/experience")}
                    >
                        Update
                    </button>
                </div>
                <div className="card-body table-responsive">
                    {experienceList.length > 0 ? (
                        <table className="table table-striped">
                            <thead>
                                <tr>
                                    <th>Role</th>
                                    <th>Company Name</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Experience</th>
                                    <th>Description</th>
                                </tr>
                            </thead>
                            <tbody>
                                {experienceList.map((exp, index) => (
                                    <tr key={index}>
                                        <td>{exp.role}</td>
                                        <td>{exp.companyName}</td>
                                        <td>{exp.startDate}</td>
                                        <td>{exp.endDate || 'Present'}</td>
                                        <td>{formatExperience(exp.expInYears)}</td>
                                        <td>{exp.description}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    ) : <p>No experience records found.</p>}
                </div>
            </div>


            <Outlet />
        </div>
    );
}

export default CandidateProfile;
