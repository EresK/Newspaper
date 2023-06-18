import React, {useContext, useEffect, useState} from 'react';
import MyButton from "../components/UI/button/MyButton";
import {AuthContext} from "../context";
import {usePosts} from "../hooks/usePosts";
import Service from "../serverComp/Service";
import {countPages} from "../helpers/pageWork";
import PostsList from "../components/PostsList";
import "../styles/Profile.css"
import axios from "axios";
import ProfilePostsList from "../components/ProfilePostsList";

const ProfilePage = () => {
    const {isAuth, setIsAuth} = useContext(AuthContext);
    const [someItem, setSomeItem] = useState([])
    const [userInfo, setUserInfo] = useState('')
    const [filter, setFilter] = useState({sort: '', query: ''})
    const [count, setCount] = useState(0)
    // const [size, setSize] = useState(4)
    // const [page, setPage] = useState(0)
    // const sortedAndSearchedPosts = usePosts(someItem, filter.sort, filter.query);
    let pages = [];
    const logout = () => {
        setIsAuth(false);
        localStorage.removeItem('auth');

    }
    useEffect(() => {
        fetchPosts()
    }, []);
    useEffect(() => {
        fetchUserInfo()
    }, []);

    async function fetchPosts() {
        const response = await Service.getPostsByUser();
        setSomeItem(response.data)
        // const amount = 7
        // setCount(countPages(amount, size))
        // console.log(response.data.length)
        console.log(response)
    }

    async function fetchUserInfo() {
        const response = await axios.get('http://localhost:8080/users/me', {
            headers: {
                Authorization: localStorage.getItem("auth")
            }
        })
        setUserInfo(response.data)
        console.log(userInfo)
    }

    // for (let i = 0; i < count; i++) {
    //     pages.push(i)
    // }
    return (
        <div>
            <h1 className="header-prof">Your Profile</h1>
            <div className="user-info-text">Username: {userInfo}</div>
            <button className="logout-button" onClick={logout}>Log out</button>
            <h1 className="header-prof">Your posts</h1>
            <ProfilePostsList posts={someItem}/>
            {/*<div className="paginate">*/}
            {/*    {pages.map(p =>*/}
            {/*        <span className={page === p ? 'pagBtn currentBtn' : 'pagBtn'} key={p} onClick={() => setPage(p)}>{p+1}</span>*/}
            {/*    )}*/}

            {/*</div>*/}
        </div>
    );
};

export default ProfilePage;