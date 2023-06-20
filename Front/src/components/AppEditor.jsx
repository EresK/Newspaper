import TextEditor from "./TextEditor";
function AppEditor(props) {
    return (
        <div className="AppEditor">
            <TextEditor id_publication={props.id}/>
        </div>
    );
}

export default AppEditor;
