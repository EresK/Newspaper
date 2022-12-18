import React, {useState} from 'react';
import ListForm from "../components/ListForm";

const TmpPostCreatePost = () => {
    const [someItem, setSomeItem] = useState([
        {id: 0, title: 'Post 1', body: 'Content 1'},
        {id: 1, title: 'Post 2', body: 'Content 2'},
        {id: 2, title: 'Post 3', body: 'Content 3'}
    ])

    const addItem = (newItem) => {
        setSomeItem([...someItem, newItem])
    }
    return (
        <div>
            <ListForm add={addItem}/>
        </div>
    );
};

export default TmpPostCreatePost;