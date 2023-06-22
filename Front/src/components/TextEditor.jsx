import React, {useEffect, useState} from "react";
import grapesjs from "grapesjs";
import gjsBlockBasic from "grapesjs-blocks-basic";
import gjsImageEdit from "grapesjs-tui-image-editor";
import "../styles/TextEditor.scss"
import axios from "axios";
import {over} from 'stompjs';
import SockJS from 'sockjs-client';
import {toast, Toaster} from "react-hot-toast";

const TextEditor = (props) => {
    const onError = (err) => {
        console.log(err);
    }

    var stompClient = null;
    const connect = () => {
        let Sock = new SockJS('http://localhost:8080/sok');
        stompClient = over(Sock);
        stompClient.connect({}, onConnected, onError);
    }

    const refresh = async (message) => {
        const result = await axios.get("http://localhost:8080/publications/content", {
            params: {id: props.id_publication},
            headers: {Authorization: localStorage.getItem('auth')}
        })
        editor.setComponents(JSON.parse(result.data.contentJson));
        editor.setStyle(JSON.parse(result.data.styleJson));
    }

    const onConnected = () => {
        stompClient.subscribe(`/editor/public/${props.id_publication}`, refresh);
    }

    connect();
    refresh({});

    const [editor, setEditor] = useState(null);

    const getResult = async () => {
        const components = JSON.stringify(editor.getComponents());
        const styles = JSON.stringify(editor.getStyle());
        const id = props.id_publication
        await axios.put("http://localhost:8080/publications/update", JSON.stringify({
                contentJson: components,
                styleJson: styles
            }),
            {
                params: {id: id},
                headers: {
                    'X-Content-Type-Options': 'nosniff',
                    'Content-Type': 'application/json',
                    Authorization: localStorage.getItem('auth')
                }
            }
        ).then(response => {
            if (response.data === "SUCCESS") {
                toast.success("Saved!", {duration: 2000, id: "info-toast"});
            } else {
                toast.error("Can not save content", {duration: 3000, id: "info-toast"});
            }
        }).catch(() => {
            toast.error("Can not save content", {duration: 3000, id: "info-toast"});
        });

        stompClient.send(`/app/upd/${props.id_publication}`,
            {
                Authorization: localStorage.getItem('auth')
            }
        );
        await refresh({});
    }

    useEffect(() => {
        const editor = grapesjs.init({
            canvasCss: `
              .gjs-dashed *[data-gjs-highlightable] {
                outline: 1.5px dashed black!important;
                outline-offset: -2px!important;
              },
            `,
            container: "#editor",
            blockManager: {
                appendTo: '#blocks',
            },
            storageManager: false,
            removable: false,
            styleManager: {
                appendTo: '#styles-container',
                sectors: [
                    {
                        name: 'First sector',
                        properties: [
                            'width', 'min-width',
                            'height', 'min-height',
                        ],
                    },
                    {
                        name: 'Second sector',
                        properties: [
                            'color', 'font-size', 'text-align',
                        ],
                    },
                    {
                        name: 'Dimension',
                        open: false,
                        buildProps: ['width', 'min-height', 'padding', 'margin'],
                        properties: [
                            {
                                type: 'integer',
                                name: 'The width',
                                property: 'width',
                                units: ['px', '%'],
                                defaults: 'auto',
                                min: 0,
                            },
                        ],
                    },
                ],
            },
            layerManager: {
                appendTo: '#layers-container',
            },
            traitManager: {
                appendTo: '#trait-container',
            },
            selectorManager: {
                appendTo: '#styles-container',
            },
            panels: {
                defaults: [
                    {
                        id: 'basic-actions',
                        el: '.panel__basic-actions',
                        buttons: [
                            {
                                id: 'visibility',
                                active: false, // active by default
                                className: 'btn-toggle-borders',
                                label: '<i class="fa fa-clone"></i>',
                                command: 'sw-visibility', // Built-in command
                            },
                        ],
                    },
                ],
            },
            plugins: [gjsBlockBasic, gjsImageEdit],
            pluginsOpts: {
                gjsBlockBasic: {},
                gjsImageEdit: {},
            },
        });
        toast.dismiss();
        setEditor(editor);
    }, []);

    const status = 1;
    if (status === 1) {
        return (
            <div className="AppEditor">
                <div id='navbar' className='sidenav d-flex flex-column overflow-scroll'>
                    <nav className='navbar-main-2 navbar-light'>
                        <div className='container-fluid'>
                            <span className='navbar-brand mb-0 h3 logo'>NSU Editor</span>
                        </div>
                    </nav>
                    <div className='my-2 d-flex flex-column'></div>
                    <div className=''>
                        <ul className='nav nav-tabs' id='myTab' role='tablist'>
                            <li className='nav-item' role='presentation'>
                                <button
                                    className='nav-link active'
                                    id='block-tab'
                                    data-bs-toggle='tab'
                                    data-bs-target='#block'
                                    type='button'
                                    role='tab'
                                    aria-controls='block'
                                    aria-selected='true'
                                >
                                    <i className='fa fa-cubes'></i>
                                </button>
                            </li>
                            <li className='nav-item' role='presentation'>
                                <button
                                    className='nav-link'
                                    id='layer-tab'
                                    data-bs-toggle='tab'
                                    data-bs-target='#layer'
                                    type='button'
                                    role='tab'
                                    aria-controls='layer'
                                    aria-selected='false'
                                >
                                    <i className='fa fa-tasks'></i>
                                </button>
                            </li>
                            <li className='nav-item' role='presentation'>
                                <button
                                    className='nav-link'
                                    id='style-tab'
                                    data-bs-toggle='tab'
                                    data-bs-target='#style'
                                    type='button'
                                    role='tab'
                                    aria-controls='style'
                                    aria-selected='false'
                                >
                                    <i className='fa fa-paint-brush'></i>
                                </button>
                            </li>
                            <li className='nav-item' role='presentation'>
                                <button
                                    className='nav-link'
                                    id='trait-tab'
                                    data-bs-toggle='tab'
                                    data-bs-target='#trait'
                                    type='button'
                                    role='tab'
                                    aria-controls='trait'
                                    aria-selected='false'
                                >
                                    <i className='fa fa-cog'></i>
                                </button>
                            </li>
                        </ul>
                        <div className='tab-content'>
                            <div
                                className='tab-pane fade show active'
                                id='block'
                                role='tabpanel'
                                aria-labelledby='block-tab'
                            >
                                <div id='blocks'></div>
                            </div>
                            <div className='tab-pane fade' id='layer' role='tabpanel' aria-labelledby='layer-tab'>
                                <div id='layers-container'></div>
                            </div>
                            <div className='tab-pane fade' id='style' role='tabpanel' aria-labelledby='style-tab'>
                                <div id='styles-container'></div>
                            </div>
                            <div className='tab-pane fade' id='trait' role='tabpanel' aria-labelledby='trait-tab'>
                                <div id='trait-container'></div>
                            </div>
                        </div>
                    </div>
                    <button className="buttonSave-2" onClick={getResult}>
                        Save content
                    </button>
                </div>
                <div className='main-content'>
                    <nav className='navbar-main-2 navbar-light'>
                        <div className='container-fluid'>
                            <div className='panel__basic-actions'></div>
                        </div>
                    </nav>
                    <div id='editor'></div>
                </div>
                <Toaster/>
            </div>
        )
    } else if (status === 2) {
        return (
            <div className='main-content-2'>
                <nav className='navbar-main-2 navbar-light'>
                    <div className='container-fluid'>
                        <div className='panel__basic-actions'></div>
                    </div>
                </nav>
                <div id='editor'></div>
                <button className="buttonSave" onClick={getResult}>
                    Save content
                </button>
                <Toaster/>
            </div>
        )
    } else {
        return (
            <div id='editor'></div>
        )
    }
}
export default TextEditor;
