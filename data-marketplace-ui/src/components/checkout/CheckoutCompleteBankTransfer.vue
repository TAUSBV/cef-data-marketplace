<template>
  <div class="w-3/4 shadow py-20 px-24 mx-auto">
    <img src="/assets/img/steps-finished.svg" alt="Steps finished" class="w-16 h-16 mb-8 mx-auto" />
    <div class="text-34px leading-49 tracking-0.76 xs:text-2xl xs:tracking-0.44  font-medium mb-12 text-center">
      Thank you for the purchase
    </div>
    <div class="text-xl leading-30 tracking-0.44 xs:text-lg xs:tracking-0.3 font-normal">
      <div class="mb-12">
        You will receive an invoice shortly. Once you have completed the payment, you will receive an email on how to
        access the purchased dataset.
      </div>
      <div>
        Best regards,
      </div>
      <div>
        Data Marketplace team
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "CheckoutCompleteBankTransfer"
});
</script>

<style scoped></style>
