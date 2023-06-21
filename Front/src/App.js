import React, {useEffect, useState} from 'react';
import './styles/App.css'
import Navbar from "./components/Navbar";
import AppRouter from "./components/AppRouter";
import {AuthContext} from "./context";

// import "./components/*"

function App() {
    const [isAuth, setIsAuth] = useState(false);

    useEffect(() => {
        if (localStorage.getItem('auth')) {
            setIsAuth(true)
        }
    }, [])


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
    //                 title: "CardForPost 1",
    //                 img: "1_a.jpg",
    //                 authors: "Tom",
    //                 description: "New description",
    //             },
    //             {
    //                 id: 2,
    //                 title: "CardForPost 2",
    //                 img: "2_a.jpg",
    //                 authors: "Peter",
    //                 description: "New description",
    //             },
    //             {
    //                 id: 3,
    //                 title: "CardForPost 3",
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
    //             {/*<CardForPost post = {{id: 1, title: 'test title', body: 'Content'}} />*/}
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
