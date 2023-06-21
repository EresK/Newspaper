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

    static async getFromServerAmount() {
        const response = await axios.get('http://localhost:8080/publications/all', {
            headers: {
                Authorization: localStorage.getItem("auth")
            }
        })
        return response;
    }

    static async getPostsByUser() {
        const response = await axios.get('http://localhost:8080/publications', {
            headers: {
                Authorization: localStorage.getItem("auth")
            }
        })
        return response;
    }

    static async getUser(id) {
        const response = await axios.get('http://localhost:8080/users/', {
            params: {
                id: id
            }
        })
    }

    static async getById(id) {
        const response = await axios.get('http://localhost:8080/publications/', {
            params: {
                id: id
            }
        })
        return response;
    }

    static async getCommentsByPostId(id) {
        const response = await axios.get(`https://jsonplaceholder.typicode.com/posts/${id}/comments`)
        return response;
    }
}