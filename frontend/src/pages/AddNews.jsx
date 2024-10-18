// pages/AddNews.js
import React, { useState } from 'react';
import {createNews} from "../services/newssService.js";
import { useNavigate, useParams } from 'react-router-dom';
import '../styles/AddNews.css';

const AddNews = () => {
    const { id } = useParams(); // ID obiektu sportowego
    const navigate = useNavigate();

    const [news, setNews] = useState({
        title: '',
        description: '',
        imageUrl: '',
        sportFacilityId: parseInt(id), // Ustaw ID obiektu
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNews({ ...news, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await createNews(news);
            navigate(`/sport-facilities/${id}/news`); // Przekierowanie do listy newsów
        } catch (error) {
            console.error('Error adding news', error);
        }
    };

    return (
        <div className="form-container">
            <h1>Add News</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Title</label>
                    <input
                        type="text"
                        name="title"
                        value={news.title}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Description</label>
                    <textarea
                        name="description"
                        value={news.description}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Image URL</label>
                    <input
                        type="text"
                        name="imageUrl"
                        value={news.imageUrl}
                        onChange={handleInputChange}
                    />
                </div>
                <button type="submit">Add News</button>
            </form>
        </div>
    );
};

export default AddNews;
