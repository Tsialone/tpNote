<script>
export default {
  name: "ProductsFilter",
  emits: ['change'],
  props: {
    products: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      inStock: true,
      orderBy: null,
      search: ''
    }
  },
  methods: {
    filterProductsByName(products) {
      let searchLower = this.search.toLowerCase();
      return products.filter(product => product.name.toLowerCase().includes(searchLower));
    },
    orderProductsByName(is_up) {
      let products = [...this.productsFiltered];
      return products.sort((a, b) => {
        const nameA = a.name.toUpperCase();
        const nameB = b.name.toUpperCase();
        if (nameA < nameB) {
          return is_up ? -1 : 1;
        }
        if (nameA > nameB) {
          return is_up ? 1 : -1;
        }
        return 0;
      });
    },
    orderProductsByPrice(is_up) {
      let products = [...this.productsFiltered];
      return products.sort((a, b) => {
        const priceA = a.unit_price;
        const priceB = b.unit_price;
        if (priceA < priceB) {
          return is_up ? -1 : 1;
        }
        if (priceA > priceB) {
          return is_up ? 1 : -1;
        }
        return 0;
      });
    },
    manageChange() {
        alert("dd")
      this.$emit('change', this.productsFilteredAndOrdered);
    }
  },
  computed: {
    productsFiltered() {
      let products = [...this.products];
      // 1 - On filtre par rapport au stock
      let result = null;
      result = this.inStock ? products.filter(product => product.quantity > 0) : this.products;

      // 2 - Et on filtre aussi par le champ de recherche
      result = this.filterProductsByName(result);

      return result;
    },
    productsFilteredAndOrdered() {
      let products = [...this.productsFiltered];

      switch (this.orderBy) {
        case 'nameUp':
          products = this.orderProductsByName(true)
          break;
        case 'nameDown':
          products = this.orderProductsByName(false)
          break;
        case 'priceUp':
          products = this.orderProductsByPrice(true)
          break;
        case 'priceDown':
          products = this.orderProductsByPrice(false)
          break;
      }

      return products;
    }
  }
}
</script>

<template>
  <aside>
    <h3 class="font-bold text-sky-500 text-xl text-center uppercase">Filtres</h3>
    <form class="p-4 m-2 bg-sky-300">
      <div class="flex justify-center">
        <div class="text-center">
          <label for="search" class="mr-3 text-center font-bold mb-2 text-sky-900">Recherche par nom</label><br>
          <input id="search" type="text" v-model="search" @change="manageChange" class="text-sky-700 p-1"/>
        </div>
      </div>
      <div class="flex flex-wrap justify-evenly items-center">
        <div>
          <h4 class="text-center font-bold mb-2 text-sky-900">En stock ?</h4>
          <div class="flex">
            <div class="mx-1">
              <input type="radio" v-model="inStock" @change="manageChange" name="in_stock" id="inStockFalse" :value="false"/>
              <label for="inStockFalse" class="ml-1">Non</label>
            </div>
            <div class="mx-1">
              <input type="radio" v-model="inStock" @change="manageChange" name="in_stock" id="inStockTrue" :value="true"/>
              <label for="inStockFalse" class="ml-1">Oui</label>
            </div>
          </div>
        </div>
        <div>
          <h4 class="text-center font-bold mb-2 text-sky-900">Ordonné par</h4>
          <select v-model="orderBy" @change="manageChange" class="p-1">
            <option value="nameDown">Nom décroissant</option>
            <option value="nameUp">Nom croissant</option>
            <option value="priceDown">Prix décroissant</option>
            <option value="priceUp">Prix croissant</option>
          </select>
        </div>
      </div>
    </form>
  </aside>
</template>

<style scoped>

</style>