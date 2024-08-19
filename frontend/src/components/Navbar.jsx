import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/login">Login</Link>
        </li>
        <li>
          <Link to="/register">Register</Link>
        </li>
        <li>
          <Link to="/sport-facilities">Sport Facilities</Link>
        </li>
        <li>
          <Link to="/coaches">Coaches</Link>
        </li>
      </ul>
    </nav>
  );
};

export default Navbar;
