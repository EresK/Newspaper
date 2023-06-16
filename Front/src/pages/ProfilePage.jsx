import React, {useContext} from 'react';
import MyButton from "../components/UI/button/MyButton";
import {AuthContext} from "../context";

const ProfilePage = () => {
    const {isAuth, setIsAuth} = useContext(AuthContext);

    const logout = () =>{
        setIsAuth(false);
        localStorage.removeItem('auth');

    }

    return (
        <div>
            prof
            <MyButton onClick={logout}>Log out</MyButton>
        </div>
    );
};

export default ProfilePage;