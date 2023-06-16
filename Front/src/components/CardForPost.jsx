import React from 'react';
import "../styles/ListItem.css"
import MyButton from "./UI/button/MyButton";

import {Link} from "react-router-dom"

const CardForPost = (props) => {
    return (
        <Link to={"post/" + props.post.id} state={{ data: props.post }} className="post">
            <div className="post__content">
                <div className="content">
                    <img src={props.post.description.coverImageLink} alt={props.post.description.title} />
                    {props.post.body}
                </div>
                <h2>{props.post.description.title}</h2>
                <div>{props.post.description.description}</div>
            </div>
        </Link>
    );
};



export default CardForPost;