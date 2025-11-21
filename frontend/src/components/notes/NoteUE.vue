<!-- src/components/semestres/Semestres.vue -->
<script>

// Importez apiClient ici si vous utilisez de vraies données API
// import apiClient from '@/api'; 

export default {
    name: "NoteUE",
    props: {
        noteUE: {
            typeof: Object,
            default: null
        },

    },
    data() {
        return {

        }
    },
    methods: {

        // async fetchNoteUE() {
        //     await new Promise(resolve => setTimeout(resolve, 500));

        //     return [
        //         { id: 1, name: 'Semestre 1' },
        //         { id: 2, name: 'Semestre 2' },
        //         { id: 3, name: 'Semestre 3' },
        //         { id: 4, name: 'Semestre 4' }
        //     ];
        // }
    },

    // async created() {
    //     this.semestres = await this.fetchSemestres();
    //     this.etudiants = await this.fetchEtudiants();

    // },
    components: {
    }
}
</script>

<template>
        <td>
            {{ noteUE.code }}
        </td>
        <td>
            {{ noteUE.courseUnitLabel }}
        </td>
        <td>
            {{ noteUE.credits }}
        </td>
        <td>
            {{ noteUE.score }}
        </td>
        <td>
            {{ noteUE.resultat }}
        </td>
        <!-- <td>
            {{ noteUE.session }}
        </td> -->
</template>
