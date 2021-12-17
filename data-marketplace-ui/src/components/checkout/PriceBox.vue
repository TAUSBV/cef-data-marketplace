<template>
  <div class="w-full bg-gray-200 px-6 py-8 rounded">
    <div class="price mb-6 overflow-hidden">
      <p class="text-base float-left">
        Price:
      </p>
      <p class="float-right text-lg font-semibold leading-6">
        {{ price | currency2Decimals }}
      </p>
    </div>
    <div v-if="taxPercentage !== undefined" class="price mb-6 overflow-hidden">
      <p class="text-base float-left">VAT({{ taxPercentage * 100 }}%):</p>
      <p class="float-right text-lg font-semibold leading-6">
        {{ taxAmount | currency2Decimals }}
      </p>
    </div>
    <div class="fee mb-1 overflow-hidden relative">
      <p class="float-left text-gray-800">Service fee ({{ serviceFee * 100 }}%)</p>
      <p class="float-right text-gray-800 text-lg font-semibold leading-6">
        {{ serviceFeePrice | currency2Decimals }}
      </p>
    </div>
    <div class="note mb-4 overflow-hidden">
      <p class="text-sm float-left text-gray-800" style="line-height: 1.43;">
        Note: Fee is not charged until 31 October 2021.
      </p>
    </div>
    <hr class="pricing-hr" />
    <div class="total-price mt-6 overflow-hidden">
      <p class="text-base float-left">
        Total price:
      </p>
      <p class="float-right text-lg font-semibold leading-6">
        {{ (totalPrice ? totalPrice : price) | currency2Decimals }}
      </p>
    </div>
    <div class="note mb-4 overflow-hidden">
      <p class="text-sm float-left text-gray-800">
        {{ taxPercentage === undefined ? "Excluding any taxes, if applicable." : "All taxes applied." }}
      </p>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "PriceBox",
  props: {
    price: {
      type: Number,
      required: true
    },
    taxPercentage: {
      type: Number,
      required: false
    },
    taxAmount: {
      type: Number,
      required: false
    },
    totalPrice: {
      type: Number,
      required: false
    },
    serviceFee: {
      type: Number,
      required: true
    }
  },
  computed: {
    serviceFeePrice(): number {
      return this.price * this.serviceFee;
    }
  }
});
</script>

<style scoped>
.fee:before {
  content: " ";
  position: absolute;
  top: 45%;
  left: 0;
  border-radius: 0.5px;
  border-bottom: 1px solid #515156;
  width: 100%;
}
.pricing-hr {
  border-top: 3px solid #fff;
}
</style>
