import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { register } from '../services/authService';
import '../styles/AuthForm.css'; // Nowy plik CSS

const Register = () => {
  const [userData, setUserData] = useState({
    name: '',
    surname: '',
    email: '',
    password: '',
    imageUrl: ''
  });
  const [error, setError] = useState(''); // Stan na przechowywanie błędu
  const navigate = useNavigate();

  const handleChange = (e) => {
    setUserData({ ...userData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await register(userData);
      navigate('/login');
    } catch (error) {
      console.error('Registration failed', error);
      setError('Registration failed. Please try again.');
    }
  };

  return (
    <div className="auth-form">
      <form onSubmit={handleSubmit} className="form-content">
        <h2>Register</h2>
        <div className="input-container">
          <label>Name:</label>
          <input
            type="text"
            name="name"
            value={userData.name}
            onChange={handleChange}
            required
          />
        </div>
        <div className="input-container">
          <label>Surname:</label>
          <input
            type="text"
            name="surname"
            value={userData.surname}
            onChange={handleChange}
            required
          />
        </div>
        <div className="input-container">
          <label>Email:</label>
          <input
            type="email"
            name="email"
            value={userData.email}
            onChange={handleChange}
            required
          />
        </div>
        <div className="input-container">
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={userData.password}
            onChange={handleChange}
            required
          />
        </div>
        <div className="input-container">
          <label>Image URL:</label>
          <input
            type="text"
            name="imageUrl"
            value={userData.imageUrl}
            onChange={handleChange}
          />
        </div>
        {error && <p className="error-message">{error}</p>}
        <button type="submit" className="auth-button">Register</button>
      </form>
    </div>
  );
};

export default Register;
