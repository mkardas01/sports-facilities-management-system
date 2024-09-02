import axios from 'axios';

// Utwórz instancję Axios
const apiClient = axios.create({
  baseURL: 'http://localhost:8080', // Podstawowy URL do backendu
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
  },
});

// Dodaj interceptor, który dodaje token JWT do każdego żądania
apiClient.interceptors.request.use(
  (config) => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user && user.token) {
      config.headers.Authorization = `Bearer ${user.token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default apiClient;