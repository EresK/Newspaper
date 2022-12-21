import React from 'react';
import "../styles/Navbar.css"
import {Link} from "react-router-dom";

const Navbar = () => {
    return (
        <div className="navbar">
            {/*<div className="links">*/}

            <div className="left" >
                <Link className="navbtn" to="/home">Home</Link>
                <Link className="navbtn" to="/addPost" style={{marginLeft: 10}}>Add new post</Link>
            </div>
            <div className="right">
                <Link className="navbtn" to="/login" style={{marginRight: 10}}>Sign in</Link>
                <Link className="navbtn" to="/register">Sign up</Link>
            </div>
            {/*</div>*/}

        </div>
    );
};

export default Navbar;