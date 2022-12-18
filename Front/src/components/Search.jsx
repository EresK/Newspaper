import React, {useState} from 'react';
import MyInput from "./UI/input/MyInput";
import MySelect from "./UI/select/MySelect";

const Search = ({filter, setFilter}) => {
    return (
        <div>
            <MyInput
                value={filter.query}
                onChange={e => setFilter({...filter, query: e.target.value})}
                placeholder="Search..."
            />
            {/*<MySelect*/}
            {/*    value={filter.sort}*/}
            {/*    onChange={selectedSort => setFilter({...filter, sort: selectedSort})}*/}
            {/*    defaultValue="Сортировка"*/}
            {/*    options={[*/}
            {/*        {value: 'title', name: 'По названию'},*/}
            {/*        {value: 'body', name: 'По описанию'},*/}
            {/*    ]}*/}
            {/*/>*/}
        </div>
    );
};

export default Search;