import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Home.css';
import { getAllNews } from '../services/newsService';

const Home = () => {
  const [news, setNews] = useState([]);

  useEffect(() => {
    const fetchNews = async () => {
      const data = await getAllNews();
      setNews(data);
    };
    fetchNews();
  }, []);

  return (
    <div className="home-layout">
      <div className="content">
        {/* Powitalny napis */}
        <h1>Welcome to the Sports Facilities Management System</h1>
        <p>This is the home page. Please use the navigation to access different parts of the application.</p>
        
        {/* Sekcja wyświetlania newsów */}
        <p>Here are the latest Sport Facility News!</p>
        <div className="tiles-container">
          {news.length === 0 ? (
            <p>No news available</p>
          ) : (
            news.map((newsItem) => (
              <div key={newsItem.id} className="news-tile">
                <div className="news-tile-content">
                  <h3>{newsItem.title}</h3>
                  <p>{newsItem.description}</p>
                  {newsItem.imageUrl && (
                    <img
                      src={newsItem.imageUrl}
                      alt={newsItem.title}
                    />
                  )}
                </div>
              </div>
            ))
          )}
        </div>

        {/* Napis "Popular Actions" */}
        <h2>Popular Actions</h2>

        {/* Kafelki do popularnych akcji */}
        <div className="tiles-container">
          <Link to="/add-coach" className="action-tile">
            <div className="tile-content">
              <h2>Add Coach</h2>
            </div>
          </Link>
          <Link to="/add-sport-facility" className="action-tile">
            <div className="tile-content">
              <h2>Add Sport Facility</h2>
            </div>
          </Link>
          <Link to="/manage-open-hours" className="action-tile">
            <div className="tile-content">
              <h2>Manage Open Hours</h2>
            </div>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Home;
