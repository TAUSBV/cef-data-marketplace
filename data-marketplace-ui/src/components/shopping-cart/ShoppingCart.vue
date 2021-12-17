<template>
  <div class="inline">
    <router-link
      to="/shopping-cart"
      class="relative inline-block text-sm py-2 px-4 leading-none text-gray-100 mt-4 md:mt-0"
    >
      <img class="md:w-4 lg:w-6" src="/assets/img/shopping-cart-outline.svg" alt="shopping-icon" />
      <span
        v-if="shoppingCartProducts > 0"
        class="absolute bottom-0 rounded-full h-5 w-5 inline-flex items-center justify-center text-xs bg-blue-700 text-gray-100 font-medium"
        style="right: 0.5rem;"
      >
        {{ shoppingCartProducts }}
      </span>
    </router-link>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "ShoppingCart",
  computed: {
    shoppingCartProducts() {
      const shoppingCart = this.$store.getters["shoppingCart/shoppingCart"];
      return shoppingCart.items && shoppingCart.items.length;
    }
  },
  mounted() {
    this.$store.dispatch("shoppingCart/list");
  }
});
</script>

<style lang="scss"></style>
