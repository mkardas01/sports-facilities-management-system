import React from 'react';
import '../styles/WeeklyCalendar.css';

const WeeklyCalendar = ({ openHours }) => {
  const daysOfWeek = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];

  return (
    <div className="weekly-calendar">
      {daysOfWeek.map(day => (
        <div key={day} className="day-column">
          <h3>{day}</h3>
          <div className="time-slot">
            <span>Start: {openHours[day.toLowerCase()]?.start || 'Closed'}</span>
            <span>End: {openHours[day.toLowerCase()]?.end || 'Closed'}</span>
          </div>
        </div>
      ))}
    </div>
  );
};

export default WeeklyCalendar;
