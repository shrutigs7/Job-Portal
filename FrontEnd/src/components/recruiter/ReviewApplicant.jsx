import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8081';

function ReviewApplicant() {
    const { userId } = useParams();
    const navigate = useNavigate();
    const [candidate, setCandidate] = useState(null);
    const [status, setStatus] = useState('');
    const [loading, setLoading] = useState(true);

    const jobId = localStorage.getItem('jobId');
    const [error, setError] = useState(null);
    const token = JSON.parse(localStorage.getItem('user'))?.token;

    useEffect(() => {
        const headers = {
            Authorization: `Bearer ${token}`,
        };
        axios.get(`${API_BASE_URL}/recruiter/candidate/${userId}`, { headers })
            .then(res => setCandidate(res.data))
            .catch(err => setError(err.message));

        axios.post(`${API_BASE_URL}/recruiter/job/application/status`, {
            userId,
            jobId
        }, { headers }).then(res => {
            setStatus(res.data);
        })
            .catch(err => setError(err.message))
            .finally(() => setLoading(false));
    }, [userId, jobId]);

    const updateStatus = (newStatus) => {

        const headers = {
            Authorization: `Bearer ${token}`,
        };

        axios.put(`${API_BASE_URL}/recruiter/job/application/status`, {
            userId,
            jobId,
            status: newStatus
        }, { headers }).then(() => setStatus(newStatus))
            .catch(err => setError(err.message));
    };

    if (loading) return <div className="p-4">Loading applicant details...</div>;
    if (!candidate) return <div className="p-4 text-danger">Applicant not found.</div>;

    return (
        <div className="container mt-4">
            {error && (
                <div style={{ color: 'red', marginBottom: '1rem' }}>
                    {error}
                </div>
            )}
            <button
                className="btn btn-outline-secondary mb-3"
                onClick={() => navigate(`/recruiter/all-jobs/${jobId}/candidates`)}
            >
                ← Back to Applicants
            </button>

            <div className="card position-relative">
                <div className="card-body">
                    <div className="d-flex justify-content-between">
                        <h3>{candidate.name}</h3>
                        <span className={`px-3 py-1 border rounded-pill text-uppercase fw-bold 
                            ${status === 'APPLIED' ? 'text-secondary border-secondary' :
                                status === 'SHORTLISTED' ? 'text-success border-success' :
                                    'text-danger border-danger'}`}>
                            {status}
                        </span>
                    </div>

                    <p><strong>Email:</strong> {candidate.email}</p>
                    <p><strong>Mobile:</strong> {candidate.mobileNo}</p>
                    <p><strong>GitHub:</strong> {candidate.gitHub}</p>
                    <p><strong>LinkedIn:</strong> {candidate.linkedIn}</p>

                    <h5 className="mt-4">Education</h5>
                    <table className="table table-bordered">
                        <thead>
                            <tr>
                                <th>Degree</th>
                                <th>University</th>
                                <th>Grade</th>
                                <th>Start Year</th>
                                <th>End Year</th>
                            </tr>
                        </thead>
                        <tbody>
                            {candidate.educationList?.map((edu, index) => (
                                <tr key={index}>
                                    <td>{edu.degree}</td>
                                    <td>{edu.university}</td>
                                    <td>{edu.grade}</td>
                                    <td>{edu.startYear}</td>
                                    <td>{edu.endYear}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    <h5 className="mt-4">Experience</h5>
                    <table className="table table-bordered">
                        <thead>
                            <tr>
                                <th>Role</th>
                                <th>Company</th>
                                <th>Start Date</th>
                                <th>End Date</th>
                                <th>Years</th>
                            </tr>
                        </thead>
                        <tbody>
                            {candidate.experienceList?.map((exp, index) => (
                                <tr key={index}>
                                    <td>{exp.role}</td>
                                    <td>{exp.companyName}</td>
                                    <td>{exp.startDate}</td>
                                    <td>{exp.endDate}</td>
                                    <td>{exp.expInYears.toFixed(1)}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    <h5 className="mt-4">Skills</h5>
                    <p>{[...candidate.cskills].map(skill => skill.skillName).join(', ')}</p>

                    {status === 'APPLIED' && (
                        <div className="d-flex justify-content-end mt-3 gap-3">
                            <button className="btn btn-danger" onClick={() => updateStatus('REJECTED')}>Reject</button>
                            <button className="btn btn-success" onClick={() => updateStatus('SHORTLISTED')}>Shortlist</button>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}

export default ReviewApplicant;
