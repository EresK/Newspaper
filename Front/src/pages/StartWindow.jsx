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

const StartWindow = () => {
    const [someItem, setSomeItem] = useState([])
    const [filter, setFilter] = useState({sort: '', query: ''})
    const [count, setCount] = useState(0)
    const [limit, setLimit] = useState(4)
    const [page, setPage] = useState(1)
    const sortedAndSearchedPosts = usePosts(someItem, filter.sort, filter.query);
    let pages = [];


    useEffect(() => {
        fetchPosts()
    }, [page])

    async function fetchPosts() {
        const response = await Service.getFromServer(limit, page);
        setSomeItem(response.data)
        const amount = 8//response.headers['x-total-count']
        setCount(countPages(amount, limit))
        console.log(response)
    }

    for (let i = 0; i < count; i++) {
        pages.push(i + 1)
    }

    return (
        <div>
            <div className="search" style={{marginLeft: 15}}>
                <Search
                    filter={filter}
                    setFilter={setFilter}
                />
            </div>

            <h1 style={{marginLeft: 15}}>The most popular posts</h1>
            <PostsList posts={sortedAndSearchedPosts}/>
            <div>
                <button onClick={fetchPosts}>jgjg</button>
            </div>
            <div className="paginate">
                {pages.map(p =>
                    <MyButton key={p} onClick={() => setPage(p)}>{p}</MyButton>
                )}

            </div>

        </div>
    );
};

export default StartWindow;