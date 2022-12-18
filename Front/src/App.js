import React, {useState} from 'react';
import Post from "./components/Post";
import Login from "./components/Login";
import Registration from "./components/Registration";
import {BrowserRouter, Link, Route, Router, Routes} from 'react-router-dom';
import './styles/App.css'
import PostsList from "./components/PostsList";
import ListForm from "./components/ListForm";
import TextEditor from "./components/TextEditor";
import "./styles/TextEditor.css"
import Articles from "./components/Articles";
import "./styles/App.css"
import StartWindow from "./pages/StartWindow";
import Search from "./components/Search";
import LoginPage from "./pages/LoginPage";
import RegistrationPage from "./pages/RegistrationPage";
import Navbar from "./components/Navbar";
import AppRouter from "./components/AppRouter";

// import "./components/*"


function App() {
    return (
        <div>
            <Navbar/>
            <AppRouter/>
        </div>
    );
}

export default App;
