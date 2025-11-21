<!-- src/components/semestres/Semestres.vue -->
<script>
import EtudiantsMoyennes from './EtudiantsMoyennes.vue';

// Importez apiClient ici si vous utilisez de vraies données API
import apiClient from '@/api';

export default {
    name: "Etudiants",
    data() {
        return {
            etudiants: [],
            semestres: [],
            fetchedData: []

        }
    },
    methods: {
        async fetchEtudiants() {

            return [
                { id: 1, name: 'ETU01' },
                { id: 2, name: 'ETU02' },
                { id: 3, name: 'ETU03' },
                { id: 4, name: 'ETU04' }
            ];
        },
        async fetchSemestres() {
            await new Promise(resolve => setTimeout(resolve, 500));

            return [
                { id: 1, name: 'Semestre 1' },
                { id: 2, name: 'Semestre 2' },
                { id: 3, name: 'Semestre 3' },
                { id: 4, name: 'Semestre 4' }
            ];
        },
        async fetchData() {
            try {
                const response = await apiClient.get('/studyPeriods/averageScores');
                // console.log("Données API reçues:", response.data);
                // console.log (response.data.data)
                console.log("data trotra ")
                console.log(response.data.data.liste)

                return response.data.data;

            } catch (error) {
                console.error("Erreur lors du fetching des moyennes:", error);
                return [];
            }
        }
    },

    async created() {
        this.etudiants = await this.fetchEtudiants();
        this.semestres = await this.fetchSemestres();
        this.fetchedData = await this.fetchData();

    },
    components: {
        EtudiantsMoyennes
    }
}
</script>

<template>
    <div class="semestres-container">
        <h1>Liste des etudiants</h1>
        <EtudiantsMoyennes :etudiants="etudiants" :id :liste="fetchedData.liste">

        </EtudiantsMoyennes>
    </div>
</template>
