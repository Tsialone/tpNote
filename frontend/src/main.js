// src/main.js

import { createApp } from 'vue';
import App from './App.vue';
import '@fortawesome/fontawesome-free/css/all.css';

import router from './router/index.js'; 

const app = createApp(App);

app.use(router); 

// 3. Montez l'application
app.mount('#app');
