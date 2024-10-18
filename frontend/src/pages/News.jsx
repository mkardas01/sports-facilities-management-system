// pages/FacilityNews.js
import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getSportFacilityDetails } from '../services/newsService';
import { deleteNews} from "../services/newssService.js";
import "../styles/FacilityNews.css"; // CSS file for custom styles

const FacilityNews = () => {
    const { id } = useParams(); // Pobierz ID obiektu z URL
    const [news, setNews] = useState([]);
    const [facilityName, setFacilityName] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchFacilityDetails = async () => {
            try {
                const data = await getSportFacilityDetails(id);
                setNews(data.news || []); // Zakładam, że w odpowiedzi znajduje się tablica 'news'
                setFacilityName(data.name); // Nazwa obiektu do wyświetlenia
            } catch (error) {
                console.error('Error fetching facility news', error);
            }
        };

        fetchFacilityDetails();
    }, [id]);

    // Funkcja usuwająca news
    const handleDelete = async (newsId) => {
        try {
            await deleteNews(newsId);
            setNews(news.filter(item => item.id !== newsId)); // Aktualizuj listę newsów
        } catch (error) {
            console.error('Error deleting news', error);
        }
    };

    return (
        <div className="news-container">
            <h1>News for {facilityName}</h1>

            <button className="add-news-button" onClick={() => navigate(`/add-news/${id}`)}>
                Add New News
            </button>

            <ul className="news-list">
                {news.map((item) => (
                    <li key={item.id} className="news-item">
                        <h2>{item.title}</h2>
                        {item.imageUrl && (
                            <img
                                src={item.imageUrl}
                                alt={item.title}
                                className="news-image"
                            />
                        )}
                        <p>{item.description}</p>

                        <button
                            className="edit-button"
                            onClick={() => navigate(`/edit-news/${item.id}/${id}`)}
                        >
                            Edit
                        </button>
                        <button
                            className="delete-button"
                            onClick={() => handleDelete(item.id)}
                        >
                            Delete
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default FacilityNews;
