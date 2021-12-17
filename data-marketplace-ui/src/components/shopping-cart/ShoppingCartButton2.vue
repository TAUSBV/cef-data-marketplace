<template>
  <div>
    <Button2 :is-loading="isLoading" :is-disabled="isDisabled" :variant="variant" @click="addProductToShoppingCart">
      <template v-slot:left-icon v-if="variant === 'normal'">
        <img
          src="/assets/img/shopping-cart-outline.svg"
          class="inline min-w-5 w-3 h-5 lg:w-5 lg:h-5"
          :class="variant === 'icon' ? 'mx-auto' : ''"
          alt="Purchase"
        />
      </template>
      <span v-if="variant === 'normal'">Add to cart</span>
      <img
        src="/assets/img/shopping-cart-outline.svg"
        class=" min-w-5 w-3 h-5 lg:w-5 lg:h-5 mx-auto"
        v-if="variant === 'icon' && !isLoading"
        alt="Purchase"
      />
    </Button2>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import Button2 from "@/components/Button2.vue";
import { CartItem, Product, ProductType } from "@/models/models";

export default Vue.extend({
  name: "ShoppingCartButton",
  props: {
    product: {
      type: Object as PropType<Product>
    },
    variant: {
      type: String,
      default: "normal"
    },
    disabled: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  data() {
    return {
      isLoading: true
    };
  },
  computed: {
    isDisabled(): boolean {
      return this.isProductInShoppingCart || this.isLoading || this.disabled;
    },
    isProductInShoppingCart(): boolean {
      const shoppingCart = this.$store.getters["shoppingCart/shoppingCart"];
      return (
        shoppingCart.items &&
        shoppingCart.items.find((item: CartItem) => {
          return this.product.documentUid === item.product.sku;
        }) !== undefined
      );
    }
  },
  methods: {
    addProductToShoppingCart() {
      if (this.product.type === ProductType.VIRTUAL_DOCUMENT) {
        this.isLoading = true;
        const productForm = new URLSearchParams();
        productForm.append("s", this.product.sourceLanguage.toString());
        productForm.append("t", this.product.targetLanguage.toString());
        this.product.domains.forEach(o => productForm.append("d", o.toString()));
        this.product.contentTypes.forEach(o => productForm.append("c", o.toString()));
        this.product.sellers.forEach(o => productForm.append("u", o.toString()));
        if (this.product.priceMin) {
          productForm.append("pmin", this.product.priceMin?.toString());
        }
        if (this.product.priceMax) {
          productForm.append("pmax", this.product.priceMax?.toString());
        }
        this.$store
          .dispatch("shoppingCart/addVirtualDocumentToCart", productForm)
          .then()
          .finally(() => {
            this.isLoading = false;
          });
      } else if (this.product.type === ProductType.SELLER_DOCUMENT) {
        this.isLoading = true;
        const productForm = new URLSearchParams();
        productForm.append("documentUid", this.product.documentUid);
        this.$store
          .dispatch("shoppingCart/addSellerDocumentToCart", productForm)
          .then()
          .finally(() => {
            this.isLoading = false;
          });
      } else if (this.product.type === ProductType.MATCHING_DOCUMENT) {
        this.isLoading = true;
        const productForm = new URLSearchParams();
        productForm.append("requestUid", this.product.requestId);
        productForm.append("amount", this.product.amount.toString());
        this.$store
          .dispatch("shoppingCart/addMatchingDocumentToCart", productForm)
          .then()
          .finally(() => {
            this.isLoading = false;
          });
      }
    }
  },
  components: {
    Button2
  }
});
</script>

<style lang="scss"></style>
