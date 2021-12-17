<template>
  <div class="flex flex-row items-center h-full shadow p-8">
    <div class="w-full border rounded-lg border-blue-700 p-40">
      <ProgressBar :percentage="percentageInternal" />
    </div>
  </div>
</template>
<script lang="ts">
import Vue from "vue";
import ProgressBar from "@/views/ProgressBar.vue";
export default Vue.extend({
  props: {
    percentage: {
      type: Number
    }
  },
  data() {
    return {
      percentageInternal: this.percentage
    };
  },
  watch: {
    percentage() {
      this.percentageInternal = this.percentage;
    }
  },
  components: {
    ProgressBar
  }
});
</script>
