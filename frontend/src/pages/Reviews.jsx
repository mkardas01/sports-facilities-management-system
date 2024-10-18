import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getRatingById } from '../services/reviewsService.js';
import "../styles/Ratings.css";

const SportFacilityRatings = () => {
    const { id } = useParams(); // Pobiera ID obiektu z URL
    const [averageRating, setAverageRating] = useState(null);

    const objectType = "SPORT_FACILITY"; // Typ obiektu ustawiony na stałe jako SportFacility

    useEffect(() => {
        const fetchRatings = async () => {
            try {
                // Pobiera dane ocen na podstawie ID i obiektu typu SportFacility
                const data = await getRatingById(id, objectType);

                // Sprawdź, czy w odpowiedzi są oceny
                if (data && data.ratingAvg !== null && data.ratingAvg !== undefined) {
                    setAverageRating(data.ratingAvg);
                }

                // Sprawdzenie, czy oceny istnieją
                if (data.ratings && data.ratings.length > 0) {
                    setRatings(data.ratings);
                    setIsEmpty(false); // Jeśli są oceny, nie jest pusto
                } else {
                    setIsEmpty(true); // Jeśli nie ma ocen
                }
            } catch (error) {
                console.error('Error fetching ratings', error);
                setIsEmpty(true); // W przypadku błędu również ustaw na brak opinii
            }
        };

        fetchRatings();
    }, [id]);

    return (
        <div>
            <h1>Ratings for Sport Facility</h1>
            {averageRating !== null ? (
                <p>Average Rating: {averageRating}</p>
            ) : (
                <p>No ratings available for this facility.</p>
            )}
        </div>
    );
};

export default SportFacilityRatings;