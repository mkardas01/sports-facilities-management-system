import axios from 'axios';

const API_URL = 'http://localhost:8080/api/SportFacilityNews';

export const getAllNews = async () => {
  const response = await axios.get(`${API_URL}/all`);
  return response.data;
};

export const createNews = async (news) => {
  const response = await axios.post(`${API_URL}/create`, news);
  return response.data;
};

export const getNewsBySportFacilityId = async (facilityId) => {
  try {
    const response = await axios.get(`http://localhost:8080/api/SportFacilityNews/facility/${facilityId}`);
    return response.data;
  } catch (error) {
    throw new Error('Error fetching news for facility');
  }
};
