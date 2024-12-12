import React, { useEffect, useState } from 'react';
import { getManagersByFacility, addManager, deleteManager } from '../services/managerService';
import "/src/index.css"; // Upewnij się, że ta ścieżka jest poprawna dla Tailwind CSS


const ManagerManagement = () => {
    const facilityId = localStorage.getItem('selectedFacilityId'); // ID obiektu sportowego
    const [managers, setManagers] = useState([]);
    const [newManagerId, setNewManagerId] = useState(''); // ID nowego menedżera

    // Funkcja do pobierania menedżerów
    const fetchManagers = async () => {
        try {
            const data = await getManagersByFacility(facilityId);
            setManagers(data);
        } catch (error) {
            console.error('Error fetching managers:', error);
        }
    };

    // Pobierz menedżerów po załadowaniu komponentu
    useEffect(() => {
        fetchManagers();
    }, [facilityId]);

    // Dodaj nowego menedżera
    const handleAddManager = async () => {
        try {
            const managerDTO = {
                userId: parseInt(newManagerId, 10), // Konwertuj na liczbę
                sportFacilityId: parseInt(facilityId, 10),
            };
            await addManager(managerDTO); // Dodaj menedżera
            setNewManagerId(''); // Reset pola
            await fetchManagers(); // Odśwież listę menedżerów
        } catch (error) {
            console.error('Error adding manager:', error);
        }
    };

    // Usuń istniejącego menedżera
    const handleDeleteManager = async (managerId) => {
        try {
            await deleteManager({ userId: managerId, sportFacilityId: parseInt(facilityId, 10) });
            setManagers(managers.filter(manager => manager.id !== managerId));
            await fetchManagers();
        } catch (error) {
            console.error('Error deleting manager:', error);
        }
    };

    return (
        <div className="container mx-auto p-6 bg-gray-900 text-white rounded-lg shadow-md">
            <h1 className="text-3xl font-bold mb-6">Manage Managers</h1>

            {/* Formularz dodawania menedżera */}
            <div className="mb-6">
                <h2 className="text-xl font-semibold mb-4">Add New Manager</h2>
                <div className="flex flex-col md:flex-row gap-4">
                    <input
                        type="text"
                        placeholder="Enter Manager User ID"
                        className="border p-2 rounded-md w-full bg-gray-800 text-white"
                        value={newManagerId}
                        onChange={(e) => setNewManagerId(e.target.value)}
                    />
                    <button
                        onClick={handleAddManager}
                        className="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 transition"
                    >
                        Add
                    </button>
                </div>
            </div>

            {/* Lista istniejących menedżerów */}
            <h2 className="text-xl font-semibold mb-4">Existing Managers</h2>
            {managers.length === 0 ? (
                <p className="text-gray-400">No managers found for this facility.</p>
            ) : (
                <ul className="space-y-4">
                    {managers.map(manager => (
                        <li key={manager.id}
                            className="flex justify-between items-center p-4 bg-gray-800 rounded-md shadow">
                            <div>
                                <p className="text-lg font-semibold">
                                    {manager.name} {manager.surname}
                                </p>
                                <p className="text-gray-400">Email: {manager.email}</p>
                                <p className="text-gray-400">Username: {manager.username}</p>
                                <p className="text-gray-400">
                                    Authorities: {manager.authorities ? manager.authorities.join(', ') : 'No authorities'}
                                </p>
                            </div>
                            <button
                                onClick={() => handleDeleteManager(manager.id)}
                                className="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600 transition"
                            >
                                Delete
                            </button>
                        </li>
                    ))}
                </ul>

            )}
        </div>
    );
};

export default ManagerManagement;