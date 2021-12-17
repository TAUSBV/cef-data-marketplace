<template>
  <section>
    <div class="bg-gray-100 xs:mt-10 sm:mt-8 md:mt-20 lg:mt-20 xs:px-4 sm:px-0">
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
        <div class="mx-auto border-2 rounded mb-20 xs:mb-16 xl:w-3/4 w-full ">
          <MatchingDataSampleUpload :learnMore="true" />
        </div>
      </div>
    </div>
    <section class="bg-blue-200">
      <div class="mx-auto md:w-3/4 xs:px-4 pt-16 xs:pt-8 pb-56 xs:pb-60">
        <SectionTitle title="MATCHING DATA TECHNOLOGY" />
        <div
          class="text-34px leading-49 text-center mt-10 tracking-0.76 mb-20 xs:mb-10 font-medium sm:text-2xl sm:tracking-0.44 xs:text-2xl xs:tracking-0.44"
        >
          In-domain Data Search Made Easy
        </div>
        <div class="grid lg:grid-cols-2 sm:grid-cols-1 md:gap-x-8 xs:gap-y-8">
          <div class="cols-span-1">
            <img src="/assets/img/md-search-result.png" />
          </div>
          <div class="cols-span-1">
            <div class="flex flex-col xl:mt-4">
              <div class="flex flex-row gap-7 items-start mb-7">
                <img class="flex-1 mt-2 w-9 h-7" src="/assets/img/icons-action-search-blue.svg" />
                <div class="flex-col">
                  <h2 class="text-xl leading-30 tracking-0.37 xs:text-lg xs:tracking-0.3 font-medium">
                    High-performance clustered search
                  </h2>
                  <span class="mt-2 text-lg tracking-0.3 xs:text-base xs:leading-relaxed xs:tracking-0.27 font-normal"
                    >Finds the most relevant segments for your sample across millions of sentences</span
                  >
                </div>
              </div>
              <div class="flex flex-row gap-7 items-start mb-7">
                <img class="flex-1 mt-2 w-9 h-7" src="/assets/img/language.svg" />
                <div class="flex-col">
                  <h2 class="text-xl leading-30 tracking-0.37 xs:text-lg xs:tracking-0.3  font-medium">
                    Integrated sentence embeddings
                  </h2>
                  <span class="mt-2 text-lg tracking-0.3 xs:text-base xs:leading-relaxed xs:tracking-0.27  font-normal"
                    >Mines the text and computes the semantic similarity between segments in the same language</span
                  >
                </div>
              </div>
              <div class="flex flex-row gap-7 items-start">
                <img class="flex-1 mt-2 w-9 h-7" src="/assets/img/icons-action-creation.svg" />
                <div class="flex-col">
                  <h2 class="text-xl leading-30 tracking-0.37 xs:text-lg xs:tracking-0.3 font-medium">
                    Automated dataset creation
                  </h2>
                  <span class="mt-2 text-lg tracking-0.3 xs:text-base xs:leading-relaxed xs:tracking-0.27 font-normal"
                    >Selects the data on-the-fly and presents you with the sample, volumes and prices</span
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section>
      <!--      // Commented as endpoint is not available yet.-->
      <!--      <div class="xl:w-3/4 md:w-full lg:w-3/4 xs:w-3/4 md:-mt-32 xs:-mt-48 mx-auto bg-gray-100 shadow">-->
      <!--        <div-->
      <!--          class="md:py-16 h-full flex md:flex-row xs:flex-col justify-center xs:items-center md:gap-x-10 lg:gap-x-10 xl:gap-x-28"-->
      <!--        >-->
      <!--          <div class="flex flex-col xs:py-8 xs:items-center xs:border-b-2">-->
      <!--            <div-->
      <!--              class="text-5xl leading-5 tracking-wide xs:text-26px xs:leading-9 xs:tracking-0.48 font-medium text-purple-900 md:py-4 align-top "-->
      <!--            >-->
      <!--              00000-->
      <!--            </div>-->
      <!--            <hr class="bg-yellow-900 w-8 h-1 md:mt-4 md:my-4 xs:my-2" />-->
      <!--            <span class="text-2xl tracking-0.44 font-medium xs:text-xl xs:leading-30 xs:tracking-0.37"-->
      <!--              >Million segments</span-->
      <!--            >-->
      <!--          </div>-->

      <!--          <div class="flex flex-col xs:py-8 xs:items-center xs:border-b-2">-->
      <!--            <div-->
      <!--              class="text-5xl leading-5 tracking-wide xs:text-26px xs:leading-9 xs:tracking-0.48  font-medium text-purple-900 py-4 align-top "-->
      <!--            >-->
      <!--              00000-->
      <!--            </div>-->
      <!--            <hr class="bg-yellow-900 w-8 h-1 md:mt-4 md:my-4 xs:my-2" />-->
      <!--            <span class="text-2xl tracking-0.44 font-medium xs:text-xl xs:leading-30 xs:tracking-0.37"-->
      <!--              >Language pairs</span-->
      <!--            >-->
      <!--          </div>-->

      <!--          <div class="flex flex-col xs:py-8 xs:items-center xs:border-b-2">-->
      <!--            <div-->
      <!--              class="text-5xl leading-5 tracking-wide xs:text-26px xs:leading-9 xs:tracking-0.48  font-medium text-purple-900 py-4 align-top "-->
      <!--            >-->
      <!--              100+-->
      <!--            </div>-->
      <!--            <hr class="bg-yellow-900 w-8 h-1 md:mt-4 md:my-4 xs:my-2" />-->
      <!--            <span class="text-2xl tracking-0.44 font-medium xs:text-xl xs:leading-30 xs:tracking-0.37"-->
      <!--              >Tailored datasets</span-->
      <!--            >-->
      <!--          </div>-->
      <!--        </div>-->
      <!--      </div>-->

      <div class="lg:w-3/4 md:px-4 xs:px-4 mx-auto">
        <div class="md:mt-24 xs:mt-16 mb-10">
          <SectionTitle title="HOW IT WORKS" />
        </div>
        <div class="md:mb-16 xs:mb-5 mx-auto text-center md:p-4 ">
          <span class="text-34px leading-49 tracking-0.76 font-medium xs:text-2xl xs:tracking-0.44">
            Get your domain-specific training data <br />
            in a few easy steps
          </span>
        </div>
        <div class="md:pb-10 xs:pb-5">
          <div
            v-if="showMore"
            @click="showMore = !showMore"
            class="flex flex-row justify-items-end pl-10 float-right cursor-pointer"
          >
            <span class="text-lg mr-2 tracking-0.3 font-normal text-blue-700">Hide steps</span>
            <img class="transform rotate-180" src="/assets/img/chevron-down-blue.svg" />
          </div>
          <div
            v-if="!showMore"
            @click="showMore = !showMore"
            class="flex flex-row justify-items-end pl-10 float-right cursor-pointer"
          >
            <span class="text-lg mr-2 tracking-0.3 font-normal text-blue-700">View all steps</span>
            <img src="/assets/img/chevron-down-blue.svg" />
          </div>
        </div>

        <div class="mx-auto md:pt-5 xs:pt-2 xl:ml-16 mb-24 xs:mb-10 relative">
          <div class="grid grid-rows-4 xs:text-center steps-container" :class="showMore ? 'md:gap-y-16' : 'gap-y-2'">
            <div class="grid md:grid-cols-12 xs:grid-rows-2 xs:gap-y-2">
              <div class="md:col-span-4 grid grid-cols-4 xs:grid-cols-3">
                <div class=" col-span-1 flex flex-row items-center xs:items-end">
                  <hr class="w-1 h-6 border-2 border-blue-700 bg-blue-700 xs:mb-1 rounded-sm mr-4" />
                  <span class=" flex-1 text-blue-700 text-26px leading-9 tracking-0.48 font-medium xs:mr-6 xs:-mb-1"
                    >01</span
                  >
                </div>
                <div
                  class="col-span-3 xs:col-span-2 xs:flex xs:flex-row xs:items-end xs:gap-x-6 self-center xs:self-end xs:text-left"
                >
                  <span class=" flex-auto text-xl text-gray-900 tracking-0.3 font-medium">Upload sample</span>
                </div>
              </div>
              <div class=" xs:hidden sm:hidden md:block md:visible h-24 bg-gray-100 z-10">
                <div
                  class="mt-2 md:col-span-1 xs:hidden sm:hidden md:flex md:visible xs:row-span-1 xs:self-center xs:mx-auto flex justify-center items-center rounded-full h-20 w-20 shadow bg-gray-100"
                >
                  <img src="/assets/img/icons-action-upload.svg" class="xs:w-6 xs:h-6" />
                </div>
              </div>
              <div class="md:col-span-7 md:ml-10 self-center">
                <span
                  class="text-xl leading-30 tracking-0.37 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27 text-gray-900"
                  >Provide a search sample representative to the domain and language pair that you need the data
                  in.</span
                >
              </div>
            </div>
            <div>
              <div class="grid md:grid-cols-12 xs:grid-rows-2 xs:self-center xs:gap-y-2">
                <div class="md:col-span-4 grid grid-cols-4 xs:grid-cols-3">
                  <div class=" col-span-1 flex flex-row items-center xs:items-end">
                    <hr :class="showMore ? 'bg-blue-700' : 'bg-gray-900'" class="w-1 h-6  xs:mb-1 rounded-sm mr-4" />
                    <span
                      :class="showMore ? 'text-blue-700' : 'text-gray-900'"
                      class=" flex-1 text-26px leading-9 tracking-0.48 font-medium xs:mr-6 xs:-mb-1"
                      >02</span
                    >
                  </div>
                  <div class="col-span-3 xs:col-span-2 self-center xs:self-end xs:text-left">
                    <span class=" flex-auto text-xl text-gray-900 tracking-0.3 font-medium">Review results</span>
                  </div>
                </div>
                <div class=" xs:hidden sm:hidden md:block md:visible h-24 bg-gray-100" :class="showMore ? 'z-10' : ''">
                  <div
                    v-if="showMore"
                    class="mt-2 xs:hidden sm:hidden md:flex md:visible md:col-span-1 xs:row-span-1 xs:self-center xs:mx-auto flex justify-center items-center rounded-full h-20 w-20 shadow bg-gray-100"
                  >
                    <img src="/assets/img/icons-action-eye.svg" class="xs:w-6 xs:h-6" />
                  </div>
                </div>
                <div v-if="showMore" class="md:col-span-7 md:ml-10 self-center">
                  <span
                    class="text-xl leading-30 tracking-0.37 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27 text-gray-900"
                    >Check the volumes, prices and samples of your matched data. Results are sorted by relevance.
                    in.</span
                  >
                </div>
              </div>
            </div>
            <div>
              <div class="grid md:grid-cols-12 xs:grid-rows-2 xs:self-center xs:gap-y-2">
                <div class="md:col-span-4 grid grid-cols-4 xs:grid-cols-3">
                  <div class=" col-span-1 flex flex-row items-center xs:items-end">
                    <hr :class="showMore ? 'bg-blue-700' : 'bg-gray-900'" class="w-1 h-6  xs:mb-1 rounded-sm mr-4" />
                    <span
                      :class="showMore ? 'text-blue-700' : 'text-gray-900'"
                      class=" flex-1 text-26px leading-9 tracking-0.48 font-medium xs:mr-6 xs:-mb-1"
                      >03</span
                    >
                  </div>
                  <div class="col-span-3 xs:col-span-2 self-center xs:self-end xs:text-left">
                    <span class=" flex-auto text-xl text-gray-900 tracking-0.3 font-medium">Choose size</span>
                  </div>
                </div>
                <div class=" xs:hidden sm:hidden md:block md:visible h-24 bg-gray-100" :class="showMore ? 'z-10' : ''">
                  <div
                    v-if="showMore"
                    class="mt-2 xs:hidden sm:hidden md:flex md:visible md:col-span-1 xs:row-span-1 xs:self-center xs:mx-auto flex justify-center items-center rounded-full h-20 w-20 shadow bg-gray-100"
                  >
                    <img src="/assets/img/icons-arrows-expand.svg" class="xs:w-6 xs:h-6" />
                  </div>
                </div>

                <div v-if="showMore" class="md:col-span-7 md:ml-10 self-center">
                  <span
                    class="text-xl leading-30 tracking-0.37 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27 text-gray-900"
                    >Decide and select how much data of the returned volumes you want - 25%, 50%, 70% or 100%.</span
                  >
                </div>
              </div>
            </div>
            <div>
              <div class="grid md:grid-cols-12 xs:grid-rows-2 xs:self-center xs:gap-y-2">
                <div class="md:col-span-4 grid grid-cols-4 xs:grid-cols-3">
                  <div class=" col-span-1 flex flex-row items-center xs:items-end">
                    <hr :class="showMore ? 'bg-blue-700' : 'bg-gray-900'" class="w-1 h-6  xs:mb-1 rounded-sm mr-4" />
                    <span
                      :class="showMore ? 'text-blue-700' : 'text-gray-900'"
                      class=" flex-1 text-26px leading-9 tracking-0.48 font-medium  xs:mr-6 xs:-mb-1"
                      >04</span
                    >
                  </div>
                  <div class="col-span-3 xs:col-span-2 self-center xs:self-end xs:text-left">
                    <span class=" flex-auto text-xl text-gray-900 tracking-0.3 font-medium">Pay & Download</span>
                  </div>
                </div>
                <div
                  class=" xs:hidden sm:hidden md:block md:visible h-24 w-24 bg-gray-100"
                  :class="showMore ? 'z-10' : ''"
                >
                  <div
                    v-if="showMore"
                    class="xs:hidden sm:hidden md:flex md:visible md:col-span-1 xs:row-span-1 xs:self-center xs:mx-auto flex justify-center items-center rounded-full h-20 w-20 shadow bg-gray-100"
                  >
                    <img src="/assets/img/icons-action-shopping-cart-blue.svg" class="xs:w-6 xs:h-6" />
                  </div>
                </div>
                <div v-if="showMore" class="md:col-span-7 md:ml-10 self-center">
                  <span
                    class="text-xl leading-30 tracking-0.37 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27 text-gray-900"
                    >When you are happy with your selection, complete the payment and download your data.</span
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="bg-blue-200">
      <div class="pt-16 xs:pt-6 md:w-3/4 xs:mx-4 mx-auto pb-32 xs:pb-20">
        <div class="mt-8 mb-10">
          <SectionTitle title="DOMAIN SPECIFIC DATASETS" />
        </div>
        <div class="mb-20 xs:mb-12 mx-auto text-center p-4 ">
          <span class="text-34px leading-49 tracking-0.76 font-medium xs:text-2xl xs:tracking-0.44">
            Try already verified off-the-shelf datasets
          </span>
        </div>
        <div class="grid lg:grid-cols-12 md:grid-cols-4 xs:grid-cols-4 md:gap-y-6 xs:gap-y-6 lg:gap-x-2 xl:gap-x-6">
          <div class="col-span-4">
            <hr class="mb-1 rounded-md h-1 bg-pink-900" />
            <div class="py-10 border-2 bg-gray-100 flex flex-col items-center justify-center">
              <h2 class="text-xl leading-30 tracking-0.37 font-medium xs:text-lg xs:tracking-0.3">
                Colloquial dataset
              </h2>
              <div class="flex flex-row">
                <a
                  href="https://md.taus.net/library/corpus/colloquial-corpus"
                  target="_blank"
                  class="text-lg mr-2 tracking-0.3 font-normal text-blue-700 cursor-pointer"
                  >View more</a
                >
                <img src="/assets/img/chevron-right-blue.svg" />
              </div>
              <div class="mt-12 text-center">
                <span class="text-xl leading-30 tracking-0.37 font-normal xs:text-lg xs:tracking-0.3"
                  >Initiated by</span
                >
              </div>
              <div class="mt-5 flex items-center justify-center"><img src="/assets/img/oracle-logo.png" /></div>
            </div>
          </div>
          <div class="col-span-4">
            <hr class="mb-1 rounded-md h-1 bg-turquoise-900" />
            <div class="py-10 border-2 bg-gray-100 flex flex-col items-center justify-center">
              <h2 class="text-xl leading-30 tracking-0.37 font-medium xs:text-lg xs:tracking-0.3">
                Customer support dataset
              </h2>
              <div class="flex flex-row">
                <a
                  href="https://md.taus.net/library/corpus/customer-support-short-sentences"
                  target="_blank"
                  class="text-lg mr-2 tracking-0.3 font-normal text-blue-700 cursor-pointer"
                  >View more</a
                >
                <img src="/assets/img/chevron-right-blue.svg" />
              </div>
              <div class="mt-12 text-center">
                <span class="text-xl leading-30 tracking-0.37 font-normal xs:text-lg xs:tracking-0.3"
                  >Initiated by</span
                >
              </div>
              <div class="mt-4 flex items-center justify-center"><img src="/assets/img/dell-logo.png" /></div>
            </div>
          </div>
          <div class="col-span-4">
            <hr class="mb-1 rounded-md h-1 bg-orange-900" />
            <div class="border-2 py-10 bg-gray-100 flex flex-col items-center justify-center">
              <h2 class="text-xl leading-30 tracking-0.37 font-medium xs:text-lg xs:tracking-0.3">
                E-commerce dataset
              </h2>
              <div class="flex flex-row">
                <a
                  href="https://md.taus.net/library/corpus/e-commerce-corpus"
                  target="_blank"
                  class="text-lg mr-2 tracking-0.3 font-normal text-blue-700 cursor-pointer"
                  >View more</a
                >
                <img src="/assets/img/chevron-right-blue.svg" />
              </div>
              <div class="mt-12 text-center">
                <span class="text-xl leading-30 tracking-0.37 font-normal xs:text-lg xs:tracking-0.3"
                  >Initiated by</span
                >
              </div>
              <div class="mt-2 flex items-center justify-center"><img src="/assets/img/ebay-logo.png" /></div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </section>
</template>

<script>
import Vue from "vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";
import MatchingDataSampleUpload from "@/components/search/MatchingDataSampleUpload.vue";
export default Vue.extend({
  name: "MatchYourData",
  data() {
    return {
      showMore: false
    };
  },
  components: {
    SectionTitle,
    MatchingDataSampleUpload
  }
});
</script>

<style lang="scss">
.steps-container::before {
  width: 0px;
  min-height: 90%;
  z-index: 1;
  content: "";
  position: absolute;
  border-left: 1px dashed #2da4df;
}
@media (min-width: 1255px) {
  .steps-container::before {
    left: 37.1%;
  }
}

@media (min-width: 760px) and(max-width:1254px) {
  .steps-container::before {
    left: 38.1%;
  }
}
@media (min-width: 0px) and(max-width:759px) {
  .steps-container::before {
    border-left: 0px !important;
  }
}
</style>
