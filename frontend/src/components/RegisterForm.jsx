import { useState } from 'react';
import { register } from '../services/authService';
import { useNavigate } from 'react-router-dom';

const RegisterForm = () => {
  const [userData, setUserData] = useState({
    name: '',
    surname: '',
    email: '',
    password: '',
    imageUrl: ''
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setUserData({ ...userData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await register(userData);
      navigate('/sport-facilities');
    } catch (error) {
      console.error('Registration failed', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Name</label>
        <input type="text" name="name" value={userData.name} onChange={handleChange} required />
      </div>
      <div>
        <label>Surname</label>
        <input type="text" name="surname" value={userData.surname} onChange={handleChange} required />
      </div>
      <div>
        <label>Email</label>
        <input type="email" name="email" value={userData.email} onChange={handleChange} required />
      </div>
      <div>
        <label>Password</label>
        <input type="password" name="password" value={userData.password} onChange={handleChange} required />
      </div>
      <div>
        <label>Image URL</label>
        <input type="text" name="imageUrl" value={userData.imageUrl} onChange={handleChange} />
      </div>
      <button type="submit">Register</button>
    </form>
  );
};

export default RegisterForm;
