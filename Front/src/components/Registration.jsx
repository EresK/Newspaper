import React, { useState } from "react";
import "../styles/Registration.css"
import MyInput from "./UI/input/MyInput";
import {Link} from "react-router-dom";

function onFileSelected (evt) {
    const fileInput = evt.target;
    const files = Array.from(evt.target.files).map(file => file.name);
    const placeholder = fileInput.getAttribute('data-placeholder') ||  fileInput.placeholder;

    // Store original placeholder
    fileInput.setAttribute('data-placeholder', placeholder);

    // Update current placeholder
    if (files.length === 1) {
        fileInput.placeholder = files[0].replace(/.*[\/\\]/, '');
    }
    else if (files.length > 1) {
        fileInput.placeholder = `${files.length} files`;
    }
    else {
        fileInput.placeholder = placeholder;
    }
}

document.querySelectorAll('input[type="file"]').forEach(el =>
    el.addEventListener('change', onFileSelected)
);

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
                <p>Nickname</p>
                <label>
                    <MyInput type="text" placeholder="Nickname"/>
                </label>
                <p>Status</p>
                <select id="selectStatus">
                    <option value="editor">Editor</option>
                    <option value="advertiser">Advertiser</option>
                </select>
                <div>
                    <Link to="/login">
                        <button className="buttonStyle2">Sign Up</button>
                    </Link>
                </div>
            </form>
        </div>
    );
};

export default Registration;