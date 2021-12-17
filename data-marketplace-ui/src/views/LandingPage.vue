<template>
  <div>
    <section class="bg-gray-100">
      <div class="above-the-fold-container relative">
        <img
          src="/assets/img/background-landing-rectangle-light.svg"
          alt="background landing rectangle light"
          class="w-screen absolute landing-page-header-img"
        />
        <img
          src="/assets/img/background-landing-rectangle-dark.svg"
          alt="background landing rectangle dark"
          class="w-screen absolute landing-page-header-img"
        />
        <img
          src="/assets/img/background-landing-cover.png"
          srcset="/assets/img/background-landing-cover@2x.png 2x, /assets/img/background-landing-cover@3x.png 3x"
          alt="background landing cover"
          class="w-screen absolute landing-page-header-img"
        />
        <div class="flex flex-col sm:w-3/4 xs:px-4 py-8 mx-auto relative">
          <section class="xl:mt-16 lg:mt-8">
            <h1
              class="text-5xl leading-60 tracking-wide xs:text-26px xs:leading-9 xs:tracking-0.48 text-gray-100 mb-6 font-medium"
            >
              Data Marketplace
            </h1>
            <div class="mb-6">
              <div class="inline-block w-6 h-1 float-left bg-blue-900 shadow"></div>
              <div class="inline-block w-6 h-1 float-left bg-pink-900 shadow"></div>
              <div class="inline-block w-6 h-1 float-left bg-red-900 shadow"></div>
              <div class="inline-block w-6 h-1 float-left bg-yellow-900 shadow"></div>
              <div class="inline-block w-6 h-1 float-left bg-green-900 shadow"></div>
              <div class="flow-root"></div>
            </div>
            <h2
              class="text-xl leading-30 tracking-5.0 font-normal xs:text-base xs:tracking-1.4 text-gray-100 xs:mb-4 sm:mb-16 md:mb-4 lg:mb-4 xl:mb-18"
            >
              FOR LANGUAGE DATA MONETIZATION AND ACQUISITION
            </h2>
            <div>
              <div>
                <LandingPageSearch class="above-the-fold-container-md" />
              </div>
            </div>
          </section>
        </div>
        <div class="lg:block hidden">
          <section class="absolute xl:bottom-0 xl:right-40 lg:-bottom-4 lg:right-10 ">
            <div class="flex items-center">
              <span class="text-lg tracking-wider mr-8 lg:mr-6  uppercase">
                Video
              </span>
              <hr class="bg-yellow-900 w-16 h-1 mt-1 lg:mt-0 mr-8 lg:mr-6" />
              <VideoModal vname="DMP-video-webversion.mp4" />
            </div>
          </section>
        </div>
      </div>
      <div class="flex flex-col sm:w-3/4 xs:px-4 mt-8 sm:pb-8 mx-auto relative">
        <section class="xs:mb-12 sm:mb-24 mt-8 mb-24 xs:mt-8">
          <div class="mb-16  xs:mb-8">
            <SectionTitle class="xs:hidden" title="FEATURED DATA SETS" position="left" />
            <SectionTitle class="sm:hidden xs:visible" title="FEATURED DATA SETS" />
          </div>
          <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
            <div class="lg:col-span-2">
              <DocumentCard :document="selectedDocument" v-if="!isFetchingData" />
              <!-- Loading placeholder -->
              <DocumentCardLoading v-else />
            </div>

            <div class="lg:col-span-2 xs:hidden">
              <div
                id="datasetsContainer"
                class="datasets-container bg-blue-200 bg-opacity-45 px-4 pt-8 pb-5 overflow-x-auto whitespace-nowrap"
                style="height: fit-content;"
              >
                <div v-if="!isFetchingData" class="inline-flex">
                  <div
                    class="mb-8"
                    :class="index === 0 ? '' : 'ml-4'"
                    v-for="(document, index) in documents.slice(0, 5)"
                    :key="document.uid"
                  >
                    <DocumentCardCompact
                      :show-rating="false"
                      :show-domain="false"
                      :show-content-type="false"
                      :show-price="false"
                      :isSelectedDocument="document.uid === selectedDocument.uid"
                      :document="document"
                      @setDocumentSelected="setDocumentSelected"
                    />
                  </div>
                </div>
                <!-- Loading placeholder -->
                <div v-else>
                  <DocumentCardCompactLoading
                    v-for="(item, index) in 5"
                    :key="`placeholder-top-${index}`"
                    class="inline-block mb-8 w-2/3"
                    :class="index === 0 ? '' : 'ml-4'"
                  />
                </div>

                <div v-if="!isFetchingData" class="flex">
                  <div
                    :class="index === 0 ? 'ml-12 mr-4' : 'mr-4'"
                    v-for="(document, index) in documents.slice(5, 10)"
                    :key="document.uid"
                  >
                    <DocumentCardCompact
                      :show-rating="false"
                      :show-domain="false"
                      :show-content-type="false"
                      :show-price="false"
                      :isSelectedDocument="document.uid === selectedDocument.uid"
                      :document="document"
                      @setDocumentSelected="setDocumentSelected"
                    />
                  </div>
                </div>
                <!-- Loading placeholder -->
                <div v-else>
                  <DocumentCardCompactLoading
                    v-for="(item, index) in 5"
                    :key="`placeholder-bottom-${index}`"
                    class="inline-block w-2/3"
                    :class="index === 0 ? 'ml-12 mr-4' : 'mr-4'"
                  />
                </div>
              </div>
              <div class="mt-12 xs:hidden">
                <img
                  src="/assets/img/chevron-left-black.svg"
                  alt="chevron down blue"
                  class="inline mr-4 cursor-pointer"
                  @click="scrollLeft()"
                />
                <img
                  src="/assets/img/chevron-right-black.svg"
                  alt="chevron down blue"
                  class="inline cursor-pointer"
                  @click="scrollRight()"
                />
              </div>
            </div>
          </div>
          <div></div>
        </section>
        <section class="mb-10 lg:mb-32">
          <div class="flex justify-center text-center mb-16 xs:mb-8">
            <SectionTitle title="WHO IS DATA MARKETPLACE FOR" />
          </div>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-8 h-full xs:w-3/4 sm:w-3/4 md:w-full lg:w-3/4 m-auto">
            <div
              class="flex flex-col xs:text-left sm:text-center md:text-left lg:text-left md:mr-auto"
              style="padding-left: 45px;width: fit-content;"
            >
              <div
                class="text-34px leading-49 tracking-0.76 xs:text-2xl xs:tracking-0.44 xs:mb-3 xs:mr-6 mb-5 font-medium xs:text-center "
              >
                Data Sellers
              </div>
              <ul class="list-checks text-xl xs:text-lg tracking-0.37 mb-8 xs:mb-5 xs:mx-auto">
                <li>Small & Large LSPs</li>
                <li>Translators</li>
                <li>Data Producers</li>
              </ul>
              <div class="text-xl xs:text-lg mb-6 xs:mb-3">
                Monetize data you collected overtime.
              </div>
              <router-link
                to="/publications"
                class="text-lg xs:text-base text-blue-700 xs:text-center font-bold underline"
              >
                Sell data
              </router-link>
            </div>
            <div
              class="flex flex-col xs:text-left sm:text-center md:text-left lg:text-left md:ml-auto"
              style="padding-left: 45px;width: fit-content;"
            >
              <div
                class="text-34px leading-49 tracking-0.76 xs:text-2xl xs:tracking-0.44 mb-5 xs:mb-3 font-medium xs:text-center xs:mr-6"
              >
                Data Buyers
              </div>
              <ul class="list-checks text-xl xs:text-lg tracking-0.37 mb-8 xs:mb-5 xs:mx-auto">
                <li>MT Providers</li>
                <li>MT Operators</li>
                <li>Enterprises</li>
              </ul>
              <div class="text-xl xs:text-lg mb-6 xs:mb-3">
                Get high-quality data to improve your
                <br />
                MT engines.
              </div>
              <router-link to="/search" class="text-lg xs:text-base text-blue-700 font-bold underline xs:text-center">
                Explore data
              </router-link>
              <a href="/search"> </a>
            </div>
          </div>
        </section>
        <section class="mb-10 lg:mb-32">
          <div class="grid grid-cols-1 lg:grid-cols-12 gap-8 h-full m-auto">
            <div class="flex flex-col lg:col-span-5 justify-center my-auto">
              <div class="flex xs:mx-auto mb-8">
                <SectionTitle class="xs:hidden" title="WHY DATA MARKETPLACE" position="left" />
                <SectionTitle class="sm:hidden xs:visible" title="WHY DATA MARKETPLACE" />
              </div>
              <div
                class="flex justify-center text-34px leading-49 tracking-0.76 font-medium xs:text-2xl xs:tracking-0.44 mb-8"
              >
                Language Data Preparation and Acquisition Made Easy
              </div>
              <div class="flex justify-center text-xl sm:leading-30 tracking-0.37 font-normal xs:text-lg ">
                Acquiring and preparing language data for your models does not have to be strenuous, time-consuming, or
                costly. The Data Marketplace provides a multi-source, scalable, and self-service alternative to manual
                data collection. Don’t need new data, but want to clean your own data? No problem. Try our automated
                cleaning and anonymization services.
              </div>
            </div>
            <div class="flex flex-col lg:col-span-7 justify-center ts-feature--square">
              <div class="grid grid-cols-1 md:grid-cols-2 mx-auto md:mx-0 gap-8 grid-flow-row">
                <CardIcon
                  img="wallet-with-base"
                  title="Opportunity for Data Sellers"
                  text="Sellers can now make money from the language data that they created or collected over time."
                />
                <CardIcon
                  class="md:mt-16"
                  img="market-with-base"
                  title="Free Market Approach"
                  text="Sellers can set their own prices and buyers get to choose the data that best fits their needs."
                />
              </div>
              <div class="grid grid-cols-1 md:grid-cols-2 mx-auto md:mx-0 gap-8 grid-flow-row">
                <CardIcon
                  class="mt-8 md:mt-0"
                  img="data-provenance-with-base"
                  title="Built-in Data Provenance"
                  text="Full transparency on where the data are coming from and which data sets they are a part of."
                />
                <CardIcon
                  class="md:mt-16"
                  img="data-with-base"
                  title="Advanced Data Preparation"
                  text="Built-in tools for data cleaning, anonymization and clustering ensure the data on the platform are high-quality and ready-to-use for MT training."
                />
              </div>
            </div>
          </div>
        </section>
      </div>
    </section>
    <section class="bg-blue-200 bg-opacity-45">
      <PublicationHeader />
      <div class="flex flex-col sm:w-3/4 xs:px-4 pb-32 xs:pb-8 mx-auto relative">
        <DocumentUpload />
      </div>
    </section>
    <section class="bg-gray-100">
      <div class="my-24 sm:w-3/4 xs:px-4 mx-auto xs:mt-10">
        <div class="flex justify-center mb-12">
          <SectionTitle title="SUCCESS STORIES" />
        </div>
        <div class="flex flex-col md:flex-row justify-center items-center lg:pb-12">
          <div class="grid sm:grid-cols-1 md:grid-cols-3 lg:grid-cols-3 xl:grid-cols-3 lg:gap-3 md:gap-5">
            <card-slider />
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import CardIcon from "@/components/CardIcon.vue";
import DocumentUpload from "@/views/publication/DocumentUpload.vue";
import DocumentCard from "@/components/DocumentCard.vue";
import DocumentCardLoading from "@/components/DocumentCardLoading.vue";
import DocumentCardCompact from "@/components/DocumentCardCompact.vue";
import DocumentCardCompactLoading from "@/components/DocumentCardCompactLoading.vue";
import PublicationHeader from "@/views/publication/PublicationHeader.vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";
import { TranslationDocument } from "@/models/models";
import VideoModal from "@/components/VideoModal.vue";
import CardSlider from "@/components/CardSlider.vue";
import LandingPageSearch from "@/components/search/LandingPageSearch.vue";
export default Vue.extend({
  name: "LandingPage",
  data() {
    return {
      documents: [] as TranslationDocument[],
      selectedDocument: {} as TranslationDocument,
      isFetchingData: true
    };
  },
  mounted() {
    this.$store
      .dispatch("seller/fetchSellersFeatured", {})
      .then((response: TranslationDocument[]) => {
        this.documents = response;
        this.selectedDocument = this.documents[0];
      })
      .finally(() => {
        this.isFetchingData = false;
      });
  },
  methods: {
    setDocumentSelected(document: TranslationDocument) {
      this.selectedDocument = document;
    },
    scrollLeft() {
      const datasetsContainer = document.querySelector("#datasetsContainer");
      datasetsContainer.scrollBy(-250, 0);
    },
    scrollRight() {
      const datasetsContainer = document.querySelector("#datasetsContainer");
      datasetsContainer.scrollBy(250, 0);
    }
  },
  components: {
    LandingPageSearch,
    CardIcon,
    DocumentUpload,
    DocumentCard,
    DocumentCardLoading,
    DocumentCardCompact,
    DocumentCardCompactLoading,
    PublicationHeader,
    VideoModal,
    SectionTitle,
    CardSlider
  }
});
</script>
<style lang="scss">
.ts-feature--square {
  &::before {
    position: absolute;
    content: url(/assets/img/square-colorful.svg);
    background-size: 100% !important;
  }
}
ul.list-checks {
  position: relative;
  li::before {
    content: url(/assets/img/check-yellow.svg);
    position: absolute;
    left: -45px;
  }
}
.landing-page-header-img {
  object-fit: cover;
}
@media (max-width: 524px) {
  .above-the-fold-container {
    min-height: 65vh;
  }
  .landing-page-header-img {
    height: 45vh;
    object-position: 50% 0%;
  }
}
@media (min-width: 525px) and (max-width: 767px) {
  .above-the-fold-container {
    min-height: 80vh;
  }
  .landing-page-header-img {
    height: 80vh;
    object-position: 50% 0%;
  }
}
@media (min-width: 768px) and (max-width: 1024px) {
  .above-the-fold-container {
    min-height: 80vh;
  }
  .landing-page-header-img {
    height: 80vh;
    object-position: 0% 100%;
  }
}
@media (min-width: 1025px) {
  .above-the-fold-container {
    min-height: 85vh;
  }
  .landing-page-header-img {
    height: 85vh;
    object-position: 0% 100%;
  }
  .ts-feature--square {
    content: "";
    right: 200px;
  }
}
@media (max-width: 1023px) {
  .ts-feature--square::before {
    content: "";
  }
}

@media (min-width: 1024px) and (max-width: 1250px) {
  .ts-feature--square::before {
    -webkit-transform: scale(0.8);
    -moz-transform: scale(0.8);
    -o-transform: scale(0.8);
    transform: scale(0.8);
    margin-left: 1rem;
  }
}
@media (min-width: 1250px) and (max-width: 1799px) {
  .ts-feature--square::before {
    -webkit-transform: scale(1);
    -moz-transform: scale(1);
    -o-transform: scale(1);
    transform: scale(1);
    margin-left: 5rem;
  }
}
@media (min-width: 1800px) {
  .ts-feature--square::before {
    -webkit-transform: scale(1.3);
    -moz-transform: scale(1.3);
    -o-transform: scale(1.3);
    transform: scale(1.3);
    margin-left: 8rem;
  }
}
.datasetsContainer {
  transition: left 1s ease-in-out;
}
.datasets-container::-webkit-scrollbar {
  width: 0;
}
.datasets-container::-webkit-scrollbar-thumb {
  width: 0;
}
.datasets-container::-webkit-scrollbar-track {
  width: 0;
}
</style>
