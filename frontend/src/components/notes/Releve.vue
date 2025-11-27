<!-- src/components/semestres/Semestres.vue -->
<script>
import InfoEtudiant from '../etudiants/InfoEtudiant.vue';
import NoteSemestre from './NoteSemestre.vue';
// Importez apiClient ici si vous utilisez de vraies données API
// import apiClient from '@/api'; 

export default {
    name: "Releve",
    props: {
        idSemestre : null,
        idEtudiant: null 
    },
    data() {
     
    },
    methods: {
     
    },

    async created() {
       

    },
    components: {
        InfoEtudiant,
        NoteSemestre
    }
}
</script>

<template>
    <div >
        <h1>Relevé</h1>
        <!-- <InfoEtudiant   id-etudiant="idEtudiant"   ></InfoEtudiant> -->
        <NoteSemestre :id-semestre="idSemestre"  :id-etudiant="idEtudiant" >

        </NoteSemestre>

     
    </div>
</template>
