import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getSportFacilityById } from '../services/sportFacilityService';
import { getNewsBySportFacilityId } from '../services/newsService';
import defaulticon from '../assets/icon.png';
import '../styles/Details.css'; // Import pliku CSS

const SportFacilityDetails = () => {
    const { id } = useParams(); // Pobierz id z adresu URL
    const [facility, setFacility] = useState(null);
    const [news, setNews] = useState([]); // Stan na newsy związane z obiektem

    useEffect(() => {
        const fetchFacility = async () => {
            try {
                const data = await getSportFacilityById(id);
                setFacility({
                    ...data,
                    imageUrl: data.imageUrl || defaulticon,
                });
            } catch (error) {
                console.error('Error fetching facility details', error);
            }
        };

        const fetchNews = async () => {
            try {
                const newsData = await getNewsBySportFacilityId(id); // Pobieramy newsy dla danego obiektu
                setNews(newsData);
            } catch (error) {
                console.error('Error fetching news', error);
            }
        };

        fetchFacility();
        fetchNews();
    }, [id]);

    if (!facility) {
        return <div>Loading...</div>;
    }

    return (
        <div className="facility-details">
            <img src={facility.imageUrl} alt={facility.name} />
            <h1>{facility.name}</h1>
            <p>{facility.description}</p>
            <p>Address: {facility.address}</p>
            <p>Type: {facility.type}</p>

            <h2>News for this Facility</h2>
            <div className="news-container">
                {news.length > 0 ? (
                    news.map((newsItem) => (
                        <div key={newsItem.id} className="news-tile">
                            <h3>{newsItem.title}</h3>
                            <p>{newsItem.description}</p>
                            {newsItem.imageUrl && (
                                <img
                                    src={newsItem.imageUrl}
                                    alt={newsItem.title}
                                    style={{ width: '100%', borderRadius: '5px' }}
                                />
                            )}
                        </div>
                    ))
                ) : (
                    <p>No news available for this facility.</p>
                )}
            </div>
        </div>
    );
};

export default SportFacilityDetails;
