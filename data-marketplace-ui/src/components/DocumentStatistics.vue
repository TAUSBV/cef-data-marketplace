<template>
  <section>
    <div
      @click="toggleMore"
      class="flex flex-wrap items-center justify-end mx-4 mt-2 text-sm text-blue-700 font-normal"
    >
      <span v-if="!showMore">Show more </span>
      <span v-if="showMore">Show less </span>
      <img
        v-if="!showMore"
        src="/assets/img/chevron-down-blue.svg"
        alt="Show more"
        class="w-2 h-2 ml-1 cursor-pointer"
      />
      <img
        v-if="showMore"
        src="/assets/img/chevron-down-blue.svg"
        alt="Show less"
        class="w-2 h-2 ml-1 transform rotate-180 cursor-pointer"
      />
    </div>
    <div class="flex flex-auto xs:flex-col mx-6 h-full">
      <div class="flex flex-col xs:flex-row xs:items-center">
        <div>
          <div class="text-34px leading-49 tracking-0.76 font-medium">
            {{ document.currentStatistics.totalUnits | integer }}
          </div>
          <div
            class="text-gray-900 text-xl leading-30 tracking-0.37 font-normal xs:text-center xs:text-base xs:leading-relaxed xs:tracking-0.27 pb-2"
          >
            Total segments
          </div>
        </div>
        <div class="xs:mt-4 xs:mx-2 mb-4">
          <div class="text-xs text-gray-900">
            {{ document.currentStatistics.totalSourceWordCount | integer }}
            <span v-if="document.sourceLanguage != null">{{ document.sourceLanguage.tag }}</span>
            <span v-else>source</span>
            words
          </div>
          <div class="text-xs text-gray-900">
            {{ document.currentStatistics.totalTargetWordCount | integer }}
            <span v-if="document.targetLanguage != null">{{ document.targetLanguage.tag }}</span>
            <span v-else>target</span>
            words
          </div>
        </div>
      </div>

      <div class="flex flex-col xs:mt-4 xl:ml-4">
        <DocumentStatisticsSegment
          title="High-quality segments"
          :totalWordCount="document.currentStatistics.totalUnitsOriginal"
          :totalSourceWordCount="document.currentStatistics.totalSourceOriginalWordCount"
          :totalTargetWordCount="document.currentStatistics.totalTargetOriginalWordCount"
          :sourceLanguageTag="document.sourceLanguage.tag"
          :targetLanguageTag="document.targetLanguage.tag"
          barColor="bar-green"
          :showMore="showMore"
          segmentQualityDesc="Unique, quality segments that don't already exist in the Data Marketplace. They will be listed for sale on the Search and Sellers pages."
          :percentage="
            getWidthPercentage(document.currentStatistics.totalUnitsOriginal, document.currentStatistics.totalUnits)
          "
          v-if="document.sourceLanguage != null && document.targetLanguage != null"
        />
        <DocumentStatisticsSegment
          title="Replica segments"
          :totalWordCount="document.currentStatistics.totalUnitsReplica"
          :totalSourceWordCount="document.currentStatistics.totalSourceReplicaWordCount"
          :totalTargetWordCount="document.currentStatistics.totalTargetReplicaWordCount"
          :sourceLanguageTag="document.sourceLanguage.tag"
          :targetLanguageTag="document.targetLanguage.tag"
          barColor="bar-turquoise"
          :showMore="showMore"
          segmentQualityDesc="Segments that already exist in the Data Marketplace. They will only be available for purchase on the Sellers' page."
          :percentage="
            getWidthPercentage(document.currentStatistics.totalUnitsReplica, document.currentStatistics.totalUnits)
          "
          v-if="document.sourceLanguage != null && document.targetLanguage != null"
        />
        <DocumentStatisticsSegment
          title="Low-quality segments"
          :totalWordCount="document.currentStatistics.totalUnitsLowQuality"
          :totalSourceWordCount="document.currentStatistics.totalSourceLowQualityWordCount"
          :totalTargetWordCount="document.currentStatistics.totalTargetLowQualityWordCount"
          :sourceLanguageTag="document.sourceLanguage.tag"
          :targetLanguageTag="document.targetLanguage.tag"
          barColor="bar-red"
          :showMore="showMore"
          segmentQualityDesc="Segments that have been flagged during the cleaning process. They will be removed from the document and not listed for sale."
          :percentage="
            getWidthPercentage(document.currentStatistics.totalUnitsLowQuality, document.currentStatistics.totalUnits)
          "
          v-if="document.sourceLanguage != null && document.targetLanguage != null"
        />
        <DocumentStatisticsSegment
          title="Segments with possible PII"
          :totalWordCount="document.currentStatistics.totalUnitsAnnotatedPii || 0"
          :totalSourceWordCount="document.currentStatistics.totalSourceWordCountAnnotatedPii || 0"
          :totalTargetWordCount="document.currentStatistics.totalTargetWordCountAnnotatedPii || 0"
          :sourceLanguageTag="document.sourceLanguage.tag"
          :targetLanguageTag="document.targetLanguage.tag"
          barColor="bar-pink"
          :showMore="showMore"
          segmentQualityDesc="Segments that contain possible personally identifiable information (PII). You can choose to remove or keep them in your document."
          :percentage="
            getWidthPercentage(
              document.currentStatistics.totalUnitsAnnotatedPii,
              document.currentStatistics.totalUnitsOriginal + document.currentStatistics.totalUnitsReplica
            )
          "
          v-if="document.sourceLanguage != null && document.targetLanguage != null"
        />
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import DocumentStatisticsSegment from "@/components/DocumentStatisticsSegment.vue";
import { TranslationDocument } from "@/models/models";

export default Vue.extend({
  props: {
    document: {
      type: Object as PropType<TranslationDocument>,
      required: true
    }
  },
  data() {
    return {
      showMore: false
    };
  },
  methods: {
    toggleMore() {
      this.showMore = !this.showMore;
    },
    getWidthPercentage(segmentCount: number, totalSegmentCount: number): number {
      return (segmentCount / totalSegmentCount) * 100;
    }
  },
  components: {
    DocumentStatisticsSegment
  }
});
</script>

<style lang="scss"></style>
