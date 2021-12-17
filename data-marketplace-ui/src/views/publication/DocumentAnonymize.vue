<template>
  <section class="md:mx-4 xs:mx-2">
    <div class="mb-4 pt-2">
      <div class="text-xl leading-30 tracking-0.44 font-medium text-gray-900 xs:text-lg xs:tracking-0.3">
        Anonymization
      </div>
      <div class="text-lg tracking-0.3 font-normal text-gray-900 xs:text-base xs:leading-relaxed xs:tracking-0.27 mt-2">
        The anonymization tool identifies segments containing possible Personally Identifiable Information (PII). You
        can choose to keep or remove those segments from the version of the document that will be published for sale.
      </div>
    </div>
    <div v-if="!languageSupportPII" class="mt-4 mb-6 flex flex-auto items-center p-4 bg-pink-900 bg-opacity-10">
      <div class="flex-initial p-1 sm:w-20 xs:w-32 xl:w-10">
        <img src="/assets/img/alert-circle-outline-purple.svg" />
      </div>
      <div class="text-gray-900 text-base leading-relaxed tracking-0.27 font-normal xs:text-sm  xs:tracking-0.23 ml-4">
        Your selected language pair is a tough one to anonymize. The identification and removal of PII are not available
        for it yet. Feel free to proceed with publishing or
        <a href="mailto:datamarketplace@taus.net" class="text-blue-700">contact us</a> if in doubt.
      </div>
    </div>
    <!--     change the whole sections calculations as per annotations -->
    <div v-else-if="languageSupportPII && document.annotations.length > 0" class="mt-6 mb-6">
      <div class="grid grid-row-2 h-full overflow-hidden">
        <div class="flex-auto overflow-hidden h-full">
          <div
            class="inline-block text-xs lg:text-lg text-pink-900 tracking-widest bg-pink-900 bg-opacity-10 border border-transparent rounded-xl p-1"
          >
            <strong>{{ document.currentStatistics.totalUnitsAnnotatedPii }}</strong>
          </div>
          <div
            class="inline-block ml-2 text-lg tracking-0.3 font-normal text-gray-900 xs:text-base xs:leading-relaxed xs:tracking-0.27"
          >
            Segments with possible PII
          </div>
        </div>
        <div class="ml-8">
          <div class="text-sm text-gray-900 tracking-wider xs:text-xs xs:leading-5 xs:tracking-0.2">
            <!--/// change to annotations word counts-->
            <strong>{{ document.currentStatistics.totalSourceWordCountAnnotatedPii }}</strong>
            {{ document.sourceLanguage.tag }} words
            <strong class="ml-3">{{ document.currentStatistics.totalTargetWordCountAnnotatedPii }}</strong>
            {{ document.targetLanguage.tag }} words
          </div>
        </div>
      </div>
      <div
        class="overflow-y-auto border-2 border-gray-300 rounded-md overflow-hidden sample-container mt-4"
        style="max-height: 17rem;"
      >
        <div v-for="annotation in document.annotations" :key="annotation.id" class="mx-2 no-last-border">
          <table class=" w-full table-fixed border-b-2 border-gray-300 border-separate">
            <thead>
              <tr class="text-sm tracking-0.23 font-medium">
                <th class=" col-span-2 flex flex-auto items-center p-2">
                  {{ annotation.description }} ({{ annotation.count }})
                  <img
                    src="/assets/img/chevron-down.svg"
                    alt="Show"
                    @click="toggle(annotation.code)"
                    class="w-3 h-2 ml-4 cursor-pointer toggle-button"
                    :class="opened.includes(annotation.code) ? 'active' : ''"
                  />
                </th>
              </tr>
            </thead>

            <tbody v-if="opened.includes(annotation.code)">
              <tr
                class="h-14 text-base tracking-wide leading-snug xs:text-xs xs:leading-5 xs:tracking-0.2 xs:break-all table-anonymize"
                v-for="item in annotation.units"
                :key="item.id"
              >
                <td
                  class="p-2 border-r-2 border-white"
                  v-html="makeBold(item.sourceValue, item.sourceAnnotations, annotation)"
                ></td>
                <td
                  class="p-2 border-l-2 border-white"
                  v-html="makeBold(item.targetValue, item.targetAnnotations, annotation)"
                ></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="grid md:grid-cols-4 grid-cols-1 items-center mt-4 px-6 py-3 bg-pink-900 bg-opacity-10">
        <div class="col-span-1 xs:text-center">
          <div>
            <!--/// to do the necessary calculations annotations size-->
            <span class="lg:text-34px leading-49 tracking-0.76 text-2xl xs:tracking-0.44 font-medium">
              {{ calcRemainingWords }}</span
            ><span class="text-lg tracking-0.3 font-medium md:ml-2">{{ document.sourceLanguage.tag }} words</span>
          </div>
          <div>
            <span class="text-lg tracking-0.3 font-normal md:ml-12">{{ calcRemainingSegments }} Segments</span>
          </div>
        </div>
        <div class="col-span-3 xs:mt-2 xs:text-center">
          <div class="text-xl leading-30 tracking-0.37 xs:text-base xs:leading-relaxed xs:tracking-0.27 font-medium">
            Word counts after anonymization
          </div>
          <div class="text-gray-900 text-lg tracking-0.3 xs:text-sm  xs:tracking-0.23 mt-2 font-normal">
            If you choose to anonymize this dataset, we will only publish the segments with no PII. This may affect the
            total word count and the final price of your dataset.
          </div>
        </div>
      </div>
      <div class="flex flex-wrap content-center pt-4">
        <input
          id="checkbox1"
          class="appearance-none cursor-pointer h-5 w-5 shadow rounded-sm bg-gray-100 border border-gray-300 checked:bg-blue-300 checked:border-blue-600 focus:outline-none"
          type="checkbox"
          v-model="checked"
          @change="updateInput"
        />
        <span
          class="cursor-pointer ml-2 text-base leading-relaxed tracking-0.27 xs:text-sm  xs:tracking-0.23 font-medium"
        >
          I would like to anonymize my dataset
        </span>
      </div>
    </div>
    <!-- --->
    <div v-else class="mt-4 mb-6 flex flex-auto items-center p-4 bg-blue-200">
      <div class="flex-initial lg:w-20 sm:w-32 xs:w-32 xl:w-12">
        <img src="/assets/img/steps-finished.svg" />
      </div>
      <div
        class="flex-auto text-gray-900 text-base leading-relaxed tracking-0.27 xs:text-sm  xs:tracking-0.23 font-normal ml-4"
      >
        It seems that your dataset is in good shape! We couldn't identify any PII. Please note that our tool is being
        continuously improved and might not catch all instances of what you could consider PII. You can proceed with
        publishing or <a href="mailto:datamarketplace@taus.net" class="text-blue-700">contact us</a> if in doubt.
      </div>
    </div>
    <div class="mt-16 grid md:grid-cols-3 grid-cols-2 gap-10">
      <div class="self-center">
        <BackToPreviousPage label="to Cleaning" @getBackToLastStep="backToCleaning" />
      </div>
      <div class="text-center">
        <Button @click="publishDocument" :isLoading="isLoading">
          Next
        </Button>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import Button from "@/components/Button.vue";
import BackToPreviousPage from "@/components/BackToPreviousPage.vue";
import { DataAnnotation, DataAnnotationCategoryCount, TranslationDocument, TranslationUnit } from "@/models/models";

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
    },
    isAnonymizeCheck: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      checked: false,
      category: "",
      showSamples: false,
      rotation: 0,
      remainingHighQualityWordCount: 0,
      opened: ["PER"] // write the code to get first value
    };
  },
  methods: {
    publishDocument() {
      this.$emit("setDocumentToAnonymize", { isPublish: true, isAnonymizeCheck: this.checked });
    },
    updateInput(event: UIEvent) {
      const isChecked = (event.target as HTMLInputElement).checked;
      this.$emit("change", isChecked);
    },
    toggleSamples() {
      this.showSamples = !this.showSamples;
    },
    backToCleaning() {
      this.$emit("getBackToLastStep", true);
    },
    toggle(code: string) {
      const index = this.opened.indexOf(code);
      if (index > -1) {
        this.opened.splice(index, 1);
      } else {
        this.opened.push(code);
      }
    },
    makeBold(unit: string, ann: Array<DataAnnotation>, cat: DataAnnotationCategoryCount) {
      if (ann) {
        const replaceArray: string[] = [];
        ann.forEach(value => {
          if (value.categoryCode == cat.code) {
            const rep = unit.substring(value.offsetStart, value.offsetEnd + 1);
            replaceArray.push(rep);
          }
        });
        replaceArray.forEach(item => {
          unit = unit.replace(item, item.bold());
        });
        return unit;
      }
      return unit;
    }
  },
  computed: {
    languageSupportPII(): boolean {
      return this.document.sourceLanguage.enabledPii || this.document.targetLanguage.enabledPii;
    },
    calcRemainingWords() {
      return (
        this.document.currentStatistics.totalSourceReplicaWordCount +
        this.document.currentStatistics.totalSourceOriginalWordCount -
        this.document.currentStatistics.totalSourceWordCountAnnotatedPii
      );
    },
    calcRemainingSegments() {
      return (
        this.document.currentStatistics.totalUnitsReplica +
        this.document.currentStatistics.totalUnitsOriginal -
        this.document.currentStatistics.totalUnitsAnnotatedPii
      );
    }
  },
  components: {
    Button,
    BackToPreviousPage
  },
  mounted() {
    this.checked = this.isAnonymizeCheck;
    //this.opened[0] = this.document.annotations[0].code;
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

.table-anonymize:nth-child(odd) {
  background-color: #cc33991a !important;
}

.no-last-border:last-child > table {
  border-bottom: 0;
}

.toggle-button.active {
  outline: none;
  transform: rotate(180deg);
}

.toggle-button {
  outline: none;
  transform: rotate(0deg);
}
</style>
