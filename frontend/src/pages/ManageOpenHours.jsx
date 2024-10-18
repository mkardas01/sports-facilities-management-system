import React, { useState, useEffect } from 'react';
import { useParams } from "react-router-dom";
import { getOpenHourById, updateOpenHour } from "../services/OpenHourService.js";
import "../styles/OpenHours.css";

const ManageOpenHours = () => {
  const { id } = useParams(); // Odczytaj ID obiektu z URL
  const [openingHours, setOpeningHours] = useState({});
  const [isEditing, setIsEditing] = useState(false); // Czy użytkownik edytuje godziny?
  const [editedHours, setEditedHours] = useState({}); // Zmodyfikowane godziny

  useEffect(() => {
    const fetchOpeningHours = async () => {
      try {
        const data = await getOpenHourById(id);
        setOpeningHours(data);
        setEditedHours(data); // Inicjalizujemy formularz danymi z serwera
      } catch (error) {
        console.error('Error fetching opening hours', error);
      }
    };
    fetchOpeningHours();
  }, [id]);

  if (!openingHours) {
    return <div>Loading...</div>;
  }

  // Dni tygodnia
  const daysOfWeek = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'];

  // Funkcja do obsługi zmiany w formularzu
  const handleInputChange = (e, day, timeType) => {
    const { value } = e.target;
    setEditedHours({
      ...editedHours,
      [day]: {
        ...editedHours[day],
        [timeType]: value,
      },
    });
  };
  editedHours.sport_facilityid = id;
  // Funkcja do zapisania zmienionych godzin otwarcia
  const handleSave = async () => {
    try {
      const updatedHours = {
        ...editedHours,
        sportFacilityId: id, // Przekazujemy ID obiektu sportowego
      };
      await updateOpenHour(updatedHours); // Aktualizacja godzin otwarcia na serwerze
      setOpeningHours(updatedHours); // Aktualizacja stanu lokalnego
      setIsEditing(false); // Zakończenie edycji
    } catch (error) {
      console.error('Error updating opening hours', error);
    }
  };

  return (
      <div>
        <h1>Godziny Otwarcia</h1>
        <table>
          <thead>
          <tr>
            <th>Dzień</th>
            <th>Godzina Otwarcia</th>
            <th>Godzina Zamknięcia</th>
          </tr>
          </thead>
          <tbody>
          {daysOfWeek.map((day) => (
              <tr key={day}>
                <td>{day.charAt(0).toUpperCase() + day.slice(1)}</td>
                <td>
                  {isEditing ? (
                      <input
                          type="time"
                          value={editedHours[day]?.start || ''}
                          onChange={(e) => handleInputChange(e, day, 'start')}
                      />
                  ) : (
                      openingHours[day]?.start || 'Zamknięte'
                  )}
                </td>
                <td>
                  {isEditing ? (
                      <input
                          type="time"
                          value={editedHours[day]?.end || ''}
                          onChange={(e) => handleInputChange(e, day, 'end')}
                      />
                  ) : (
                      openingHours[day]?.end || 'Zamknięte'
                  )}
                </td>
              </tr>
          ))}
          </tbody>
        </table>

        {isEditing ? (
            <div>
              <button onClick={handleSave}>Zapisz</button>
              <button onClick={() => setIsEditing(false)}>Anuluj</button>
            </div>
        ) : (
            <button onClick={() => setIsEditing(true)}>Edytuj Godziny Otwarcia</button>
        )}
      </div>
  );
};

export default ManageOpenHours;
