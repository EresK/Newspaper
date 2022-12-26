import React, {useState} from 'react';

const ListForm = ({add}) => {
    const [item, setItem] = useState({title: '', body: ''})

    const addNewItem = (e) =>{
        e.preventDefault()
        const newItem = {
            ...item, id: Date.now()
        }
        add(newItem)
        setItem({title: '', body: ''})
    }

    return (
        <form>
            <input
                value={item.title}
                onChange={e => setItem({...item, title: e.target.value})}
                type="text" placeholder="Title"
            />
            <input
                value={item.body}
                onChange={e => setItem({...item, body: e.target.value})}
                type="text" placeholder="Cont"
            />
            <button onClick={addNewItem} type="submit">
                Add

            </button>

        </form>
    );
};

export default ListForm;