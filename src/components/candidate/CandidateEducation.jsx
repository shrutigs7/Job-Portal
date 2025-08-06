import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const API_BASE_URL = 'http://localhost:8081'; // Replace with your actual base URL

function CandidateEducation() {
    const user = JSON.parse(localStorage.getItem('user'));
    const userId = user?.userId;

    const navigate = useNavigate();
    const [educations, setEducations] = useState([]);
    const [formData, setFormData] = useState({
        educationId: null,
        degree: '',
        grade: '',
        startYear: '',
        endYear: '',
        university: ''
    });
    const [editIndex, setEditIndex] = useState(null);
    const [errors, setErrors] = useState({});

    const degreeOptions = [
         'BE', 'BTECH', 'ME', 'MTECH', 'BCS', 'BSC', 'MCS', 'MSC', 'DIPLOMA', 'TENTH', 'TWELTH'
    ];

    useEffect(() => {
        fetchEducations();
    }, []);

    const normalizeEducationData = (data) => {
        return data.map(edu => ({
            ...edu,
            educationId: edu.eduId // map backend ID
        }));
    };

    const fetchEducations = () => {
        axios.get(`${API_BASE_URL}/candidate/${userId}`)
            .then(res => {
                const normalized = normalizeEducationData(res.data.educationList || []);
                setEducations(normalized);
            })
            .catch(err => console.error("Failed to load education list", err));
    };

    const validate = () => {
        const errs = {};
        if (!formData.degree) errs.degree = 'Degree is required';
        if (!formData.grade) errs.grade = 'Grade is required';
        if (!formData.startYear) errs.startYear = 'Start year is required';
        if (!formData.endYear) errs.endYear = 'End year is required';
        if (!formData.university) errs.university = 'University is required';
        return errs;
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const validationErrors = validate();
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }

        if (editIndex !== null) {
            axios.put(`${API_BASE_URL}/candidate/education/${userId}/${formData.educationId}`, formData)
                .then(() => {
                    fetchEducations();
                    resetForm();
                })
                .catch(err => {
                    console.error("Update failed", err);
                    alert("Error updating education");
                });
            return;
        }

        axios.post(`${API_BASE_URL}/candidate/education/${userId}`, formData)
            .then(() => {
                fetchEducations();
                resetForm();
            })
            .catch(err => {
                console.error("Failed to add education:", err);
                alert("Error adding education");
            });
    };

    const resetForm = () => {
        setFormData({
            educationId: null,
            degree: '',
            grade: '',
            startYear: '',
            endYear: '',
            university: ''
        });
        setErrors({});
        setEditIndex(null);
    };

    const handleEdit = (edu, index) => {
        setFormData({
            educationId: edu.educationId,
            degree: edu.degree,
            grade: edu.grade,
            startYear: edu.startYear,
            endYear: edu.endYear,
            university: edu.university
        });
        setEditIndex(index);
    };

    const handleDelete = (eduId) => {
        axios.delete(`${API_BASE_URL}/candidate/education/${userId}/${eduId}`)
            .then(() => fetchEducations())
            .catch(err => {
                console.error("Delete failed", err);
                alert("Error deleting education");
            });
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    return (
        <div className="container mt-4">
            <h3>Candidate Education</h3>

            <form onSubmit={handleSubmit} className="mb-4">
                <div className="row">

                    {/* Degree Dropdown */}
                    <div className="col-md-4 mb-3">
                        <label className="form-label">Degree</label>
                        <select
                            className={`form-select ${errors.degree ? 'is-invalid' : ''}`}
                            value={formData.degree}
                            onChange={(e) => setFormData({ ...formData, degree: e.target.value })}
                        >
                            {degreeOptions.map((option, idx) => (
                                <option key={idx} value={option}>{option || 'Select Degree'}</option>
                            ))}
                        </select>
                        {errors.degree && <div className="invalid-feedback">{errors.degree}</div>}
                    </div>

                    <div className="col-md-4 mb-3">
                        <label className="form-label">Grade *</label>
                        <input
                            type="text"
                            name="grade"
                            className={`form-control ${errors.grade ? 'is-invalid' : ''}`}
                            value={formData.grade}
                            onChange={handleChange}
                        />
                        {errors.grade && <div className="invalid-feedback">{errors.grade}</div>}
                    </div>

                    <div className="col-md-2 mb-3">
                        <label className="form-label">Start Year *</label>
                        <input
                            type="date"
                            name="startYear"
                            className={`form-control ${errors.startYear ? 'is-invalid' : ''}`}
                            value={formData.startYear}
                            onChange={handleChange}
                        />
                        {errors.startYear && <div className="invalid-feedback">{errors.startYear}</div>}
                    </div>

                    <div className="col-md-2 mb-3">
                        <label className="form-label">End Year *</label>
                        <input
                            type="date"
                            name="endYear"
                            className={`form-control ${errors.endYear ? 'is-invalid' : ''}`}
                            value={formData.endYear}
                            onChange={handleChange}
                        />
                        {errors.endYear && <div className="invalid-feedback">{errors.endYear}</div>}
                    </div>

                    <div className="col-md-6 mb-3">
                        <label className="form-label">University *</label>
                        <input
                            type="text"
                            name="university"
                            className={`form-control ${errors.university ? 'is-invalid' : ''}`}
                            value={formData.university}
                            onChange={handleChange}
                        />
                        {errors.university && <div className="invalid-feedback">{errors.university}</div>}
                    </div>
                </div>
                

                <button type="submit" className="btn btn-success">
                    {editIndex !== null ? 'Update Education' : 'Add Education'}
                </button>
                {editIndex !== null && (
                    <button type="button" className="btn btn-secondary ms-2" onClick={resetForm}>
                        Cancel
                    </button>
                )}
            </form>

            <h5>Education List</h5>
            {educations.length > 0 ? (
                <table className="table table-bordered">
                    <thead>
                        <tr>
                            <th>Degree</th>
                            <th>Grade</th>
                            <th>Start Year</th>
                            <th>End Year</th>
                            <th>University</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {educations.map((edu, index) => (
                            <tr key={edu.educationId}>
                                <td>{edu.degree}</td>
                                <td>{edu.grade}</td>
                                <td>{edu.startYear}</td>
                                <td>{edu.endYear}</td>
                                <td>{edu.university}</td>
                                <td>
                                    <button className="btn btn-warning btn-sm me-2" onClick={() => handleEdit(edu, index)}>Edit</button>
                                    <button className="btn btn-danger btn-sm" onClick={() => handleDelete(edu.educationId)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            ) : <p>No education records found.</p>}

            <button className="btn btn-outline-primary mb-3" onClick={() => navigate('/candidate/profile')}>
                ← Back to Profile
            </button>
        </div>
    );
}

export default CandidateEducation;
