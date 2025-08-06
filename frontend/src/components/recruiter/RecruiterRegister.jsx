import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const API_BASE_URL = 'http://localhost:8081';

const INDUSTRY_OPTIONS = [
    "IT", "FINANCE", "HEALTHCARE", "EDUCATION", "MANUFACTURING",
    "RETAIL", "CONSTRUCTION", "GOVERNMENT", "CONSULTING"
];

const SIZE_OPTIONS = ["SMALL", "MEDIUM", "LARGE", "ENTERPRISE"];


function RecruiterRegister() {
    const navigate = useNavigate();
    const [form, setForm] = useState({
        name: '',
        mobNumber: '',
        title: '',
        companyName: '',
        location: '',
        industry: '',
        size: '',
        foundedYear: '',
        website: '',
        description: ''
    });

    const [userId, setUserId] = useState(null);
    const [success, setSuccess] = useState('');
    const [error, setError] = useState('');

    useEffect(() => {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user && user.userId) {
            setUserId(user.userId);
        } else {
            setError('User not found. Please log in again.');
        }
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm((prev) => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setSuccess('');
        setError('');

        try {
            await axios.post(`${API_BASE_URL}/recruiter/add/${userId}`, {
                name: form.name,
                mobNumber: form.mobNumber,
                title: form.title,
                companyName: form.companyName,  
                location: form.location,
                industry: form.industry,
                size: form.size,
                foundedYear: form.foundedYear,
                website: form.website,
                description: form.description
            });
            setSuccess('Recruiter profile created successfully.');
            setTimeout(() => {
                navigate('/login');
            }, 1500);
        } catch (err) {
            console.error(err);
            setError(err.response?.data?.message || 'Failed to create profile.');
        }
    };

    return (
        <div className="container mt-5">
            <div className="card p-4 shadow rounded-4">
                <h3 className="mb-4">Recruiter Profile</h3>

                {success && <div className="alert alert-success">{success}</div>}
                {error && <div className="alert alert-danger">{error}</div>}

                <form onSubmit={handleSubmit}>
                    <h5 className="mb-3">Personal Details</h5>

                    <div className="mb-3">
                        <label>Name</label>
                        <input type="text" className="form-control" name="name" value={form.name} onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label>Mobile Number</label>
                        <input type="text" className="form-control" name="mobNumber" value={form.mobNumber} onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label>Title</label>
                        <input type="text" className="form-control" name="title" value={form.title} onChange={handleChange} required />
                    </div>

                    <h5 className="mt-4 mb-3">Company Details</h5>

                    <div className="mb-3">
                        <label>Company Name</label>
                        <input type="text" className="form-control" name="companyName" value={form.companyName} onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label>Location</label>
                        <input type="text" className="form-control" name="location" value={form.location} onChange={handleChange} />
                    </div>

                    <div className="mb-3">
                        <label>Industry</label>
                        <select className="form-select" name="industry" value={form.industry} onChange={handleChange} required>
                            <option value="">Select Industry</option>
                            {INDUSTRY_OPTIONS.map((option) => (
                                <option key={option} value={option}>{option}</option>
                            ))}
                        </select>
                    </div>

                    <div className="mb-3">
                        <label>Size</label>
                        <select className="form-select" name="size" value={form.size} onChange={handleChange} required>
                            <option value="">Select Company Size</option>
                            {SIZE_OPTIONS.map((option) => (
                                <option key={option} value={option}>{option}</option>
                            ))}
                        </select>
                    </div>

                    <div className="mb-3">
                        <label>Founded Year</label>
                        <input type="number" className="form-control" name="foundedYear" value={form.foundedYear} onChange={handleChange} />
                    </div>

                    <div className="mb-3">
                        <label>Website</label>
                        <input type="text" className="form-control" name="website" value={form.website} onChange={handleChange} />
                    </div>

                    <div className="mb-3">
                        <label>Description</label>
                        <textarea className="form-control" name="description" rows={3} value={form.description} onChange={handleChange} />
                    </div>

                    <button type="submit" className="btn btn-primary w-100">Create Profile</button>
                </form>
            </div>
        </div>
    );
}

export default RecruiterRegister;
