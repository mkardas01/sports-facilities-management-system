import React, { useEffect, useState } from 'react';
import { getCoachesBySportFacility } from '../services/coachService';

const Coaches = ({ sportFacilityID }) => {
  const [coaches, setCoaches] = useState([]);

  useEffect(() => {
    const fetchCoaches = async () => {
      try {
        const data = await getCoachesBySportFacility(sportFacilityID);
        setCoaches(data);
      } catch (error) {
        console.error('Error fetching coaches', error);
      }
    };
    fetchCoaches();
  }, [sportFacilityID]);

  return (
    <div>
      <h1>Coaches</h1>
      <ul>
        {coaches.map((coach) => (
          <li key={coach.id}>
            <h2>{coach.name} {coach.surname}</h2>
            <p>Associated Sport Facility: {coach.sportFacility.name}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Coaches;
