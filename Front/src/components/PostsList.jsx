import React, {useState} from 'react';
import Post from "./Post";

const PostsList = ({posts, title}) => {

    return (
        <div>
            <h1 style={{textAlign: 'left'}}>{title}</h1>
            <div className="postList">
                {posts.map((post, index) =>
                    <Post number={index + 1} post={post} key={post.id}/>)}
            </div>
        </div>
    );
};

export default PostsList;