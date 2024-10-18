import React, { useState, useEffect } from 'react';
import { getEqById, updateEq } from '../services/equipmentService';
import { useParams, useNavigate } from 'react-router-dom';
import '../styles/AddEquipment.css';

const UpdateEquipment = () => {
    const { id, sportFacilityID } = useParams(); // Pobiera ID sprzętu oraz ID obiektu sportowego z URL
    const navigate = useNavigate();
    const [equipment, setEquipment] = useState({
        type: '',
        brand: '',
        model: '',
        description: '',
        imageUrl: '',
        amount: 1,
        sportFacilitiesId: sportFacilityID, // Przekazuje ID obiektu sportowego
    });

    useEffect(() => {
        const fetchEquipment = async () => {
            try {
                const equipmentData = await getEqById(id); // Pobiera dane sprzętu
                setEquipment(equipmentData); // Ustawia dane sprzętu w stanie
            } catch (error) {
                console.error('Error fetching equipment data', error);
            }
        };

        fetchEquipment();
    }, [id]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setEquipment({ ...equipment, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await updateEq(equipment); // Aktualizuje sprzęt
            navigate(`/sport-facilities/${sportFacilityID}/equipment`); // Przekierowuje do listy sprzętu po sukcesie
        } catch (error) {
            console.error('Error updating equipment', error);
        }
    };

    return (
        <div className="form-container">
            <h1>Update Equipment</h1>
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
                <button type="submit">Update Equipment</button>
            </form>
        </div>
    );
};

export default UpdateEquipment;
