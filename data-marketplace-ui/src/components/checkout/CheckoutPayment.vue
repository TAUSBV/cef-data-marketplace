<template>
  <div class="w-full grid grid-cols-3 xs:gap-x-24 sm:gap-x-24 md:gap-x-24 lg:gap-x-10 xl:gap-x-24">
    <form class="col-span-2 xs:col-span-3 sm:col-span-3 md:col-span-3 lg:col-span-2 grid grid-cols-1 gap-4 w-full">
      <label for="paypalMethod" class="w-full mb-8 p-6 border rounded border-gray-300 relative cursor-pointer">
        <input
          id="paypalMethod"
          type="radio"
          value="PAYPAL"
          v-model="form.paymentMethod"
          class="block float-left mt-2"
        />
        <label for="paypalMethod" class="pl-4 pr-24 w-10/12 block float-left cursor-pointer">
          <span class="text-lg font-bold">PayPal</span><br />
          <span class="text-base text-gray-800 leading-6">
            The purchase will be securely completed through PayPal interface.
          </span>
        </label>
        <img src="/assets/img/paypal.png" class="absolute w-24 right-0 mr-6" alt="PayPal" />
      </label>
      <label for="stripe" class="w-full mb-8 p-6 border rounded border-gray-300 relative cursor-pointer">
        <input id="stripe" type="radio" value="STRIPE" v-model="form.paymentMethod" class="block float-left mt-2" />
        <label for="stripe" class="pl-4 pr-24 w-10/12 block float-left cursor-pointer">
          <span class="text-lg font-bold">Credit card</span><br />
          <span class="text-base text-gray-800 leading-6">
            You will be redirected to the Stripe website to complete your purchase securely.
          </span>
        </label>
        <img src="/assets/img/stripe.png" class="absolute w-16 right-0 mr-6" alt="Stripe" />
      </label>
      <label for="bank" class="w-full p-6 border rounded border-gray-300 relative cursor-pointer">
        <input
          id="bank"
          type="radio"
          value="BANK_TRANSFER"
          v-model="form.paymentMethod"
          class="block float-left mt-2"
        />
        <label for="bank" class="pl-4 pr-24 w-10/12 block float-left cursor-pointer">
          <span class="text-lg font-bold">Bank transfer</span><br />
          <span class="text-base text-gray-800 leading-6">
            Once you have completed the payment, you will receive an invoice shortly and how to access the purchased
            dataset.
          </span>
        </label>
        <img src="/assets/img/bank.png" class="w-10 absolute right-0 mr-6" alt="Bank Transfer" />
      </label>
      <div class="text-xs text-red-900 flex items-center justify-center h-0" v-if="$v.form.paymentMethod.$error">
        Required field
      </div>
      <Alert v-if="serviceErrors.length > 0" :messages="serviceErrors" type="DANGER" />
    </form>
    <div
      class="col-span-1 xs:col-span-3 sm:col-span-3 md:col-span-3 lg:col-span-1 xs:mt-24 sm:mt-24 md:mt-24 lg:mt-0 xl:mt-0"
    >
      <PriceBox
        :price="this.cart.subTotal"
        :taxPercentage="0.21"
        :taxAmount="this.cart.tax"
        :totalPrice="this.cart.total"
        :serviceFee="this.cart.serviceFee"
      />
    </div>
    <div
      class="col-span-2 xs:col-span-3 sm:col-span-3 md:col-span-3 lg:col-span-2 grid grid-cols-2 items-center w-full mt-24"
    >
      <div>
        <a class="float-left text-base text-blue-700 font-semibold cursor-pointer leading-none" @click="back()">
          <img src="/assets/img/chevron-right-blue.svg" alt="Back" class="inline pl-1 transform rotate-180" />
          Back to user details
        </a>
      </div>
      <div>
        <PayPalCheckout @onApprove="onPayPalApprove" @onError="onPayPalError" v-if="form.paymentMethod === 'PAYPAL'" />
        <Button
          v-else
          type="button"
          class="float-right"
          :disabled="disablePurchase"
          :isLoading="isLoading"
          @click="purchase()"
        >
          Purchase
        </Button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import Alert from "@/components/Alert.vue";
import Button from "@/components/Button.vue";
import PriceBox from "@/components/checkout/PriceBox.vue";
import PayPalCheckout from "@/components/checkout/PayPalCheckout.vue";
import { required } from "vuelidate/lib/validators";
import { Cart } from "@/models/models";

export default Vue.extend({
  name: "PaymentMethods",
  props: {
    cart: {
      type: Object as PropType<Cart>
    }
  },
  data() {
    return {
      form: {
        paymentMethod: ""
      },
      isLoading: false,
      serviceErrors: []
    };
  },
  validations: {
    form: {
      paymentMethod: {
        required
      }
    }
  },
  methods: {
    complete(cart: Cart): void {
      this.$emit("onSuccess", cart);
    },
    onPayPalApprove(cart: Cart): void {
      this.complete(cart);
    },
    onPayPalError(error: Error): void {
      this.serviceErrors.push(error.message);
      this.$emit("onError", error);
    },
    updatePaymentMethod(value: string) {
      this.form.paymentMethod = value;
      this.$v.form.paymentMethod.$touch();
    },
    back() {
      this.$emit("onCancel");
    },
    purchase() {
      this.isLoading = true;
      const paymentFormData = new URLSearchParams();
      paymentFormData.append("p", this.cart.uid);
      paymentFormData.append("paymentMethod", this.form.paymentMethod);
      this.$store
        .dispatch("checkout/payment", paymentFormData)
        .then(response => {
          switch (this.form.paymentMethod) {
            case "STRIPE":
              this.checkoutStripe(response.payment.brokerTransactionId);
              break;
            case "BANK_TRANSFER":
              this.complete(response);
              break;
          }
        })
        .catch(error => {
          if (typeof error.response.data === "string") {
            this.serviceErrors.push(error.response.data);
          } else {
            this.serviceErrors = error.response.data;
          }
        })
        .finally(() => {
          this.isLoading = false;
        });
    },
    checkoutStripe(brokerTransactionId: string) {
      this.$store.dispatch("checkout/stripe", brokerTransactionId).finally(() => {
        this.isLoading = false;
      });
    }
  },
  computed: {
    disablePurchase(): boolean {
      return this.$v.$error || this.$v.$invalid;
    }
  },
  components: {
    Alert,
    Button,
    PriceBox,
    PayPalCheckout
  }
});
</script>

<style scoped></style>
