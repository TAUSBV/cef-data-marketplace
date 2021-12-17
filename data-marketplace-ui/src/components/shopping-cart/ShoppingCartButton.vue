<template>
  <div>
    <div v-if="isCompact">
      <div class="inline float-right">
        <span
          class="bg-blue-900 rounded-full h-10 w-10 flex items-center justify-center"
          :class="isDisabled ? 'cursor-not-allowed opacity-50' : 'cursor-pointer'"
          @click.stop="addProductToShoppingCart"
        >
          <Spinner :is-active="isLoading" />
          <img v-if="!isLoading" src="/assets/img/shopping-cart-outline.svg" class="inline w-5" alt="Purchase" />
        </span>
      </div>
    </div>
    <div v-else>
      <Button
        variant="compact"
        :class="customStyling ? '' : 'h-14 px-6'"
        :isLoading="isLoading"
        :disabled="isDisabled"
        :customStyling="customStyling"
        :isLabelVisible="isLabelVisible"
        @click="addProductToShoppingCart"
      >
        <template v-slot:left-icon>
          <img
            src="/assets/img/shopping-cart-outline.svg"
            class="inline min-w-5 w-3 h-5 lg:w-5 lg:h-5"
            :class="isLabelVisible ? '' : 'mx-auto'"
            alt="Purchase"
          />
        </template>
        <span class="font-normal ml-1 sm:ml-2" v-if="isLabelVisible">
          Add to cart
        </span>
      </Button>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import Button from "@/components/Button.vue";
import Spinner from "@/components/ui/Spinner.vue";
import { CartItem, Product, ProductType } from "@/models/models";

export default Vue.extend({
  name: "ShoppingCartButton",
  props: {
    product: {
      type: Object as PropType<Product>
    },
    variant: {
      type: String
    },
    disabled: {
      type: Boolean,
      required: false,
      default: false
    },
    isLabelVisible: {
      type: Boolean,
      default: true
    },
    customStyling: {
      type: String
    }
  },
  data() {
    return {
      isLoading: false
    };
  },
  computed: {
    isDisabled(): boolean {
      return this.isProductInShoppingCart || this.isLoading || this.disabled;
    },
    isCompact(): boolean {
      return this.variant === "compact";
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
    Spinner,
    Button
  }
});
</script>

<style lang="scss"></style>
