import { useEffect, useState } from 'react';
import { getAllCoaches } from '../services/coachService';

const CoachList = () => {
  const [coaches, setCoaches] = useState([]);

  useEffect(() => {
    const fetchCoaches = async () => {
      const data = await getAllCoaches();
      setCoaches(data);
    };
    fetchCoaches();
  }, []);

  return (
    <div>
      <h2>Coaches</h2>
      <ul>
        {coaches.map((coach) => (
          <li key={coach.id}>{coach.name} {coach.surname}</li>
        ))}
      </ul>
    </div>
  );
};

export default CoachList;
