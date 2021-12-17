<template>
  <div class="bg-blue-200">
    <div
      class="mx-auto w-4/5 md:w-3/4 mt-16 mb-32 bg-white border border-gray-300 rounded py-10 px-12 md:px-16 lg:px-24 text-gray-900"
    >
      <h1 class="text-3xl font-medium tracking-wide pb-2">Contact Us</h1>
      <p class="text-lg tracking-wide">Our team of experts will guide you in acquiring the right dataset</p>
      <div class="mt-8">
        <HubSpotForm></HubSpotForm>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import Vue from "vue";
import HubSpotForm from "@/components/HubSpotForm.vue";

export default Vue.extend({
  name: "ContactUs",
  components: {
    HubSpotForm
  }
});
</script>
