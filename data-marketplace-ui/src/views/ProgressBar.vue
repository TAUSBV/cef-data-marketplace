<template>
  <div class="mx-auto w-3/4">
    <div class="h-3 rounded-sm bg-blue-200">
      <div class="h-3 rounded bg-blue-700 opacity-100 animate-width" :style="`width: ${percentage}%`"></div>
    </div>
    <div class="text-gray-800 text-2xl">
      <span class="float-left">{{ text }}</span>
      <span class="float-right">{{ percentage }} %</span>
    </div>
  </div>
</template>
<script lang="ts">
export default {
  name: "ProgressBar",
  props: {
    percentage: {
      type: Number,
      required: true
    },
    text: {
      type: String,
      default: "Uploading..."
    }
  }
};
</script>
<style lang="scss">
.animate-width {
  transition: width 500ms ease-in-out;
}
</style>
