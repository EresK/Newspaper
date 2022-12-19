import React, {useState} from 'react';
import Search from "../components/Search";
import "../styles/StartWindow.css"
import Scrollable from "../components/Scrollable";
import Post from "../components/Post";

const StartWindow = () => {
    const [someItem, setSomeItem] = useState([
        {id: 0, title: 'Post 1', author: 'Author 1', datePublic: "12.12.2012", body: 'Content 1'},
        {id: 1, title: 'Post 2', author: 'Author 2', datePublic: "13.12.2012", body: 'Content 2'},
        {id: 2, title: 'Post 3', author: 'Author 3', datePublic: "14.12.2012", body: 'Content 3'},
        {id: 3, title: 'Post 4', author: 'Author 4', datePublic: "15.12.2012", body: 'Content 4'},
        {id: 4, title: 'Post 5', author: 'Author 5', datePublic: "16.12.2012", body: 'Content 5'},
        {id: 5, title: 'Post 6', author: 'Author 6', datePublic: "17.12.2012", body: 'Content 6'},
        {id: 6, title: 'Post 7', author: 'Author 7', datePublic: "18.12.2012", body: 'Content 7'},
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

            <h1>Most popular publications</h1>
            <div className="container">
                <Scrollable _class='items'>
                    {
                        someItem.map((post) => {
                            return (
                                <div>
                                    <Post number={post.id + 1} post={post} key={post.id}/>
                                </div>
                            )
                        })
                    }
                </Scrollable>
            </div>
        </div>
    );
};

export default StartWindow;