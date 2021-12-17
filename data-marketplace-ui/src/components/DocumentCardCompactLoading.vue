<template>
  <div class="flex flex-col justify-between rounded border bg-gray-100">
    <div class="animate-pulse p-3">
      <div class="h-4 w-32 bg-gray-200 rounded"></div>
      <div class="flex justify-center items-center py-8 border-b border-gray-200">
        <div class="w-24 h-4 bg-gray-200 rounded"></div>
        <div class="w-7 h-6 mx-6 bg-gray-200 rounded"></div>
        <div class="w-24 h-4 bg-gray-200 rounded"></div>
      </div>
      <div class="mt-6 mb-4 rounded bg-gray-200 w-full h-8"></div>
      <div class="mt-4 rounded bg-gray-200 w-full h-8"></div>
      <div class="mt-4 mb-2 rounded bg-gray-200 w-full h-8"></div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "DocumentCardCompactLoading"
});
</script>

<style lang="scss"></style>
