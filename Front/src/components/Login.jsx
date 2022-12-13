import React from 'react';
import "../styles/Login.css"

const Login = () => {
    return (
        <div className="login-wrapper">
            <h1>Please Log In</h1>
            <form>
                <label>
                    <p>Login</p>
                    <input type="text" placeholder="Login"/>
                </label>
                <label>
                    <p>Password</p>
                    <input type="password" placeholder="Password"/>
                </label>
                <div className="login-button">
                    <button type="submit" style={{marginTop: 15}}>Log in</button>
                </div>
            </form>
        </div>
    );
};

export default Login;