import React from 'react';
import {Route, Routes} from "react-router-dom";
import StartWindow from "../pages/StartWindow";
import LoginPage from "../pages/LoginPage";
import RegistrationPage from "../pages/RegistrationPage";
import {TextEditorPage} from "../pages/TextEditorPage";
import CreatingPostPage from "../pages/CreatingPostPage";

const AppRouter = () => {
    return (
        <Routes>
            <Route path="/start" element={<StartWindow/>}/>
            <Route path="/login" element={<LoginPage/>}/>
            <Route path="/register" element={<RegistrationPage/>}/>
            <Route path="/addPost" element={<CreatingPostPage/>}/>
            <Route path="/" element={<StartWindow/>}/>
            <Route path="/:number/editor" element={<TextEditorPage/>}/>
        </Routes>
    );
};

export default AppRouter;