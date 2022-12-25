import axios from "axios";

export default class Service {
    static async getFromServer(limit = 4, page = 1) {
        const response = await axios.get('http://localhost:8080/publications/all', {
            params: {
                _limit: limit, _page: page
            }
        })
        return response;
    }

    static async getById(id) {
        const response = await axios.get('http://localhost:8080/publications/all/' + id)
        return response;
    }

    static async getCommentsByPostId(id) {
        const response = await axios.get(`https://jsonplaceholder.typicode.com/posts/${id}/comments`)
        return response;
    }
}