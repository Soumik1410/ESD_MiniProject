import logo from './logo.svg';
import React, {useState} from "react";
//import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import {Route, Routes, useNavigate} from "react-router-dom";
import './App.css';
import './Home.css';


function Home() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const [token, setToken] = useState(null);
    const navigate = useNavigate();

    const submit = async (e) => {
        e.preventDefault();
        const data = {
            email,
            password
        };
        try {
            const response = await fetch('http://localhost:8081/api/v1/faculty/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });
            if (response.headers.get('Content-Type').includes('text/plain')) {
                if (!response.ok) {
                    const error = await response.text();
                    console.log(error);
                }
                else {
                    const responseData = await response.text();
                    console.log(responseData);
                    const tokenMatch = responseData.match(/JWT Token:\s([A-Za-z0-9\-\._~\+\/]+=*)/);
                    if (tokenMatch) {
                        const jwtToken = tokenMatch[1];
                        setToken(jwtToken);
                        console.log(jwtToken);
                        localStorage.setItem('token', jwtToken);
                        localStorage.setItem('email', email);
                        navigate('/dashboard');
                    }
                }
            }
            setEmail('');
            setPassword('');
            setError(null);
        }
        catch(error)
        {
            console.error('Error during login:', error);
            setError(error.message);
        }
        finally
        {
        }
    };

    return (
        <div className="Home">
            <div className="form-container">
                <h2>Faculty Sign In</h2>
                <form onSubmit={submit}>
                    <div className="form-group">
                        {//<label htmlFor="username">Username</label>
                        }
                        <input
                            type="text"
                            id="email"
                            name="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            placeholder="Email ID"
                            required
                        />
                    </div>
                    <div className="form-group">
                        {//<label htmlFor="password">Password</label>
                        }
                        <input
                            type="password"
                            id="password"
                            name="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder="Password"
                            required
                        />
                    </div>
                    <button type="submit" className="submit-btn">Sign In</button>
                </form>
            </div>
        </div>
    );
}

export default Home;
