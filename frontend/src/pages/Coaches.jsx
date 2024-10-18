import React, { useEffect, useState } from 'react';
import { getCoachesBySportFacility } from '../services/coachService';
import { deleteCoach} from "../services/coachService";
import { useNavigate } from 'react-router-dom';
import {useParams} from "react-router-dom";
import "../styles/Coaches.css"

const Coaches = () => {
  const { id } = useParams();
  const [coaches, setCoaches] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCoaches = async () => {
      try {
        const data = await getCoachesBySportFacility(id);
        setCoaches(data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching coaches', error);
        setLoading(false);
      }
    };
    fetchCoaches();
  }, [id]);

  const handleDelete = async (id) => {
    try {
      await deleteCoach(id);
      setCoaches(coaches.filter(coach => coach.id !== id));
    } catch (error) {
      console.error('Error deleting coach', error);
    }
  };




  return (
      <div className="coaches-container">
        <h1>Coaches</h1>
        <button className="add-coach-button" onClick={() => navigate(`/add-coach/${id}`)}
        >Add New Coach</button>
        <div
            className="coaches-list">
          {coaches.map((coach) => (
              <div key={coach.id} className="coach-card">
                <img src={coach.imageUrl} alt={`${coach.name} ${coach.surname}`} className="coach-image" />
                <h3>{coach.name} {coach.surname}</h3>
                <button className="update-button" onClick={() => navigate(`/update-coach/${coach.id}/${id}`)}>Update</button>
                <button className="delete-button" onClick={() => handleDelete(coach.id)}>Delete</button>
              </div>
          ))}
        </div>
      </div>
  );
};


export default Coaches;
