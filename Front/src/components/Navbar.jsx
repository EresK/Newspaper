import React from 'react';
import "../styles/Navbar.css"
import {Link} from "react-router-dom";
const Navbar = () => {
    return (
        <div className="navbar">
            <div className="links">
                <Link className="navbtn" to="/login">Sign in</Link>
                <Link className="navbtn" to="/register">Sign up</Link>
                <Link className="navbtn" to="/addPost">Add new post</Link>
            </div>

        </div>
    );
};

export default Navbar;