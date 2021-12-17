<template>
  <div class="grid grid-cols-1 lg:grid-cols-3 gap-4 mb-8">
    <div class="lg:col-span-1 space-y-2">
      <div class="border border-gray-300 rounded divide-y divide-gray-200 p-6 h-full">
        <div class="animate-pulse space-y-6">
          <div class="h-20 bg-gray-200 rounded"></div>
          <div class="space-y-2">
            <div class="h-4 bg-gray-200 rounded"></div>
            <div class="h-4 bg-gray-200 rounded w-1/2"></div>
            <div class="h-4 bg-gray-200 rounded w-3/4"></div>
            <div class="h-4 bg-gray-200 rounded w-5/6"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="lg:col-span-2">
      <div class="flex flex-wrap border border-gray-300 rounded p-6 h-full">
        <div class="flex-1 space-y-2 p-6 mt-6 animate-pulse">
          <div class="h-4 bg-gray-200 rounded w-1/2"></div>
          <div class="h-4 bg-gray-200 rounded"></div>
          <div class="h-4 bg-gray-200 rounded w-1/3"></div>
          <div class="h-4 bg-gray-200 rounded w-1/3"></div>
        </div>
        <div class="flex-1 space-y-6 px-6 animate-pulse">
          <div v-for="(item, index) in 3" :key="index" class="space-y-2">
            <div class="h-4 bg-gray-200 rounded"></div>
            <div class="h-4 bg-gray-200 rounded w-1/2"></div>
            <div class="h-4 bg-gray-200 rounded w-1/2"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "DocumentAnalysisLoading"
});
</script>

<style lang="scss"></style>
