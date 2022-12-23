import axios from "axios";

export default class Service {
    static async getPosts(limit = 10, page = 1) {
        const response = await axios.get('http://localhost:8080/publications/all', {
            params: {
                _limit: limit, page: page
            }
        })

        return response;
    }
}