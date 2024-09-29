import React, { useEffect, useState } from 'react';
import { getAllSportFacilities } from '../services/sportFacilityService';
import {Link} from "react-router-dom";
import '../styles/SportFacilities.css';
import defaulticon from '../assets/icon.png';



const SportFacilities = () => {
  const [facilities, setFacilities] = useState([]);

  useEffect(() => {
    const fetchFacilities = async () => {
      try {
        const data = await getAllSportFacilities();
        const filteredData = data.map((facility) => ({
          id: facility.id,
          name: facility.name,
          description: facility.description,
          address: facility.address,
          type: facility.type,
          imageUrl: facility.imageUrl || defaulticon,
        }));
        setFacilities(filteredData);
      } catch (error) {
        console.error('Error fetching sport facilities', error);
      }
    };
    fetchFacilities();
  }, []);

  return (
      <div>
        <h1>Sport Facilities</h1>

        <div className="facility-container">
          {facilities.map((facility) => (
              <Link key={facility.id} to={`/SportFacilitiy/${facility.id}`} className="facility-card-link">
              <div key={facility.id} className="facility-card">
                <img src={facility.imageUrl} alt={facility.name}/>
                <h2>{facility.name}</h2>
                <p>{facility.description}</p>
                <p>Address: {facility.address}</p>
                <p>Type: {facility.type}</p>
              </div>
              </Link>
          ))}
        </div>

        <Link to="/add-sport-facility" className="add-facility-button">
          Add Facility +
        </Link>
      </div>
  );
};

export default SportFacilities;