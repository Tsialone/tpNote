<!-- Login.vue -->
<script>
import apiClient from '@/api';

export default {
    name: "Login",
    data() {
        return {
            name: "admin",
            password: "admin"
        }
    },
    methods: {
        async login() {
            console.log("Tentative de connexion...");
            try {
                const response = await apiClient.post('/login', {
                    username: this.name,
                    password: this.password
                });

                console.log("Connexion réussie:", response.data);

                if (response.data && response.data.token) {
                    localStorage.setItem('token', response.data.token);
                    // alert("Connexion réussie ! Token enregistré.");
                    console.log("Connexion réussie ! Token enregistré.")
                    this.$router.push('/semestres');

                } else {
                    console.log("Connexion réussie, mais aucun token reçu.")
                }

            } catch (error) {
                console.error("Erreur de connexion:", error.response ? error.response.data : error.message);

                let errorMessage = "Une erreur est survenue lors de la connexion.";
                if (error.response && error.response.data && error.response.data.message) {
                    errorMessage = error.response.data.message;
                }
                alert(errorMessage);
            }
        }
    },
    // mounted() {
    //     if (localStorage.getItem('token')) {
    //         console.log("Token existant, redirection vers Semestres.");
    //         this.$router.push('/semestres');
    //     }
    // }
}
</script>

<template>
    <div class="login-container">
        <h1>Connexion</h1>
        <form @submit.prevent="login">
            <div class="form-group">
                <label for="name">Nom d'utilisateur :</label>
                <input type="text" id="name" v-model="name" required>
            </div>

            <div class="form-group">
                <label for="password">Mot de passe :</label>
                <input type="password" id="password" v-model="password" required>
            </div>

            <button type="submit">Se connecter</button>
        </form>
    </div>
</template>
