import React, {useState} from 'react';
import ListForm from "../components/ListForm";
import TextEditor from "../components/TextEditor";
import TextEd from "../components/TextEd";
import CKEditor from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import parse from "html-react-parser";

const TmpCreatePost = () => {
    return (
        <div>
            <TextEditor/>
        </div>

    );
};

export default TmpCreatePost;