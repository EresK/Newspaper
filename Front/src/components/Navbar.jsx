import React, {useContext} from 'react';
import "../styles/Navbar.css"
import {Link} from "react-router-dom";
import {AuthContext} from "../context";

const Navbar = () => {
    const {isAuth, setIsAuth} = useContext(AuthContext);
    return (
        <div className="navbar">
            {/*<div className="links">*/}

            <div className="left" >
                <Link id="home" className="navbtn" to="/">Home</Link>
                <div className="separator"></div>
                <Link className="navbtn" to="/posts">Posts</Link>
            </div>
            <div className="right">
                {isAuth ? (
                    <Link className="navbtn" to="/profile">Profile</Link>
                ) : (
                        <>
                            <Link className="navbtn" to="/login">Sign in</Link>
                            <div className="separator"></div>
                            <Link className="navbtn" to="/register">Sign up</Link>
                        </>


                    )}



                 {/*<Link className="navbtn" to="/profile">Profile</Link>*/}
                 {/*<Link className="navbtn" to="/login">Sign in</Link>*/}
                 {/*<div className="separator"></div>*/}
                 {/*<Link className="navbtn" to="/register">Sign up</Link>*/}
             {/*</div>*/}
            </div>

        </div>
    );
};

export default Navbar;