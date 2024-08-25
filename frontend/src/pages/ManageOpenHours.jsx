import React, { useState, useEffect } from 'react';
import axios from 'axios';
import WeeklyCalendar from '../components/WeeklyCalendar'; // Komponent kalendarza tygodniowego

const ManageOpenHours = () => {
  const [openHours, setOpenHours] = useState(null);

  useEffect(() => {
    // Pobierz dane o godzinach otwarcia z backendu
    axios.get('http://localhost:8080/api/openhour/someFacilityId') // Replace 'someFacilityId' with actual facility ID
      .then(response => {
        setOpenHours(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the open hours!', error);
      });
  }, []);

  return (
    <div>
      <h2>Manage Open Hours</h2>
      {openHours ? (
        <WeeklyCalendar openHours={openHours} />
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
};

export default ManageOpenHours;
