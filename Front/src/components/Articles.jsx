import React, {Component} from 'react';
import Article from "./Article";
import "../styles/Articles.css"

class Articles extends Component {
    render() {
        return (
            <div className="articles">
                {this.props.items.map(el => (
                    <Article key={el.id} item={el}/>
                ))}
            </div>
        );
    }
}

export default Articles;