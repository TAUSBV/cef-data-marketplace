<template>
  <div class="grid grid-rows-3 rounded-lg shadow-input h-56">
    <div class="w-56 h-50 py-2 px-4 text-watermelon text-base bg-red-300 bg-opacity-25 rounded-tl-lg rounded-br-xxl">
      LOW LANGUAGE
    </div>
    <div>
      <LanguageDataAvailabilityIndicator :category="priceIndexEntry.availabilityCategory" />
    </div>
    <div class="flex flex-cols">
      <div
        class="flex mx-1 mb-2"
        style="background-image: url('/assets/img/language-54_54-watermelon.svg'); width: 54px; height: 54px;"
      >
        <div class="flex flex-1 justify-center self-center text-center text-red-400">
          {{ priceIndexEntry.sourceLanguage.tag }} -
          {{ priceIndexEntry.targetLanguage.tag }}
        </div>
      </div>
      <div class="mx-1 mb-2 self-center">
        {{ priceIndexEntry.sourceLanguage.name }} -
        {{ priceIndexEntry.targetLanguage.name }}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import { PriceIndexEntry } from "@/models/models";
import LanguageDataAvailabilityIndicator from "@/components/LanguageDataAvailabilityIndicator.vue";

export default Vue.extend({
  name: "LanguageDataAvailabilityCard",
  props: {
    priceIndexEntry: {
      type: Object as PropType<PriceIndexEntry>,
      required: true
    }
  },
  components: {
    LanguageDataAvailabilityIndicator
  }
});
</script>
