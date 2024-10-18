// pages/AddCoach.js
import React, { useState } from 'react';
import { createCoach } from '../services/coachService';
import { useNavigate, useParams } from 'react-router-dom';
import '../styles/AddCoach.css';

const AddCoach = () => {
  const { id } = useParams(); // Pobiera ID obiektu sportowego z URL
  const navigate = useNavigate();

  const [coach, setCoach] = useState({
    name: '',
    surname: '',
    image_url: '',
    sportFacilitiesId: id, // Ustawia ID obiektu sportowego dla nowego trenera
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCoach({ ...coach, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createCoach(coach); // Dodaje nowego trenera
      navigate(`/sport-facilities/${id}/coaches`); // Przekierowuje do listy trenerów po sukcesie
    } catch (error) {
      console.error('Error adding coach', error);
    }
  };

  return (
      <div className="form-container">
        <h1>Add New Coach</h1>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Name</label>
            <input
                type="text"
                name="name"
                value={coach.name}
                onChange={handleInputChange}
                required
            />
          </div>
          <div className="form-group">
            <label>Surname</label>
            <input
                type="text"
                name="surname"
                value={coach.surname}
                onChange={handleInputChange}
                required
            />
          </div>
          <div className="form-group">
            <label>Image URL</label>
            <input
                type="text"
                name="imageUrl"
                value={coach.imageUrl}
                onChange={handleInputChange}
            />
          </div>
          <button type="submit">Add Coach</button>
        </form>
      </div>
  );
};

export default AddCoach;
