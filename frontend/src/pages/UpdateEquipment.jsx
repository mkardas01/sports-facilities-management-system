// pages/UpdateEquipment.js
import React, { useState, useEffect } from 'react';
import { getEqById, updateEq } from '../services/equipmentService';
import { useParams, useNavigate } from 'react-router-dom';

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
        <div className="max-w-2xl mx-auto p-6 bg-white shadow-lg rounded-lg mt-10">
            <h1 className="text-2xl font-semibold text-gray-800 mb-6">Update Equipment</h1>
            <form onSubmit={handleSubmit} className="space-y-6">
                <div className="form-group">
                    <label htmlFor="type" className="block text-sm font-medium text-gray-700">Type</label>
                    <input
                        id="type"
                        type="text"
                        name="type"
                        value={equipment.type}
                        onChange={handleInputChange}
                        required
                        className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="brand" className="block text-sm font-medium text-gray-700">Brand</label>
                    <input
                        id="brand"
                        type="text"
                        name="brand"
                        value={equipment.brand}
                        onChange={handleInputChange}
                        required
                        className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="model" className="block text-sm font-medium text-gray-700">Model</label>
                    <input
                        id="model"
                        type="text"
                        name="model"
                        value={equipment.model}
                        onChange={handleInputChange}
                        required
                        className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="description" className="block text-sm font-medium text-gray-700">Description</label>
                    <input
                        id="description"
                        type="text"
                        name="description"
                        value={equipment.description}
                        onChange={handleInputChange}
                        className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="amount" className="block text-sm font-medium text-gray-700">Amount</label>
                    <input
                        id="amount"
                        type="number"
                        name="amount"
                        value={equipment.amount}
                        onChange={handleInputChange}
                        required
                        className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="imageUrl" className="block text-sm font-medium text-gray-700">Image URL</label>
                    <input
                        id="imageUrl"
                        type="text"
                        name="imageUrl"
                        value={equipment.imageUrl}
                        onChange={handleInputChange}
                        className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                    />
                </div>

                <button
                    type="submit"
                    className="w-full py-2 px-4 bg-indigo-600 text-white font-semibold rounded-md shadow hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-opacity-50"
                >
                    Update Equipment
                </button>
            </form>
        </div>
    );
};

export default UpdateEquipment;
