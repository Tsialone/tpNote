<!-- src/components/semestres/Semestres.vue -->
<script>
import EtudiantMoyennes from './EtudiantMoyennes.vue';

// Importez apiClient ici si vous utilisez de vraies données API
// import apiClient from '@/api'; 

export default {
    name: "EtudiantsMoyennes",
    props: {
        liste: {
            typeof: Array,
            default: []
        }
        // etudiants: {
        //     typeof: Array,
        //     default: []
        // }
    },
    data() {
        return {
            headers: [],
            etudiants: [],
            sesemstres:[]
            // semestreMoyennes:[]
        }
    },
    methods: {
        // async fetchEtudiants() {
        //     await new Promise(resolve => setTimeout(resolve, 500));
        //     return [
        //         { id: 1, name: 'ETU01' },
        //         { id: 2, name: 'ETU02' },
        //         { id: 3, name: 'ETU03' },
        //         { id: 4, name: 'ETU04' }
        //     ];
        // },
        // async fetchSemestreMoyennes() {
        //     await new Promise(resolve => setTimeout(resolve, 500));
        //     return [
        //         { id: 1, name: 'ETU01' },
        //         { id: 2, name: 'ETU02' },
        //         { id: 3, name: 'ETU03' },
        //         { id: 4, name: 'ETU04' }
        //     ];
        // }


    },
    components: {
        EtudiantMoyennes

    },
    async created() {
        // this.etudiants = await this.fetchEtudiants();
        console.log("semestre")

        this.semestres = [
            { id: 1, name: 'Semestre 1' },
            { id: 2, name: 'Semestre 2' },
            { id: 3, name: 'Semestre 3' },
            { id: 4, name: 'Semestre 4' }
        ];
        console.log(this.semestres)
        this.headers = ["etu", ...this.semestres.map(semestre => semestre.name)];
    }
}
</script>

<template>

    <table border="1">
        <th v-for="item in headers">
            {{ item }}
        </th>
        <tr v-for="(item, index) in liste" :key="index">
            <EtudiantMoyennes :scores="item.scores" :etu="item.student.etu" :id-etudiant="item.student.id">
            </EtudiantMoyennes>
        </tr>
    </table>


</template>
