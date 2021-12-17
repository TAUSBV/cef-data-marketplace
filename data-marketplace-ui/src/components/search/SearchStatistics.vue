<template>
  <main class="lg:w-full xl:w-full 2xl:w-9/12 mx-auto" v-if="result && result.statistics">
    <section class="w-full sm:border border-gray-300 rounded">
      <div class="xs:flex xs:justify-center" v-if="!isLoading">
        <div
          class="rounded
            xs:block xs:overflow-y-auto xs:p-0
            sm:block sm:overflow-y-auto sm:p-5
            md:block md:overflow-y-auto md:p-5
            lg:flex lg:items-center lg:p-5 xl:p-8 2xl:p-8"
        >
          <div
            class="flex items-center ml-0 mr-auto 
                xs:w-full xs:mb-5 
                sm:w-6/12 sm:float-left sm:mb-5 
                md:w-6/12 md:float-left md:mb-5 
                lg:w-min lg:mb-0"
          >
            <div
              class="bg-blue-700 bg-opacity-20 rounded flex items-center justify-center 
                xs:mr-3 sm:mr-3 md:mr-3 lg:mr-3 xl:mr-5 2xl:mr-5 
                w-3.75 min-w-3.75 h-3.75 min-h-3.75"
            >
              <img class="w-6 min-w-6" src="/assets/img/language-alt.svg" />
            </div>
            <div class="xs:w-full lg:w-min xl:w-max 2xl:w-max lg:pr-3 xl:pr-5 2xl:pr-5">
              <span class="md:text-xl lg:text-2xl leading-8 tracking-wide font-medium block">
                {{ result.statistics.sourceWordCountTotal | integer }}
              </span>
              <span class="text-lg leading-7 tracking-wider block" style="letter-spacing: 0.3px;">
                {{ removeLanguageCountry(result.statistics.sourceLanguage.displayName) }} words
              </span>
            </div>
          </div>

          <div
            class="flex items-center mx-auto 
                xs:w-full xs:mb-5 
                sm:w-6/12 sm:float-left sm:mb-5 
                md:w-6/12 md:float-left md:mb-5 
                lg:w-min lg:mb-0"
          >
            <div
              class="bg-blue-700 bg-opacity-20 rounded flex items-center justify-center 
                xs:mr-3 sm:mr-3 md:mr-3 lg:mr-3 xl:mr-5 2xl:mr-5 
                w-3.75 min-w-3.75 h-3.75 min-h-3.75"
            >
              <img class="w-6 min-w-6" src="/assets/img/language-target.svg" />
            </div>
            <div class="xs:w-full lg:w-min xl:w-max 2xl:w-max lg:pr-3 xl:pr-5 2xl:pr-5">
              <span class="md:text-xl lg:text-2xl leading-8 tracking-wide font-medium block">
                {{ result.statistics.targetWordCountTotal | integer }}
              </span>
              <span class="text-lg leading-7 tracking-wider block" style="letter-spacing: 0.3px;">
                {{ removeLanguageCountry(result.statistics.targetLanguage.displayName) }} words
              </span>
            </div>
          </div>

          <div
            class="flex items-center mx-auto 
                xs:w-full xs:mb-5 
                sm:w-6/12 sm:float-left 
                md:w-6/12 md:float-left 
                lg:w-min"
          >
            <div
              class="bg-blue-700 bg-opacity-20 rounded flex items-center justify-center 
                xs:mr-3 sm:mr-3 md:mr-3 lg:mr-3 xl:mr-5 2xl:mr-5 
                w-3.75 min-w-3.75 h-3.75 min-h-3.75"
            >
              <img class="w-6 min-w-6" src="/assets/img/file-outline.svg" />
            </div>
            <div class="xs:w-full lg:w-min xl:w-max 2xl:w-max lg:pr-3 xl:pr-5 2xl:pr-5">
              <span class="md:text-xl lg:text-2xl leading-8 tracking-wide font-medium block">
                {{ result.statistics.documents }}
              </span>
              <span class="text-lg leading-7 tracking-wider block" style="letter-spacing: 0.3px;">
                Document<span v-if="result.statistics.documents > 1">s</span>
              </span>
            </div>
          </div>

          <div
            class="flex items-center mx-auto 
                xs:w-full
                sm:w-6/12 sm:float-left 
                md:w-6/12 md:float-left 
                lg:w-min"
          >
            <div
              class="bg-blue-700 bg-opacity-20 rounded flex items-center justify-center 
                xs:mr-3 sm:mr-3 md:mr-3 lg:mr-3 xl:mr-5 2xl:mr-5 
                w-3.75 min-w-3.75 h-3.75 min-h-3.75"
            >
              <img class="w-6 min-w-6" src="/assets/img/user-blue.svg" />
            </div>
            <div class="xs:w-full lg:w-min xl:w-max 2xl:w-max lg:pr-3 xl:pr-5 2xl:pr-5">
              <span class="md:text-xl lg:text-2xl leading-8 tracking-wide font-medium block">
                {{ result.statistics.sellersTotal }}
              </span>
              <span class="text-lg leading-7 tracking-wider block" style="letter-spacing: 0.3px;">
                Seller<span v-if="result.statistics.sellersTotal > 1">s</span>
              </span>
            </div>
          </div>
        </div>
      </div>
      <!-- Loading placeholder -->
      <ExploreStatisticsLoading v-else />
    </section>
  </main>
</template>

<script lang="ts">
import Vue from "vue";
import { computed, ref, watch } from "@vue/composition-api";
import ExploreStatisticsLoading from "@/views/explorer/ExplorerStatisticsLoading.vue";
import explorer from "@/services/Explorer";
import { ProductType } from "@/models/models";

export default Vue.extend({
  name: "SearchStatistics",
  setup() {
    const { result, getVirtualProduct, isLoading } = explorer();
    const state = ref({
      result: null
    });

    const removeLanguageCountry = function(languangeName: string) {
      return languangeName.replace(/ *\([^)]*\) */g, "");
    };

    const productVirtual = computed(() => {
      return { type: ProductType.VIRTUAL_DOCUMENT, ...getVirtualProduct() };
    });

    const updateResult = async () => {
      state.value.result = result;
    };

    watch(result, updateResult);

    return {
      result: computed(() => state.value.result),
      productVirtual: productVirtual,
      isLoading: isLoading,
      removeLanguageCountry: removeLanguageCountry
    };
  },
  components: {
    ExploreStatisticsLoading
  }
});
</script>

<style lang="scss" scoped></style>
