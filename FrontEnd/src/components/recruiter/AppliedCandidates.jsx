import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8081';

function AppliedCandidates() {
  const { jobId } = useParams();
  const navigate = useNavigate();
  const [error, setError] = useState(null);

  const token = JSON.parse(localStorage.getItem('user'))?.token;

  const [job, setJob] = useState(null);
  const [applicants, setApplicants] = useState([]);
  const [showApplicants, setShowApplicants] = useState(false);
  const [statusMap, setStatusMap] = useState({});
  const [statusCount, setStatusCount] = useState({
    APPLIED: 0,
    SHORTLISTED: 0,
    REJECTED: 0
  });

  useEffect(() => {
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    axios.get(`${API_BASE_URL}/recruiter/job/${jobId}`, { headers })
      .then(res => setJob(res.data))
      .catch(err => setError(err.message));
  }, [jobId]);

  const fetchStatus = async (userId) => {
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    try {
      const res = await axios.post(`${API_BASE_URL}/recruiter/job/application/status`, {
        userId,
        jobId
      }, { headers });
      return res.data;
    } catch (err) {
      console.error(`Error fetching status for user ${userId}:`, err);
      setError(err.message);
      return 'UNKNOWN';
    }
  };

  const fetchApplicants = async () => {

    const headers = {
      Authorization: `Bearer ${token}`,
    };
    try {
      const res = await axios.get(`${API_BASE_URL}/recruiter/job/application/${jobId}`, { headers });
      setApplicants(res.data);
      setShowApplicants(true);

      const newStatusMap = {};
      const newStatusCount = {
        APPLIED: 0,
        SHORTLISTED: 0,
        REJECTED: 0
      };

      await Promise.all(
        res.data.map(async (applicant) => {
          const status = await fetchStatus(applicant.userId);
          newStatusMap[applicant.userId] = status;
          if (newStatusCount[status] !== undefined) {
            newStatusCount[status]++;
          }
        })
      );

      setStatusMap(newStatusMap);
      setStatusCount(newStatusCount);
    } catch (err) {
      console.error("Error fetching applicants:", err);
      setError(err.message);
    }
  };

  const formatExperience = (years) => {
    const fullYears = Math.floor(years);
    const months = Math.round((years - fullYears) * 12);
    const yearStr = fullYears > 0 ? `${fullYears} yr${fullYears > 1 ? 's' : ''}` : '';
    const monthStr = months > 0 ? `${months} mo${months > 1 ? 's' : ''}` : '';
    return [yearStr, monthStr].filter(Boolean).join(' ');
  };

  const getBadgeClass = (status) => {
    switch (status) {
      case 'APPLIED':
        return 'text-secondary border border-secondary';
      case 'SHORTLISTED':
        return 'text-success border border-success';
      case 'REJECTED':
        return 'text-danger border border-danger';
      default:
        return 'text-muted border border-dark';
    }
  };

  return (
    <div className="container mt-4">
      {error && (
        <div style={{ color: 'red', marginBottom: '1rem' }}>
          {error}
        </div>
      )}
      <h3 className="mb-3">Applied Candidates</h3>

      {job && (
        <div className="card mb-4">
          <div className="card-body">
            <h5 className="card-title">{job.title}</h5>
            <p className="card-text">
              <strong>Company:</strong> {job.companyName}<br />
              <strong>Location:</strong> {job.location}<br />
              <strong>Experience:</strong> {job.yearOfExperience} years<br />
              <strong>Type:</strong> {job.type}<br />
              <strong>Description:</strong> {job.description}
            </p>
            <button className="btn btn-outline-primary" onClick={fetchApplicants}>
              Show Applicants
            </button>
          </div>
        </div>
      )}

      {showApplicants && (
        <div>
          <h5 className="mb-3">List of Applicants</h5>

          {/* Summary section */}
          <div className="mb-3">
            <span className="me-3">
              <span className="badge rounded-pill bg-secondary">{statusCount.APPLIED}</span> Applied
            </span>
            <span className="me-3">
              <span className="badge rounded-pill bg-success">{statusCount.SHORTLISTED}</span> Shortlisted
            </span>
            <span className="me-3">
              <span className="badge rounded-pill bg-danger">{statusCount.REJECTED}</span> Rejected
            </span>
          </div>

          {/* Applicant list */}
          {applicants.length === 0 ? (
            <p>No candidates have applied for this job yet.</p>
          ) : (
            <ul className="list-group">
              {applicants.map(applicant => {
                const totalExp = applicant.experienceList?.reduce(
                  (sum, exp) => sum + (exp.expInYears || 0), 0
                ) || 0;
                const status = statusMap[applicant.userId] || 'LOADING';

                return (
                  <li
                    key={applicant.userId}
                    className="list-group-item d-flex justify-content-between align-items-center"
                  >
                    <div>
                      <strong>{applicant.name}</strong> – {formatExperience(totalExp)}
                      <span className={`ms-3 px-2 py-1 rounded-pill small fw-bold text-uppercase ${getBadgeClass(status)}`}>
                        {status}
                      </span>
                    </div>
                    <button
                      className="btn btn-sm btn-outline-secondary"
                      onClick={() => {
                        localStorage.setItem('jobId', jobId);
                        navigate(`/recruiter/review-applicant/${applicant.userId}`);
                      }}
                    >
                      Details
                    </button>
                  </li>
                );
              })}
            </ul>
          )}
        </div>
      )}
    </div>
  );
}

export default AppliedCandidates;
