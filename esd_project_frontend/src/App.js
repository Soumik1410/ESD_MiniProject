import logo from './logo.svg';
import React, {useState} from "react";
//import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import {Route, Routes, useNavigate} from "react-router-dom";
import './App.css';
import Dashboard from "./Dashboard";
import Home from "./Home";
import Payslip from "./Payslip";

function App() {
  return (
    <div className="App">
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/dashboard" element={<Dashboard/>} />
            <Route path="/payslip" element={<Payslip/>} />
        </Routes>
    </div>
  );
}

export default App;
