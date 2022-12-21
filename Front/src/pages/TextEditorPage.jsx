import TextEditor from "../components/TextEditor";
import {Link} from "react-router-dom";
import React from "react";
import { useLocation } from "react-router-dom";
import "../styles/TextEditorPage.css"



export const TextEditorPage = () => {
    const location = useLocation();
    const data = location.state?.data;

    return (
        <div className="editor-page">
            <h1>Описание статьи</h1>
            <div className='header-publication'>
                <h3>Название статьи: </h3><span>{data.title}</span>
                <h3>Авторы: </h3><span>{data.author}</span>
                <h3>Дата публикации: </h3><span>{data.issueDate}</span>
                <h3>Содержание: </h3><span>{data.description}</span>
            </div>
            <TextEditor content={data.content}/>
            <Link to={"/"}>
                <button className="buttonStyle">Main page</button>
            </Link>
        </div>
    );
}