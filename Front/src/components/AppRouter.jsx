import React from 'react';
import {Route, Routes} from "react-router-dom";
import StartWindow from "../pages/StartWindow";
import LoginPage from "../pages/LoginPage";
import RegistrationPage from "../pages/RegistrationPage";
import ListForm from "./ListForm";
import {TextEditorPage} from "../pages/TextEditorPage";

const AppRouter = () => {
    return (
        <Routes>
            <Route path="/start" element={<StartWindow/>}/>
            <Route path="/login" element={<LoginPage/>}/>
            <Route path="/register" element={<RegistrationPage/>}/>
            <Route path="/addPost" element={<ListForm/>}/>
            <Route path="/" element={<StartWindow/>}/>
            <Route path="/:number/editor" element={<TextEditorPage/>}/>
        </Routes>
    );
};

export default AppRouter;