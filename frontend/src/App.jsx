import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import SportFacilities from './pages/SportFacilities';
import Coaches from './pages/Coaches';
import AddCoach from './pages/AddCoach';
import AddSportFacility from './pages/AddSportFacility';
import ManageOpenHours from './pages/ManageOpenHours';
import UserProfile from './pages/UserProfile';
import Navbar from './components/Navbar';
import icon from './assets/icon.png';

import './styles/App.css';

function App() {
  return (
    <Router>
      <Navbar />
      <img src={icon} alt="logo" className="logo-icon" />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/sport-facilities" element={<SportFacilities />} />
        <Route path="/coaches" element={<Coaches />} />
        <Route path="/add-coach" element={<AddCoach />} />
        <Route path="/add-sport-facility" element={<AddSportFacility />} />
        <Route path="/manage-open-hours" element={<ManageOpenHours />} />
        <Route path="/user-profile" element={<UserProfile />} />
      </Routes>
    </Router>
  );
}

export default App;
