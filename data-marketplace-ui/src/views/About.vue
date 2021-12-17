<template>
  <section class="bg-gray-100 overflow-x-hidden">
    <div class="mt-8">
      <SectionTitle title="MISSION" />
    </div>
    <div class="flex flex-col w-3/4 lg:w-2/5 pt-24 pb-16 mx-auto relative text-xl leading-7 tracking-wide text-justify">
      <div class="mb-8">
        The objective of the Data Marketplace is to provide a
        <span class="pb-1 border-b-2 border-yellow-900">universal, secure market for language data</span>
        that will open up a continuous and long-term supply of high-quality language data for machine translation and
        other machine learning applications.
      </div>
      <div class="mb-8">
        By creating a broader market for desired and underrepresented language combinations and domains, the
        <span class="pb-1 border-b-2 border-yellow-900">Data Marketplace serves the data needs</span>
        of all stakeholders
        <span class="pb-1 border-b-2 border-yellow-900">in the global translation space.</span>
      </div>
      <div class="mb-8">
        The Data Marketplace started as a project co-financed by the European Union. It is a result of a collaboration
        with our esteemed partners
        <a class="font-medium text-blue-700" href="https://www.fbk.eu/en/" target="_blank">Fondazione Bruno Kessler</a>
        and
        <a class="font-medium text-blue-700" href="https://translated.com/welcome" target="_blank">Translated</a>.
      </div>
    </div>
    <div class="bg-blue-200 bg-opacity-45">
      <div class="flex flex-col w-3/4 py-16 mx-auto">
        <div class="text-4xl mb-20 mx-auto">Why Language Data Matters?</div>
        <div class="grid grid-cols-1 md:grid-cols-5 gap-8 m-auto relative">
          <div class="flex flex-col col-span-2 colum-border--top colum-border--left">
            <div class="bg-gray-100 m-3 p-5">
              <img
                src="/assets/img/data-matters.jpg"
                srcset="/assets/img/data-matters@2x.jpg 2x, /assets/img/data-matters@3x.jpg 3x"
                alt="data matters"
              />
            </div>
          </div>
          <div class="flex flex-col col-span-3 colum-border--bottom colum-border--right">
            <div class="bg-gray-100 text-xl leading-9 tracking-wide m-3 p-5">
              <div class="mb-8">
                In the increasingly digital world, the ability to communicate in all languages has an economic value.
                Consequently, the need to close the gap between under-connected and hyper-digitized countries has never
                been bigger. The only way to do that is through automated translation and AI-assisted language services.
              </div>
              <div class="mb-8">
                While the computational power and the model complexity increase by the year, the availability of the
                data in various language pairs and domains has not scaled proportionally.
                <span class="italic">More data outperforms a smart model and better data outperforms more data.</span>
                However, the data quality question continues to be a concern, and reliable multilingual data sources are
                scarce.
              </div>
              <div>
                Data Marketplace is a unique platform where the demand for language data meets supply.
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="flex flex-col w-5/6 md:w-3/4 lg:w-2/5 mt-16 mx-auto relative pb-32">
      <div class="mx-auto mb-16">
        <SectionTitle title="HISTORY OF TAUS DATA" />
      </div>
      <div class="grid grid-cols-1 grid-cols-5 gap-16">
        <div class="flex flex-col col-span-1 ml-auto relative">
          <div class="text-lg font-bold timeline-line">2008</div>
        </div>
        <div class="flex flex-col col-span-4">
          <div class="timeline-start text-lg">
            TAUS Data Association (TDA) founded, the first (and only) industry-shared language data repository (6B
            words)
          </div>
        </div>

        <div class="flex flex-col col-span-1 ml-auto relative">
          <div class="text-lg font-bold timeline-line">2012</div>
        </div>
        <div class="flex flex-col col-span-4">
          <div class="timeline text-lg">
            TDA evolves into TAUS Data Cloud, data repository grew to 40B words
          </div>
          <div class="text-gray-800 text-lg" v-if="!showMore">
            3 more items
            <a class="text-blue-700 font-medium md:ml-3 block md:inline" href="#show-more">
              Show more
              <img src="/assets/img/chevron-down-blue.svg" alt="chevron down blue" class="inline pl-1" />
            </a>
          </div>
        </div>

        <div class="flex flex-col col-span-1 ml-auto relative" v-if="showMore">
          <div class="text-lg font-bold timeline-line">2016</div>
        </div>
        <div class="flex flex-col col-span-4" v-if="showMore">
          <div class="timeline text-lg">
            New UI & extensive cleaning of the data repository, reducing 73B words to 35B words
          </div>
        </div>

        <div class="flex flex-col col-span-1 ml-auto relative" v-if="showMore">
          <div class="text-lg font-bold timeline-line">Jan 2019</div>
        </div>
        <div class="flex flex-col col-span-4" v-if="showMore">
          <div class="timeline text-lg">
            Release of TAUS Matching Data clustered search technology and TAUS Data Library
          </div>
        </div>

        <div class="flex flex-col col-span-1 ml-auto" v-if="showMore">
          <div class="text-lg font-bold">Mar 2019</div>
        </div>
        <div class="flex flex-col col-span-4" v-if="showMore">
          <div class="timeline text-lg">
            TAUS Data Cleaning Service included in Matching Data
          </div>
        </div>

        <div class="flex flex-col col-span-1 ml-auto">
          <div class="text-lg font-bold">Oct 2020</div>
        </div>
        <div class="flex flex-col col-span-4">
          <div class="timeline-end text-lg">Launch of Data Marketplace</div>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";

export default Vue.extend({
  name: "About",
  computed: {
    showMore() {
      return this.$route.hash === "#show-more";
    }
  },
  components: {
    SectionTitle
  }
});
</script>
<style lang="scss">
.colum-border--top::after {
  content: "";
  position: absolute;
  width: 0.25rem;
  height: 67%;
  top: 0;
  left: 0;
  @apply bg-yellow-800;
}

.colum-border--bottom::after {
  content: "";
  position: absolute;
  width: 0.25rem;
  height: 67%;
  bottom: 0;
  right: 0;
  @apply bg-yellow-800;
}

.colum-border--left::before {
  content: "";
  position: absolute;
  width: 33%;
  height: 0.25rem;
  top: 0;
  left: 0;
  @apply bg-yellow-800;
}

.colum-border--right::before {
  content: "";
  position: absolute;
  width: 33%;
  height: 0.25rem;
  bottom: 0;
  right: 0;
  @apply bg-yellow-800;
}

.timeline-start,
.timeline-end,
.timeline {
  &::before {
    position: absolute;
    content: "";
    width: 2rem;
    height: 100%;
    max-height: 6rem;
    margin-left: -45px;
  }
}

.timeline-start {
  &::before {
    content: url(/assets/img/steps-timeline-full-blue-line.svg);
  }
}

.timeline-end {
  &::before {
    content: url(/assets/img/steps-timeline-full-blue.svg);
  }
}

.timeline {
  &::before {
    background: url(/assets/img/steps-timeline-half-blue-line.svg) no-repeat;
  }
}

@media (max-width: 767px) {
  .timeline-start,
  .timeline-end,
  .timeline {
    &::before {
      margin-left: -32px;
    }
  }
}
</style>
