import React, {useContext} from 'react';
import {Navigate, Route, Routes} from "react-router-dom";
import StartWindow from "../pages/StartWindow";
import LoginPage from "../pages/LoginPage";
import RegistrationPage from "../pages/RegistrationPage";
import {TextEditorPage} from "../pages/TextEditorPage";
import ProfilePage from "../pages/ProfilePage";
import {AuthContext} from "../context";
import CreatingPostPage from "../pages/CreatingPostPage";
import Posts from "../pages/Posts";

const AppRouter = () => {
    // const {isAuth, isLoading} = useContext(AuthContext);
    const {isAuth, setIsAuth} = useContext(AuthContext);
    console.log(isAuth)
    return (
        <Routes>
            {/*<Route path="/" element={<StartWindow/>}/>*/}
            <Route path="/profile" element={isAuth ? <ProfilePage/> : <LoginPage/>}/>
            <Route path="/login" element={isAuth ? <Navigate to="/"/> : <LoginPage/>}/>
            <Route path="/register" element={isAuth ? <Navigate to="/"/> : <RegistrationPage/>}/>
            <Route path="/posts" element={isAuth ? <Posts/> : <LoginPage/>}/>
            <Route path="/post/:id" element={isAuth ? <TextEditorPage/> : <LoginPage/>}/>
            <Route path="/post/create" element={isAuth ? <CreatingPostPage/> : <LoginPage/>}/>
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