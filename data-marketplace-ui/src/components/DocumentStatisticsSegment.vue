<template>
  <div class="text-lg mb-4">
    <div class="grid grid-cols-3">
      <div class="col-span-1 text-right bg-gray-100 rounded-full">
        <span
          class="inline-block list-bar lg:text-base lg:leading-relaxed lg:tracking-0.27 text-sm tracking-0.23 xl:px-2 pl-2 pr-1 font-medium rounded-full"
          :class="barColor"
          :style="{ width: getWidth + 'px' }"
          >{{ totalWordCount }}</span
        >
      </div>
      <div class="col-span-2 ml-2">
        <span class="text-gray-900 text-lg tracking-0.3 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27">
          {{ title }}</span
        >
      </div>
    </div>
    <div v-if="showMore" class="grid grid-cols-3 mt-1">
      <div class="col-span-1 text-right text-xs text-gray-800">
        <div class="text-xs text-gray-900">
          {{ totalSourceWordCount | integer }}
          {{ sourceLanguageTag }} words
        </div>
        <div class="text-xs text-gray-900">
          {{ totalTargetWordCount | integer }}
          {{ targetLanguageTag }} words
        </div>
      </div>
      <div class=" col-span-2 ml-2 text-xs text-gray-900">
        <span>{{ segmentQualityDesc }}</span>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  props: {
    title: {
      type: String,
      required: true
    },
    totalWordCount: {
      type: Number,
      required: true
    },
    totalSourceWordCount: {
      type: Number,
      required: true
    },
    totalTargetWordCount: {
      type: Number,
      required: true
    },
    sourceLanguageTag: {
      type: String,
      required: true
    },
    targetLanguageTag: {
      type: String,
      required: true
    },
    barColor: {
      type: String,
      required: true
    },
    showMore: {
      type: Boolean,
      required: true
    },
    segmentQualityDesc: {
      type: String,
      required: false
    },
    percentage: {
      type: Number,
      required: false
    }
  },
  computed: {
    getWidth(): number {
      const minWidth = 8 * this.totalWordCount?.toString().length;
      let maxWidth;
      if (window.innerWidth < 375) {
        maxWidth = 75;
      } else if (window.innerWidth < 1281) {
        maxWidth = 101;
      } else {
        maxWidth = 144;
      }
      const calcPx = Math.round((this.percentage / 100) * maxWidth);
      const totalWidth = calcPx + minWidth;
      if (totalWidth >= maxWidth) {
        return calcPx;
      } else if (window.innerWidth < 1281) {
        return totalWidth;
      } else {
        return totalWidth + 16;
      }
    }
  }
});
</script>

<style lang="scss">
.list-bar {
  position: relative;

  &::before {
    content: "";
    position: absolute;
  }
  &.bar-green {
    @apply bg-green-900;
    @apply bg-opacity-10;
    @apply text-green-900;
  }
  &.bar-turquoise {
    @apply bg-turquoise-blue;
    @apply bg-opacity-10;
    @apply text-turquoise-900;
  }
  &.bar-red {
    @apply bg-red-700;
    @apply bg-opacity-10;
    @apply text-red-700;
  }
  &.bar-pink {
    @apply bg-pink-900;
    @apply bg-opacity-10;
    @apply text-pink-900;
  }
}
</style>
