import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllSportFacilities } from '../services/sportFacilityService';
import '../styles/SportFacilities.css'; // Import stylów dla kart

const SportFacilities = () => {
  const [facilities, setFacilities] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchFacilities = async () => {
      try {
        const data = await getAllSportFacilities();
        setFacilities(data);
      } catch (error) {
        console.error('Error fetching sport facilities', error);
      }
    };
    fetchFacilities();
  }, []);

  const handleCardClick = (id) => {
    // Tutaj możesz przekierować użytkownika do strony szczegółów obiektu sportowego
    // Na przykład: window.location.href = `/facility/${id}`;
    navigate(`/sport-facilities/${id}`)
  };

  return (
      <div className="sport-facilities">
        <h1>Sport Facilities</h1>
        <button onClick={() => navigate('/add-sport-facility')} className="add-facility-button">
          Add New Facility
        </button>
        <div className="facility-grid">
          {facilities.map((facility) => (
              <div key={facility.id} className="facility-card" onClick={() => handleCardClick(facility.id)}>
                <h2>{facility.name}</h2>
                <p>{facility.description}</p>
                <p><strong>Address:</strong> {facility.address}</p>
                <p><strong>Type:</strong> {facility.type}</p>
              </div>
          ))}
        </div>
      </div>
  );
};

export default SportFacilities;
