import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getSportFacilityById } from '../services/sportFacilityService';
import defaulticon from '../assets/icon.png'; // Upewnij się, że ścieżka jest poprawna

const SportFacilityDetails = () => {
    const { id } = useParams(); // Pobierz id z adresu URL
    const [facility, setFacility] = useState(null);

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

        fetchFacility();
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
        </div>
    );
};

export default SportFacilityDetails;
