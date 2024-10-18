import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';

import SportFacilities from './pages/SportFacilities';
import AddSportFacility from './pages/AddSportFacility';

import Coaches from './pages/Coaches';
import AddCoach from './pages/AddCoach';
import UpdateCoach from './pages/UpdateCoach';

import ManageOpenHours from './pages/ManageOpenHours';

import SportFacilityDetails from "./pages/SportFacilityDetails.jsx";

import SportFacilityRatings from "./pages/Reviews.jsx";

import SportFacilityEquipment from "./pages/Equipment.jsx";
import AddEquipment from "./pages/AddEquipment.jsx";
import UpdateEquipment from "./pages/UpdateEquipment.jsx";

import FacilityNews from "./pages/News.jsx";
import AddNews from "./pages/AddNews.jsx";
import EditNews from "./pages/UpdateNews.jsx";

import TrainingSessionsCalendar from "./pages/TrainingSessions.jsx";


import Navbar from './components/Navbar';
import icon from './assets/icon.png';

import './styles/App.css';


function App() {
  return (
    <Router>
      <img src={icon} alt="logo" className="logo-icon" />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/sport-facilities" element={<SportFacilities />} />
        <Route path="/sport-facilities/add" element={<AddSportFacility />} />
          <Route path="/sport-facilities/:id" element={<SportFacilityDetails />} />
          <Route path="/sport-facilities/:id/coaches" element={<Coaches />} />
          <Route path="/sport-facilities/:id/open-hours" element={<ManageOpenHours />} />
        <Route path="/add-coach/:id" element={<AddCoach />} />
        <Route path="/update-coach/:id/:sportFacilityID" element={<UpdateCoach />} />
        <Route path="//sport-facilities/:id/reviews" element={<SportFacilityRatings />} />
        <Route path="//sport-facilities/:id/equipment" element={<SportFacilityEquipment />} />
        <Route path="/add-equipment/:id" element={<AddEquipment />} />
        <Route path="/update-equipment/:id/:sportFacilityID" element={<UpdateEquipment />} />
        <Route path="/sport-facilities/:id/news" element={<FacilityNews />} />
        <Route path="/add-news/:id" element={<AddNews />} />
        <Route path="/edit-news/:newsId/:sportFacilityId" element={<EditNews />} />
        <Route path="/sport-facilities/:id/training-sessions" element={<TrainingSessionsCalendar />} />
        <Route path="/add-sport-facility" element={<AddSportFacility />} />
        <Route path="/manage-open-hours" element={<ManageOpenHours />} />
      </Routes>
    </Router>
  );
}

export default App;
