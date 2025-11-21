<!-- src/components/semestres/Semestres.vue -->
<script>
// Importez apiClient ici si vous utilisez de vraies données API
// import apiClient from '@/api'; 

export default {
    name: "Semestres",
    data() {
        return {
            headersTableau: ['libelle'], 
            semestres: [],
        }
    },
    methods: {
        async fetchSemestres() {
            console.log("Fetching semestres...");
            await new Promise(resolve => setTimeout(resolve, 500)); 

            return [
                { id: 1, name: 'S1' },
                { id: 2, name: 'S2' },
                { id: 3, name: 'S3' },
                { id: 4, name: 'S4' }
            ];
        }
    },
    async created() {
        this.semestres = await this.fetchSemestres();
    }
}
</script>

<template>
    <div class="semestres-container">
        <h1>Liste des Semestres</h1>
        <table  border="1" >
            <thead>
                <tr>
                    <th v-for="header in headersTableau" :key="header">{{ header }}</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="semestre in semestres" :key="semestre.id">
                    <td>{{ semestre.name }}</td>
                </tr>
            </tbody>
        </table>

        <a href="/etudiants">Liste des étudiants</a>
    </div>
</template>


