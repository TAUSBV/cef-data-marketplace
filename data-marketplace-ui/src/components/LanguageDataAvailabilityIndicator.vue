<template>
  <div class="flex flex-col">
    <ul class="inline-flex flex-row">
      <li
        class="mr-1"
        :class="[
          { 'rounded-tl rounded-bl': key === 0 },
          { 'rounded-tr rounded-br': key === items.length - 1 },
          { 'bg-blue-700': key <= category.sort },
          { 'bg-blue-200': key > category.sort }
        ]"
        style="width: 1.375rem; height: 1rem;"
        v-for="key in items"
        :key="key"
      ></li>
    </ul>
    <span class="text-xs text-gray-800">
      {{ category.label | cleanString }}
    </span>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import { LanguageDataAvailabilityCategory } from "@/models/models";

export default Vue.extend({
  name: "LanguageDataAvailabilityIndicator",
  props: {
    category: {
      type: Object as PropType<LanguageDataAvailabilityCategory>,
      required: true
    }
  },
  data() {
    return {
      items: [0, 1, 2, 3, 4]
    };
  }
});
</script>

<style scoped></style>
