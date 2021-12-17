<template>
  <div class="flex flex-auto items-center">
    <img src="/assets/img/chevron-left-blue.svg" alt="previous page" class="w-2 h-2 ml-1 cursor-pointer" />
    <div
      class="xs:hidden text-blue-700 text-base justify-start tracking-0.27 ml-2 font-normal cursor-pointer"
      @click="backToLastStep"
    >
      Back {{ label }}
    </div>
    <div
      class="xs:visible sm:hidden text-blue-700 text-base justify-start tracking-0.27 ml-2 font-normal cursor-pointer"
      @click="backToLastStep"
    >
      Back
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  props: {
    label: {
      type: String,
      required: true
    }
  },
  methods: {
    backToLastStep() {
      this.$emit("getBackToLastStep");
    }
  }
});
</script>

<style scoped></style>
