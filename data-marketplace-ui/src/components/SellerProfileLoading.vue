<template>
  <div class="flex justify-center items-center animate-pulse" v-if="isBasic">
    <div class="h-0.5 w-full hidden sm:block bg-gray-200"></div>
    <div class="flex justify-center items-center min-w-22.75 mx-6">
      <div class="rounded-full bg-gray-200 h-20 w-20 min-w-20"></div>
      <div class="ml-6 text-center w-full">
        <div class="h-4 w-full bg-gray-200 rounded mb-4"></div>
        <div class="h-4 w-full bg-gray-200 rounded"></div>
      </div>
    </div>
    <div class="h-0.5 w-full hidden sm:block bg-gray-200"></div>
  </div>

  <div class="flex flex-col justify-center items-center" v-else>
    <div class="rounded-full h-32 w-32 bg-gray-200 mb-6"></div>
    <div class="bg-gray-200 mb-2 w-full h-4 rounded"></div>
    <div class="bg-gray-200 mb-1 w-full h-2 rounded"></div>
    <div class="bg-gray-200 w-full h-2 rounded"></div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "SellerProfileLoading",
  props: {
    isBasic: {
      type: Boolean,
      default: () => true
    }
  }
});
</script>

<style lang="scss"></style>
