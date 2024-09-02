import React, { useState } from 'react';
import axios from 'axios';
import '../styles/AddSportFacility.css';

const AddSportFacility = () => {
  const [facility, setFacility] = useState({
    name: '',
    description: '',
    address: '',
    type: '',
    membershipRequired: 'no', // Domyślna wartość ustawiona na 'no'
    imageUrl: ''
  });

  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    setFacility({
      ...facility,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/sport-facility/create', facility);
      if (response.status === 200) {
        setMessage('Sport facility added successfully');
      }
    } catch (error) {
      setMessage('Error adding sport facility: ' + error.response.data.message);
    }
  };

  return (
    <div className="add-facility-form">
      <h2>Add Sport Facility</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Name:</label>
          <input
            type="text"
            name="name"
            value={facility.name}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Description:</label>
          <input
            type="text"
            name="description"
            value={facility.description}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Address:</label>
          <input
            type="text"
            name="address"
            value={facility.address}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Type:</label>
          <input
            type="text"
            name="type"
            value={facility.type}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Membership Required (yes/no):</label>
          <select
            name="membershipRequired"
            value={facility.membershipRequired}
            onChange={handleChange}
            required
          >
            <option value="yes">Yes</option>
            <option value="no">No</option>
          </select>
        </div>
        <div>
          <label>Image URL:</label>
          <input
            type="text"
            name="imageUrl"
            value={facility.imageUrl}
            onChange={handleChange}
          />
        </div>
        <button type="submit">Add Sport Facility</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default AddSportFacility;
