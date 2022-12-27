import React, {useState} from 'react';
import Post from "./components/Post";
import Login from "./components/Login";
import Registration from "./components/Registration";
import {BrowserRouter, Link, Route, Router, Routes} from 'react-router-dom';
import './styles/App.css'
import PostsList from "./components/PostsList";
import ListForm from "./components/ListForm";
import TextEditor from "./components/TextEditor";
import "./styles/TextEditor.css"
import Articles from "./components/Articles";
import "./styles/App.css"
import StartWindow from "./pages/StartWindow";
import Search from "./components/Search";
import LoginPage from "./pages/LoginPage";
import RegistrationPage from "./pages/RegistrationPage";
import Navbar from "./components/Navbar";
import AppRouter from "./components/AppRouter";
import {AuthContext} from "./context";

// import "./components/*"


function App() {
    const [isAuth, setIsAuth] = useState(false);

    return (
        <AuthContext.Provider value={{isAuth, setIsAuth}}>
            <Navbar/>
            <AppRouter/>
        </AuthContext.Provider>
    );

    //
    // constructor(props) {
    //     super(props);
    //     this.state = {
    //         items: [
    //             {
    //                 id: 1,
    //                 title: "Post 1",
    //                 img: "1_a.jpg",
    //                 authors: "Tom",
    //                 description: "New description",
    //             },
    //             {
    //                 id: 2,
    //                 title: "Post 2",
    //                 img: "2_a.jpg",
    //                 authors: "Peter",
    //                 description: "New description",
    //             },
    //             {
    //                 id: 3,
    //                 title: "Post 3",
    //                 img: "3_a.jpg",
    //                 authors: "Anna",
    //                 description: "New description",
    //             }
    //         ]
    //     }
    // }
    // render () {
    //     return (
    //         <div className="app">
    //             <Articles items={this.state.items}/>
    //             <Login/>
    //             <Registration/>
    //             {/*<Registration/>*/}
    //             {/*<ListForm add={addItem}/>*/}
    //             {/*<PostsList posts={someItem} title={"Most popular publications"}/>*/}
    //             {/*<Post post = {{id: 1, title: 'test title', body: 'Content'}} />*/}
    //             <div>
    //                 <div>
    //                     <h1>Описание статьи</h1>
    //                     <h3>Название статьи:</h3>
    //                     <h3>Авторы:</h3>
    //                     <h3>Содержание:</h3>
    //                     <h3>Дата публикации:</h3>
    //                 </div>
    //                 <TextEditor />
    //             </div>
    //         </div>
    //     );
    // }


}

export default App;
