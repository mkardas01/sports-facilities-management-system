import React, { useEffect, useState } from 'react';
import { getAllCoaches } from '../services/coachService';

const Coaches = () => {
  const [coaches, setCoaches] = useState([]);

  useEffect(() => {
    const fetchCoaches = async () => {
      try {
        const data = await getAllCoaches();
        setCoaches(data);
      } catch (error) {
        console.error('Error fetching coaches', error);
      }
    };
    fetchCoaches();
  }, []);

  return (
    <div>
      <h1>Coaches</h1>
      <ul>
        {coaches.map((coach) => (
          <li key={coach.id}>
            <h2>{coach.name} {coach.surname}</h2>
            <p>Associated Sport Facility: {coach.sportFacility.name}</p>
            {/* Dodaj inne pola, które chcesz wyświetlić */}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Coaches;
