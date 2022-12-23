import React, {useEffect, useState} from 'react';
import PostsList from "../components/PostsList";
import Search from "../components/Search";
import "../styles/StartWindow.css"
import {usePosts} from "../hooks/usePosts";
import Service from "../serverComp/Service";
import {countPages} from "../helpers/pageWork";
import MyButton from "../components/UI/button/MyButton";

const StartWindow = () => {
    const [someItem, setSomeItem] = useState([])
    const [filter, setFilter] = useState({sort: '', query: ''})
    const [count, setCount] = useState(0)
    const [limit, setLimit] = useState(6)
    const [page, setPage] = useState(1)
    const sortedAndSearchedPosts = usePosts(someItem, filter.sort, filter.query);
    let pages = [];


    useEffect(() => {
        fetchPosts()
    }, [page])

    async function fetchPosts() {
        const response = await Service.getPosts(limit, page);
        setSomeItem(response.data)
        console.log(response.data)
        const amount = response.data.length
        setCount(countPages(amount, limit))
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

            <h1 style={{marginLeft: 15}}>All publications</h1>
            <PostsList posts={sortedAndSearchedPosts}/>

            <div className="paginate">
                {pages.map(p =>
                    <MyButton key = {p} onClick={() => setPage(p)}>{p}</MyButton>
                )}
            </div>

        </div>
    );
};

export default StartWindow;