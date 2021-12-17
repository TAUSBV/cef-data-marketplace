<template>
  <div class="bg-blue-200 px-6 py-6 rounded text-center">
    <p class="text-lg tracking-0.3 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27 mb-5">
      You have to set a payout account and update your payment details in order to get paid.
    </p>
    <Button type="button" @click="$emit('set-payout-account')">
      Set Payout Account
    </Button>
  </div>
</template>

<script>
import Vue from "vue";
import Button from "@/components/Button.vue";

export default Vue.extend({
  name: "UserPaymentDetailsEmpty",
  components: {
    Button
  }
});
</script>
