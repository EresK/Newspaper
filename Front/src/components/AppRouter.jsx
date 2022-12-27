import React, {useContext} from 'react';
import {Navigate, Route, Routes} from "react-router-dom";
import StartWindow from "../pages/StartWindow";
import LoginPage from "../pages/LoginPage";
import RegistrationPage from "../pages/RegistrationPage";
import ListForm from "./ListForm";
import Post from "./Post";
import PostPage from "../pages/PostPage";
import {TextEditorPage} from "../pages/TextEditorPage";
import ProfilePage from "../pages/ProfilePage";
import {AuthContext} from "../context";
import MyModal from "./UI/modal/MyModal";
import NotAuth from "../pages/NotAuth";
import CreatingPostPage from "../pages/CreatingPostPage";

const AppRouter = () => {
    // const isAuth = true;
    const {isAuth, setIsAuth} = useContext(AuthContext);
    console.log(isAuth)
    return (
        <Routes>
            {/*<Route path="/" element={<StartWindow/>}/>*/}
            <Route path="/profile" element={<ProfilePage/>}/>
            <Route path="/login" element={isAuth ? <Navigate to="/"/> : <LoginPage/>}/>
            <Route path="/register" element={isAuth ? <Navigate to="/"/> : <RegistrationPage/>}/>
            <Route path="/addPost" element={<CreatingPostPage/>}/>
            <Route path="/post/:id" element={<TextEditorPage/>}/>
            {/*<Route path="/home/:id" element={<PostPage/>}/>*/}
            <Route path="*" element={<StartWindow/>}/>
        </Routes>

        // {/*// isAuth*/}
        // {/*//     ?*/}
        // {/*//     <Routes>*/}
        // {/*//         /!*<Route path="/" element={<StartWindow/>}/>*!/*/}
        // {/*//         <Route path="/profile" element={<ProfilePage/>}/>*/}
        // {/*//         <Route path="/login" element={<LoginPage/>}/>*/}
        // {/*//         <Route path="/register" element={<RegistrationPage/>}/>*/}
        // {/*//         <Route path="/addPost" element={<TmpCreatePost/>}/>*/}
        // {/*//         <Route path="/post/:id" element={<TextEditorPage/>}/>*/}
        // {/*//         /!*<Route path="/home/:id" element={<PostPage/>}/>*!/*/}
        // {/*//         <Route path="*" element={<StartWindow/>}/>*/}
        // {/*//*/}
        // {/*//     </Routes>*/}
        // {/*//     :*/}
        // {/*//     <Routes>*/}
        // {/*//         /!*<Route path="/" element={<StartWindow/>}/>*!/*/}
        // {/*//         <Route path="/profile" element={<ProfilePage/>}/>*/}
        // {/*//         <Route path="/login" element={<LoginPage/>}/>*/}
        // {/*//         <Route path="/register" element={<RegistrationPage/>}/>*/}
        // {/*//         <Route path="/addPost" element={<TmpCreatePost/>}/>*/}
        // {/*//         <Route path="/post/:id" element={<TextEditorPage/>}/>*/}
        // {/*//         /!*<Route path="/home/:id" element={<PostPage/>}/>*!/*/}
        // {/*//         <Route path="*" element={<StartWindow/>}/>*/}
        // {/*//     </Routes>*/}

    );
};

export default AppRouter;