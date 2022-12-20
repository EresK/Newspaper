import React, {useEffect, useState} from 'react';
import PostsList from "../components/PostsList";
import Search from "../components/Search";
import MySelect from "../components/UI/select/MySelect";
import "../styles/StartWindow.css"
import Navbar from "../components/Navbar";
import ListForm from "../components/ListForm";
import axios from "axios";

const StartWindow = () => {
    const [someItem, setSomeItem] = useState([
    ])

    useEffect(() =>{
        fetchPosts()
    }, [])

    async function fetchPosts() {
        const response = await axios.get('https://jsonplaceholder.typicode.com/posts?_limit=5')
        console.log(response)
        setSomeItem(response.data)
    }

    const [filter, setFilter] = useState({sort: '', query: ''})
    const addItem = (newItem) => {
        setSomeItem([...someItem, newItem])
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
            <PostsList posts={someItem}/>

            <div className="authors" style={{marginLeft: 15}}>


            </div>
        </div>
    );
};

export default StartWindow;