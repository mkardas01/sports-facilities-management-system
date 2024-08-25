import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Home.css';

const Home = () => {
  return (
    <div className="home-layout">
      <nav className="vertical-nav">
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
      <div className="content">
        <h1>Welcome to the Sports Facilities Management System</h1>
        <p>This is the home page. Please use the navigation to access different parts of the application.</p>
        <div className="tiles-container">
          <Link to="/add-coach" className="tile">
            <div className="tile-content">
              <h2>Add Coach</h2>
            </div>
          </Link>
          <Link to="/add-sport-facility" className="tile">
            <div className="tile-content">
              <h2>Add Sport Facility</h2>
            </div>
          </Link>
          <Link to="/manage-open-hours" className="tile">
            <div className="tile-content">
              <h2>Manage Open Hours</h2>
            </div>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Home;
