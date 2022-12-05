import React, {useState} from 'react';
import Post from "./components/Post";
import Login from "./components/Login";
import Registration from "./components/Registration";
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import './styles/App.css'
import PostsList from "./components/PostsList";
import ListForm from "./components/ListForm";
import TextEditor from "./components/TextEditor";

// import "./components/*"


function App() {
    const [someItem, setSomeItem] = useState([
        {id: 0, title: 'Post 1', body: 'Content 1'},
        {id: 1, title: 'Post 2', body: 'Content 2'},
        {id: 2, title: 'Post 3', body: 'Content 3'}
    ])

    const addItem = (newItem) => {
        setSomeItem([...someItem, newItem])
    }

    return (
        <div className="App">
            {/*<Login/>*/}
            {/*<Registration/>*/}
            {/*<ListForm add={addItem}/>*/}
            <PostsList posts={someItem} title={"Most popular publications"}/>
            {/*<Post post = {{id: 1, title: 'test title', body: 'Content'}} />*/}
            <div className="textEditorBlock">
                <TextEditor />
            </div>
        </div>
    );
}

export default App;
