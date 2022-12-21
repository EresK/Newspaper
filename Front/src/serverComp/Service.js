import axios from "axios";

export default class Service {
    static async getPosts() {
        const response = await axios.get('http://localhost:8080/publications/all')
        return response;
    }
}