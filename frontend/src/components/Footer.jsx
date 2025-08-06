import React from 'react';

function Footer() {
  const year = new Date().getFullYear();

  return (
    <footer className="text-center py-3 mt-auto border-top bg-light text-secondary">
      <small>© JobSeek {year}. All rights reserved.</small>
    </footer>
  );
}

export default Footer;
