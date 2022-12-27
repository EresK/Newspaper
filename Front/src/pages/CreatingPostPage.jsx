import React from 'react';
import MyInput from "../components/UI/input/MyInput";
import "../styles/CreatingPostPage.css"
import MyButton from "../components/UI/button/MyButton";

const CreatingPostPage = () => {

    return (
        <div className='outer'>
            <div className='inner'>
                <h1>Create new post</h1>
                <form>
                    <p>Title</p>
                    <label>
                        <MyInput type="text" placeholder="Publication title"/>
                    </label>
                    <p>Authors</p>
                    <label>
                        <MyInput type="text" placeholder="Authors"/>
                    </label>
                    <p>Description</p>
                    <label>
                        <MyInput type="text" placeholder="Description"/>
                    </label>
                    <p>Add image</p>
                    <label>
                        <MyInput type="text" placeholder="URL"/>
                    </label>
                    {/*<p>Add image</p>*/}
                    {/*<img src="../resources/placeholder.jpeg" width={300} height={300}/>*/}
                    <MyButton style = {{marginTop: 30}}>Save</MyButton>
                </form>
            </div>
        </div>
    );
};

export default CreatingPostPage;