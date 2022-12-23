import React from 'react';
import "../styles/ListItem.css"
import {Link} from "react-router-dom";

const Post = (props) => {
    return (
        <div className="post">
            <div className="post__content">
                <Link to={props.post.id + "/editor"} state={{data: props.post}}>
                    <div className="content">
                        {props.post.body}
                    </div>
                </Link>
                <strong style={{fontSize: '18px'}}>{props.post.description.title}</strong>
            </div>
            
        </div>
    );
};

export default Post;