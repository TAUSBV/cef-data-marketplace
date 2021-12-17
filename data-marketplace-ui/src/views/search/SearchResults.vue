<template>
  <div>
    <section class="w-full" v-if="result && result.sample.content.length > 0">
      <div class="flex items-center justify-between mb-5">
        <span v-if="toggleState" class="text-base leading-6 tracking-0.27">
          <span class="xs:hidden sm:hidden md:hidden lg:inline">
            {{ result.statistics.unitsTotal | integer }} unique segments found
          </span>
          <span class="lg:hidden"> {{ result.statistics.unitsTotal | integer }} segments </span>
        </span>
        <span v-else class="text-base leading-6 tracking-0.27">
          <span class="xs:hidden sm:hidden md:hidden lg:inline">
            {{ result.statistics.unitsTotal | integer }} segments found. You're viewing samples of documents.
          </span>
          <span class="lg:hidden"> {{ result.statistics.unitsTotal | integer }} segments </span>
        </span>
        <div>
          <span class="flex items-center xs:text-base sm:text-lg leading-7 tracking-0.27">
            Show segments
            <Toggler :isEnabled="isTogglerEnabled" class="ml-5" @toggle="selectedView($event)" />
          </span>
        </div>
      </div>
      <template v-if="!isLoading">
        <div v-if="toggleState">
          <SegmentsResults
            :segments="result.sample.content"
            :totalPrice="result.statistics.totalPrice"
            :segmentsExpanded="segmentsExpanded"
            @viewMoreState="setSegmentsExpanded($event)"
          />
        </div>
        <div v-else>
          <DocumentsResults :payload="result.sample" />

          <Pagination
            v-if="result.sample.totalPages && result.sample.totalPages > 1"
            v-model="pageNumber"
            :number-of-pages="result.sample.totalPages"
            @move="changePage($event)"
          />
        </div>
      </template>
      <template v-else>
        <UnitCardLoading v-for="(items, index) in 4" :key="index" />
      </template>
    </section>
    <section v-else-if="result && result.filters !== null && result.sample.content.length === 0">
      <div class="pl-10 pt-12">
        <span class="text-lg"
          >Oops, there are no results for this specific filter selection. Try clearing all filters or choosing a
          different combination.</span
        >
      </div>
    </section>
    <section v-else-if="sourceLanguage && targetLanguage && sourceLanguage !== '' && targetLanguage !== ''">
      <div class="pl-10 pt-12">
        <span class="text-lg">Oops, there are no results found for this specific language pair selection.</span>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Pagination from "@/components/Pagination.vue";
import SegmentsResults from "@/views/search/SegmentsResults.vue";
import DocumentsResults from "@/views/search/DocumentsResults.vue";
import UnitCardLoading from "@/components/UnitCardLoading.vue";
import Toggler from "@/components/Toggler.vue";
import explorer from "@/services/Explorer";
import { computed, ref, watch } from "@vue/composition-api";

export default Vue.extend({
  name: "SearchResults",
  props: {
    sourceLanguage: {
      type: String
    },
    targetLanguage: {
      type: String
    }
  },
  setup(_, context) {
    const { result, isLoading, setResultsView, explore, setPage } = explorer();
    const state = ref({
      result: null
    });

    const isTogglerEnabled = ref();

    const setRoute = function(param: object) {
      context.root.$router.push({ query: Object.assign({}, context.root.$route.query, param) });
    };

    const toggleState = ref(false);
    const selectedView = function(toggle: boolean) {
      // Remove the pg query param from the URL if selected view is units
      if (toggle && context.root.$route.query.pg !== undefined) {
        delete context.root.$route.query.pg;
      }

      toggle ? setRoute({ v: "units" }) : setRoute({ v: "documents" });
      toggleState.value = toggle;
      setResultsView(toggle);
      explore();
    };

    if (context.root.$route.query.v) {
      context.root.$route.query.v === "units" ? (isTogglerEnabled.value = true) : (isTogglerEnabled.value = false);
      toggleState.value = isTogglerEnabled.value;
    }

    const segmentsExpanded = ref();
    const setSegmentsExpanded = function(state: boolean) {
      segmentsExpanded.value = state;
    };

    const pageNumber = ref(0);
    const changePage = function(pgNumber: number) {
      pageNumber.value = pgNumber;
      setPage(pgNumber);

      // If user clicks on same page again, don't set route again because Duplicate route name error will be logged in the console
      if (context.root.$route.query.pg === undefined) {
        setRoute({ pg: pgNumber });
      } else if (parseInt(context.root.$route.query.pg.toString()) !== pgNumber) {
        setRoute({ pg: pgNumber });
      }
      explore();
    };

    if (context.root.$route.query.pg) {
      pageNumber.value = parseInt(context.root.$route.query.pg.toString());
      setPage(pageNumber.value);
      explore();
    }

    const updateResult = async () => {
      state.value.result = result;
    };
    watch(result, updateResult);

    return {
      result: computed(() => state.value.result),
      isLoading: isLoading,
      toggleState: toggleState,
      changePage: changePage,
      selectedView: selectedView,
      setSegmentsExpanded: setSegmentsExpanded,
      segmentsExpanded: segmentsExpanded,
      pageNumber: pageNumber,
      isTogglerEnabled: isTogglerEnabled
    };
  },
  components: {
    SegmentsResults,
    DocumentsResults,
    UnitCardLoading,
    Pagination,
    Toggler
  }
});
</script>

<style lang="scss" scoped></style>
