import apiClient from './axiosConfig';

const API_URL = '/auth'; // baza URL jest teraz zarządzana w axiosConfig

export const login = async (email, password) => {
  try {
    const response = await apiClient.post(`${API_URL}/login`, { email, password });
    if (response.data.token) {
      localStorage.setItem('user', JSON.stringify(response.data)); // Zapisz dane użytkownika z tokenem w localStorage
    }
    return response.data;
  } catch (error) {
    throw new Error('Login failed. Please check your email and password.');
  }
};

export const register = async (userData) => {
  try {
    const response = await apiClient.post(`${API_URL}/signup`, userData);
    if (response.data.token) {
      localStorage.setItem('user', JSON.stringify(response.data)); // Zapisz dane użytkownika z tokenem w localStorage
    }
    return response.data;
  } catch (error) {
    throw new Error('Registration failed. Please check your input.');
  }
};

export const logout = () => {
  localStorage.removeItem('user'); // Usuń dane użytkownika z localStorage podczas wylogowania
};
