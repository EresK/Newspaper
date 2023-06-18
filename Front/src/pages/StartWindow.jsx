import React, {useEffect, useState} from 'react';
import PostsList from "../components/PostsList";
import Search from "../components/Search";
import MySelect from "../components/UI/select/MySelect";
import "../styles/StartWindow.css"
import Navbar from "../components/Navbar";
import ListForm from "../components/ListForm";
import axios from "axios";
import {usePosts} from "../hooks/usePosts";
import Service from "../serverComp/Service";
import {countPages} from "../helpers/pageWork";
import MyButton from "../components/UI/button/MyButton";

import {useActionData} from "react-router";
import {useNavigate} from "react-router-dom";
import navbar from "../components/Navbar";

const StartWindow = () => {
    const [someItem, setSomeItem] = useState([])
    const [filter, setFilter] = useState({sort: '', query: ''})
    const [count, setCount] = useState(0)
    const [size, setSize] = useState(4)
    const [page, setPage] = useState(0)
    const sortedAndSearchedPosts = usePosts(someItem, filter.sort, filter.query);
    let pages = [];


    useEffect(() => {
        fetchPosts()
    }, [page])

    const router = useNavigate();

    useEffect(()=>{

    })
    async function fetchPosts() {
        const response = await Service.getFromServer(page, size);
        setSomeItem(response.data)
        const amount = 7
        setCount(countPages(amount, size))
        // console.log(response.data.length)
    }

    for (let i = 0; i < count; i++) {
        pages.push(i)
    }

    return (
        <div>


            <h1 className="header">The most popular posts</h1>
            <PostsList posts={sortedAndSearchedPosts}/>
            {/*<div>*/}
            {/*    <button onClick={fetchPosts}>jgjg</button>*/}
            {/*</div>*/}
            {/*<div className="paginate">*/}
            {/*    {pages.map(p =>*/}
            {/*        <span className={page === p ? 'pagBtn currentBtn' : 'pagBtn'} key={p} onClick={() => setPage(p)}>{p+1}</span>*/}
            {/*    )}*/}

            {/*</div>*/}

        </div>
    );
};

export default StartWindow;