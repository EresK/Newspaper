import React, {useState} from 'react';
import CardForPost from "./CardForPost";
// import "../styles/ListItem.css"

const PostsList = ({posts, title}) => {

    return (
        <div style={{marginLeft: 15}}>
            <h1>{title}</h1>
            <div className="postList">
                {posts.map((post, index) =>
                    <CardForPost number={index + 1} post={post} key={post.id}/>)}
            </div>
        </div>
    );
};

export default PostsList;