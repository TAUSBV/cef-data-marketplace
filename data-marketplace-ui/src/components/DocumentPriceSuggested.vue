<template>
  <div class="flex flex-col items-center">
    <div class="text-xl font-medium tracking-wide">
      Marketplace suggested price
    </div>
    <div class="flex flex-col justify-between lg:w-3/4 mt-4 p-6 bg-white border border-gray-300 rounded">
      <div class="flex flex-row justify-between items-start mb-4">
        <div class="inline-block">
          <div class="text-4xl font-medium">
            {{ suggestedPricePerWord.suggestedPricePerWord | currency }}
          </div>
          <div class="text-gray-800">Suggested price / word</div>
        </div>
        <button class="text-blue-900 font-medium underline" @click.prevent="useSuggestedPrice">
          Use this price
        </button>
      </div>
      <div class="flex flex-row justify-between items-end mb-3">
        <LanguageDataAvailabilityIndicator :category="availabilityCategory" />
      </div>
      <div class="flex flex-row">
        <div
          class="inline-block text-xs lg:text-lg text-green-900 tracking-widest bg-green-900 bg-opacity-25 border border-transparent rounded py-2 px-4"
        >
          <strong>{{ document.currentStatistics.totalUnitsOriginal }}</strong>
          segments
        </div>
        <div class="ml-3">
          <div class="text-sm text-green-900 tracking-wider">
            <strong>{{ document.currentStatistics.totalSourceOriginalWordCount }}</strong>
            {{ document.sourceLanguage.tag }} words
          </div>
          <div class="text-sm text-green-900 tracking-wider">
            <strong>{{ document.currentStatistics.totalTargetOriginalWordCount }}</strong>
            {{ document.targetLanguage.tag }} words
          </div>
        </div>
      </div>
    </div>
    <div class="lg:w-3/4 pt-2 ml-2 text-sm text-gray-800 tracking-wide leading-relaxed">
      The suggested price is based on the
      <a class="text-blue-600 underline" href="/price-index" target="_blank">
        Price Index Table
      </a>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import LanguageDataAvailabilityIndicator from "@/components/LanguageDataAvailabilityIndicator.vue";
import { LanguageDataAvailabilityCategory, SuggestedPricePerWord, TranslationDocument } from "@/models/models";

export default Vue.extend({
  props: {
    document: {
      type: Object as PropType<TranslationDocument>,
      required: true
    },
    suggestedPricePerWord: {
      type: Object as PropType<SuggestedPricePerWord>,
      required: true
    }
  },
  data() {
    return {
      pricePerWord: this.document.pricePerWord,
      isFetching: true
    };
  },
  computed: {
    availabilityCategory(): LanguageDataAvailabilityCategory {
      return {
        id: this.suggestedPricePerWord.categoryId.toString(),
        label: this.suggestedPricePerWord.categoryLabel,
        sort: this.suggestedPricePerWord.categorySort
      };
    }
  },
  methods: {
    useSuggestedPrice() {
      this.$emit("setUseSuggestedPrice");
    }
  },
  components: {
    LanguageDataAvailabilityIndicator
  }
});
</script>

<style lang="scss"></style>
