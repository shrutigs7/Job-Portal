import React from 'react';
import logo from '../assets/logo.png';

function Header() {
  return (
    <header className="d-flex align-items-center justify-content-between px-4 py-2 border-bottom">
      <div className="d-flex align-items-center gap-2">
        <img src={logo}
        alt="JobSeek Logo"
        style={{ height: '40px', width: '40px' }} />
        <h4 className="mb-0 fw-bold" style={{color:'#58b0eb'}}>JobSeek</h4>
      </div>
    </header>
  );
}

export default Header;
