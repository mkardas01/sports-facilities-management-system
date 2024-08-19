import React, { useEffect, useState } from 'react';
import { getAllSportFacilities } from '../services/sportFacilityService';

const SportFacilities = () => {
  const [facilities, setFacilities] = useState([]);

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

  return (
    <div>
      <h1>Sport Facilities</h1>
      <ul>
        {facilities.map((facility) => (
          <li key={facility.id}>
            <h2>{facility.name}</h2>
            <p>{facility.description}</p>
            <p>Address: {facility.address}</p>
            <p>Type: {facility.type}</p>
            {/* Dodaj inne pola, które chcesz wyświetlić */}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SportFacilities;
