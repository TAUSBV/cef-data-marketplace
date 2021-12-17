<template>
  <div class="container mx-auto my-12">
    <div class="w-full">
      <div><span class="text-2xl">Button</span></div>
      <div class="flex flex-col gap-2">
        <div>Button</div>
        <Button :isLoading="false" :disabled="false">
          <template v-slot:left-icon>
            <img
              src="/assets/img/shopping-cart-outline.svg"
              class="inline min-w-5 w-3 h-5 lg:w-5 lg:h-5"
              alt="Purchase"
            />
          </template>
          Add to cart
        </Button>
        <div>Button 2 Normal</div>
        <Button2 :is-loading="false" :is-disabled="false">
          <template v-slot:left-icon>
            <img
              src="/assets/img/shopping-cart-outline.svg"
              class="inline min-w-5 w-3 h-5 lg:w-5 lg:h-5"
              alt="Purchase"
            />
          </template>
          Add to cart
        </Button2>

        <div>Button Compact</div>
        <Button :isLoading="false" variant="compact" />
        <div>Button2 Compact</div>
        <Button2 :isLoading="false">
          Press Me
        </Button2>

        <div>Shopping Cart Button</div>
        <ShoppingCartButton />
        <div>Shopping Cart Button 2</div>
        <ShoppingCartButton2 />

        <div>Shopping Cart Button Compact</div>
        <ShoppingCartButton variant="compact" />
        <div>Shopping Cart Button Compact 2</div>
        <ShoppingCartButton2 variant="icon" />

        <div>Shopping Cart Custom Style MDSampleStatistics.vue</div>
        <ShoppingCartButton :isLabelVisible="false" :customStyling="'p-0 rounded-full w-11 h-11 mx-auto'" />

        <div>Shopping Cart 2 Custom Style MDSampleStatistics.vue</div>
        <ShoppingCartButton2 variant="icon" />

        <div>Shopping Cart Custom Style MDSampleStatistics.vue</div>
        <ShoppingCartButton
          :isLabelVisible="false"
          :customStyling="false ? '' : 'p-0 rounded-full w-11 h-11 mx-auto'"
        />
        <div>Shopping Cart Custom Style SegmentsResults.vue</div>
        <ShoppingCartButton
          :isLabelVisible="false"
          :customStyling="false ? '' : 'p-0 rounded-full w-11 h-11 mx-auto'"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Button from "@/components/Button.vue";
import Button2 from "@/components/Button2.vue";
import ShoppingCartButton from "@/components/shopping-cart/ShoppingCartButton.vue";
import ShoppingCartButton2 from "@/components/shopping-cart/ShoppingCartButton2.vue";

export default Vue.extend({
  name: "Playground",
  components: { ShoppingCartButton, Button, Button2, ShoppingCartButton2 },
  data() {
    return {
      selectedItems: [],
      options: [
        {
          value: "option1",
          text: "Option 1"
        },
        {
          value: "option2",
          text: "Option 2"
        },
        {
          value: "option3",
          text: "Option 3"
        }
      ],
      reviewScore: 0
    };
  },
  methods: {
    setSelectedItems(items: Array<{ value: string; text: string }>) {
      this.selectedItems.push(items);
    },
    addRating(rating: number): void {
      console.log(rating);
      this.reviewScore = rating;
    }
  }
});
</script>

<style scoped></style>
