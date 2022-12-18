import React from 'react';
import "../styles/Registration.css"
import MyInput from "./UI/input/MyInput";

const Registration = () => {
    return (
        <div className="signUp-wrapper">
            <h1>Please Sign Up</h1>
            <form>
                <p>Login</p>
                <label>
                    <MyInput type="text" placeholder="Login(email)"/>
                </label>
                <p>Password</p>
                <label>
                    <MyInput type="password" placeholder="Password"/>
                </label>
                <p>Status</p>
                <label>
                    <MyInput type="text" placeholder="Status"/>
                </label>
                <p>Nickname</p>
                <label>
                    <MyInput type="text" placeholder="Nickname"/>
                </label>
                <div>
                    <button type="submit" style={{marginTop: 15}}>Sign Up</button>
                </div>
            </form>
        </div>
    );
};

export default Registration;