<template>
  <section>
    <div class="grid grid-rows-4 md:grid-rows-2 grid-flow-col gap-4">
      <!-- Payment Method -->
      <div v-if="paymentDetails.method === 'PayPal'">
        <img src="/assets/img/paypal-logo.svg" alt="paypal logo" class="block mr-2 mb-1 w-24" />
        <span class="text-gray-500 font-medium inline-block">{{ paymentDetails.paypalEmail }}</span>
      </div>
      <div v-if="paymentDetails.method === 'TransferWise'">
        <img src="/assets/img/transferwise-logo.svg" alt="transferwise logo" class="block mr-2 mb-1 w-40" />
      </div>
      <!-- Legal Entity -->
      <div>
        <h5 class="text-lg font-medium mb-2">
          <img src="/assets/img/identity-card.svg" alt="identity card icon" class="inline-block mr-2 w-6 pb-1" />
          Legal Entity
        </h5>
        <span class="block">{{ paymentDetails.name }}</span>
        <span class="block">{{ paymentDetails.legalType }}</span>
        <span class="block" v-if="paymentDetails.vatNumber">{{ paymentDetails.vatNumber }}</span>
      </div>
      <!-- Address -->
      <div>
        <h5 class="text-lg font-medium mb-2">
          <img src="/assets/img/address.svg" alt="address icon" class="inline-block mr-2 w-6 pb-1" />
          Address
        </h5>
        <span class="block">{{ paymentDetails.addressLine }}, {{ paymentDetails.zipCode }}</span>
        <span class="block">{{ paymentDetails.city }}, {{ paymentDetails.countryCode }}</span>
      </div>
      <!--  Account Details -->
      <div v-if="paymentDetails.method === 'TransferWise'">
        <h5 class="text-lg font-medium mb-2">
          <img src="/assets/img/bank.svg" alt="address icon" class="inline-block mr-2 w-6 pb-1" />
          Account Details
        </h5>
        <span class="block" v-if="paymentDetails.iban">{{ paymentDetails.iban }}</span
        ><span class="block" v-if="paymentDetails.ifscCode">{{ paymentDetails.ifscCode }}</span
        ><span class="block" v-if="bankName">{{ bankName }}</span
        ><span class="block" v-if="branchName">{{ branchName }}</span>
        <span class="block" v-if="paymentDetails.accountNumber">{{ paymentDetails.accountNumber }}</span>
        <span class="block" v-if="paymentDetails.swift">{{ paymentDetails.swift }}</span>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "UserPaymentDetailsFilled",
  props: {
    paymentDetails: {
      type: Object,
      required: true
    },
    bankName: {
      type: String,
      required: false
    },
    branchName: {
      type: String,
      required: false
    }
  }
});
</script>
