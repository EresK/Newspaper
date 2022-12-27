import React from 'react';
import "../styles/ListItem.css"
import MyButton from "./UI/button/MyButton";

import {Link} from "react-router-dom"

const Post = (props) => {


    return (
        <div className="post">
            <div className="post__content">
                <strong style={{fontSize: '18px'}}>{props.post.description.title}</strong>
                <Link to={"post/" + props.post.id} state={{data: props.post}}>
                    <div className="content">
                        {props.post.body}
                    </div>
                </Link>
                <strong style={{fontSize: '18px'}}>{props.post.description.description}</strong>
            </div>
        </div>

    );
};
export default Post;