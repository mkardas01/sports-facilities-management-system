import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { logout } from '../services/authService';

const LogoutPage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    // Wywołaj funkcję wylogowania, która usunie token
    logout();
    // Przekieruj użytkownika na stronę logowania
    navigate('/login');
  }, [navigate]);

  return (
    <div>
      <h2>Logging out...</h2>
    </div>
  );
};

export default LogoutPage;
