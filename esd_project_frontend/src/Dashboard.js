import React, { useEffect, useState } from 'react';
import {Route, Routes, useNavigate} from "react-router-dom";
import './App.css';
import './Dashboard.css';

function Dashboard() {
    const [salaryHistory, setSalaryHistory] = useState([]);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    const token = localStorage.getItem('token');
    const email = localStorage.getItem('email');

    useEffect(() => {
        if (!token) {
            setError("No token provided.");
            navigate('/');
            return;
        }

        const fetchSalaryHistory = async () => {
            try {
                const response = await fetch('http://localhost:8081/api/v1/faculty/salary_history', {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'x-access-token': token
                    }
                });

                if (!response.ok) {
                    const error = await response.text();
                    console.log(error);
                }
                else {
                    const data = await response.json();
                    setSalaryHistory(data);
                }
            } catch (error) {
                console.log('Error fetching salary history:', error);
                setError(error.message);
            } finally {
            }
        };
        fetchSalaryHistory();
    }, []);

    const handleActionButtonClick = (id) => {
        console.log(`Button clicked for salary record ID: ${id}`);
        localStorage.setItem('selectedSalaryId', id);
        navigate(`/payslip`);
    };

    return (
        <div className="dashboard">
            <h2>Welcome back, {email}!. <hr/><br/> Here's a look at your salary history.</h2>
            {error && <p>Error: {error}</p>}

            {!error && salaryHistory.length > 0 && (
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Payment Date</th>
                        <th>Amount</th>
                        <th>Description</th>
                        <th>View</th>
                    </tr>
                    </thead>
                    <tbody>
                    {salaryHistory.map((item) => (
                        <tr key={item.id}>
                            <td>{item.id}</td>
                            <td>{item.payment_date}</td>
                            <td>{item.amount}</td>
                            <td>{item.description}</td>
                            <td>
                                <button className="view-button" onClick={() => handleActionButtonClick(item.id)}>
                                    View Payslip
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}

            {!error && salaryHistory.length === 0 && <p>No salary history available.</p>}
        </div>
    );
}

export default Dashboard;