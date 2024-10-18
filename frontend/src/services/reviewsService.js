import axios from 'axios';

const API_URL = 'http://localhost:8080/api/rating';

export const getRatingById = async (id, objectType) => {
    const response = await axios.get(`${API_URL}/all`, {
        params: {
            id,
            objectType
        }
    });
    return response.data;
};
