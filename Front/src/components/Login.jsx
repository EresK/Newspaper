import React from 'react';
import "../styles/Login.css"
import MyInput from "./UI/input/MyInput";
import MyButton from "./UI/button/MyButton";
import {Link} from "react-router-dom";

const Login = () => {
    return (
        <div className="login-wrapper">
            <h1>Please Log In</h1>
            <form>
                <p>Login</p>
                <label>

                    <MyInput type="text" placeholder="Login"/>
                </label>
                <p>Password</p>
                <label>

                    <MyInput type="password" placeholder="Password"/>
                </label>
                <div className="login-button">
                    <Link to="/">
                        <MyButton type="submit" style={{marginTop: 15, marginLeft: 50}}>Log in</MyButton>
                    </Link>
                </div>
            </form>
        </div>
    );
};

export default Login;