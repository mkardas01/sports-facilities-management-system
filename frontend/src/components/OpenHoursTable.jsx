import React from 'react';
import '../styles/OpenHoursTable.css';

const OpenHoursTable = ({ openHours, handleInputChange, handleSave }) => {
  const daysOfWeek = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];

  return (
    <table className="open-hours-table">
      <thead>
        <tr>
          <th>Day</th>
          <th>Start Time</th>
          <th>End Time</th>
        </tr>
      </thead>
      <tbody>
        {daysOfWeek.map(day => (
          <tr key={day}>
            <td>{day}</td>
            <td>
              <input
                type="time"
                name={`${day.toLowerCase()}Start`}
                value={openHours[day.toLowerCase()]?.start || ''}
                onChange={handleInputChange}
              />
            </td>
            <td>
              <input
                type="time"
                name={`${day.toLowerCase()}End`}
                value={openHours[day.toLowerCase()]?.end || ''}
                onChange={handleInputChange}
              />
            </td>
          </tr>
        ))}
      </tbody>
      <tfoot>
        <tr>
          <td colSpan="3">
            <button onClick={handleSave}>Save Changes</button>
          </td>
        </tr>
      </tfoot>
    </table>
  );
};

export default OpenHoursTable;
