import React from "react";

const Scrollable = props => {
    return(
        <div className={props._class}>
            {
                React.Children.map(props.children, child => React.Children.only(child))
            }
        </div>
    )
}

export default Scrollable;