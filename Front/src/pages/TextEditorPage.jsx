import {useLocation} from "react-router-dom";
import React from "react";
import "../styles/TextEditorPage.css"
import "grapesjs/dist/css/grapes.min.css";
import "bootstrap";
import Members from "../components/Members/Members";
import AppEditor from "../components/AppEditor";

export const TextEditorPage = () => {
    const location = useLocation();
    const data = location.state?.data;

    return (
        <div className="editor-page">
            <h1>Описание статьи</h1>
            <div className='header-publication'>
                <h3>Название статьи: </h3><span>{data.description.title}</span>
                <h3>Авторы: </h3><span>{data.description.author}</span>
                <h3>Дата публикации: </h3><span>{data.description.issueDate}</span>
                <h3>Содержание: </h3><span>{data.description.description}</span>
            </div>
            <div>
                <Members id_publication={data.id}/>
            </div>

            <AppEditor id={data.id}/>
        </div>
    );
}
