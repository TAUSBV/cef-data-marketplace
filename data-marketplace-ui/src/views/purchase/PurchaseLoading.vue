<template>
  <div class="animate-pulse grid grid-cols-1 place-content-around h-full p-4 space-y-8">
    <div class="grid grid-cols-3 gap-6 mb-12">
      <div v-for="(items, index) in 3" :key="index" class="space-y-8">
        <div v-for="(items, index) in 3" :key="index" class="space-y-2">
          <div class="h-4 bg-gray-200 rounded w-2/3"></div>
          <div class="h-4 bg-gray-200 rounded w-1/3"></div>
        </div>
      </div>
    </div>

    <div class="border-b border-gray-200"></div>

    <div class="grid grid-cols-3 gap-4">
      <div class="space-y-8">
        <div class="h-16 bg-gray-200 rounded"></div>
        <div class="grid grid-cols-2 gap-6">
          <div class="space-y-2">
            <div class="h-4 bg-gray-200 rounded"></div>
            <div class="h-4 bg-gray-200 rounded"></div>
          </div>
          <div class="space-y-2">
            <div class="h-4 bg-gray-200 rounded w-1/2"></div>
            <div class="h-4 bg-gray-200 rounded w-1/2"></div>
          </div>
        </div>
      </div>
      <div class="space-y-8">
        <div>
          <div class="space-x-2" v-for="(item, index) in 2" :key="`quantity-placeholder-${index}`">
            <div class="h-4 bg-gray-200 rounded w-1/2 inline-block"></div>
            <div class="h-4 bg-gray-200 rounded w-1/6 inline-block"></div>
          </div>
        </div>
        <div class="text-center space-x-16">
          <div
            class="rounded-full bg-gray-200 h-4 w-4 mb-6 pl-4 inline-block"
            v-for="(item, index) in 3"
            :key="`quantity-radio-placeholder-${index}`"
          ></div>
        </div>
      </div>
      <div class="space-x-2">
        <div class="h-4 bg-gray-200 rounded w-1/2 inline-block"></div>
        <div class="h-4 bg-gray-200 rounded w-1/6 inline-block"></div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "PurchaseLoading"
});
</script>

<style lang="scss"></style>
