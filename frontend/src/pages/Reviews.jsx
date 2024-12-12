import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getRatingById } from '../services/reviewsService.js';
import '/src/index.css'

const SportFacilityRatings = () => {
    const id = localStorage.getItem('selectedFacilityId');
    const [averageRating, setAverageRating] = useState(null);
    const [isEmpty, setIsEmpty] = useState(false);

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
                    setIsEmpty(false); // Jeśli są oceny, nie jest pusto
                } else {
                    setIsEmpty(true); // Jeśli nie ma ocen
                }
            } catch (error) {
                console.error('Error fetching ratings', error);
                setIsEmpty(true); //
            }
        };

        fetchRatings();
    }, [id]);

    return (
        <div className="max-w-4xl mx-auto p-6 bg-white shadow-lg rounded-lg mt-10">
            <h1 className="text-2xl font-semibold text-gray-800 mb-6">Ratings for Sport Facility</h1>
            {averageRating !== null ? (
                <p className="text-lg text-gray-700">Average Rating: <span className="font-bold text-indigo-600">{averageRating}</span></p>
            ) : (
                <p className="text-lg text-gray-500">No ratings available for this facility.</p>
            )}
            {isEmpty && (
                <p className="mt-4 text-gray-500"></p>
            )}
        </div>
    );
};

export default SportFacilityRatings;
