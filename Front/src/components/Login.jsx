import React, {useContext, useEffect, useRef, useState} from 'react';
import "../styles/Login.css"
import MyInput from "./UI/input/MyInput";
import MyButton from "./UI/button/MyButton";
import axios from "axios";
import {AuthContext} from "../context";

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
        const response = await axios.post("http://localhost:8080/login",
            "username=" + user + "&password=" + pwd,
            {
                headers: {'Content-Type': 'application/json'},
                withCredentials: true
           }

        );
        console.log("username=" + user + "&password=" + pwd)
        // setIsAuth(true)
        console.log(isAuth)

        // setIsAuth(true);
        // localStorage.setItem('auth', 'true')
        // console.log(isAuth)

    }

    return (
        <div className="login-wrapper">
            <h1 style={{color: "teal"}}>Please Log In</h1>
            <form onSubmit={handleSubmit}>
                <p style={{color: "black", marginTop: 15}}>Login</p>
                <label htmlFor="username">
                    <MyInput type="text" placeholder="Login" id="username" ref={userRef} onChange={(e)=>setUser(e.target.value)} required/>
                </label>

                <p style={{color: "black", marginTop: 10}}>Password</p>
                <label htmlFor="password">
                    <MyInput type="password" placeholder="Password" id="password" onChange={(e)=>setPwd(e.target.value)} required/>
                </label>

                <div className="login-button">
                    <MyButton style={{marginTop: 15}}>Log in</MyButton>
                </div>
            </form>
        </div>
    );
};

export default Login;