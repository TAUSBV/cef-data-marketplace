<template>
  <section class="bg-gray-100 xs:mt-10 sm:mt-10 md:mt-20 lg:mt-20 xs:px-4 sm:px-0 mb-32" style="min-height: 40rem">
    <div class="xs:w-full sm:w-4/5 md:w-3/4 lg:w-3/4 mx-auto xs:mb-8 sm:mb-8 md:mb-16 lg:mb-16">
      <SectionTitle title="Search" class="xs:mb-8 sm:mb-8 md:mb-14 lg:mb-14" />
      <span class="block w-full text-gray-900 text-center font-medium span-34px mb-3"
        >Find the Right Data for Your AI Projects</span
      >
      <span class="block w-full text-gray-900 text-center font-medium span-18px"
        >Search documents or unique segments that fit your requirements</span
      >
    </div>
    <div class="xs:w-full sm:w-4/5 md:w-3/4 lg:w-3/4 mx-auto xs:mb-14">
      <div class="xs:w-full sm:w-full md:w-8/12 lg:w-full xl:w-8/12 mx-auto">
        <SearchBar :s="sourceLanguage" :t="targetLanguage" />
      </div>
    </div>
    <div class="scrollTopPoint xs:w-full sm:w-4/5 md:w-3/4 lg:w-3/4 mx-auto xs:mt-8 xs:mb-14 sm:mt-8 md:mt-16 lg:mt-16">
      <SearchStatistics />
    </div>
    <div
      class="xs:w-full sm:w-4/5 md:w-4/5 lg:w-4/5 mx-auto
          xs:mt-8 sm:mt-8 md:mt-16 lg:mt-16
          lg:grid lg:grid-cols-12 lg:gap-6"
    >
      <div
        v-if="result"
        class="filters-toggler items-center xs:flex sm:flex md:flex lg:hidden mb-12"
        @click="filtersToggler"
      >
        <div class="flex items-center cursor-pointer mx-auto">
          <img class="w-5 min-w-5" src="/assets/img/filter_list_black.svg" />
          <span class="text-lg tracking-1.6 xs:text-base xs:leading-relaxed xs:tracking-wide uppercase ml-2">
            FILTERS
          </span>
        </div>
      </div>
      <div
        class="filters-container relative lg:col-span-3 3xl:col-span-2 overflow-visible"
        :class="filtersResponsiveState ? 'restored' : 'removed'"
      >
        <div class="absolute w-24 xs:inline-block sm:inline-block md:inline-block lg:hidden" @click="filtersToggler">
          <span class="inline-flex items-center border border-gray-100" id="close-filters">
            <img class="w-6 min-w-6 mx-auto" src="/assets/img/close_black_slim.svg" />
          </span>
        </div>
        <SearchFilters @closeResponsiveFilters="filtersToggler" />
      </div>
      <div class="search-results lg:col-span-9 3xl:col-span-10">
        <SearchResults :sourceLanguage="sourceLanguage" :targetLanguage="targetLanguage" />
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";
import SearchBar from "@/components/search/SearchBar.vue";
import SearchFilters from "@/components/search/SearchFilters.vue";
import SearchStatistics from "@/components/search/SearchStatistics.vue";
import SearchResults from "@/views/search/SearchResults.vue";
import explorer from "@/services/Explorer";
import { ref, computed, watch, onUpdated } from "@vue/composition-api";

export default Vue.extend({
  name: "Search",
  setup(_, context) {
    const state = ref({
      result: null
    });
    const { result, exploreWithFilters } = explorer();
    const sourceLanguage = context.root.$route.query.s ? context.root.$route.query.s.toString() : "";
    const targetLanguage = context.root.$route.query.t ? context.root.$route.query.t.toString() : "";
    const domains = [];
    const contentTypes = [];
    const sellers = [];
    const prices = { min: "" as string, max: "" as string };
    const resultsView = ref(null);
    const filtersResponsiveState = ref(false);
    const windowWidth = ref(window.innerWidth);

    filtersResponsiveState.value = windowWidth.value >= 1023;

    const filtersToggler = function() {
      filtersResponsiveState.value = !filtersResponsiveState.value;
    };

    // Get filters params from URL and load the filters accordingly
    if (context.root.$route.query.d) {
      if (Array.isArray(context.root.$route.query.d)) {
        domains.push(...context.root.$route.query.d);
      } else {
        domains.push(context.root.$route.query.d);
      }
    }

    if (context.root.$route.query.c) {
      if (Array.isArray(context.root.$route.query.c)) {
        contentTypes.push(...context.root.$route.query.c);
      } else {
        contentTypes.push(context.root.$route.query.c);
      }
    }

    if (context.root.$route.query.u) {
      if (Array.isArray(context.root.$route.query.u)) {
        sellers.push(...context.root.$route.query.u);
      } else {
        sellers.push(context.root.$route.query.u);
      }
    }

    if (context.root.$route.query.pmin) {
      prices.min = context.root.$route.query.pmin as string;
    }

    if (context.root.$route.query.pmax) {
      prices.max = context.root.$route.query.pmax as string;
    }

    if (context.root.$route.query.v) {
      context.root.$route.query.v === "units" ? (resultsView.value = true) : (resultsView.value = false);
    }

    if (
      domains.length > 0 ||
      contentTypes.length > 0 ||
      sellers.length > 0 ||
      prices.min ||
      prices.max ||
      resultsView.value
    ) {
      exploreWithFilters(
        sourceLanguage,
        targetLanguage,
        domains,
        contentTypes,
        sellers,
        prices.min,
        prices.max,
        resultsView.value
      );
    }

    const updateResult = async () => {
      state.value.result = result;
    };

    watch(result, updateResult);

    onUpdated(() => {
      let offset = 0;
      if (window.innerWidth >= 1024) {
        offset = 100;
      } else if (window.innerWidth < 1023 && window.innerWidth >= 640) {
        offset = 100;
      } else {
        offset = 70;
      }

      window.scrollTo(
        0,
        document.querySelector(".scrollTopPoint").getBoundingClientRect().top +
          -1 * document.body.getBoundingClientRect().top -
          offset
      );
    });

    return {
      result: computed(() => state.value.result),
      sourceLanguage: sourceLanguage,
      targetLanguage: targetLanguage,
      filtersToggler: filtersToggler,
      filtersResponsiveState: filtersResponsiveState
    };
  },
  components: {
    SectionTitle,
    SearchBar,
    SearchStatistics,
    SearchFilters,
    SearchResults
  }
});
</script>

<style lang="scss" scoped>
@media (min-width: 992px) {
  .span-34px {
    font-size: 34px;
    line-height: 49px;
    letter-spacing: 0.76px;
  }
  .span-18px {
    font-size: 18px;
    line-height: 28px;
    letter-spacing: 0.3px;
  }
}
@media (max-width: 991px) {
  .span-34px {
    font-size: 24px;
    line-height: 33px;
    letter-spacing: 0.44px;
  }
  .span-18px {
    font-size: 16px;
    line-height: 26px;
    letter-spacing: 0.27px;
  }
}

@media (max-width: 640px) {
  .filters-container {
    padding-top: 70px;
  }
}

@media (min-width: 641px) and (max-width: 1023px) {
  .filters-container {
    padding-top: 90px;
  }
}

@media (max-width: 1023px) {
  .filters-toggler {
    text-align: center;
    border-top-right-radius: 4px;
    border-bottom-right-radius: 4px;
  }

  .filters-container {
    top: 0;
    z-index: 1;
    width: 100%;
    height: 100vh;
    position: fixed;
    padding-left: 20px;
    padding-right: 20px;
    background-color: #fff;
    transition: all 0.2s ease-in-out;
  }

  .filters-container.restored {
    left: 0;
  }
  .filters-container.removed {
    left: -1100px;
  }
}
</style>
