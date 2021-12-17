<template>
  <section>
    <div class="m-4 pt-4" v-if="showHelp">
      <div class="grid grid-cols-1 md:grid-cols-2 md:gap-16 md:mt-3">
        <div class="xs:mb-4 sm:mb-4 md:mb-0 lg:mb-0">
          <div class="list-dotted dot-narrow dot-green text-lg font-medium">
            High quality segments
          </div>
          <div class="text-gray-800">
            Unique, quality segments that don't already exist in the Data Marketplace. They will be listed for sale on
            the Search and Sellers pages.
          </div>
        </div>
        <div class="xs:mb-4 sm:mb-4 md:mb-0 lg:mb-0 ml-8">
          <div class="list-dotted dot-narrow dot-turquoise text-lg font-medium">
            Replica segments
          </div>
          <div class="text-gray-800">
            Segments that already exist in the Data Marketplace. They will only be available for purchase on the
            Sellers' page.
          </div>
        </div>
        <div class="xs:mb-4 sm:mb-4 md:mb-0 lg:mb-0">
          <div class="list-dotted dot-narrow dot-red text-lg font-medium">
            Low quality segments
          </div>
          <div class="text-gray-800">
            Segments that have been flagged during the cleaning process. They will be removed from the document and not
            listed for sale.
          </div>
        </div>
        <div class="xs:mb-4 sm:mb-4 md:mb-0 lg:mb-0 ml-8">
          <div class="list-dotted dot-narrow dot-pink text-lg font-medium">
            Segments with possible PIIs
          </div>
          <div class="text-gray-800">
            Segments that contain possible personally identifiable information (PII). You can choose to remove or keep
            them in your document.
          </div>
        </div>
      </div>
    </div>
    <!--    <div v-if="showSamples" class="mb-4 pt-4">-->
    <!--      <div class="grid grid-row-2 h-full overflow-hidden pb-4">-->
    <!--        <div class="flex-auto overflow-hidden h-full">-->
    <!--          <div-->
    <!--            class="inline-block text-xs lg:text-lg text-red-700 tracking-widest bg-red-900 bg-opacity-10 border border-transparent rounded-xl p-1"-->
    <!--          >-->
    <!--            <strong>{{ document.units.length }}</strong>-->
    <!--          </div>-->
    <!--          <div class="inline-block ml-2 text-lg tracking-0.3 font-normal text-gray-900">Low-quality segments</div>-->
    <!--        </div>-->
    <!--        <div class="ml-8">-->
    <!--          <div class="text-sm text-gray-900 tracking-wider">-->
    <!--            <strong>{{ totalSourceLowQualityWordCountUnits }}</strong>-->
    <!--            {{ document.sourceLanguage.tag }} words-->

    <!--            <strong class="ml-3">{{ totalTargetLowQualityWordCountUnits }}</strong>-->
    <!--            {{ document.targetLanguage.tag }} words-->
    <!--          </div>-->
    <!--        </div>-->
    <!--      </div>-->

    <!--      <div class="overflow-y-auto" style="height: 12.8rem;">-->
    <!--        <table class="w-full table-fixed mt-4">-->
    <!--          <tbody>-->
    <!--            <tr-->
    <!--              class="h-14 text-base tracking-wide leading-snug odd:bg-red-200"-->
    <!--              v-for="(unit, index) in document.units"-->
    <!--              v-bind:key="index"-->
    <!--            >-->
    <!--              <td class="p-2">{{ unit.sourceValue }}</td>-->
    <!--              <td class="p-2">{{ unit.targetValue }}</td>-->
    <!--            </tr>-->
    <!--          </tbody>-->
    <!--        </table>-->
    <!--      </div>-->
    <!--    </div>-->
  </section>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import { TranslationDocument, TranslationUnit } from "@/models/models";

export default Vue.extend({
  props: {
    showHelp: {
      type: Boolean,
      required: true,
      default: false
    },
    showSamples: {
      type: Boolean,
      required: true,
      default: false
    },
    document: {
      type: Object as PropType<TranslationDocument>,
      required: true
    }
  },
  computed: {
    totalSourceLowQualityWordCountUnits() {
      return this.document.units.reduce((prev: number, cur: TranslationUnit): number => {
        return prev + parseInt(cur.sourceWordCount);
      }, 0);
    },
    totalTargetLowQualityWordCountUnits() {
      return this.document.units.reduce((prev: number, cur: TranslationUnit): number => {
        return prev + parseInt(cur.targetWordCount);
      }, 0);
    }
  }
});
</script>
