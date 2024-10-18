// services/sportFacilityService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/details';

export const getSportFacilityDetails = async (id) => {
    const response = await axios.get(`${API_URL}/${id}`);
    return response.data;
};
