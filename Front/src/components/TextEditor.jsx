import React, {useEffect, useState} from "react";
import grapesjs from "grapesjs";
import gjsBlockBasic from "grapesjs-blocks-basic";
import "../styles/TextEditor.scss"

const TextEditor = (props) => {
    const [editor, setEditor] = useState(null);

    useEffect(() => {
        const editor = grapesjs.init({
            container: "#editor",
            blockManager: {
                appendTo: '#blocks',
            },
            storageManager: false,
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
                        buildProps: ['width', 'min-height', 'padding'],
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
                                active: true, // active by default
                                className: 'btn-toggle-borders',
                                label: '<i class="fa fa-clone"></i>',
                                command: 'sw-visibility', // Built-in command
                            },
                        ],
                    },
                    {
                        id: 'panel-devices',
                        el: '.panel__devices',
                        buttons: [
                            {
                                id: 'device-desktop',
                                label: '<i class="fa fa-television"></i>',
                                command: 'set-device-desktop',
                                active: true,
                                togglable: false,
                            },
                            {
                                id: 'device-mobile',
                                label: '<i class="fa fa-mobile"></i>',
                                command: 'set-device-mobile',
                                togglable: false,
                            },
                        ],
                    },
                ],
            },
            plugins: [gjsBlockBasic],
            pluginsOpts: {
                gjsBlockBasic: {},
            },
        });
        const components = localStorage.getItem(`components_${props.id_publication}`);
        const styles = localStorage.getItem(`styles_${props.id_publication}`);
        if (components == null || styles == null) {
            editor.setComponents(null);
            editor.setStyle(null);
        } else {
            editor.setComponents(JSON.parse(components));
            editor.setStyle(JSON.parse(styles));
        }
        setEditor(editor);
    }, []);

    const getResult = () => {
        localStorage.setItem(`components_${props.id_publication}`, JSON.stringify(editor.getComponents()));
        localStorage.setItem(`styles_${props.id_publication}`, JSON.stringify(editor.getStyle()))
    }

    const status = 2;
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
            </div>
        )
    } else {
        return (
            <div id='editor'></div>
        )
    }
}
export default TextEditor;
