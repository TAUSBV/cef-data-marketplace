<template>
  <div class="animate-pulse space-y-6">
    <div class="grid grid-cols-3 gap-8">
      <div class="col-span-2 space-y-6">
        <div class="grid grid-cols-2 gap-8">
          <div class="h-16 bg-gray-200 rounded"></div>
          <div class="h-16 bg-gray-200 rounded"></div>
        </div>
        <div class="h-64 bg-gray-200 rounded"></div>
      </div>
      <div class="col-span-1 space-y-2">
        <div class="h-24 bg-gray-200 rounded"></div>
        <div class="h-4 bg-gray-200 rounded"></div>
        <div class="h-4 bg-gray-200 rounded w-1/2"></div>
        <div class="h-4 bg-gray-200 rounded w-3/4"></div>
        <div class="h-4 bg-gray-200 rounded w-5/6"></div>
        <div class="h-4 bg-gray-200 rounded"></div>
        <div class="h-4 bg-gray-200 rounded w-1/2"></div>
        <div class="h-4 bg-gray-200 rounded w-3/4"></div>
        <div class="h-4 bg-gray-200 rounded w-5/6"></div>
        <div class="h-4 bg-gray-200 rounded"></div>
        <div class="h-4 bg-gray-200 rounded w-1/2"></div>
      </div>
    </div>
    <div class="grid grid-cols-2 gap-8">
      <div class="h-64 bg-gray-200 rounded"></div>
      <div class="h-64 bg-gray-200 rounded"></div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "UserDocumentLoading"
});
</script>

<style lang="scss"></style>
