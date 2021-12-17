<template>
  <div id="paypal_buttons" />
</template>

<script lang="ts">
import Vue from "vue";
import { loadScript } from "@paypal/paypal-js";

interface PayPalData {
  orderID: string;
}

interface PayPal {
  Buttons(config: object): PayPalButtons;
}

interface PayPalButtons {
  render(elementId: string): void;
}

export default Vue.extend({
  name: "PayPalCheckout",
  data() {
    return {
      purchase: undefined
    };
  },
  mounted() {
    loadScript({
      "client-id": import.meta.env.VITE_APP_PAYPAL_KEY,
      currency: import.meta.env.VITE_APP_CURRENCY,
      "disable-funding": import.meta.env.VITE_APP_PAYPAL_DISABLED_FUNDING
    }).then((paypal: PayPal) => {
      paypal
        .Buttons({
          createOrder: () => {
            const formData = new URLSearchParams();
            formData.append("paymentMethod", "PAYPAL");
            return this.$store.dispatch("checkout/payment", formData).then(r => {
              this.purchase = r;
              return r.payment.brokerTransactionId;
            });
          },
          onApprove: () => {
            this.$emit("onApprove", this.purchase);
          },
          onError: (error: Error) => {
            this.$emit("onError", error);
          }
        })
        .render("#paypal_buttons");
    });
  }
});
</script>

<style scoped></style>
