import React, {Component} from 'react'
import '../styles/Article.css'

class Article extends Component {
    render() {
        return (
            <div className="article">
                <img src={"./img/" + this.props.item.img}/>
                <div className="desc">
                    <h2> {this.props .item.title }</h2>
                    <b>{this.props.item.authors }</b>
                    <p> {this.props.item.description }</p>
                </div>
            </div>
        );
    }
}

export default Article;