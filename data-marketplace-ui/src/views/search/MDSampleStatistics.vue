<template>
  <div>
    <div class="grid lg:grid-cols-4 md:cols-span-1 xs:cols-span-1 gap-x-6 md:gap-y-10 xs:gap-y-8 mt-12">
      <div class="lg:col-span-1 md:col-span-1 xs:col-span-1 items-stretch justify-items-center">
        <div class="border-2 xl:mt-8 lg:mt-14 rounded-md flex flex-col">
          <div class="lg:mb-3 md:mb-6 sm:mb-6 xs:mb-6">
            <div
              class="py-3 md:py-5 sm:py-5 xs:py-5 md:text-center xs:text-center lg:text-left lg:ml-12 text-lg tracking-0.3 font-normal"
            >
              <span>Statistics</span>
            </div>
            <div
              class="flex lg:flex-col md:flex-wrap md:gap-x-6 md:items-center md:justify-center xs:justify-center lg:justify-start lg:items-start gap-y-3"
            >
              <div class="">
                <div class="flex flex-row items-center">
                  <img src="/assets/img/language-alt.svg" class=" w-5 h-5 mx-4" />
                  <span>{{ sourceWordCount }}</span>
                </div>
                <span class="ml-12"> Source words</span>
              </div>
              <div class="">
                <div class="flex flex-row items-center">
                  <img src="/assets/img/language-target.svg" class="mx-4 w-5 h-4" />
                  <span>{{ targetWordCount }}</span>
                </div>
                <span class="ml-12"> Target words</span>
              </div>
              <div class="">
                <div class="flex flex-row items-center">
                  <img src="/assets/img/communication-segments.svg" class="mx-4 w-5 h-4" />
                  <span>{{ totalSegment }}</span>
                </div>
                <span class="ml-12"> Segments</span>
              </div>
            </div>
          </div>
          <div class="lg:mb-4 md:mb-5 xs:mb-5">
            <div
              class="border-t-2 mx-4 md:py-5 sm:py-5 xs:py-5 md:text-center xs:text-center lg:text-left text-lg tracking-0.3 font-normal"
            >
              <span class="md:text-center lg:ml-8">Dataset size</span>
            </div>
            <div
              class="grid lg:grid-cols-2 md:grid-cols-4 sm:grid-cols-4 xs:grid-cols-4 gap-y-3 ml-4 lg:justify-items-start md:justify-items-center "
            >
              <div v-for="(sizes, index) in datasetSizes" :key="index">
                <label class="flex items-center space-x-3 pr-5">
                  <input
                    type="radio"
                    :id="sizes"
                    :value="sizes"
                    v-model="fetchResultSet"
                    @click="fetchResult(sizes)"
                    class="appearance-none cursor-pointer h-5 w-5 shadow rounded-full border border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
                  />
                  <span class="font-normal cursor-pointer text-gray-900 text-base leading-relaxed tracking-0.27"
                    >{{ sizes }}%</span
                  >
                </label>
              </div>
            </div>
          </div>
          <div>
            <div
              class="border-t-2 mx-4 pl-8 py-3 flex xl:flex-row lg:flex-col flex-row md:justify-center md:gap-x-12 sm:justify-center sm:gap-x-12 xs:justify-center xs:gap-x-12 gap-x-4 items-center "
            >
              <div class="flex lg:flex-col md:flex-row xs:flex-row sm:flex-row text-lg tracking-0.3">
                <span class="font-normal lg:mr-0 md:mr-12 xs:mr-12">Price</span>
                <div class="font-medium">{{ price | currency2Decimals }}</div>
              </div>

              <div class="">
                <ShoppingCartButton
                  :product="{ requestId: requestId, type: productType(), amount: fetchResultSet / 100 }"
                  :isLabelVisible="false"
                  :customStyling="'p-0 rounded-full w-11 h-11 mx-auto'"
                />
              </div>
            </div>
          </div>
        </div>
        <div class="lg:block hidden">
          <div class="mt-16 relative text-center">
            <img class="md:invisible lg:visible" src="/assets/img/background-contactus.png" />
            <div class=" absolute xl:top-5 lg:top-2 xl:p-5 lg:p-2 ">
              <span class="xl:text-lg lg: text-base tracking-0.3 font-medium text-gray-100"
                >Need further customization before purchasing?</span
              >
              <button
                class="inline-flex items-center bg-gray-100 border border-transparent text-lg text-gray-900 tracking-0.3 font-medium focus:outline-none mt-8 h-14 py-3 px-8 rounded"
                @click="goToContactPage"
              >
                Contact us
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="lg:col-span-3">
        <p class="mb-2 text-base leading-relaxed tracking-0.27 font-normal text-gray-900">
          This is an example of data that we've matched based on your provided sample.
        </p>
        <div class="sample-container border-2 rounded-md slim-scrollbar static-bar">
          <div class="space-y-1.5 p-2 mb-2" v-for="(sample, index) in sampleUnits" :key="index">
            <div class="grid grid-cols-2 xs:gap-1 sm:gap-1.5 rounded">
              <div
                class="col-span-1 rounded xs:p-1 sm:px-3 sm:py-2"
                :class="isOdd(index) ? 'bg-blue-200 bg-opacity-75' : ''"
              >
                <span
                  class="xs:text-0.95 xs:leading-3 xs:tracking-0 sm:text-base sm:leading-5 sm:tracking-0.2 xs:break-words"
                >
                  {{ sample.sourceValue }}
                </span>
              </div>
              <div
                class="col-span-1 rounded xs:p-1 sm:px-3 sm:py-2"
                :class="isOdd(index) ? 'bg-blue-200 bg-opacity-75' : ''"
              >
                <span
                  class="xs:text-0.95 xs:leading-3 xs:tracking-0 sm:text-base sm:leading-5 sm:tracking-0.2 xs:break-words"
                >
                  {{ sample.targetValue }}
                </span>
              </div>
            </div>
          </div>
        </div>
        <div class="bg-gray-200 w-full p-3">
          <div class="flex flex-row justify-center items-center">
            <span class="mr-10 text-lg tracking-0.3 font-normal">Dataset size</span>
            <div v-for="(sizes, index) in datasetSizes" :key="index">
              <label class="flex items-center space-x-3 pr-5">
                <input
                  type="radio"
                  :id="sizes"
                  :value="sizes"
                  v-model="fetchResultSet"
                  @click="fetchResult(sizes)"
                  class="appearance-none cursor-pointer h-5 w-5 bg-gray-100 shadow rounded-full border border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
                />
                <span class="font-normal cursor-pointer text-gray-900 text-lg tracking-0.3">{{ sizes }}%</span>
              </label>
            </div>
          </div>
        </div>
        <div class=" border-t-2 border-gray-100 bg-gray-200 w-full">
          <div class="h-24 grid grid-cols-2 items-stretch justify-items-center">
            <span class="self-center text-xl leading-1.875 xs:tracking-0.2 sm:tracking-0.37 xl:mr-4">Price:</span>
            <div class="self-center flex flex-row gap-x-10 items-center">
              <span class=" text-xl lg:text-1.625 leading-2.25 xs:tracking-0.2 sm:tracking-0.48 font-medium">{{
                price | currency2Decimals
              }}</span>
              <ShoppingCartButton
                :product="{ requestId: requestId, type: productType(), amount: fetchResultSet / 100 }"
                :isLabelVisible="responsiveCartBtn"
                :customStyling="responsiveCartBtn ? '' : 'p-0 rounded-full w-11 h-11 mx-auto'"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="lg:hidden">
      <div class="mt-16 relative text-center">
        <img src="/assets/img/background-contactus-medium.png" />
        <div class=" absolute top-3 p-5 ">
          <span class="text-lg tracking-0.3 font-medium text-gray-100"
            >Need further customization before purchasing?</span
          >
          <button
            class="inline-flex items-center bg-gray-100 border border-transparent text-lg text-gray-900 tracking-0.3 font-medium focus:outline-none mt-8 xs:mt-4 h-14 py-3 px-8 rounded"
            @click="goToContactPage"
          >
            Contact us
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import { TranslationUnit } from "@/models/models";
import ShoppingCartButton from "@/components/shopping-cart/ShoppingCartButton.vue";
import { ProductType } from "@/models/models";

export default Vue.extend({
  name: "MDSampleStatistics",
  props: {
    resultStats: {
      type: Array,
      required: false
    },
    sampleUnits: {
      type: Array as PropType<Array<TranslationUnit>>,
      required: false
    },
    requestId: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      totalSegment: Number,
      sourceWordCount: Number,
      targetWordCount: Number,
      price: Number,
      size: 100,
      datasetSizes: [100, 75, 50, 25]
    };
  },
  computed: {
    fetchResultSet(): number {
      for (let i = 0; i < (this as any).resultStats.length; i++) {
        if ((this as any).size / 100 == (this as any).resultStats[i].datasetSize.amount) {
          (this as any).totalSegment = (this as any).resultStats[i].metaData.segmentCount;
          (this as any).sourceWordCount = (this as any).resultStats[i].metaData.sourceWordCount;
          (this as any).targetWordCount = (this as any).resultStats[i].metaData.targetWordCount;
          (this as any).price = (this as any).resultStats[i].metaData.price;
        }
      }
      return (this as any).size;
    },
    responsiveCartBtn(): boolean {
      return window.innerWidth >= 1023;
    }
  },
  methods: {
    goToContactPage() {
      window.open("/contact-us");
    },
    fetchResult(size: number) {
      this.size = size;
    },
    isOdd(index: number) {
      return (index + 1) % 2 == 1;
    },
    productType() {
      return ProductType.MATCHING_DOCUMENT;
    }
  },
  components: {
    ShoppingCartButton
  }
});
</script>

<style lang="scss" scoped>
.sample-container {
  overflow: auto;
  max-height: 100vh;
}
</style>
