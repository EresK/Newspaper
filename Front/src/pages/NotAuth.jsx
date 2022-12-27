import React, {useContext, useEffect, useState} from 'react';
import MyModal from "../components/UI/modal/MyModal";

const NotAuth = () => {
    const [modal, setModal] = useState(false)
    useEffect(() => {
        setModal(true)
    }, [])
    return (
        <div>
            <MyModal visible={modal} setVisible={setModal}>
                Please sign in or sign up
            </MyModal>
        </div>
    );
};

export default NotAuth;