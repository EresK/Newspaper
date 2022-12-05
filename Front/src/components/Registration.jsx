import React from 'react';

const Registration = () => {
    return (
        <div className="signUp-wrapper">
            <h1>Please Sign Up</h1>
            <form>
                <label>
                    <p>Login</p>
                    <input type="text" placeholder="Login(email)"/>
                </label>
                <label>
                    <p>Password</p>
                    <input type="password" placeholder="Password"/>
                </label>
                <label>
                    <p>Status</p>
                    <input type="text" placeholder="Status"/>
                </label>
                <label>
                    <p>Nickname</p>
                    <input type="text" placeholder="Nickname"/>
                </label>
                <div>
                    <button type="submit" style={{marginTop: 15}}>Sign Up</button>
                </div>
            </form>
        </div>
    );
};

export default Registration;