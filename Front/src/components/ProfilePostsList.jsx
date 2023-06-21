import React from 'react';
import ProfilePosts from "./ProfilePosts";

const ProfilePostsList = ({posts, title}) => {
    return (
        <div style={{marginLeft: 15}}>
            <h1>{title}</h1>
            <div className="postList">
                {posts.map((post, index) =>
                    <ProfilePosts number={index + 1} post={post} key={post.id}/>)}
            </div>
        </div>
    );
};

export default ProfilePostsList;