<script>
import ProductsFilter from "./ProductsFilter.vue";
import Rating from "./Rating.vue";
import Product from "./Product.vue";

export default {
  components: {
    ProductsFilter,
    Rating,
    Product
  },
  data() {
    return {
      products: [
        {'id': 1, 'rating': 1.5, 'name': 'Produit A', 'unit_price': 10, 'quantity': 0, 'description': '<b>genial</b>'  , 'note':2},
        {'id': 2, 'rating': 2, 'name': 'Produit B', 'unit_price': 140, 'quantity': 4, 'description': '<b style="color: green;">On valide</b>', 'note':2},
        {'id': 3, 'rating': 3, 'name': 'Produit C', 'unit_price': 15, 'quantity': 5, 'description': null, 'note':2},
        {'id': 4, 'rating': 4, 'name': 'Produit D', 'unit_price': 12, 'quantity': 8, 'description': 'sans commentaire', 'note':2},
        {'id': 5, 'rating': 5, 'name': 'Produit E', 'unit_price': 4, 'quantity': 5, 'description': '<b>ok</b>', 'note':2},
        {'id': 6, 'rating': 4, 'name': 'Produit F', 'unit_price': 160, 'quantity': 6, 'description': '<b>cool</b>', 'note':2},
        {'id': 7, 'rating': 3, 'name': 'Produit G', 'unit_price': 105, 'quantity': 5, 'description': '<b>bof</b>', 'note':2},
        {'id': 8, 'rating': 2, 'name': 'Produit H', 'unit_price': 50, 'quantity': 8, 'description': '<b>incroyable</b>', 'note':2},
        {'id': 9, 'rating': 2, 'name': 'Produit I', 'unit_price': 40, 'quantity': 5, 'description': '<b>super</b>', 'note':2.5},
        {'id': 10, 'rating': 1, 'name': 'Produit J', 'unit_price': 80, 'quantity': 5, 'description': '<u>génial</u>', 'note':2},
      ],
      productsToShow: []
    }
  },
  created() {
    this.productsToShow = [...this.products];
  },
  computed: {
    productsLength() {
      return this.products.length;
    },
    cheapestPrice() {
      return Math.min(...this.products.map(product => product.unit_price));
    }
  }
}
</script>

<template>
  <div>
    <h1 class="text-xl p-4 text-center uppercase mb-4 bg-sky-300 text-sky-800">
      Nos produits<br>
      Nombre de produits: {{ productsLength }}
    </h1>

    <ProductsFilter :products="products" @change="productsToShow = $event"></ProductsFilter>

    <div class="container mx-auto flex flex-wrap" v-if="products.length > 0">
      <Product v-for="product in productsToShow" :key="product.id" :data="product" :cheapest-price="cheapestPrice"></Product>
    </div>
    <div v-else>
      <p>Il n'y a pas encore de produits !</p>
    </div>
  </div>
</template>

<style scoped>

</style>