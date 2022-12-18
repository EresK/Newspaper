import React, {useState} from 'react';
import PostsList from "../components/PostsList";
import Search from "../components/Search";
import MySelect from "../components/UI/select/MySelect";
import "../styles/StartWindow.css"
import Navbar from "../components/Navbar";
import ListForm from "../components/ListForm";

const StartWindow = () => {
    const [someItem, setSomeItem] = useState([
        {id: 0, title: 'Post 1', body: 'Content 1'},
        {id: 1, title: 'Post 2', body: 'Content 2'},
        {id: 2, title: 'Post 3', body: 'Content 3'}
    ])

    const [filter, setFilter] = useState({sort: '', query: ''})
    const addItem = (newItem) => {
        setSomeItem([...someItem, newItem])
    }
    return (
        <div>
            <div className="search">
                <Search
                    filter={filter}
                    setFilter={setFilter}
                />
            </div>

            <div className="list">
                <PostsList posts={someItem} title={"Most popular publications"}/>
            </div>
        </div>
    );
};

export default StartWindow;