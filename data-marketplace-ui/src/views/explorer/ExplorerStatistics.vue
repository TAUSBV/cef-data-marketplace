<template>
  <main class="mt-16" v-if="result && result.statistics">
    <div class="w-3/4 mx-auto grid grid-cols-12 gap-0 mb-3">
      <div class="col-span-10">
        <span class="text-lg tracking-1.6 font-normal xs:text-base xs:leading-relaxed xs:tracking-wide "
          >STATISTICS</span
        >
      </div>
      <div class="col-span-2">
        <span class="text-lg tracking-1.6 font-normal xs:text-base xs:leading-relaxed xs:tracking-wide">PRICE</span>
      </div>
    </div>
    <section class="w-full bg-blue-200 bg-opacity-50 py-4">
      <div v-if="!isLoading" class="w-3/4 mx-auto grid grid-cols-12 gap-0 bg-gray-100 rounded px-10 py-8 shadow-md">
        <div
          class="xs:col-span-12 sm:col-span-12 md:col-span-12 lg:col-span-10 grid grid-cols-4 gap-4 items-center pr-8"
        >
          <div class="flex xs:col-span-4 sm:col-span-2 md:col-span-2 lg:col-span-1 xs:w-full">
            <div class="block">
              <img
                class="w-8 h-8 mt-2 mr-4"
                style="min-width: 32px; min-height: 32px;"
                src="/assets/img/file-outline.svg"
                alt="file outline"
              />
            </div>
            <div class="block xs:w-full xs:text-center">
              <span class="text-34px leading-49 tracking-0.76 xs:text-2xl xs:tracking-0.44 font-medium block">
                {{ result.statistics.documents }}
              </span>
              <span class="text-lg text-gray-800 tracking-wider block">
                Document<span v-if="result.statistics.documents > 1">s</span>
              </span>
            </div>
          </div>
          <div class="flex xs:col-span-4 sm:col-span-2 md:col-span-2 lg:col-span-1">
            <div class="block">
              <img
                class="w-8 h-8 mt-2 mr-4"
                style="min-width: 32px; min-height: 32px;"
                src="/assets/img/user-outline-blue-icon.svg"
                alt="file outline"
              />
            </div>
            <div class="block xs:w-full xs:text-center">
              <span class="text-34px leading-49 tracking-0.76 xs:text-2xl xs:tracking-0.44  font-medium block">
                {{ result.statistics.sellersTotal }}
              </span>
              <span class="text-lg text-gray-800 tracking-wider block">
                Seller<span v-if="result.statistics.sellersTotal > 1">s</span>
              </span>
            </div>
          </div>
          <div class="flex xs:col-span-4 sm:col-span-2 md:col-span-2 lg:col-span-1">
            <div class="block">
              <img
                class="w-8 h-8 mt-2 mr-4"
                style="min-width: 32px; min-height: 32px;"
                src="/assets/img/donut-blue-icon.svg"
                alt="file outline"
              />
            </div>
            <div class="block xs:w-full xs:text-center">
              <span class="text-34px leading-49 tracking-0.76 xs:text-2xl xs:tracking-0.44 font-medium block">
                {{ result.statistics.sourceWordCountTotal | integer }}
              </span>
              <span class="text-lg text-gray-800 tracking-wider block">
                {{ result.statistics.sourceLanguage.displayName }} words
              </span>
            </div>
          </div>
          <div class="xs:col-span-4 sm:col-span-2 md:col-span-2 lg:col-span-1 xs:w-full xs:text-center">
            <div>
              <div class="text-lg text-gray-800 font-medium block mr-2">
                {{ result.statistics.targetWordCountTotal | integer }}
              </div>
              <div class="text-lg text-gray-800 block">{{ result.statistics.targetLanguage.displayName }} words</div>
            </div>
            <div>
              <div class="text-lg text-gray-800 font-medium block mr-2">
                {{ result.statistics.unitsTotal | integer }}
              </div>
              <div class="text-lg text-gray-800 block">total segments</div>
            </div>
          </div>
        </div>
        <div
          class="xs:col-span-12 sm:col-span-12 md:col-span-12 lg:col-span-2 items-center lg:pl-8 lg:border-l border-gray-200 xs:mt-10 sm:mt-10 md:mt-0 lg:mt-0 w-full xs:text-center sm:text-center"
        >
          <span
            class="text-26px leading-9 tracking-0.48 xs:text-xl xs:leading-30 xs:tracking-0.37 font-medium block mb-4"
          >
            {{ result.statistics.totalPrice | currency2Decimals }}
          </span>
          <ShoppingCartButton :product="productVirtual" :disabled="true" />
          (Coming Soon)
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
import ShoppingCartButton from "@/components/shopping-cart/ShoppingCartButton.vue";
import ExploreStatisticsLoading from "@/views/explorer/ExplorerStatisticsLoading.vue";
import explorer from "@/services/Explorer";
import { ProductType } from "@/models/models";

export default Vue.extend({
  name: "ExploreStatistics",
  setup() {
    const { result, getVirtualProduct, isLoading } = explorer();
    const state = ref({
      result: null
    });

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
      isLoading: isLoading
    };
  },
  components: {
    ExploreStatisticsLoading,
    ShoppingCartButton
  }
});
</script>

<style lang="scss" scoped></style>
