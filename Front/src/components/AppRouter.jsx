import React from 'react';
import {Route, Routes} from "react-router-dom";
import StartWindow from "../pages/StartWindow";
import LoginPage from "../pages/LoginPage";
import RegistrationPage from "../pages/RegistrationPage";
import ListForm from "./ListForm";
import TmpCreatePost from "../pages/TmpCreatePost";
import Post from "./Post";
import PostPage from "../pages/PostPage";
import {TextEditorPage} from "../pages/TextEditorPage";

const AppRouter = () => {
    return (
        <Routes>
            <Route path="/home" element={<StartWindow/>}/>
            <Route path="/login" element={<LoginPage/>}/>
            <Route path="/register" element={<RegistrationPage/>}/>
            <Route path="/addPost" element={<TmpCreatePost/>}/>
            <Route path="/home/:id" element={<TextEditorPage/>}/>
            {/*<Route path="/home/:id" element={<PostPage/>}/>*/}
            <Route path="*" element={<StartWindow/>}/>
        </Routes>
    );
};

export default AppRouter;