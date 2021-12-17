<template>
  <section>
    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
      <div class="h-12 w-full bg-gray-200 rounded animate-pulse"></div>
      <div class="h-12 w-full bg-gray-200 rounded animate-pulse"></div>
      <div class="h-12 w-full bg-gray-200 rounded animate-pulse"></div>
      <div class="h-12 w-full bg-gray-200 rounded animate-pulse"></div>
    </div>
    <div class="grid grid-cols-1 gap-4 mt-4">
      <div class="h-12 w-full bg-gray-200 rounded animate-pulse"></div>
      <div class="h-12 w-full bg-gray-200 rounded animate-pulse"></div>
    </div>
    <div class="flex flex-wrap content-center justify-between mt-4">
      <div class="h-6 w-6 bg-gray-200 rounded animate-pulse"></div>
      <div class="h-6 ml-4 flex-auto bg-gray-200 rounded animate-pulse"></div>
    </div>
    <div class="grid grid-cols-1 justify-items-center mt-8">
      <div class="h-14 w-48 bg-gray-200 rounded animate-pulse"></div>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "HubSpotFormLoading"
});
</script>
