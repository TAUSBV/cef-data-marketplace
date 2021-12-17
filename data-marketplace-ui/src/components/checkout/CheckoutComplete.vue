<template>
  <div class="w-3/4 shadow py-20 px-24 mx-auto text-center">
    <img src="/assets/img/steps-finished.svg" alt="Purchase Completed" class="w-16 h-16 mb-8 mx-auto" />
    <div class="text-34px leading-49 tracking-0.76 font-medium xs:text-2xl xs:tracking-0.44  mb-12 text-center">
      <span id="purchase-thank-you">Thank you!</span>
    </div>
    <div v-if="this.purchase" class="text-xl leading-30 tracking-0.44 xs:text-lg xs:tracking-0.3 font-normal mb-8">
      You have successfully completed your purchase of
      <strong
        >€<span id="purchase-total">{{ this.purchase.total | decimal2FractionDigits }}</span
        >.</strong
      >
    </div>
    <div class="text-xl leading-30 tracking-0.44 xs:text-lg xs:tracking-0.3 font-normal">
      <div class="mb-12" v-if="isBankTransfer">
        You will receive an email with the bank transfer instructions shortly.
        <br />
        Once you have completed the transfer, you will receive an email with instructions on how to download the dataset
        and your invoice.
      </div>
      <div class="text-xl leading-30 tracking-0.44 xs:text-lg xs:tracking-0.3 font-normal mb-12" v-else>
        You will receive your invoice shortly.
        <br />
        Follow the link below to download your data.
      </div>
      <router-link
        :to="{ name: 'Purchase', params: { uid: this.purchase.uid } }"
        class="text-lg text-blue-700 font-medium cursor-pointer"
      >
        Purchase Details
      </router-link>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import { Cart } from "@/models/models";

export default Vue.extend({
  name: "CheckoutComplete",
  props: {
    purchase: {
      type: Object as PropType<Cart>
    }
  },
  computed: {
    isBankTransfer(): boolean {
      return this.purchase.payment.method === "BANK_TRANSFER";
    }
  }
});
</script>

<style scoped></style>
