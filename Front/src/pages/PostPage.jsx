import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import Service from "../serverComp/Service";
import {countPages} from "../helpers/pageWork";

const PostPage = () => {
    const params = useParams()
    useEffect(() =>{
        fetchPost(params.id)
    })

    const [post, setPost] = useState({});

    async function fetchPost(id) {
        const response = await Service.getById(id);
        setPost(response.data)
    }

    return (
        <div>
            <h1>{post.id}. {post.title}</h1>
            <div>{post.body}</div>

        </div>
    );
};

export default PostPage;