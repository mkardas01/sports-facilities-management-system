import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom'; // Dodano Link do nawigacji
import { getSportFacilityById } from '../services/sportFacilityService';
import '../styles/SportFacilitiesDetails.css'

const SportFacilityDetails = () => {
    const { id } = useParams(); // Odczytaj ID z URL
    const [facility, setFacility] = useState(null);

    useEffect(() => {
        const fetchFacility = async () => {
            try {
                const data = await getSportFacilityById(id);
                setFacility(data);
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
        <div>
            <h1>{facility.name}</h1>
            <img src={facility.imageUrl} alt={facility.name} style={{ maxWidth: '100%', height: 'auto' }} />
            <p>{facility.description}</p>
            <p>Address: {facility.address}</p>
            <p>Type: {facility.type}</p>
            <p>Membership Required: {facility.membershipRequired ? 'Yes' : 'No'}</p>

            {/* Karty do różnych sekcji */}
            <div className="section-cards">
                <Link to={`/sport-facilities/${id}/coaches`} className="card">
                    <h3>Trenerzy</h3>
                    <p>Zarządzaj trenerami.</p>
                </Link>

                <Link to={`/sport-facilities/${id}/open-hours`} className="card">
                    <h3>Godziny otwarcia</h3>
                    <p>Ustaw godziny otwarcia obiektu.</p>
                </Link>

                <Link to={`/sport-facilities/${id}/reviews`} className="card">
                    <h3>Oceny</h3>
                    <p>Przeczytaj oceny użytkowników.</p>
                </Link>

                <Link to={`/sport-facilities/${id}/equipment`} className="card">
                    <h3>Wyposażenie</h3>
                    <p>Zarządzaj dostępnym wyposażeniem.</p>
                </Link>

                <Link to={`/sport-facilities/${id}/news`} className="card">
                    <h3>Newsy</h3>
                    <p>Dodaj newsy.</p>
                </Link>

                <Link to={`/sport-facilities/${id}/training-sessions`} className="card">
                    <h3>Sesje Treningowe</h3>
                    <p>Zarządzaj sesjami treningowymi.</p>
                </Link>
            </div>
        </div>
    );
};

export default SportFacilityDetails;
