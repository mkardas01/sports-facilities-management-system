// pages/TrainingSessionsCalendar.js
import React, { useEffect, useState } from 'react';
import { getSportFacilityDetails } from "../services/newsService.js";
import '../styles/TrainingSessionsCalendar.css';

const daysOfWeek = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];

// eslint-disable-next-line react/prop-types
const TrainingSessionsCalendar = ({ facilityId }) => {
    const [sessions, setSessions] = useState([]);

    useEffect(() => {
        const fetchSessions = async () => {
            try {
                const data = await getSportFacilityDetails(facilityId);
                setSessions(data.trainingSessions || []);
            } catch (error) {
                console.error('Error fetching training sessions', error);
            }
        };

        fetchSessions();
    }, [facilityId]);

    const groupSessionsByDay = () => {
        // Funkcja grupująca sesje według dni tygodnia
        const grouped = daysOfWeek.reduce((acc, day) => {
            acc[day] = [];
            return acc;
        }, {});

        sessions.forEach(session => {
            const sessionDate = new Date(session.trainingDate);
            const day = daysOfWeek[sessionDate.getDay() - 1]; // getDay() zwraca dzień tygodnia (0 - niedziela)
            grouped[day].push(session);
        });

        return grouped;
    };

    const groupedSessions = groupSessionsByDay();

    return (
        <div className="calendar-container">
            <h1>Weekly Training Sessions</h1>
            <div className="calendar-grid">
                {daysOfWeek.map(day => (
                    <div key={day} className="day-column">
                        <h2>{day}</h2>
                        {groupedSessions[day].length === 0 ? (
                            <p>No sessions</p>
                        ) : (
                            groupedSessions[day].map(session => (
                                <div key={session.id} className="session-card">
                                    <h3>{session.name}</h3>
                                    <p>Start: {session.startHour}</p>
                                    <p>Duration: {session.duration} min</p>
                                    <p>Free Spots: {session.freeBooked} / {session.capacity}</p>
                                </div>
                            ))
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default TrainingSessionsCalendar;
