<template>
  <div class="grid grid-flow-row auto-rows-max">
    <div class="grid grid-cols-2 divide-x divide-gray-200 pt-2">
      <div class="text-base leading-relaxed tracking-0.27 font-normal xs:text-sm  xs:tracking-0.23 pb-8 pr-4">
        {{ translationUnit.sourceValue }}
      </div>
      <div class="text-base leading-relaxed tracking-0.27 font-normal xs:text-sm  xs:tracking-0.23 pb-8 pl-4">
        {{ translationUnit.targetValue }}
      </div>
    </div>
    <div class="grid grid-cols-2 divide-x divide-gray-200 pb-2">
      <div class="text-yellow-1000 text-sm text-right pr-4">
        <span class="font-medium">{{ translationUnit.sourceWordCount }}</span>
        words
      </div>
      <div class="text-yellow-1000 text-sm text-right pl-4">
        <span class="font-medium">{{ translationUnit.targetWordCount }}</span>
        words
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import { TranslationUnit } from "@/models/models";

export default Vue.extend({
  props: {
    translationUnit: {
      type: Object as PropType<TranslationUnit>
    }
  }
});
</script>
