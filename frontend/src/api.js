// src/services/api.js
import axios from 'axios';

// const API_BASE_URL = 'http://localhost:8080'; 
const API_BASE_URL = 'http://172.22.36.217:8080'; 


const apiClient = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10000, 
  headers: { 'Content-Type': 'application/json' }
});

apiClient.interceptors.request.use((config) => {
  const token = localStorage.getItem('token'); 
  if (token) { config.headers['Auth'] = `Bearer ${token}`; }
  return config;
}, (error) => Promise.reject(error));

export default apiClient;
