<!-- src/components/semestres/Semestres.vue -->
<script>
// Importez apiClient ici si vous utilisez de vraies données API
// import apiClient from '@/api'; 

export default {
    name: "EtudiantMoyennes",
    props: {
        idEtudiant: null,
        etu: null,
        scores: {
            typeof: Array,
            default: []
        }
    },
    data() {
        return {
        
        }
    },
    methods: {
        // async fetchSemestreMoyennes() {
        //     try {
        //         const response = await apiClient.get('/studyPeriodes/averageScores');
        //         console.log("Données API reçues:", response.data);
        //         return response.data;

        //     } catch (error) {
        //         console.error("Erreur lors du fetching des moyennes:", error);
        //         return [];
        //     }
        // },
        goToReleveDetails(idSemestre) {
            this.$router.push({
                name: 'Releve',
                params: {
                    idEtudiant: this.idEtudiant,
                    idSemestre: idSemestre
                }
            });
        }
    },

    async created() {
        // this.semestresMoyennes = await this.fetchSemestreMoyennes();
    }


}
</script>

<template>
    <td>{{ etu }}</td>
    <td v-for="(item, index) in this.scores" :key="index" @click="goToReleveDetails(index)"
        style="cursor: pointer; color: blue; text-decoration: underline;">
        {{ item }}
    </td>


</template>
