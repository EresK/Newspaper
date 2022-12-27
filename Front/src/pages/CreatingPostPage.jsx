import React, {useState} from 'react';
import MyInput from "../components/UI/input/MyInput";
import "../styles/CreatingPostPage.css"
import MyButton from "../components/UI/button/MyButton";
import "../styles/CreatingPostPage.css"

const CreatingPostPage = () => {
    // const [drag, setDrag] = useState(false)
    //
    // function dragStartHandler(e) {
    //     e.preventDefault()
    //     setDrag(true)
    // }
    //
    // function dragLeaveHandler(e) {
    //     e.preventDefault()
    //     setDrag(false)
    // }
    //
    // function onDropHandler(e) {
    //     e.preventDefault()
    //     let files = [...e.dataTransfer.files]
    //     console.log(files[0])
    //
    //     const reader = new FileReader();
    //     reader.addEventListener("load", () => {
    //         console.log(reader.result);
    //     })
    //     reader.readAsDataURL(files[0])
    //     setDrag(false)
    //
    // }

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
                        <MyInput type="text" placeholder="URL image"/>
                    </label>
                    {/*<div>*/}
                    {/*    {drag*/}
                    {/*        ? <div className="drop-area"*/}
                    {/*               onDragStart={e => dragStartHandler(e)}*/}
                    {/*               onDragLeave={e => dragLeaveHandler(e)}*/}
                    {/*               onDragOver={e => dragStartHandler(e)}*/}
                    {/*        onDrop={e => onDropHandler(e)}>Отпустите файлы</div>*/}
                    {/*        : <div onDragStart={e => dragStartHandler(e)}*/}
                    {/*               onDragLeave={e => dragLeaveHandler(e)}*/}
                    {/*               onDragOver={e => dragStartHandler(e)}>Перетащите  файлы</div>*/}
                    {/*    }*/}
                    {/*</div>*/}
                    <MyButton type = "submit" style = {{marginTop: 30}}>Save</MyButton>
                </form>
            </div>
        </div>
    );
};

export default CreatingPostPage;