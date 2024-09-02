import axios from 'axios';

const API_URL = 'http://localhost:8080/api/TrainingSession';

export const getAllTrainingSessions = async () => {
  const response = await axios.get(`${API_URL}/all`);
  return response.data;
};

export const getTrainingSessionById = async (id) => {
  const response = await axios.get(`${API_URL}/${id}`);
  return response.data;
};

export const createTrainingSession = async (trainingSession) => {
  const response = await axios.post(`${API_URL}/create`, trainingSession);
  return response.data;
};

export const updateTrainingSession = async (trainingSession) => {
  const response = await axios.put(`${API_URL}/update`, trainingSession);
  return response.data;
};

export const deleteTrainingSession = async (id) => {
  const response = await axios.delete(`${API_URL}/delete/${id}`);
  return response.data;
};
