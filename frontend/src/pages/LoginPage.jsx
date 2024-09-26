import {useEffect, useState} from "react";
import { login } from '../services/authService.js';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

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
            navigate('/');
        }catch(error){
            console.error('Login failed', error);
        }
    };

    return (
        <div style={{ display: "flex", justifyContent: "center", alignItems: "center", height: "100vh" }}>
            {isLoggedIn ? (
                <h2>You are logged in</h2>
            ):(
                <form onSubmit={handleSubmit} style={{border: "1px solid #ccc", padding: "20px", borderRadius: "5px"}}>
                    <h2>Login</h2>
                    <div>
                        <label>Email:</label>
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                            style={{ width: "100%", padding: "5px", margin: "5px 0", boxSizing: "border-box" }}
                        />
                    </div>
                    <div style = {{textAlign: "center"}}>
                        <label>Password:</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            style={{ width: "100%", padding: "5px", margin: "5px 0", boxSizing: "border-box" }}
                        />
                    </div>
                    <button type="submit">Login</button>
                    <div style={{textAlign: "center"}}>
                        <p>Don t have an account?</p>
                        <Link to="/register" style={{color: "blue", textDecoration: "underline"}}>
                            Sign Up
                        </Link>
                    </div>
                </form>
            )}
        </div>
    );
}

export default LoginPage;
