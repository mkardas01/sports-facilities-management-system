import { useEffect, useState } from 'react';
import { getAllOpenHours } from '../services/openHourService';

const OpenHourList = () => {
  const [openHours, setOpenHours] = useState([]);

  useEffect(() => {
    const fetchOpenHours = async () => {
      const data = await getAllOpenHours();
      setOpenHours(data);
    };
    fetchOpenHours();
  }, []);

  return (
    <div>
      <h2>Open Hours</h2>
      <ul>
        {openHours.map((hour) => (
          <li key={hour.id}>{hour.dayOfWeek}: {hour.openTime} - {hour.closeTime}</li>
        ))}
      </ul>
    </div>
  );
};

export default OpenHourList;
