import React, {useEffect, useState} from 'react';
import "../styles/PageWithPosts.css"
import Service from "../serverComp/Service";
import {countPages} from "../helpers/pageWork";
import PostsList from "../components/PostsList";
import {usePosts} from "../hooks/usePosts";
import Search from "../components/Search";

const Posts = () => {
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

    async function fetchPosts() {
        const resp = await Service.getFromServerAmount()
        const amount = resp.data.length
        const response = await Service.getFromServer(page, size);
        setSomeItem(response.data)

        setCount(countPages(amount, size))
        console.log(response)
        console.log(amount)
    }


    for (let i = 0; i < count; i++) {
        pages.push(i)
    }
    return (
        <div>
            <Search
                filter={filter}
                setFilter={setFilter}
            />
            <PostsList posts={sortedAndSearchedPosts}/>
            <div className="paginate">
                {pages.map(p =>
                    <span className={page === p ? 'pagBtn currentBtn' : 'pagBtn'} key={p}
                          onClick={() => setPage(p)}>{p + 1}</span>
                )}

            </div>
        </div>
    );
};

export default Posts;