
import axios from 'axios';


const apiClient = axios.create({
    baseURL: 'http://localhost:8080/aluno',
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
    }
});

export default {
    getAlunos() {
        return apiClient.get('/todos');
    },
}
