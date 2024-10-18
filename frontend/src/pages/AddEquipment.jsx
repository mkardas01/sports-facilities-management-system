import React, { useState } from 'react';
import { createEquipment } from '../services/equipmentService';
import { useNavigate, useParams } from 'react-router-dom';
import '../styles/AddEquipment.css';

const AddEquipment = () => {
    const { id } = useParams(); // Pobiera ID obiektu sportowego z URL
    const navigate = useNavigate();

    const [equipment, setEquipment] = useState({
        type: '',
        brand: '',
        model: '',
        description: '',
        imageUrl: '',
        amount: 1,
        owner_sport_facility_id: id, // Ustawia ID obiektu sportowego dla nowego sprzętu
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setEquipment({ ...equipment, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await createEquipment(equipment); // Dodaje nowy sprzęt
            navigate(`/sport-facilities/${id}/equipment`); // Przekierowuje do listy sprzętu po sukcesie
        } catch (error) {
            console.error('Error adding equipment', error);
        }
    };

    return (
        <div className="form-container">
            <h1>Add New Equipment</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Type</label>
                    <input
                        type="text"
                        name="type"
                        value={equipment.type}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Brand</label>
                    <input
                        type="text"
                        name="brand"
                        value={equipment.brand}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Model</label>
                    <input
                        type="text"
                        name="model"
                        value={equipment.model}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Description</label>
                    <input
                        type="text"
                        name="description"
                        value={equipment.description}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="form-group">
                    <label>Amount</label>
                    <input
                        type="number"
                        name="amount"
                        value={equipment.amount}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Image URL</label>
                    <input
                        type="text"
                        name="imageUrl"
                        value={equipment.imageUrl}
                        onChange={handleInputChange}
                    />
                </div>
                <button type="submit">Add Equipment</button>
            </form>
        </div>
    );
};

export default AddEquipment;
