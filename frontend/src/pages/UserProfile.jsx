import React, { useEffect, useState } from 'react';
import apiClient from '../services/axiosConfig';

const UserProfile = () => {
  const [profile, setProfile] = useState(null);

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const response = await apiClient.get('/user/profile'); // Pobierz dane profilu użytkownika
        setProfile(response.data); // Zapisz dane profilu w stanie
      } catch (error) {
        console.error('Error fetching profile', error); // Obsłuż błąd w przypadku problemów z pobraniem danych
      }
    };

    fetchProfile(); // Wywołaj funkcję pobierania danych profilu przy montowaniu komponentu
  }, []);

  return (
    <div>
      <h1>User Profile</h1>
      {profile ? (
        <div>
          <p>Name: {profile.name}</p>
          <p>Email: {profile.email}</p>
          {/* Dodaj tutaj inne pola profilu użytkownika */}
        </div>
      ) : (
        <p>Loading...</p> // Pokaż komunikat ładowania podczas pobierania danych
      )}
    </div>
  );
};

export default UserProfile;
