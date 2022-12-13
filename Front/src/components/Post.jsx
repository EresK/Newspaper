import React from 'react';
import "../styles/ListItem.css"

const Post = (props) => {
    return (
        <div className="post">
            <div className="post__content">

                <div className="content">
                    {props.post.body}
                </div>
                <strong style={{fontSize: '18px'}}>{props.number}. {props.post.title}</strong>
            </div>
            {/*<div>*/}
            {/*    */}
            {/*</div>*/}
            
        </div>
    );
};

export default Post;