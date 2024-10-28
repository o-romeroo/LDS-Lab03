
import axios from 'axios';
import environments from '../enviroment/enviroments.js';

const apiClient = axios.create({
    baseURL: environments.prod.API_URL,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
    }
});

export default {
    
}
