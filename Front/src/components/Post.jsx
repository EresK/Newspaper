import React from 'react';
import "../styles/ListItem.css"
import MyButton from "./UI/button/MyButton";
import {Link, useNavigate} from "react-router-dom"

const Post = (props) => {

    const router = useNavigate();
    return (
        <div className="post">
            <div className="post__content">
                <Link to={"post" + props.post.id} state={{data: props.post}}>
                    <div className="content">
                        {props.post.body}
                    </div>
                </Link>
                <strong style={{fontSize: '18px'}}>{props.post.description.title}</strong>
            </div>

         </div>
        // <div className="post">
        //     <div className="post__content">
        //         <strong>{props.post.id}. {props.post.title}</strong>
        //         <div>
        //             {props.post.body}
        //         </div>
        //     </div>
        //     <MyButton onClick={() => router(`/home/${props.post.id}`)}>Open</MyButton>
        // </div>

    );
};

export default Post;