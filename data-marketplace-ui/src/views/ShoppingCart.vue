<template>
  <div class="w-full overflow-x-hidden">
    <div class="mt-8">
      <SectionTitle title="Shopping Cart" />
    </div>
    <div class="container mt-2 pb-32 w-4/5 mx-auto" v-if="!isFetchingData && this.serviceErrors.length === 0">
      <p class="text-lg tracking-0.3 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27 mb-4">
        You have
        <strong v-if="cart.items">
          {{ cart.items.length }}
          <span v-if="cart.items.length === 1">product</span>
          <span v-else>products</span>
        </strong>
        <strong v-else>0 products</strong>
        in your cart
      </p>

      <div class="border-b border-gray-200"></div>

      <div
        class="mt-6 w-full grid grid-cols-7 xs:gap-x-0 sm:gap-x-0 md:gap-x-2 lg:gap-x-4 xl:gap-x-6"
        v-if="cart.items && cart.items.length > 0"
      >
        <!-- LEFT COLUMN -->
        <div class="w-full sclg:col-span-7 lg:col-span-5">
          <div class="w-full grid grid-cols-10 xs:gap-x-4 sm:gap-x-6 lg:gap-x-6 xl:gap-12">
            <h3
              class="text-xl leading-30 tracking-5.0 font-normal xs:text-base xs:leading-relaxed xs:tracking-wide mb-8 xs:col-span-10 sm:col-span-10 md:col-span-5 lg:col-span-4"
            >
              DATASET
            </h3>
            <h3
              class="text-xl leading-30 tracking-5.0 font-normal xs:text-base xs:leading-relaxed xs:tracking-wide mb-8 xs:col-span-5 sm:col-span-5 md:col-span-3 lg:col-span-3"
            >
              QUANTITY
            </h3>
            <h3
              class="text-xl leading-30 tracking-5.0 font-normal xs:text-base xs:leading-relaxed xs:tracking-wide mb-8 xs:col-span-5 sm:col-span-5 md:col-span-2 lg:col-span-3"
            >
              PRICE
            </h3>
          </div>

          <!-- Cart Items -->
          <div v-for="(cartItem, index) in cart.items" :key="`cart-item-${cartItem.uid}`">
            <ShoppingCartItem
              :class="{
                'border-b border-gray-200': index < cart.items.length - 1
              }"
              :cartItem="cartItem"
            />
          </div>
        </div>

        <!-- RIGHT COLUMN -->
        <div class="sclg:col-span-7 lg:col-span-2">
          <!-- TOTAL -->
          <h3 class="text-xl leading-30 tracking-5.0 font-normal xs:text-base xs:leading-relaxed xs:tracking-wide mb-8">
            TOTAL
          </h3>
          <PriceBox v-bind:price="cartPrice" :serviceFee="cart.serviceFee" />
          <div class="bg-gray-200 px-6">
            <Button type="button" class="m-auto w-full mb-6" @click="navigateToCheckout()">
              <span class="text-lg font-light w-full">Proceed</span>
            </Button>

            <div class="pb-8">
              <div class="m-auto" style="width: fit-content;">
                <img src="/assets/img/paypal-icon.svg" alt="Paypal" class="inline h-5 mr-2" style="width: 2.125rem;" />
                <img src="/assets/img/stripe-icon.svg" alt="Stripe" class="inline mr-2 h-5" style="width: 2.125rem;" />
                <img src="/assets/img/bank-icon.svg" alt="Bank" class="inline h-5" style="width: 2.125rem;" />
              </div>
            </div>
          </div>
        </div>

        <div class="xs:col-span-7 sm:col-span-7 md:col-span-7 lg:col-span-5 grid grid-cols-2 items-center w-full mt-24">
          <div>
            <router-link
              to="/sellers"
              class="float-left text-base text-blue-700 font-semibold cursor-pointer leading-none"
            >
              <img
                src="/assets/img/chevron-right-blue.svg"
                alt="Back"
                class="inline w-2 h-4 mr-2 transform rotate-180"
              />
              Continue shopping
            </router-link>
          </div>
        </div>
      </div>
      <div v-else class="mt-4">
        <!-- No items in the cart -->
        <router-link to="/sellers" class="text-base text-blue-700 font-semibold cursor-pointer leading-none">
          Start adding items
        </router-link>
      </div>
    </div>
    <div class="container mt-2 pb-32 w-4/5 mx-auto" v-else-if="serviceErrors.length > 0">
      <Alert :messages="serviceErrors" type="DANGER" />
    </div>
    <div class="container mt-2 pb-32 w-4/5 mx-auto" v-else>
      <ShoppingCartLoading />
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Button from "@/components/Button.vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";
import PriceBox from "@/components/checkout/PriceBox.vue";
import ShoppingCartItem from "@/components/shopping-cart/ShoppingCartItem.vue";
import ShoppingCartLoading from "@/components/shopping-cart/ShoppingCartLoading.vue";
import Alert from "@/components/Alert.vue";
import { Cart } from "@/models/models";

export default Vue.extend({
  name: "ShoppingCart",
  data() {
    return {
      serviceErrors: []
    };
  },
  computed: {
    cartPrice(): number {
      return this.cart.items.reduce((a, b) => {
        return a + b.price.price;
      }, 0);
    },
    cart(): Cart {
      return this.$store.getters["shoppingCart/shoppingCart"];
    },
    isFetchingData(): boolean {
      return !this.cart;
    }
  },
  methods: {
    navigateToCheckout() {
      this.$router.push({
        name: "Checkout"
      });
    }
  },
  components: {
    Button,
    PriceBox,
    SectionTitle,
    ShoppingCartItem,
    ShoppingCartLoading,
    Alert
  }
});
</script>

<style scoped></style>
