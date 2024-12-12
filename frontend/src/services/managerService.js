import axios from 'axios';
import config from '../config';

const API_URL = `${config.proxy}/api/manager`;

// Pobierz menedżerów dla danego obiektu
export const getManagersByFacility = async (facilityId) => {
    const response = await axios.get(`${API_URL}/${facilityId}`);
    return response.data;
};

// Dodaj nowego menedżera
export const addManager = async (managerDTO) => {
    const response = await axios.post(`${API_URL}/add`, managerDTO);
    return response.data;
};

// Usuń menedżera
export const deleteManager = async (managerDTO) => {
    const response = await axios.post(`${API_URL}/delete`, managerDTO);
    return response.data;
};
