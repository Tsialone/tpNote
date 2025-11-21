// src/router/index.js (ou src/router.js)

import { createRouter, createWebHistory } from 'vue-router';
import Login from '@/components/auth/Login.vue';
import Semestres from '@/components/semestres/Semestres.vue';
import Etudiants from '@/components/etudiants/Etudiants.vue';
import Releve from '@/components/notes/Releve.vue';

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/semestres',
    name: 'Semestres',
    component: Semestres
  },
  {
    path: '/etudiants',
    name: 'Etudiants',
    component: Etudiants
  },
  {
    path: '/releves/:idSemestre/:idEtudiant',
    name: 'Releve',
    component: Releve,
    props: true
  }
];

const router = createRouter({
  // Modifiez cette ligne pour utiliser import.meta.env
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router;
