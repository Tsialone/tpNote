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
                this.$router.push('/semestres');
                const response = await apiClient.post('/login', {
                    username: this.name,
                    password: this.password
                });

                console.log("Connexion réussie:", response.data);
                
                if (response.data && response.data.token) {
                    localStorage.setItem('token', response.data.token);
                    console.log("Connexion réussie ! Token enregistré.")

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


<style  scoped>
    /* Login.vue */
.login-container {
    max-width: 320px;
    margin: 80px auto;
    padding: 20px;
    border-radius: 10px;
    background: #fff;
    box-shadow: 0 2px 10px rgba(0,0,0,0.08);
    font-family: sans-serif;
}

.login-container h1 {
    text-align: center;
    font-size: 1.4rem;
    margin-bottom: 20px;
}

.form-group {
    margin-bottom: 15px;
    display: flex;
    flex-direction: column;
}

label {
    font-size: 0.9rem;
    margin-bottom: 5px;
    color: #444;
}

input {
    padding: 8px 10px;
    border: 1px solid #ccc;
    border-radius: 6px;
    font-size: 0.95rem;
}

button {
    width: 100%;
    padding: 10px;
    background-color: #3f51b5;
    border: none;
    border-radius: 6px;
    color: white;
    font-size: 1rem;
    cursor: pointer;
    margin-top: 10px;
}

button:hover {
    background-color: #32429e;
}

</style>