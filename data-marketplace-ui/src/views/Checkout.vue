<template>
  <div class="w-full overflow-x-hidden">
    <div class="mt-8">
      <SectionTitle title="Checkout" />
    </div>
    <div class="container mt-8 pb-32 w-4/5 mx-auto">
      <div class="grid grid-cols-1 gap-x-24 gap-y-10">
        <div class="col-span-1">
          <Stepper class="float-left w-full" :steps="steps" />
        </div>
        <div class="col-span-1">
          <CheckoutBuyer
            class="float-left"
            v-if="this.steps[0].status === 'current'"
            :buyer="this.cartInternal.buyer"
            :price="this.cartInternal.subTotal"
            :serviceFee="this.cartInternal.serviceFee"
            @onSuccess="onCheckoutBuyerSuccess"
          />
          <CheckoutPayment
            class="float-left"
            v-if="this.steps[1].status === 'current'"
            :cart="cartInternal"
            @onCancel="onCheckoutPaymentCancel"
            @onSuccess="onCheckoutPaymentSuccess"
          />
          <CheckoutComplete v-if="this.steps[2].status === 'completed'" :purchase="this.cartInternal" />
          <Alert v-if="serviceErrors.length > 0" :messages="serviceErrors" type="DANGER" />
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import Alert from "@/components/Alert.vue";
import Stepper from "@/components/Stepper.vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";
import CheckoutBuyer from "@/components/checkout/CheckoutBuyer.vue";
import CheckoutPayment from "@/components/checkout/CheckoutPayment.vue";
import CheckoutComplete from "@/components/checkout/CheckoutComplete.vue";
import { Cart } from "@/models/models";

export default Vue.extend({
  name: "Checkout",
  props: {
    cart: {
      type: Object as PropType<Cart>
    }
  },
  data() {
    return {
      steps: [
        {
          name: "USER DETAILS",
          status: "current"
        },
        {
          name: "PAYMENT",
          status: ""
        },
        {
          name: "COMPLETE",
          status: ""
        }
      ],
      cartInternal: this.cart,
      serviceErrors: []
    };
  },
  watch: {
    cartInternal: {
      handler: function(cart: Cart) {
        if (cart.status === "AWAITING_INVOICE" || cart.status === "COMPLETED") {
          this.steps[0].status = "completed";
          this.steps[1].status = "active";
          this.complete()
            .then(() => {
              this.steps[1].status = "completed";
              this.steps[2].status = "completed";
            })
            .catch(() => {
              this.steps[1].status = "failed";
            });
        } else {
          if (cart.buyer !== null) {
            this.steps[0].status = "completed";
            this.steps[1].status = "current";
          }
        }
      },
      immediate: true
    }
  },
  methods: {
    complete(): Promise<Cart> {
      const formData = new URLSearchParams();
      formData.append("p", this.cart.uid);
      this.steps[1].status = "active";
      return this.$store
        .dispatch("checkout/complete", formData)
        .then((cart: Cart) => {
          this.$gtag.event("purchase-completed", {
            "event_category": "purchases",
            "event_label": "Successful Purchase",
            "value": Math.round(this.cartInternal.total)
          });
          this.$store.commit("shoppingCart/clean");
          return cart;
        })
        .catch(error => {
          this.serviceErrors.push(error.response.data);
          return error;
        });
    },
    onCheckoutBuyerSuccess(cart: Cart) {
      this.cartInternal = cart;
      window.scroll(0, 0);
    },
    onCheckoutPaymentSuccess() {
      this.complete().then((cart: Cart) => (this.cartInternal = cart));
      window.scroll(0, 0);
    },
    onCheckoutPaymentCancel() {
      this.steps[0].status = "current";
      for (let i = 1; i < this.steps.length; i++) {
        this.steps[1].status = "";
      }
    }
  },
  components: {
    Alert,
    Stepper,
    SectionTitle,
    CheckoutBuyer,
    CheckoutPayment,
    CheckoutComplete
  }
});
</script>

<style scoped></style>
