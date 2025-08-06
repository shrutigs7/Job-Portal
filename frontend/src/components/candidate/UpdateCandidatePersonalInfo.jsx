 import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function UpdateCandidatePersonalInfo() {
    const user = JSON.parse(localStorage.getItem('user'));
    const userId = user?.userId;

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: '',
        // email: '',
        dateOfBirth: '',
        mobileNo: '',
        linkedIn: '',
        gitHub: '',
    });

    useEffect(() => {
        axios.get(`http://localhost:8081/candidate/${userId}`)
            .then(res => {
                const data = res.data;
                setFormData({
                    name: data.name || '',
                    //   email: data.email || '',
                    dateOfBirth: data.dateOfBirth || '',
                    mobileNo: data.mobileNo || '',
                    linkedIn: data.linkedIn || '',
                    gitHub: data.gitHub || '',
                });
            })
            .catch(err => console.error("Failed to fetch candidate data", err));
    }, [userId]);

    const handleChange = (e) => {
        setFormData(prev => ({
            ...prev,
            [e.target.name]: e.target.value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        axios.put(`http://localhost:8081/candidate/update/${userId}`, formData)
            .then(res => {
                alert("Personal information updated successfully!");
                navigate("/candidate/profile");
            })
            .catch(err => {
                console.error("Update failed", err);
                alert("Failed to update personal information.");
            });
    };

    return (
        <div className="container mt-4">
            <h3>Update Personal Information</h3>
            <form onSubmit={handleSubmit} className="mt-4">
                <div className="mb-3">
                    <label>Name</label>
                    <input type="text" name="name" className="form-control" value={formData.name} onChange={handleChange} required />
                </div>


                <div className="mb-3">
                    <label>Date of Birth</label>
                    <input type="date" name="dateOfBirth" className="form-control" value={formData.dateOfBirth} onChange={handleChange} required />
                </div>

                <div className="mb-3">
                    <label>Mobile No</label>
                    <input type="text" name="mobileNo" className="form-control" value={formData.mobileNo} onChange={handleChange} required />
                </div>

                <div className="mb-3">
                    <label>LinkedIn</label>
                    <input type="website" name="linkedIn" className="form-control" value={formData.linkedIn} onChange={handleChange} />
                </div>

                <div className="mb-3">
                    <label>GitHub</label>
                    <input type="website" name="gitHub" className="form-control" value={formData.gitHub} onChange={handleChange} />
                </div>

                <div className="d-flex justify-content-between">
                    <button className="btn btn-secondary" onClick={() => navigate("/candidate/profile")}>Cancel</button>
                    <button type="submit" className="btn btn-success">Save Changes</button>
                </div>
            </form>
        </div>
    );
}

export default UpdateCandidatePersonalInfo;
