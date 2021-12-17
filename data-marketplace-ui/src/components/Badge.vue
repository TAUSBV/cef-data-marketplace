<template>
  <span class="rounded-full h-8 w-8 inline-flex items-center justify-center bg-blue-500 text-blue-900 font-medium">
    {{ constrainedValue }}
  </span>
</template>
<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "Badge",
  props: {
    value: {
      type: Number,
      required: true
    }
  },
  computed: {
    constrainedValue(): string {
      return this.value.toString().length > 2 ? "99+" : this.value.toString();
    }
  }
});
</script>
