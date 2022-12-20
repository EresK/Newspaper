import React from 'react';
import "../styles/ListItem.css"

const Post = (props) => {
    return (
        <div className="post">
            <div className="post__content">
                <strong>{props.post.id}. {props.post.title}</strong>
                <div>
                    {props.post.body}
                </div>
            </div>

        </div>

    );
};

export default Post;