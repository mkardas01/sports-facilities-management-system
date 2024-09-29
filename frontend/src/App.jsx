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
import Navbar from './components/Navbar';
import icon from './assets/icon.png';
import './styles/App.css';
import LoginPage from "./pages/LoginPage.jsx";
import SportFacilityDetails from "./pages/SportFacilityDetails.jsx";
import AddNews from './pages/AddNews';
import Logout from './pages/LogoutPage';

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    useEffect(() => {
        const token = localStorage.getItem("user");
        if(token){
            setIsLoggedIn(true);
        }else{
            setIsLoggedIn(false);
        }
    }, []);

    return (
        <Router>
            <div className="app-container">
                <Navbar />
                <div className="content-container">
                    <img src={icon} alt="logo" className="logo-icon" />
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/login" element={<LoginPage />} />
                        <Route path="/register" element={<Register />} />
                        <Route path="/sport-facilities" element={<SportFacilities />} />
                        <Route path="/coaches" element={<Coaches />} />
                        <Route path="/add-coach" element={<AddCoach />} />
                        <Route path="/add-sport-facility" element={<AddSportFacility />} />
                        <Route path="/manage-open-hours" element={<ManageOpenHours />} />
                        <Route path="/SportFacilitiy/:id" element={<SportFacilityDetails />} />
                        <Route path="/add-news" element={<AddNews />} /> {/* Nowa ścieżka */}
                        <Route path="/logout" element={<Logout />} />
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
