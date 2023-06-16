import React, {useContext, useEffect, useRef, useState} from 'react';
import "../styles/Login.css"
import MyInput from "./UI/input/MyInput";
import MyButton from "./UI/button/MyButton";
import axios from "axios";
import {AuthContext} from "../context";
import {Buffer} from 'buffer';

const Login = () => {
    const userRef = useRef();
    const errRef = useRef();
    const {isAuth, setIsAuth} = useContext(AuthContext);
    const [user, setUser] = useState('');
    const [pwd, setPwd] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('');
    }, [user, pwd])

    const handleSubmit = async (e) => {
        e.preventDefault();

        const encodedBase64Token = Buffer.from(`${user}:${pwd}`).toString('base64');
        const authorization = `Basic ${encodedBase64Token}`;

        await axios.post("http://localhost:8080/users/login", JSON.stringify({email: user, password: pwd}),
            {
                headers: {"Content-Type": "application/json"}
            }
        ).then(response => {
            if (response.status === 200 && response.data === true) {
                localStorage.setItem("auth", authorization)
                console.log(response)
            }
        });
    }

    return (
        <div className="login-wrapper">
            <h1>Please Log In</h1>
            <form onSubmit={handleSubmit}>
                <div className="my-input">
                    <p>Login</p>
                    <label htmlFor="username">
                        <input type="text" placeholder="Login" id="username" ref={userRef}
                               onChange={(e) => setUser(e.target.value)} value={user} required/>
                    </label>
                </div>

                <div className="my-input">
                    <p>Password</p>
                    <label htmlFor="password">
                        <input type="password" placeholder="Password" id="password"
                               onChange={(e) => setPwd(e.target.value)} value={pwd} required/>
                    </label>
                </div>

                <div className="login-button">
                    <button className="login-button">Log in</button>
                </div>
            </form>
        </div>
    );
};

export default Login;