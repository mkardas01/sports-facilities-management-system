import React, { useState } from 'react';
import axios from 'axios';
import '../styles/AddNews.css';
import { useNavigate } from 'react-router-dom';

const AddNews = () => {
  const [news, setNews] = useState({
    sportFacilitiesId: '',
    title: '',
    description: '',
    imageUrl: ''
  });

  const [message, setMessage] = useState('');
  const navigate = useNavigate(); // Dodajemy nawigację

  const handleChange = (e) => {
    setNews({
      ...news,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/SportFacilityNews/create', news);
      if (response.status === 201) {
        setMessage('News added successfully');
        navigate('/'); // Przekierowanie na stronę główną po dodaniu newsa
      }
    } catch (error) {
      setMessage('Error adding news: ' + error.response?.data?.message || 'Unexpected error');
    }
  };

  return (
    <div className="add-news-form">
      <h2>Add News</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Sport Facility ID:</label>
          <input
            type="text"
            name="sportFacilitiesId"
            value={news.sportFacilitiesId}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Title:</label>
          <input
            type="text"
            name="title"
            value={news.title}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Description:</label>
          <textarea
            name="description"
            value={news.description}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Image URL:</label>
          <input
            type="text"
            name="imageUrl"
            value={news.imageUrl}
            onChange={handleChange}
          />
        </div>
        <button type="submit">Add News</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default AddNews;
