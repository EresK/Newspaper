import React from 'react';
import {Route, Routes} from "react-router-dom";
import StartWindow from "../pages/StartWindow";
import LoginPage from "../pages/LoginPage";
import RegistrationPage from "../pages/RegistrationPage";
import ListForm from "./ListForm";
import TmpCreatePost from "../pages/TmpCreatePost";

const AppRouter = () => {
    return (
        <Routes>
            <Route path="/home" element={<StartWindow/>}/>
            <Route path="/login" element={<LoginPage/>}/>
            <Route path="/register" element={<RegistrationPage/>}/>
            <Route path="/addPost" element={<TmpCreatePost/>}/>
            <Route path="*" element={<StartWindow/>}/>
        </Routes>
    );
};

export default AppRouter;