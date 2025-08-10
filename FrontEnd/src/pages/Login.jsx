import React from 'react';
import { useNavigate } from 'react-router-dom';
import { loginUser } from '../api/auth';
import { jwtDecode } from 'jwt-decode';


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

      var data = await loginUser(email, password);

      console.log('Login success:', data);

      const token = data.token;
      localStorage.setItem('token', token);

      const user = {
        ...data,
        userId: Number(data.message)
      };

      localStorage.setItem('user', JSON.stringify(user));
      console.log('user storage',user);

      var role = '';

      if (token) {
        const decodedToken = jwtDecode(token);

        role = decodedToken.role || decodedToken.authorities?.[0];
        console.log(role)

        // Optional: store role/user info
        localStorage.setItem('role', role);

        // Navigate based on role
      }
      if (role === 'ROLE_ADMIN') {
        navigate('/admin/home');
      } else if (role === 'ROLE_RECRUITER') {
        navigate('/recruiter');
      } else if (role === 'ROLE_CANDIDATE') {
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
          {/* <div className="mb-3 text-end">
            <button
              type="button"
              className="btn btn-link p-0 text-primary"
              onClick={() => navigate('/forgot-password')}
            >
              Forgot Password?
            </button>
          </div> */}
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
