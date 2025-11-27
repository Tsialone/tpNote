<!-- src/components/semestres/Semestres.vue -->
<script>
import EtudiantsMoyennes from './EtudiantsMoyennes.vue';

// Importez apiClient ici si vous utilisez de vraies données API
// import apiClient from '@/api'; 

export default {
    name: "InfoEtudiant",
    props: {
        idEtudiant: null
    },
    data() {

        return {
            etudiant: null,
        }
    },
    methods: {
        async fetchEtudiant() {
            await new Promise(resolve => setTimeout(resolve, 500));

            return { id: 1, name: 'ETU01' };
        }

    },

    async created() {
        this.etudiant = await this.fetchEtudiant();
        console.log(this.etudiant)
    },
    components: {
        EtudiantsMoyennes
    }
}
</script>

<template>
    <div class="semestres-container">
        <div v-if="etudiant">
            <p>id: {{ etudiant.id }}</p>
            <p>etu: {{ etudiant.name }}</p>
        </div>
        <div v-else>
            Chargement...
        </div>
    </div>
</template>
