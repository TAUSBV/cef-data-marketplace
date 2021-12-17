<template>
  <header class="bg-dark-grey-blue py-4 xs:p-2 z-50 sticky top-0">
    <Notification />
    <nav class="flex xl:px-40 lg:px-10 w-full md:px-3 sm:px-4 xs:px-2 mx-auto items-center justify-between flex-wrap">
      <div class="flex items-center flex-shrink-0">
        <router-link to="/" class=" lg:block hidden no-underline">
          <img
            class="lg:w-32 md:w-20 xl:w-36"
            alt="TAUS Data Marketplace"
            src="/assets/img/data-marketplace-logo-1.png"
            srcset="/assets/img/data-marketplace-logo-1@2x.png 2x, /assets/img/data-marketplace-logo-1@3x.png 3x"
          />
        </router-link>
        <router-link
          to="/"
          class="block visible lg:hidden xl:hidden no-underline md:ml-4 md:mr-2"
          @click.native="showMenu ? (showMenu = !showMenu) : (showMenu = showMenu)"
        >
          <img alt="TAUS Data Marketplace" src="/assets/img/cloud_logo@2x.png" class="w-9" />
        </router-link>
      </div>
      <div class="block md:hidden">
        <button
          class="flex items-center px-3 py-2 border rounded text-gray-100 border-gray-100"
          @click="showMenu = !showMenu"
        >
          <svg class="fill-current h-4 w-4" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
            <title>Menu</title>
            <path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z" />
          </svg>
          <span
            v-if="shoppingCartProducts > 0 && !showMenu"
            class="active:hidden absolute bottom-2 rounded-full h-5 w-5 inline-flex items-center justify-center text-xs bg-blue-700 text-gray-100 font-medium"
            style="right: 0.5rem;"
          >
            {{ shoppingCartProducts }}
          </span>
        </button>
      </div>
      <div
        class="w-full block justify-center flex-grow md:flex md:items-center md:w-auto"
        v-if="!isMobile || (isMobile && showMenu)"
      >
        <div class="text-sm md:flex">
          <router-link
            to="/publications"
            class="block mt-4 md:inline-block md:mt-0 xl:text-lg lg:text-lg md:text-sm text-gray-100 mr-8"
            @click.native="showMenu = !showMenu"
          >
            Sell
          </router-link>
          <router-link
            to="/search"
            class="block mt-4 md:inline-block md:mt-0 xl:text-lg lg:text-lg md:text-sm text-gray-100 mr-8"
            @click.native="showMenu = !showMenu"
          >
            Search
          </router-link>
          <router-link
            to="/sellers"
            class="block mt-4 md:inline-block md:mt-0 xl:text-lg lg:text-lg md:text-sm text-gray-100 mr-8"
            @click.native="showMenu = !showMenu"
          >
            Sellers
          </router-link>
          <router-link
            to="/how-it-works"
            class="block mt-4 md:inline-block md:mt-0 xl:text-lg lg:text-lg  md:text-sm text-gray-100 mr-8"
            @click.native="showMenu = !showMenu"
          >
            How It Works
          </router-link>
          <router-link
            to="/price-index"
            class="block mt-4 md:inline-block md:mt-0 xl:text-lg lg:text-lg md:text-sm text-gray-100 mr-8"
            @click.native="showMenu = !showMenu"
          >
            Price Index
          </router-link>
          <router-link
            to="/about"
            class="block mt-4 md:inline-block md:mt-0 xl:text-lg lg:text-lg md:text-sm text-gray-100 mr-8"
            @click.native="showMenu = !showMenu"
          >
            About
          </router-link>
        </div>
      </div>
      <div v-if="!isMobile || (isMobile && showMenu)">
        <Account @click.native="showMenu = !showMenu" />
        <ShoppingCart @click.native="showMenu = !showMenu" />
      </div>
    </nav>
  </header>
</template>

<script lang="ts">
import Vue from "vue";
import Account from "@/components/Account.vue";
import ShoppingCart from "@/components/shopping-cart/ShoppingCart.vue";
import Notification from "@/components/Notification.vue";

export default Vue.extend({
  name: "Header",
  data() {
    return {
      isMobile: window.innerWidth <= 768,
      showMenu: false
    };
  },
  created() {
    addEventListener("resize", () => {
      this.isMobile = innerWidth < 768;
    });
  },
  computed: {
    shoppingCartProducts() {
      const shoppingCart = this.$store.getters["shoppingCart/shoppingCart"];
      return shoppingCart.items && shoppingCart.items.length;
    }
  },
  components: {
    Account,
    Notification,
    ShoppingCart
  }
});
</script>

<style lang="scss">
nav .router-link-active:not(.no-underline) {
  border-bottom: 1px solid;
  @apply text-gray-100;
}
</style>
