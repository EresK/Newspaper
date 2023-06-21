import React from 'react';
import {Link} from "react-router-dom";
import "../styles/Profile.css"

const ProfilePosts = (props) => {
    return (
        <Link to={"/post/" + props.post.id} state={{data: props.post}} className="post-prof">
            <div className="post__content-prof">
                <div className="content-prof">
                    <img src={props.post.description.coverImageLink} alt={props.post.description.title}/>
                    {props.post.body}
                </div>
                <h2>{props.post.description.title}</h2>
                <div>{props.post.description.description}</div>
            </div>
        </Link>
    );
};

export default ProfilePosts;