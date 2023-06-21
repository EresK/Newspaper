import React, {useEffect, useRef, useState} from 'react';
import "../styles/Registration.css"
import axios from "axios";
import {toast, Toaster} from "react-hot-toast";

// const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
// const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const Registration = () => {
    const userRef = useRef();
    const errRef = useRef();

    const [user, setUser] = useState('');
    const [validName, setValidName] = useState(false);
    const [userFocus, setUserFocus] = useState(false);

    const [userFName, setUserFName] = useState('');
    const [userSName, setUserSName] = useState('');

    const [pwd, setPwd] = useState('');
    const [validPwd, setValidPwd] = useState(false);
    const [pwdFocus, setPwdFocus] = useState(false);

    const [matchPwd, setMatchPwd] = useState('');
    const [validMatch, setValidMatch] = useState(false);
    const [matchFocus, setMatchFocus] = useState(false);

    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    const [buttonState, setButtonState] = useState(false);

    useEffect(() => {
        userRef.current.focus();
        toast.dismiss();
    }, [])

    // useEffect(() => {
    //     setValidName(USER_REGEX.test(user));
    // }, [user])
    //
    // useEffect(() => {
    //     setValidPwd(PWD_REGEX.test(pwd));
    //     setValidMatch(pwd === matchPwd);
    // }, [pwd, matchPwd])
    //
    // useEffect(() => {
    //     setErrMsg('');
    // }, [user, pwd, matchPwd])

    const handleSubmit = async (e) => {
        e.preventDefault();
        setButtonState(true)

        const response = await axios.post("http://localhost:8080/registration",
            JSON.stringify({email: user, firstName: userFName, lastName: userSName, password: pwd}),
            {
                headers: {'Content-Type': 'application/json'},
                // withCredentials: true
            }
        ).then(response => {
            if (response.status === 200 && response.data === 'SUCCESS') {
                toast.success("You signed up!", {duration: 2000});
            } else {
                toast.error("Bad credentials", {duration: 3000});
            }
        });

        setTimeout(() => setButtonState(false), 3000);

        // console.log(response.data);
        // console.log(response);
        // console.log(JSON.stringify({email: user, firstName: userFName, lastName: userSName, password: pwd}))
        // console.log(JSON.stringify(response));
    }

    return (
        <div className="signUp-wrapper">
            <h1>Please Sign Up</h1>
            <form onSubmit={handleSubmit}>

                <div className="my-input">
                    <p>Login</p>
                    <label htmlFor="username">
                        <input
                            type="text"
                            placeholder="Login(email)"
                            id="username"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setUser(e.target.value)}
                            // value={user}
                            required
                            onFocus={() => setUserFocus(true)}
                            onBlur={() => setUserFocus(false)}

                        />
                    </label>
                </div>

                <div className="my-input">
                    <p>First name</p>
                    <label htmlFor="name_1">
                        <input
                            type="text"
                            placeholder="First name"
                            id="name_1"
                            autoComplete="off"
                            onChange={(e) => setUserFName(e.target.value)}
                            // value={user}
                            required
                            onFocus={() => setUserFocus(true)}
                            onBlur={() => setUserFocus(false)}
                        />
                    </label>
                </div>


                <div className="my-input">
                    <p>Second name</p>
                    <label htmlFor="name_2">
                        <input
                            type="text"
                            placeholder="Second name"
                            id="name_2"
                            autoComplete="off"
                            onChange={(e) => setUserSName(e.target.value)}
                            // value={user}
                            required
                            onFocus={() => setUserFocus(true)}
                            onBlur={() => setUserFocus(false)}
                        />
                    </label>
                </div>

                <div className="my-input">
                    <p>Password</p>
                    <label htmlFor="password">
                        <input
                            type="password"
                            placeholder="Password"
                            id="password"
                            onChange={(e) => setPwd(e.target.value)}
                            value={pwd}
                            required
                            onFocus={() => setPwdFocus(true)}
                            onBlur={() => setPwdFocus(false)}
                        />
                    </label>
                </div>

                <div className="reg-button">
                    <button disabled={buttonState}>Sign Up</button>
                </div>
            </form>
            <Toaster/>
        </div>
    );
};

export default Registration;