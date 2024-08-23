import React, { useState } from 'react';
import axios from 'axios';
import '../styles/AddCoach.css';

const AddCoach = () => {
  const [coach, setCoach] = useState({
    name: '',
    surname: '',
    sportFacilitiesId: '',
    imageUrl: ''
  });

  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    setCoach({
      ...coach,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/coach/create', coach);
      if (response.status === 200) {
        setMessage('Coach added successfully');
      }
    } catch (error) {
      setMessage('Error adding coach: ' + error.response.data.message);
    }
  };

  return (
    <div className="add-coach-form">
      <h2>Add Coach</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Name:</label>
          <input
            type="text"
            name="name"
            value={coach.name}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Surname:</label>
          <input
            type="text"
            name="surname"
            value={coach.surname}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Sports Facility Name:</label>
          <input
            type="text"
            name="sportFacilitiesId"
            value={coach.sportFacilitiesId}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Image URL:</label>
          <input
            type="text"
            name="imageUrl"
            value={coach.imageUrl}
            onChange={handleChange}
          />
        </div>
        <button type="submit">Add Coach</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default AddCoach;
