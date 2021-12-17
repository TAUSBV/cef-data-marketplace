<template>
  <section class="mx-4">
    <div class="mb-4 pt-2">
      <div>
        <div class="text-xl leading-30 tracking-0.44 font-medium text-gray-900 xs:text-lg xs:tracking-0.3">
          Cleaning
        </div>
        <div
          class="text-lg tracking-0.3 font-normal text-gray-900 mt-2 xs:text-base xs:leading-relaxed xs:tracking-0.27"
        >
          The cleaning algorithm identifies low-quality segments in your dataset, such as incorrect or misaligned
          translations. They will be automatically removed from the version of the dataset that will be published for
          sale.
        </div>
      </div>
      <div v-if="document.units.length > 0">
        <div class="grid grid-row-2 h-full overflow-hidden mb-4 mt-6">
          <div class="flex-auto overflow-hidden h-full">
            <div
              class="inline-block text-xs lg:text-lg text-red-700 tracking-widest bg-red-900 bg-opacity-10 border border-transparent rounded-xl p-1"
            >
              <strong>{{ document.units.length }}</strong>
            </div>
            <div
              class="inline-block ml-2 text-lg tracking-0.3 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27 text-gray-900"
            >
              Low-quality segments
            </div>
          </div>
          <div class="ml-8">
            <div class="text-sm text-gray-900 xs:text-xs xs:leading-5 xs:tracking-0.2 tracking-wider">
              <strong>{{ totalSourceLowQualityWordCountUnits }}</strong>
              {{ document.sourceLanguage.tag }} words

              <strong class="ml-3">{{ totalTargetLowQualityWordCountUnits }}</strong>
              {{ document.targetLanguage.tag }} words
            </div>
          </div>
        </div>
        <div class="overflow-y-auto sample-container" style="height: 12.8rem;">
          <table class="w-full table-fixed mt-4">
            <tbody>
              <tr
                class="h-14 text-base tracking-wide leading-snug table-clean"
                v-for="(unit, index) in document.units"
                v-bind:key="index"
              >
                <td class="p-2 border-r-4 border-gray-100">
                  {{ unit.sourceValue }}
                </td>
                <td class="p-2 border-l-2 border-gray-100">{{ unit.targetValue }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div v-else class="mt-4 mb-6 flex flex-auto items-center p-4 bg-blue-200">
        <div class="flex-initial lg:w-8 md:w-14 ">
          <img src="/assets/img/steps-finished.svg" />
        </div>
        <div
          class="flex-auto text-gray-900 text-base leading-relaxed tracking-0.27 xs:text-sm  xs:tracking-0.23 font-normal ml-4"
        >
          Great news! We couldn't identify any low-quality segments in your dataset. Feel free to proceed with
          publishing or
          <a href="mailto:datamarketplace@taus.net" class="text-blue-700">contact us</a> if in doubt.
        </div>
      </div>
      <div class="px-2 md:px-12 text-center">
        <Button @click="cleanDocument" :isLoading="isLoading">
          Next
        </Button>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import { TranslationDocument, TranslationUnit } from "@/models/models";
import Button from "@/components/Button.vue";

export default Vue.extend({
  props: {
    isLoading: {
      type: Boolean,
      required: false,
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
  },
  methods: {
    cleanDocument() {
      this.$emit("setDocumentToClean", true);
    }
  },
  components: {
    Button
  }
});
</script>

<style lang="scss">
/* width */
.sample-container::-webkit-scrollbar {
  width: 3px;
}

/* Track */
.sample-container::-webkit-scrollbar-track {
  background: #f2f2f5;
}

/* Handle */
.sample-container::-webkit-scrollbar-thumb {
  background: #888;
}

/* Handle on hover */
.sample-container::-webkit-scrollbar-thumb:hover {
  background: #e4e4eb;
}
.table-clean:nth-child(odd) {
  background-color: #e657651a !important ;
}
</style>
