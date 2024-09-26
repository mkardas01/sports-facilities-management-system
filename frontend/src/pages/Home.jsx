import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Home.css';

const Home = () => {

  return (
    <div className="home-layout">
      <div className="content">
        <h1>Welcome to the Sports Facilities Management System</h1>
        <p>This is the home page. Please use the navigation to access different parts of the application.</p>
        <p>Here, in the future, you will see latest port Facility News!</p>
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