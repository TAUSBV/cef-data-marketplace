<template>
  <div class="w-3/4 mx-auto grid grid-cols-12 gap-0 bg-gray-100 rounded px-10 py-8 shadow-md">
    <div
      class="animate-pulse xs:col-span-12 sm:col-span-12 md:col-span-12 lg:col-span-10 grid grid-cols-4 gap-4 items-center pr-8"
    >
      <div
        v-for="(items, index) in 4"
        :key="index"
        class="xs:col-span-4 sm:col-span-2 md:col-span-2 lg:col-span-1 xs:w-full space-y-4"
      >
        <div class="w-3/4 h-4 bg-gray-200 rounded"></div>
        <div class="w-3/4 h-4 bg-gray-200 rounded"></div>
      </div>
    </div>
    <div
      class="animate-pulse xs:col-span-12 sm:col-span-12 md:col-span-12 lg:col-span-2 items-center lg:pl-8 lg:border-l border-gray-200 xs:mt-10 sm:mt-10 md:mt-0 lg:mt-0 w-full xs:text-center sm:text-center space-y-2"
    >
      <div class="h-6 bg-gray-200 rounded"></div>
      <div class="h-12 bg-gray-200 rounded"></div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "ExploreStatisticsLoading"
});
</script>

<style lang="scss" scoped></style>
