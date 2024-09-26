import {useEffect, useState} from "react";
import { login } from '../services/authService.js';
import { Link, useNavigate } from 'react-router-dom';

function LoginPage() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const [isLoggedIn, setisLoggedIn] = useState(false)

    useEffect(() => {
        const token = localStorage.getItem("user");
        if(token){
            setisLoggedIn(true);
        }
    }, []);

    const handleSubmit = async (e) => {

        e.preventDefault();
        try{
            await login(email,password);
            setisLoggedIn(true);
            navigate('/sport-facilities');
        }catch(error){
            console.error('Login failed', error);
        }
    };

    return (
        <div className="auth-form">
          {isLoggedIn ? (
            <h2>You are logged in</h2>
          ) : (
            <form onSubmit={handleSubmit} className="form-content">
              <h2>Login</h2>
              <div className="input-container">
                <label>Email:</label>
                <input
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />
              </div>
              <div className="input-container">
                <label>Password:</label>
                <input
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>
              <button type="submit" className="auth-button">Login</button>
              <div className="redirect">
                <p>Don't have an account?</p>
                <Link to="/register">Sign Up</Link>
              </div>
            </form>
          )}
        </div>
      );
    }
    
    export default LoginPage;
