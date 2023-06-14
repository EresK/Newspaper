import axios from "axios";

export default class Service {
    static async getFromServer(page = 1, size = 4) {
        const response = await axios.get('http://localhost:8080/publications/all', {
            params: {
                page: page, size: size
            },
            headers: {
                Authorization: localStorage.getItem("auth")
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