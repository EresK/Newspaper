import React, {useState} from 'react';
import {EditorState, convertToRaw, convertFromRaw} from "draft-js";
import {Editor} from "react-draft-wysiwyg";
import "react-draft-wysiwyg/dist/react-draft-wysiwyg.css"
import "../styles/TextEditor.css"
import '../App'


// Функция для загрузки изображений с рабочего стола (пока не работает)
// function uploadImageCallBack(file) {
//     return new Promise(
//         (resolve, reject) => {
//             const xhr = new XMLHttpRequest();
//             xhr.open('POST', 'https://api.imgur.com/3/image');
//             xhr.setRequestHeader('Authorization', 'Client-ID ##clientid###');
//             const data = new FormData();
//             data.append('image', file);
//             xhr.send(data);
//             xhr.addEventListener('load', () => {
//                 const response = JSON.parse(xhr.responseText);
//                 console.log(response)
//                 resolve(response);
//             });
//             xhr.addEventListener('error', () => {
//                 const error = JSON.parse(xhr.responseText);
//                 console.log(error)
//                 reject(error);
//             });
//         }
//     );
// }


const TextEditor = () => {
    const [editorState, setEditorState] = useState(
        () => {
            const content = window.localStorage.getItem('content');

            if (content) {
                return EditorState.createWithContent(convertFromRaw(JSON.parse(content)));
            } else {
                return EditorState.createEmpty();
            }
        }
    );

    const [readOnly, setReadOnly] = useState(1)
    const [toolBarStyle, setToolBarStyle] = useState("toolbarStyle_v2")


    const saveContent = () => {
        setToolBarStyle("toolbarStyle_v2")
        setReadOnly(1)
        window.localStorage.setItem('content', JSON.stringify(convertToRaw(editorState.getCurrentContent())));
    }

    const changeEditorSetting = () => {
        setToolBarStyle("toolbarStyle")
        setReadOnly(0)
    }

    return(
        <div>
            <Editor
                editorClassName="editorStyle"
                toolbarClassName={toolBarStyle}
                wrapperClassName="wrapper-class"
                editorState={editorState}
                onEditorStateChange={setEditorState}
                readOnly={readOnly}
                toolbar={{
                    options: ['inline', 'blockType', 'fontSize', 'fontFamily', 'list', 'textAlign', 'colorPicker', 'emoji', 'image', 'remove', 'history'],
                    inline: {
                        inDropdown: false,
                        className: undefined,
                        component: undefined,
                        dropdownClassName: undefined,
                        options: ['bold', 'italic', 'underline', 'strikethrough'],
                    },
                    blockType: {
                        inDropdown: true,
                        options: ['Normal', 'H1', 'H2', 'H3', 'H4', 'H5', 'H6', 'Blockquote', 'Code'],
                        className: undefined,
                        component: undefined,
                        dropdownClassName: undefined,
                    },
                    fontSize: {
                        options: [8, 9, 10, 11, 12, 14, 16, 18, 24, 30, 36, 48, 60, 72, 96],
                        className: undefined,
                        component: undefined,
                        dropdownClassName: undefined,
                    },
                    fontFamily: {
                        options: ['Arial', 'Georgia', 'Impact', 'Tahoma', 'Times New Roman', 'Verdana'],
                        className: undefined,
                        component: undefined,
                        dropdownClassName: undefined,
                    },
                    textAlign: {
                        inDropdown: false,
                        className: undefined,
                        component: undefined,
                        dropdownClassName: undefined,
                        options: ['left', 'center', 'right', 'justify'],
                    },
                    list: {
                        inDropdown: false,
                        className: undefined,
                        component: undefined,
                        dropdownClassName: undefined,
                        options: ['unordered', 'ordered', 'indent', 'outdent'],
                    },
                    colorPicker: {
                        className: undefined,
                        component: undefined,
                        popupClassName: undefined,
                        colors: ['rgb(97,189,109)', 'rgb(26,188,156)', 'rgb(84,172,210)', 'rgb(44,130,201)',
                            'rgb(147,101,184)', 'rgb(71,85,119)', 'rgb(204,204,204)', 'rgb(65,168,95)', 'rgb(0,168,133)',
                            'rgb(61,142,185)', 'rgb(41,105,176)', 'rgb(85,57,130)', 'rgb(40,50,78)', 'rgb(0,0,0)',
                            'rgb(247,218,100)', 'rgb(251,160,38)', 'rgb(235,107,86)', 'rgb(226,80,65)', 'rgb(163,143,132)',
                            'rgb(239,239,239)', 'rgb(255,255,255)', 'rgb(250,197,28)', 'rgb(243,121,52)', 'rgb(209,72,65)',
                            'rgb(184,49,47)', 'rgb(124,112,107)', 'rgb(209,213,216)'],
                    },
                    image: {
                        className: undefined,
                        component: undefined,
                        popupClassName: undefined,
                        urlEnabled: true,
                        uploadEnabled: true,
                        alignmentEnabled: true,
                        uploadCallback: undefined,
                        display: 'flex',
                        previewImage: true,
                        inputAccept: 'image/gif,image/jpeg,image/jpg,image/png,image/svg',
                        alt: { present: false, mandatory: false },
                        defaultSize: {
                            height: 'auto',
                            width: 'auto',
                        },
                    },
                    remove: { className: undefined, component: undefined },
                    history: {
                        inDropdown: false,
                        className: undefined,
                        component: undefined,
                        dropdownClassName: undefined,
                        options: ['undo', 'redo'],
                    },
                }}
            />
            <button className="buttonStyle" onClick={() => saveContent()}>Save</button>
            <button className="buttonStyle" onClick={() => changeEditorSetting()}>Edit</button>
        </div>
    );
};

export default TextEditor;