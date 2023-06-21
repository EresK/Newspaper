import React, {useEffect, useState} from 'react';
import MyInput from "../components/UI/input/MyInput";
import "../styles/CreatingPostPage.css"
import MyButton from "../components/UI/button/MyButton";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import {toast, Toaster} from "react-hot-toast";

const CreatingPostPage = () => {
    const router = useNavigate()

    const [title, setTitle] = useState('');
    const [author, setAuthor] = useState('');
    const [description, setDescription] = useState('');
    const [issueNumber, setIssueNumber] = useState('');
    const [imageLink, setImageLink] = useState('');
    const [buttonState, setButtonState] = useState(false);

    useEffect(() => {
        toast.dismiss();
    }, []);

    const createPublication = async () => {
        setButtonState(true);

        await axios.post("http://localhost:8080/publications",
            JSON.stringify({
                description: {
                    title: title,
                    author: author,
                    description: description,
                    issueNumber: isNaN(parseInt(issueNumber)) ? 1 : parseInt(issueNumber),
                    issueDate: new Date().getDate(),
                    coverImageLink: imageLink
                },
                isHide: false
            }),
            {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: localStorage.getItem("auth")
                }
            }
        ).then(response => {
            console.log(response);
            if (response.data === 'SUCCESS') {
                toast.success("Publication created!", {id: "info-toast"});
            } else {
                toast.error("Can not create publication", {duration: 3000, id: "info-toast"});
            }
        }).catch(error => {
            toast.error("Can not create publication", {duration: 3000, id: "info-toast"});
        });

        setTimeout(() => setButtonState(false), 3000);
    }

    return (
        <div className='outer'>
            <div className='inner'>
                <h1>Create new post</h1>
                <div>
                    <p>Title</p>
                    <label>
                        <MyInput type="text" placeholder="Publication title"
                                 onChange={(e) => setTitle(e.target.value)}/>
                    </label>
                    <p>Authors</p>
                    <label>
                        <MyInput type="text" placeholder="Authors"
                                 onChange={(e) => setAuthor(e.target.value)}/>
                    </label>
                    <p>Description</p>
                    <label>
                        <MyInput type="text" placeholder="Description"
                                 onChange={(e) => setDescription(e.target.value)}/>
                    </label>
                    <p>Issue number</p>
                    <label>
                        <MyInput type="text" placeholder="Issue number"
                                 onChange={(e) => setIssueNumber(e.target.value)}/>
                    </label>
                    <p>Add image</p>
                    <label>
                        <MyInput type="text" placeholder="URL"
                                 onChange={(e) => setImageLink(e.target.value)}/>
                    </label>
                    {/*<p>Add image</p>*/}
                    {/*<img src="../resources/placeholder.jpeg" width={300} height={300}/>*/}
                    <MyButton onClick={createPublication} disabled={buttonState} style={{marginTop: 30}}>Save</MyButton>
                </div>
            </div>
            <Toaster/>
        </div>
    );
};

export default CreatingPostPage;