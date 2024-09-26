import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import SportFacilities from './pages/SportFacilities';
import Coaches from './pages/Coaches';
import AddCoach from './pages/AddCoach';
import AddSportFacility from './pages/AddSportFacility';
import ManageOpenHours from './pages/ManageOpenHours';
import AddNews from './pages/AddNews';  // Import nowej strony
import Navbar from './components/Navbar';
import icon from './assets/icon.png';
import LogoutPage from './pages/LogoutPage';
import './styles/App.css';
import LoginPage from "./pages/LoginPage.jsx";
import SportFacilityDetails from "./pages/SportFacilityDetails.jsx";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("user");
    if (token) {
      setIsLoggedIn(true);
    } else {
      setIsLoggedIn(false);
    }
  }, []);

  return (
    <Router>
      <div className="app-container">
        {isLoggedIn && <Navbar />} {/* Navbar widoczny po zalogowaniu */}
        <div className="content-container">
          <img src={icon} alt="logo" className="logo-icon" />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/testlogin" element={<LoginPage />} />
            <Route path="/register" element={<Register />} />
            <Route path="/sport-facilities" element={<SportFacilities />} />
            <Route path="/coaches" element={<Coaches />} />
            <Route path="/add-coach" element={<AddCoach />} />
            <Route path="/add-sport-facility" element={<AddSportFacility />} />
            <Route path="/manage-open-hours" element={<ManageOpenHours />} />
            <Route path="/add-news" element={<AddNews />} /> 
            <Route path="/SportFacilitiy/:id" element={<SportFacilityDetails />} />
            <Route path="/logout" element={<LogoutPage />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
