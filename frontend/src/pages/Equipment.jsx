import React, { useEffect, useState } from 'react';
import { getEqBySportId, deleteEquipment } from '../services/equipmentService';
import { useNavigate, useParams } from 'react-router-dom';
import "../styles/Equipment.css";

const Equipment = () => {
    const { id } = useParams();
    const [equipment, setEquipment] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchEquipment = async () => {
            try {
                const data = await getEqBySportId(id);
                setEquipment(data);
                setLoading(false);
            } catch (error) {
                console.error('Error fetching equipment', error);
                setLoading(false);
            }
        };
        fetchEquipment();
    }, [id]);

    const handleDelete = async (equipmentId) => {
        try {
            await deleteEquipment(equipmentId);
            setEquipment(equipment.filter(item => item.id !== equipmentId)); // Usuwa sprzęt z listy po usunięciu
        } catch (error) {
            console.error('Error deleting equipment', error);
        }
    };

    return (
        <div className="equipment-container">
            <h1>Equipment</h1>
            <button
                className="add-equipment-button"
                onClick={() => navigate(`/add-equipment/${id}`)}>
                Add New Equipment
            </button>
            <div className="equipment-list">
                {equipment.map((item) => (
                    <div key={item.id} className="equipment-card">
                        <img src={item.imageUrl} alt={`${item.type} ${item.brand}`} className="equipment-image" />
                        <h3>{item.type} {item.brand}</h3>
                        <p>{item.description}</p>
                        <p>Amount: {item.amount}</p>
                        <button className="update-button" onClick={() => navigate(`/update-equipment/${item.id}/${id}`)}>Update</button>
                        <button className="delete-button" onClick={() => handleDelete(item.id)}>Delete</button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Equipment;
