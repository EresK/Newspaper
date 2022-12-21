import React, {useState, useEffect} from 'react';
import Search from "../components/Search";
import "../styles/StartWindow.css"
import Scrollable from "../components/Scrollable";
import Post from "../components/Post";
import Service from "../serverComp/Service";

const StartWindow = () => {
    const [someItem, setSomeItem] = useState([])

    useEffect(() => {
       fetchPosts()
    }, []);

    async function fetchPosts() {
        const response = await Service.getPosts();
        console.log(response)
        setSomeItem(response.data)
    }


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
                                    <Post number={post.id + 1} post={post.description} key={post.id}/>
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