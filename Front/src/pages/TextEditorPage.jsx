import {useLocation} from "react-router-dom";
import React from "react";
import "../styles/TextEditorPage.css"
import "../styles/DraftJsEditor.css"
import Members from "../components/Members/Members";
import TextEditor from "../components/TextEditor";

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
            {/*<TextEditor id_publication={data.id}/>*/}
            <TextEditor/>
            {/*<Link to={"/"}>*/}
            {/*    <button className="buttonStyle">Main page</button>*/}
            {/*</Link>*/}
        </div>
    );
}