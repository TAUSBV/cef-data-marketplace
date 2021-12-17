<template>
  <section class="w-full mb-8 pb-8 grid grid-cols-10 xs:gap-x-4 sm:gap-x-8 lg:gap-x-6 xl:gap-12 ">
    <!-- DATASET -->
    <div class="rounded shadow p-5 col-span-10 sm:col-span-10 md:col-span-5 lg:col-span-4">
      <DocumentCardLanguage
        :sourceLanguage="cartItem.product.sourceLanguage"
        :targetLanguage="cartItem.product.targetLanguage"
        class="bg-blue-200 bg-opacity-75 rounded mb-6 p-4"
      />

      <div class="pt-4 px-4 xs:text-center" v-if="isSellerDocument">
        <p>
          <span class="text-sm font-normal text-gray-800 mr-2">
            Domain:
          </span>
          <span class="text-base leading-relaxed tracking-0.27 font-medium xs:text-sm  xs:tracking-0.23">
            {{ cartItem.product.domains.map(d => d.description).join(", ") }}
          </span>
        </p>
        <p>
          <span class="text-sm font-normal text-gray-800 mr-2">
            Content type:
          </span>
          <span class="text-base leading-relaxed tracking-0.27 font-medium xs:text-sm  xs:tracking-0.23">
            {{ cartItem.product.contentTypes.map(c => c.description).join(", ") }}
          </span>
        </p>
      </div>
      <div class="pt-4 px-4 xs:text-center" v-else>
        Multiple Domains and Content Types
      </div>
    </div>

    <!-- QUANTITY -->
    <div class="col-span-5 sm:col-span-5 md:col-span-3 lg:col-span-3 sm:mt-8 md:mt-0 lg:mt-0">
      <div class="mb-2 pt-4">
        <div class="text-base leading-relaxed tracking-0.27 font-medium xs:text-sm  xs:tracking-0.23">
          {{ this.cartItem.price.totalSourceWords }}
        </div>
        <div class="text-sm text-gray-800">{{ cartItem.product.sourceLanguage.displayName }} words</div>
      </div>

      <div class="mb-2">
        <div class="text-base leading-relaxed tracking-0.27 font-medium xs:text-sm  xs:tracking-0.23">
          {{ cartItem.price.totalUnits }}
        </div>
        <div class="text-sm text-gray-800">Segments</div>
      </div>

      <div class="w-full mt-6" v-if="canChangeAmount">
        <div class="w-full flex items-center justify-between">
          <div class="" style="width: fit-content;">
            <label class="flex-col items-center" :for="cartItem.uid + '50percent'">
              <input
                :id="cartItem.uid + '25percent'"
                type="radio"
                v-model="selectedPriceAmount"
                @change="onSelectedPriceAmountChange"
                value="0.25"
                class="appearance-none cursor-pointer h-5 w-5 shadow rounded-full border
            border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
              />
              <span class="block cursor-pointer">25%</span>
            </label>
          </div>
          <div class="" style="width: fit-content;">
            <label class="flex-col items-center" :for="cartItem.uid + '50percent'">
              <input
                :id="cartItem.uid + '50percent'"
                type="radio"
                v-model="selectedPriceAmount"
                @change="onSelectedPriceAmountChange"
                value="0.5"
                class="appearance-none cursor-pointer h-5 w-5 shadow rounded-full border
            border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
              />
              <span class="block cursor-pointer">50%</span>
            </label>
          </div>

          <div style="width: fit-content;">
            <label class="flex-col items-center" :for="cartItem.uid + '75percent'">
              <input
                :id="cartItem.uid + '75percent'"
                type="radio"
                v-model="selectedPriceAmount"
                @change="onSelectedPriceAmountChange"
                value="0.75"
                class="appearance-none cursor-pointer h-5 w-5 shadow rounded-full border
            border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
              />
              <span class="block cursor-pointer">75%</span>
            </label>
          </div>
          <div class="" style="width: fit-content;">
            <label class="flex-col items-center" :for="cartItem.uid + '100percent'">
              <input
                :id="cartItem.uid + '100percent'"
                type="radio"
                v-model="selectedPriceAmount"
                @change="onSelectedPriceAmountChange"
                value="1"
                class="appearance-none cursor-pointer h-5 w-5 shadow rounded-full border
            border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
              />

              <span class="block cursor-pointer">100%</span>
            </label>
          </div>
        </div>
      </div>
      <div v-else>
        <div class="text-base leading-relaxed tracking-0.27 font-medium xs:text-sm  xs:tracking-0.23">
          {{ selectedPriceAmount * 100 }}%
        </div>
        <div class="text-sm text-gray-800">Amount</div>
      </div>
    </div>

    <!-- PRICE -->
    <div class="col-span-5 sm:col-span-5 md:col-span-2 lg:col-span-3 sm:mt-2 md:mt-0 lg:mt-0">
      <div class="pt-8 flex">
        <div class="mr-auto" style="width: fit-content;">
          <p class="text-base leading-relaxed tracking-0.27 font-medium xs:text-sm  xs:tracking-0.23">
            {{ cartItem.price.price | currency2Decimals }}
          </p>
        </div>
        <div class="mr-auto" style="width: fit-content;" v-if="!isReadOnly">
          <img
            src="/assets/img/trash-icon.svg"
            alt="Trash"
            class="h-5 my-auto cursor-pointer"
            style="width: 1.125rem;"
            @click="deleteProduct()"
          />
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import DocumentCardLanguage from "@/components/DocumentCardLanguage.vue";
import { CartItem, ProductType } from "@/models/models";

export default Vue.extend({
  name: "ShoppingCartItem",
  props: {
    cartItem: {
      type: Object as PropType<CartItem>,
      required: true
    },
    isReadOnly: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      selectedPriceAmount: this.cartItem.price.amount
    };
  },
  computed: {
    canChangeAmount(): boolean {
      return !this.isReadOnly && this.cartItem.product.amountVariable;
    },
    isSellerDocument(): boolean {
      return this.cartItem.product.type === ProductType.SELLER_DOCUMENT;
    }
  },
  methods: {
    onSelectedPriceAmountChange() {
      const updatedItemForm = new URLSearchParams();
      updatedItemForm.append("itemUid", this.cartItem.uid);
      updatedItemForm.append("amount", this.selectedPriceAmount.toString());
      this.$store.dispatch("shoppingCart/update", updatedItemForm);
    },
    deleteProduct() {
      const productForm = new URLSearchParams();
      productForm.append("itemUid", this.cartItem.uid);
      this.$store.dispatch("shoppingCart/delete", productForm).then(() => {
        this.$store.dispatch("shoppingCart/list");
      });
    }
  },
  components: {
    DocumentCardLanguage
  }
});
</script>

<style lang="scss"></style>
