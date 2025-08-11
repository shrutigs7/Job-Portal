import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const API_BASE_URL = 'http://localhost:8081';

function Signup() {
    const [formData, setFormData] = useState({
        email: '',
        password: '',
        confirmPassword: '',
        role: 'CANDIDATE',
    });
    const [error, setError] = useState('');
    const [passwordError, setPasswordError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;

        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));

        // Validate password match live
        if (name === 'confirmPassword' || name === 'password') {
            if (
                name === 'confirmPassword' && value !== formData.password ||
                name === 'password' && formData.confirmPassword && value !== formData.confirmPassword
            ) {
                setPasswordError('Passwords do not match');
            } else {
                setPasswordError('');
            }
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        if (formData.password !== formData.confirmPassword) {
            setPasswordError('Passwords do not match');
            return;
        }

        try {
            const response = await axios.post(`${API_BASE_URL}/users/signup`, {
                email: formData.email,
                password: formData.password,
                role: formData.role
            });

            const user = response.data;
            localStorage.setItem('user', JSON.stringify(user));

            // Navigate based on role
            if (user.role === 'RECRUITER') {
                navigate('/recruiter/register');
            } else {
                navigate('/candidate/register/personal');
            }
        } catch (err) {
            console.error(err);
            setError(err.response?.data?.message || 'Signup failed. Please try again.');
        }
    };

    return (
        <div className="container mt-5">
            <div className="card p-4 shadow rounded-4">
                <h3 className="mb-4">Sign Up</h3>

                {error && <div className="alert alert-danger">{error}</div>}
                {passwordError && <div className="alert alert-warning">{passwordError}</div>}

                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label>Email</label>
                        <input
                            type="email"
                            name="email"
                            className="form-control"
                            required
                            value={formData.email}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="mb-3">
                        <label>Password</label>
                        <input
                            type="password"
                            name="password"
                            className="form-control"
                            required
                            value={formData.password}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="mb-3">
                        <label>Confirm Password</label>
                        <input
                            type="password"
                            name="confirmPassword"
                            className="form-control"
                            required
                            value={formData.confirmPassword}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="mb-3">
                        <label>Role</label>
                        <select
                            name="role"
                            className="form-select"
                            value={formData.role}
                            onChange={handleChange}
                        >
                            <option value="CANDIDATE">Candidate</option>
                            <option value="RECRUITER">Recruiter</option>
                        </select>
                    </div>

                    <button type="submit" className="btn btn-primary w-100" disabled={passwordError}>
                        Sign Up
                    </button>
                </form>
            </div>
        </div>
    );
}

export default Signup;
