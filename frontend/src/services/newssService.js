// services/sportFacilityService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/SportFacilityNews/';

export const deleteNews = async (id) => {
    const response = await axios.delete(`${API_URL}/delete/${id}`);
    return response.data;
};

export const createNews= async (News) => {
    const response = await axios.post(`${API_URL}/create`, News);
    return response.data;
};

export const getNewsById = async (id) => {
    const response = await axios.get(`${API_URL}/${id}`);
    return response.data;
};

export const updateNews = async (News) => {
    const response = await axios.put(`${API_URL}/update`, News);
    return response.data;
};