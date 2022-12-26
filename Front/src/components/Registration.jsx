import React from 'react';
import "../styles/Registration.css"
import MyInput from "./UI/input/MyInput";
import MyButton from "./UI/button/MyButton";

const Registration = () => {
    return (
        <div className="signUp-wrapper">
            <h1 style={{color: "teal"}}>Please Sign Up</h1>
            <form>
                <p style={{marginTop: 15}}>Login</p>
                <label>
                    <MyInput type="text" placeholder="Login(email)"/>
                </label>
                <p style={{marginTop: 10}}>Password</p>
                <label>
                    <MyInput type="password" placeholder="Password"/>
                </label>
                <p style={{marginTop: 10}}>Status</p>
                <label>
                    <MyInput type="text" placeholder="Status"/>
                </label>
                <p style={{marginTop: 10}}>Nickname</p>
                <label>
                    <MyInput type="text" placeholder="Nickname"/>
                </label>
                <div>
                    <MyButton type="submit" style={{marginTop: 15}}>Sign Up</MyButton>
                </div>
            </form>
        </div>
    );
};

export default Registration;