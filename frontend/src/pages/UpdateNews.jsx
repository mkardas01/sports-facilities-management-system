// pages/EditNews.js
import React, { useState, useEffect } from 'react';
import {updateNews, getNewsById} from "../services/newssService.js";
import { useParams, useNavigate } from 'react-router-dom';
import '../styles/AddNews.css';

const EditNews = () => {
    const { newsId, sportFacilityId } = useParams(); // ID newsa i obiektu sportowego
    const navigate = useNavigate();

    const [news, setNews] = useState({
        title: '',
        description: '',
        imageUrl: '',
        sportFacilityId: parseInt(sportFacilityId),
    });

    useEffect(() => {
        const fetchNews = async () => {
            try {
                const newsData = await getNewsById(newsId);
                setNews(newsData);
            } catch (error) {
                console.error('Error fetching news data', error);
            }
        };

        fetchNews();
    }, [newsId]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNews({ ...news, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await updateNews(news);
            navigate(`/sport-facilities/${sportFacilityId}/news`); // Przekierowanie do listy newsów
        } catch (error) {
            console.error('Error updating news', error);
        }
    };

    return (
        <div className="form-container">
            <h1>Edit News</h1>
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
                <button type="submit">Update News</button>
            </form>
        </div>
    );
};

export default EditNews;
