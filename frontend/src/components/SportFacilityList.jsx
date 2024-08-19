import { useEffect, useState } from 'react';
import { getAllSportFacilities } from '../services/sportFacilityService';

const SportFacilityList = () => {
  const [facilities, setFacilities] = useState([]);

  useEffect(() => {
    const fetchFacilities = async () => {
      const data = await getAllSportFacilities();
      setFacilities(data);
    };
    fetchFacilities();
  }, []);

  return (
    <div>
      <h2>Sport Facilities</h2>
      <ul>
        {facilities.map((facility) => (
          <li key={facility.id}>{facility.name} - {facility.address}</li>
        ))}
      </ul>
    </div>
  );
};

export default SportFacilityList;
