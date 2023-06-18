import React, {useState} from 'react';
import MyInput from "./UI/input/MyInput";
import MySelect from "./UI/select/MySelect";
import "../styles/Search.css"
const Search = ({filter, setFilter}) => {
    return (
        <div className="wrapper">
            <input type="text" className="search"
                value={filter.query}
                onChange={e => setFilter({...filter, query: e.target.value})}
                placeholder="Search..."
            />
            <MySelect
                value={filter.sort}
                onChange={selectedSort => setFilter({...filter, sort: selectedSort})}
                defaultValue="Sort"
                options={[
                    {value: 'title', name: 'By name'},
                    {value: 'description', name: 'By description'},
                    {value: '', name: 'Reset'}
                ]}
            />
        </div>
    );
};

export default Search;