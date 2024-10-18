// pages/UpdateCoach.js
import React, { useState, useEffect } from 'react';
import { getCoachById, updateCoach } from '../services/coachService';
import { useParams, useNavigate } from 'react-router-dom';
import '../styles/AddCoach.css';

const UpdateCoach = () => {
    const { id, sportFacilityID } = useParams(); // Pobiera ID trenera oraz ID obiektu sportowego z URL
    const navigate = useNavigate();
    const [coach, setCoach] = useState({
        name: '',
        surname: '',
        image_url: '',
        sportFacilitiesId: sportFacilityID,
    });

    useEffect(() => {
        const fetchCoach = async () => {
            try {
                const coachData = await getCoachById(id); // Pobiera dane trenera z backendu
                setCoach(coachData); // Ustawia dane w stanie
            } catch (error) {
                console.error('Error fetching coach data', error);
            }
        };

        fetchCoach();
    }, [id]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setCoach({ ...coach, [name]: value });
    };

    coach.sportFacilitiesId = sportFacilityID;

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await updateCoach(coach); // Aktualizuje dane trenera
            navigate(`/sport-facilities/${sportFacilityID}/coaches`); // Przekierowuje do listy trenerów po sukcesie
        } catch (error) {
            console.error('Error updating coach', error);
        }
    };

    return (
        <div className="form-container">
            <h1>Update Coach</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Name</label>
                    <input
                        type="text"
                        name="name"
                        value={coach.name}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Surname</label>
                    <input
                        type="text"
                        name="surname"
                        value={coach.surname}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Image URL</label>
                    <input
                        type="text"
                        name="imageUrl"
                        value={coach.imageUrl}
                        onChange={handleInputChange}
                    />
                </div>
                <button type="submit">Update Coach</button>
            </form>
        </div>
    );
};

export default UpdateCoach;
