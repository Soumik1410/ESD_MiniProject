import React, { useEffect, useState } from 'react';
import {Route, Routes, useNavigate} from "react-router-dom";
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';
import './Payslip.css';

function Payslip() {
    const [payslipData, setPayslipData] = useState(null);
    const [error, setError] = useState(null);
    const id = localStorage.getItem('selectedSalaryId');
    const token = localStorage.getItem('token');
    const navigate = useNavigate();

    useEffect(() => {
        if (!token) {
            setError("No token provided.");
            navigate('/');
            return;
        }

        const fetchPayslip = async () => {
            try {
                // Fetch payslip data using the ID
                const response = await fetch(`http://localhost:8081/api/v1/faculty/salary_history/${id}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'x-access-token': token
                    }
                });

                if (!response.ok) {
                    const error = await response.text();
                    setError(error.message);
                    console.log(error);
                } else {
                    const data = await response.json();
                    setPayslipData(data);
                }
            } catch (error) {
                setError(error.message);
            }
        };

        fetchPayslip();
    }, [id]);

    const generatePDF = () => {
        console.log('Donwload button was clicked');

        const doc = new jsPDF();
        doc.setFontSize(18);
        doc.text(`Payslip for: ${payslipData.first_name} ${payslipData.last_name}`, 10, 30);
        doc.setFontSize(12);
        doc.text(`Email: ${payslipData.email}`, 10, 40);
        doc.text(`Title: ${payslipData.title}, Department: ${payslipData.department}`, 10, 50);

        doc.autoTable({
            head: [['Payment Date', 'Amount', 'Description', 'Status']],
            body: [
                [payslipData.payment_date, `$${payslipData.amount}`, payslipData.description || '', 'Paid'] // Replace null with empty string
            ],
            styles: {fontsize: 12},
            startY: 70,
        });
        doc.save('payslip.pdf');
    };

    if (error) {
        return <div>Error: {error}</div>;
    }

    if (!payslipData) {
        return <div>Loading payslip...</div>;
    }
    const description = payslipData.description || '';
    return (
        <div className="payslip-container">
            <div className="employee-info">
                <h2>{payslipData.first_name} {payslipData.last_name}</h2>
                <p>Email: {payslipData.email}</p>
                <p>Title: {payslipData.title}, Department: {payslipData.department}</p>
            </div>

            <table className="payslip-table">
                <thead>
                <tr>
                    <th>Payment Date</th>
                    <th>Amount</th>
                    <th>Description</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>{payslipData.payment_date}</td>
                    <td>{payslipData.amount}</td>
                    <td>{description}</td>
                    <td>Paid</td>
                </tr>
                </tbody>
            </table>

            <button className="download-pdf-btn" onClick={generatePDF}>
                Download as PDF
            </button>
        </div>
    );
}

export default Payslip;