<!-- src/components/semestres/Semestres.vue -->
<script>
import InfoEtudiant from '../etudiants/InfoEtudiant.vue';
import NoteUE from './NoteUE.vue';

// Importez apiClient ici si vous utilisez de vraies données API
// import apiClient from '@/api'; 

export default {
    name: "NoteSemestre",
    props: {
        idSemestre: null,
        idEtudiant:null
    },
    data() {
        return {
            headersTableau: ['etu', 'intitule', 'credits', 'note', 'resultat', 'session'],
            notesUE: []
        }
    },
    methods: {
        //     return [
        //     {
        //         id: 1,
        //         ue: 'UE1',
        //         intitule: 'Introduction à la programmation',
        //         credits: 6,
        //         note: 14.5,
        //         resultat: 'Validé',
        //         session: 'Normale'
        //     },
        //     {
        //         id: 2,
        //         ue: 'UE2',
        //         intitule: 'Bases de données',
        //         credits: 6,
        //         note: 9.2,
        //         resultat: 'Non Validé',
        //         session: 'Normale'
        //     },
        // ];
        // }
        async fetchNotesUE() {
            try {
                const response = await apiClient.get(`/students/${this.idEtudiant}/studyPeriods/${this.idSemestre}/scores`);
                console.log("data trotra ")
                console.log(response.data.data)
                
                return response.data.data;

            } catch (error) {
                console.error("Erreur lors du fetching des moyennes:", error);
                return [];
            }
        }


    },

    async created() {
        this.notesUE = await this.fetchNotesUE();

    },
    components: {
        InfoEtudiant,
        NoteUE
    }
}
</script>

<template>
    <div class="semestres-container">
        <table border="1">
            <th v-for="item in this.headersTableau">
                {{ item }}
            </th>
            <tr v-for="(item, index) in this.notesUE" :key="item.id">
                <NoteUE :note-u-e="item">
                </NoteUE>
            </tr>
        </table>

    </div>
</template>
