import React from 'react';
import { useNavigate } from 'react-router-dom';
import { loginUser } from '../api/auth';

function Login() {
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    const email = e.target.email.value;
    const password = e.target.password.value;

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      alert('Please enter a valid email address');
      return;
    }

    try {

      const data = await loginUser(email, password);

      console.log('Login success:', data);

      // Optional: Save token or user info to localStorage/sessionStorage

      localStorage.setItem("user", JSON.stringify(data));
      //  localStorage.setItem("token", data.token);

      // Navigate based on role
      const role = data.role;
      if (role === 'ADMIN') {
        navigate('/admin/home');
      } else if (role === 'RECRUITER') {
        navigate('/recruiter');
      } else if (role === 'CANDIDATE') {
        navigate('/candidate');
      } else {
        alert("Unknown role!");
      }

    } catch (error) {
      console.error('Login failed:', error);
      alert(error.message || 'Invalid credentials or server error');
    }
  };

  return (
    <div
      className="d-flex justify-content-center align-items-center"
      style={{
        height: '100dvh'
      }}
    >
      <div className="card p-4 shadow" style={{ maxWidth: '400px', width: '100%' }}>
        <h3 className="text-center mb-4">Login</h3>
        <form onSubmit={handleLogin}>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email address</label>
            <input type="email" className="form-control" id="email" required />
          </div>
          <div className="mb-2">
            <label htmlFor="password" className="form-label">Password</label>
            <input type="password" className="form-control" id="password" required />
          </div>
          <div className="mb-3 text-end">
            <button
              type="button"
              className="btn btn-link p-0 text-primary"
              onClick={() => navigate('/forgot-password')}
            >
              Forgot Password?
            </button>
          </div>
          <button type="submit" className="btn btn-primary w-100 mb-2">
            Sign In
          </button>
        </form>
        <div className="text-center">
          <p className="mb-0">
            Don’t have an account?{' '}
            <button
              className="btn btn-link p-0 text-primary"
              onClick={() => navigate('/signup')}
            >
              Sign Up
            </button>
          </p>
        </div>
      </div>
    </div>
  );
}

export default Login;
