<template>
  <div class="shadow-documentCard h-full overflow-y mb-8">
    <div class="animate-pulse grid grid-cols-1 place-content-around h-full p-4 space-y-8">
      <div class="rounded-full bg-gray-200 h-10 w-10"></div>
      <div class="grid grid-cols-2 gap-6">
        <div v-for="(item, index) in 2" :key="index" class="space-y-2">
          <div class="h-4 bg-gray-200 rounded"></div>
          <div class="h-4 bg-gray-200 rounded w-5/6"></div>
          <div class="h-4 bg-gray-200 rounded w-1/2"></div>
          <div class="h-4 bg-gray-200 rounded w-3/4"></div>
        </div>
      </div>
      <div class="grid grid-cols-2 gap-6">
        <div v-for="(item, index) in 2" :key="index" class="space-y-2">
          <div class="h-8 bg-gray-200 rounded"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "DocumentCardLoading"
});
</script>

<style lang="scss"></style>
