<!-- App.vue -->
<script setup>
// Avec <script setup>, nous n'avons plus besoin des imports des composants de vue spécifiques (Produit, Produits)
// ni de la configuration Font Awesome ici, car tout cela est géré soit par le routeur, soit dans main.js.

// Les imports originaux n'ont plus d'utilité directe dans ce fichier une fois le routeur mis en place :
// import Produit from './components/Product.vue';
// import Produits from './components/Produits.vue';
// ... configuration Font Awesome ...

import Login from './components/auth/Login.vue';
</script>


<template>
  <div id="app-container">

    <main>
      <router-view></router-view>

    </main>
  </div>
</template>
