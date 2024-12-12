import React, { useEffect, useState } from 'react';
import { getCoachesBySportFacility, deleteCoach } from '../services/coachService';
import { useNavigate } from 'react-router-dom';
import "/src/index.css";
import { getPicture } from "../services/fileService.js"; // Ensure this is the correct path for fileService

const Coaches = () => {
  const id = localStorage.getItem('selectedFacilityId');
  const [coaches, setCoaches] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  // Stan przechowujący URL obrazka dla każdego trenera
  const [coachImages, setCoachImages] = useState({});

  // Funkcja do pobierania obrazka dla trenera
  const fetchImage = async (coachId, filename) => {
    try {
      const url = await getPicture(filename); // Pobieranie URL obrazka
      setCoachImages((prevImages) => ({
        ...prevImages,
        [coachId]: url,
      })); // Ustawienie URL obrazka w stanie dla tego trenera
    } catch (error) {
      console.error('Error fetching image:', error);
    }
  };

  useEffect(() => {
    const fetchCoaches = async () => {
      try {
        const data = await getCoachesBySportFacility(id);
        setCoaches(data);
        setLoading(false);

        // Po załadowaniu trenerów, uruchamiamy fetchImage dla każdego trenera
        data.forEach((coach) => {
          if (coach.imageUrl) {
            fetchImage(coach.id, coach.imageUrl); // Pobieranie obrazka, jeśli jest dostępny
          }
        });
      } catch (error) {
        console.error('Error fetching coaches', error);
        setLoading(false);
      }
    };

    fetchCoaches();
  }, [id]);

  const handleDelete = async (coachId) => {
    try {
      await deleteCoach(coachId);
      setCoaches(coaches.filter(coach => coach.id !== coachId)); // Remove coach from the list after deletion
      setCoachImages((prevImages) => {
        const newImages = { ...prevImages };
        delete newImages[coachId]; // Usuń obrazek z pamięci podręcznej
        return newImages;
      });
    } catch (error) {
      console.error('Error deleting coach', error);
    }
  };

  return (
      <div className="container flex flex-col mx-auto p-4">
        {/* Wyśrodkowanie tytułu */}
        <h1 className="text-2xl font-bold mb-4 text-center">Coaches</h1>

        {/* Wyśrodkowanie przycisku */}
        <button
            className="mb-4 bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 mx-auto block"
            onClick={() => navigate(`/add-coach`)}
        >
          Add New Coach
        </button>

        {loading ? (
            <p className="text-center text-gray-500">Loading coaches...</p>
        ) : (
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
              {coaches.map((coach) => (
                  <div key={coach.id} className="bg-white shadow-md rounded-lg overflow-hidden p-4 flex flex-col items-center">
                    {/* Sprawdzamy, czy URL obrazu trenera istnieje w stanie */}
                    {coach.imageUrl && coachImages[coach.id] ? (
                        <img
                            src={coachImages[coach.id]} // Używamy pobranego URL z coachImages
                            alt={coach.name}
                            className="w-48 h-48 rounded-full object-cover mb-4"
                        />
                    ) : (
                        <div className="w-48 h-48 rounded-full bg-gray-300 mb-4 flex items-center justify-center">
                          {/* Jeśli brak zdjęcia, wyświetlamy placeholder */}
                          <span className="text-white">No Image</span>
                        </div>
                    )}

                    <h3 className="text-lg font-semibold text-black text-center">{coach.name} {coach.surname}</h3>
                    <div className="flex justify-between mt-4 w-full">
                      <button
                          className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-500"

                      >
                        Modify
                      </button>
                      <button
                          className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
                          onClick={() => handleDelete(coach.id)}
                      >
                        Delete
                      </button>
                    </div>
                  </div>
              ))}
            </div>
        )}
      </div>
  );

};

export default Coaches;
