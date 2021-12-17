<template>
  <div class="md:w-3/4 mx-auto bg-gray-100 xs:mt-10 sm:mt-8 md:mt-20 lg:mt-20 xs:px-4 sm:px-0 mb-32">
    <div class="flex flex-col md:w-3/4 xs:mx-4 mx-auto relative">
      <div class="mx-auto mb-10">
        <SectionTitle title="MATCH YOUR DATA" />
      </div>
      <div class="md:w-3/4 mb-20 xs:mb-10 mx-auto text-center md:p-4 ">
        <span class="text-34px leading-49 tracking-0.76 font-medium xs:text-2xl xs:tracking-0.44">
          Ensure the Best Results with Custom-Made Datasets
        </span>
        <br />
        <span class="text-lg tracking-0.3 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27">
          Matching the unique characteristics of your sample, domain and content type using clustered search
          technology</span
        >
      </div>
    </div>
    <div v-if="showLess" class="w-full mb-12">
      <div class="text-blue-700 relative right-4 top-2 float-right cursor-pointer" @click="showLess = !showLess">
        <img src="/assets/img/close.svg" />
      </div>
      <div class="px-6 xs:px-4 bg-blue-200 border-blue-700 border p-3 flex flex-row items-center rounded-sm">
        <img src="/assets/img/info.svg" alt="Info" class="flex-initials mr-6 w-6 h-6" />
        <span class="text-base leading-relaxed tracking-0.27 font-normal"
          >In case the search takes longer, we’ll use your provided email
          <strong class="text-blue-700">{{ matchingDataInternal.email }} </strong>to notify you when the results become
          available. Meanwhile you can wait for the search results right here or explore the rest of the website.</span
        >
      </div>
    </div>
    <div class="grid lg:grid-cols-3 md:grid-cols-1 xs:grid-cols-1 gap-6 mb-12 xs:mb-6">
      <div class="col-span-1 border border-gray-300 flex flex-col bg-blue-200">
        <div class=" flex flex-row pt-6 text-lg tracking-0.3 font-medium">
          <img src="/assets/img/language-alt.svg" class="ml-6 mr-5" /><span>Languages</span>
        </div>
        <div
          class="flex flex-row gap-x-5 mt-5 ml-16 items-center text-xl leading-30 tracking-0.37 font-normal capitalize pb-16"
        >
          <span>{{ matchingDataInternal.sourceLanguage.displayName }}</span>
          <img src="/assets/img/arrow-translate.svg" /><span>{{
            matchingDataInternal.targetLanguage.displayName
          }}</span>
        </div>
      </div>
      <div class="col-span-1 lg:col-span-2 p-6 border border-gray-300 flex flex-col bg-gray-100">
        <div class=" flex flex-row pb-3 text-lg tracking-0.3 font-medium">
          <img src="/assets/img/file-outline.svg" class="mr-5" /><span>Your uploaded sample</span>
        </div>
        <div
          class="grid grid-cols-2 xs:grid-cols-1 xs:gap-y-2 md:gap-x-10 md:gap-y-3 text-lg leading-30 tracking-0.37 font-normal ml-10"
        >
          <div class="overflow-ellipsis overflow-hidden whitespace-nowrap">
            Doc name: {{ matchingDataInternal.fileName }}
          </div>
          <div class="">Format: {{ matchingDataInternal.fileName.split(".").pop() }}</div>
          <div class="">Segments: {{ matchingDataInternal.totalUnits }}</div>
          <div class="capitalize">Type: {{ matchingDataInternal.type.toLowerCase() }}</div>
        </div>
      </div>
    </div>

    <div v-if="hasError">
      <Alert v-if="hasError" :messages="[errorDescription]" type="DANGER" />
    </div>
    <div v-else-if="!hasError && isAnalyzing"><MDSearchResultLoading /></div>
    <div v-else-if="isMatching">
      <MDSampleStatistics
        :resultStats="matchingDataInternal.availableSubsets"
        :sampleUnits="matchingDataInternal.sampleUnits"
        :requestId="matchingDataInternal.requestId"
      />
    </div>
  </div>
</template>

<script lang="ts">
import SectionTitle from "@/components/layout/SectionTitle.vue";
import Alert from "@/components/Alert.vue";
import MDSampleStatistics from "@/views/search/MDSampleStatistics.vue";
import { MatchingData } from "@/models/models";
import Vue, { PropType } from "vue";
import MDSearchResultLoading from "@/views/search/MDSearchResultLoading.vue";
export default Vue.extend({
  props: {
    matchingData: {
      type: Object as PropType<MatchingData>,
      required: true
    }
  },
  data() {
    const mdparse = MatchingData.parse(this.matchingData);
    return {
      lang: mdparse.sourceLanguage,
      matchingDataInternal: mdparse,
      showLess: true,
      errorDescription: null,
      statusTimer: null
    };
  },
  mounted() {
    if (
      (this.matchingDataInternal.status == "RECEIVED" ||
        this.matchingDataInternal.status == "ANALYZING" ||
        this.matchingDataInternal.status == "MATCHING") &&
      !this.matchingDataInternal.hasErrors() &&
      !this.matchingDataInternal.hasResults()
    ) {
      this.checkStatus(this.matchingDataInternal);
    } else if (this.matchingDataInternal.status == "COMPLETED" || this.matchingDataInternal.status == "FAILED") {
      console.log("Process completed");
    }
  },
  beforeDestroy() {
    clearTimeout(this.statusTimer);
  },
  computed: {
    hasError(): boolean {
      const length = this.matchingDataInternal.allEvents.length;
      if (this.matchingDataInternal.hasErrors()) {
        (this as any).errorDescription = this.matchingDataInternal.allEvents[length - 1].description;
        return true;
      } else {
        return false;
      }
    },
    isAnalyzing(): boolean {
      const length = this.matchingDataInternal.allEvents.length;
      if (
        !this.hasError &&
        (this.matchingDataInternal.allEvents[length - 1].status == "RECEIVED" ||
          this.matchingDataInternal.allEvents[length - 1].status == "ANALYZING") &&
        this.matchingDataInternal.allEvents[length - 1].type != "ERROR"
      ) {
        return true;
      } else {
        return false;
      }
    },
    isMatching(): boolean {
      if (!this.matchingDataInternal.hasErrors() || this.matchingDataInternal.hasResults()) {
        return true;
      } else {
        return false;
      }
    }
  },
  methods: {
    checkStatus(matchingdata: MatchingData) {
      this.statusTimer = setTimeout(() => {
        this.$store
          .dispatch("matchingDataSearch/fetchStatus", {
            id: matchingdata.requestId,
            currentStatus: matchingdata.status
          })
          .then(mdObject => {
            if (mdObject.requestId) {
              this.matchingDataInternal = mdObject;
              const hasErrors = mdObject.hasErrors();
              if (
                (mdObject.status == "MATCHING" || mdObject.status == "ANALYZING" || mdObject.status == "RECEIVED") &&
                !hasErrors
              ) {
                this.checkStatus(mdObject);
              } else if (mdObject.status == "COMPLETED" || mdObject.status == "FAILED") {
                console.log("Process completed");
              }
            } else {
              this.checkStatus(this.matchingDataInternal);
            }
          })
          .catch(() => {
            this.checkStatus(this.matchingDataInternal);
          });
      }, 10000);
    }
  },
  components: {
    Alert,
    SectionTitle,
    MDSampleStatistics,
    MDSearchResultLoading
  }
});
</script>
