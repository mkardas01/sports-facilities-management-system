import axios from 'axios';

const API_URL = 'http://localhost:8080/auth';

export const login = async (email, password) => {
  const response = await axios.post(`${API_URL}/login`, { email, password });
  if (response.data.token) {
    localStorage.setItem('user', JSON.stringify(response.data));

  }
  return response.data;
};

export const register = async (userData) => {
  const response = await axios.post(`${API_URL}/signup`, userData);
  if (response.data.token) {
    localStorage.setItem('user', JSON.stringify(response.data));
  }
  return response.data;
};

export const logout = () => {
  localStorage.removeItem('user');
};
