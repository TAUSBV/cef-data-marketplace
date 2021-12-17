<template>
  <section
    class="search-box
            xs:block sm:block md:block lg:flex md:items-center lg:items-center 
            rounded pt-4 pb-4 lg:pr-4"
    :class="hasBorder ? 'border border-gray-300' : ''"
  >
    <div
      class="relative xs:w-full sm:w-full md:w-full lg:w-5/12 md:float-left xs:mb-5 sm:mb-5 md:mb-5 lg:mb-0 lg:border-r border-gray-300 pl-4 pr-4"
    >
      <DropDown
        id="source"
        class="w-full rounded border-2 border-gray-100 transition-all transition-75"
        :class="[isSourceOpen ? 'open' : 'closed', indicateSearch ? 'border-blue-800' : '']"
        :placeholder="sourcePlaceholder"
        :options="renderedSources ? renderedSources : sources"
        trackByKey="nameAndCountry"
        :is-required="false"
        :value="sourceSelected[0] || []"
        @searched="val => onSearch(val, 'source')"
        @selected="val => selectSourceLanguage(val)"
        @removed="deselectSourceLanguage()"
        @opened="() => onDdOpen('source')"
        @closed="() => onDdClose('source')"
      >
        <template v-slot:noresults>
          <div class="p-2 bg-red-700 bg-opacity-20 rounded noresults-warning">
            <span class="block w-full mb-1 text-red-700">This language is not yet available.</span>
            <span class="block w-full">
              <span>However, we might have it in our Data Library. </span>
              <a class="text-blue-700 cursor-pointer" @mousedown.prevent="clickToContact">Contact us</a>
            </span>
          </div>
        </template>
      </DropDown>
      <span
        :class="renderedTargets ? 'inline-block' : 'hidden'"
        class="clear-languages absolute bg-gray-100 cursor-pointer"
        @click="clearLanguages"
        ><img class="w-6 min-w-6 mx-auto" src="/assets/img/close_black_slim.svg"
      /></span>
    </div>
    <div
      class="relative xs:w-full sm:w-full md:w-full md:float-left lg:w-5/12 xs:mb-5 sm:mb-5 md:mb-5 lg:mb-0 pl-4 pr-4"
    >
      <DropDown
        id="target"
        class="w-full rounded border-2 border-gray-100"
        :class="[isTargetOpen ? 'open' : 'closed']"
        :placeholder="targetPlaceholder"
        :options="renderedTargets ? renderedTargets : targets"
        trackByKey="nameAndCountry"
        :is-required="false"
        :value="targetSelected[0] || []"
        @searched="val => onSearch(val, 'target')"
        @selected="val => selectTargetLanguage(val)"
        @removed="deselectTargetLanguage()"
        @opened="() => onDdOpen('target')"
        @closed="() => onDdClose('target')"
      >
        <template v-slot:noresults>
          <div class="p-2 bg-red-700 bg-opacity-20 rounded noresults-warning">
            <span class="block w-full mb-1 text-red-700">This language is not yet available.</span>
            <span class="block w-full">
              <span>However, we might have it in our Data Library. </span>
              <a class="text-blue-700 cursor-pointer" @mousedown.prevent="clickToContact">Contact us</a>
            </span>
          </div>
        </template>
      </DropDown>
      <span
        :class="renderedSources ? 'inline-block' : 'hidden'"
        class="clear-languages absolute bg-gray-100 cursor-pointer"
        @click="clearLanguages"
        ><img class="w-6 min-w-6 mx-auto" src="/assets/img/close_black_slim.svg"
      /></span>
    </div>
    <div
      class="xs:w-full sm:w-full md:w-full md:mx-auto lg:w-2/12 xs:pl-4 xs:pr-4 sm:pl-4 sm:pr-4 md:pl-4 md:pr-4 lg:pl-0 lg:pr-0"
    >
      <Button
        v-if="!isMD"
        class="rounded w-full justify-center"
        :isLoading="isLoading"
        :disabled="isDisabled"
        @click="exploreData()"
      >
        Search
      </Button>
      <Button
        v-if="isMD"
        class="rounded w-full justify-center"
        :isLoading="isLoading"
        :disabled="isDisabled"
        @click="exploreData()"
      >
        Search
      </Button>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Button from "@/components/Button.vue";
import DropDown from "@/components/DropDown.vue";
import { Language, LanguageSourceTargetCount } from "@/models/models";
import { computed, onMounted, ref } from "@vue/composition-api";
import DataExplorerService from "@/services/DataExplorerService";
import explorer from "@/services/Explorer";

export default Vue.extend({
  name: "SearchBar",
  props: {
    s: {
      type: String,
      required: false
    },
    t: {
      type: String,
      required: false
    },
    hasBorder: {
      type: Boolean,
      default: true
    },
    isMD: {
      type: Boolean,
      default: false
    },
    disable: {
      type: Boolean,
      default: false
    }
  },
  setup(props, context) {
    const { setSourceLanguage, setTargetLanguage, explore, isLoading, resetFilters } = explorer();
    const state = ref({
      sourceLanguages: [],
      targetLanguages: [],
      languages: {
        sources: [] as LanguageSourceTargetCount[],
        targets: [] as LanguageSourceTargetCount[]
      },
      sourceSelected: [] as Language[],
      targetSelected: [] as Language[],
      isSourceDdOpen: false,
      isTargetDdOpen: false,
      sourceSearchQuery: null,
      targetSearchQuery: null,
      debounce: null,
      isLoading: null
    });
    const indicateSearch = ref(false);

    function exploreData() {
      if (!props.isMD) {
        if (state.value.sourceSelected.length !== 0 && state.value.sourceSelected.length !== 0) {
          if (context.root.$route.query.s === undefined && context.root.$route.query.t === undefined) {
            context.root.$router.push({
              name: "Search",
              query: {
                s: state.value.sourceSelected[0].id.toString(),
                t: state.value.targetSelected[0].id.toString()
              }
            });
            resetFilters();
            explore();
          } else if (
            parseInt(context.root.$route.query.s.toString()) !==
              parseInt(state.value.sourceSelected[0].id.toString()) ||
            parseInt(context.root.$route.query.t.toString()) !== parseInt(state.value.targetSelected[0].id.toString())
          ) {
            context.root.$router.push({
              name: "Search",
              query: {
                s: state.value.sourceSelected[0].id.toString(),
                t: state.value.targetSelected[0].id.toString()
              }
            });
            resetFilters();
            explore();
          }
        } else {
          indicateSearch.value = true;
          setTimeout(function() {
            indicateSearch.value = false;
          }, 500);
        }
      } else {
        state.value.isLoading = true;
        context.emit("onSubmitSample", state.value.sourceSelected[0], state.value.targetSelected[0]);
      }
    }

    const sources = computed(() => state.value.languages.sources.map(lg => lg.source));
    const targets = computed(() => state.value.languages.targets.map(lg => lg.source));

    const renderedTargets = ref();
    const renderedSources = ref();

    const sourceTargets = function() {
      if (state.value.sourceSelected.length > 0) {
        renderedTargets.value = state.value.languages.sources
          .find(lg => lg.source.id === state.value.sourceSelected[0].id)
          .targets.map(t => t.language);
      } else {
        renderedTargets.value = [];
      }
    };
    const targetTargets = function() {
      if (state.value.targetSelected.length > 0) {
        renderedSources.value = state.value.languages.targets
          .find(lg => lg.source.id === state.value.targetSelected[0].id)
          .targets.map(t => t.language);
      } else {
        renderedSources.value = [];
      }
    };

    function selectSourceLanguage(selectedValue: Language) {
      state.value.sourceSelected.splice(0);
      state.value.sourceSelected.push(selectedValue);
      setSourceLanguage(selectedValue);
      sourceTargets();
    }

    function deselectSourceLanguage() {
      state.value.sourceSelected.splice(0);
      state.value.targetSelected.splice(0);
      renderedTargets.value = undefined;
      renderedSources.value = undefined;
    }

    function selectTargetLanguage(selectedValue: Language) {
      state.value.targetSelected.splice(0);
      state.value.targetSelected.push(selectedValue);
      setTargetLanguage(selectedValue);
      targetTargets();
    }

    function deselectTargetLanguage() {
      state.value.sourceSelected.splice(0);
      state.value.targetSelected.splice(0);
      renderedSources.value = undefined;
      renderedTargets.value = undefined;
    }

    const clearLanguages = function() {
      state.value.sourceSelected.splice(0);
      state.value.targetSelected.splice(0);
      renderedTargets.value = undefined;
      renderedSources.value = undefined;
    };

    const isDisabled = computed(() => {
      if (!props.isMD) {
        return state.value.sourceSelected.length === 0 || state.value.targetSelected.length === 0;
      } else {
        return state.value.sourceSelected.length === 0 || state.value.targetSelected.length === 0 || props.disable;
      }
    });

    const sourcePlaceholder = computed(() => (state.value.isSourceDdOpen ? "Start typing..." : "Source Language"));
    const isSourceOpen = computed(() => state.value.isSourceDdOpen);

    const targetPlaceholder = computed(() => (state.value.isTargetDdOpen ? "Start typing..." : "Target Language"));
    const isTargetOpen = computed(() => state.value.isTargetDdOpen);

    const loadLanguages = async () => {
      state.value.languages = await DataExplorerService.languages().then(r => r.data);

      // Preselect source and target language and the filters if those are part of the url
      if (props.s && props.t) {
        const sourcePreselected: Language = sources.value.find(source => source.id.toString() === props.s);
        selectSourceLanguage(sourcePreselected);
        const targetPreselected: Language = targets.value.find(target => target.id.toString() === props.t);
        selectTargetLanguage(targetPreselected);

        if (
          !context.root.$route.query.d &&
          !context.root.$route.query.c &&
          !context.root.$route.query.u &&
          !context.root.$route.query.pmin &&
          !context.root.$route.query.pmax &&
          !context.root.$route.query.v &&
          !context.root.$route.query.pg
        ) {
          explore();
        }
      }
    };

    onMounted(loadLanguages);

    function onDdOpen(element: string) {
      if (element === "source") {
        state.value.isSourceDdOpen = true;
      } else {
        state.value.isTargetDdOpen = true;
      }
    }

    function onDdClose(element: string) {
      if (element === "source") {
        state.value.isSourceDdOpen = false;
      } else {
        state.value.isTargetDdOpen = false;
      }
    }

    function onSearch(query: string, element: string) {
      state.value.sourceSearchQuery = null;
      state.value.targetSearchQuery = null;

      clearTimeout(state.value.debounce);
      state.value.debounce = setTimeout(() => {
        if (element === "source") {
          state.value.sourceSearchQuery = query;
        } else {
          state.value.targetSearchQuery = query;
        }
      }, 600);
    }

    function clickToContact() {
      context.root.$router.push({
        name: "ContactUs",
        query: {
          q: state.value.sourceSearchQuery || state.value.targetSearchQuery
        }
      });
    }

    return {
      sourceLanguages: state.value.sourceLanguages,
      targetLanguages: state.value.targetLanguages,
      sources: sources,
      targets: targets,
      renderedTargets: renderedTargets,
      renderedSources: renderedSources,
      selectSourceLanguage: selectSourceLanguage,
      deselectSourceLanguage: deselectSourceLanguage,
      selectTargetLanguage: selectTargetLanguage,
      deselectTargetLanguage: deselectTargetLanguage,
      clearLanguages: clearLanguages,
      sourceSelected: state.value.sourceSelected,
      targetSelected: state.value.targetSelected,
      isDisabled: isDisabled,
      exploreData: exploreData,
      isLoading: isLoading,
      sourcePlaceholder,
      targetPlaceholder,
      isSourceOpen,
      isTargetOpen,
      isSourceDdOpen: state.value.isSourceDdOpen,
      isTargetDdOpen: state.value.isTargetDdOpen,
      onDdOpen: onDdOpen,
      onDdClose: onDdClose,
      onSearch: onSearch,
      clickToContact: clickToContact,
      indicateSearch: indicateSearch
    };
  },
  components: {
    Button,
    DropDown
  }
});
</script>

<style lang="scss">
.search-box {
  .open {
    .multiselect {
      @apply border;
      @apply rounded-t;
      @apply border-gray-300;

      .multiselect-placeholder {
        @apply text-gray-400;
        @apply font-normal;
      }
    }
  }

  .closed {
    .multiselect {
      .multiselect-placeholder {
        @apply text-gray-900;
      }
    }
  }

  /* Multiselect (DropDown Component) styles overrides
   * only for this specific occurrence
   */
  .multiselect {
    display: flex;
    align-items: center;
    position: relative;
    width: 100%;
    height: 3.5rem;
    @apply font-medium;
    @apply shadow-none;
    @apply rounded-t;
    @apply rounded-b-none;
    @apply pl-6;
    @apply pr-2;
    @apply border-gray-100;

    .multiselect-input {
      @apply text-gray-900;

      .multiselect-single-label,
      .multiselect-placeholder {
        @apply flex;
        @apply items-center;
        @apply pl-0;
        @apply leading-7;
        @apply tracking-wide;
      }
      @media (max-width: 767px) {
        .multiselect-single-label,
        .multiselect-placeholder {
          @apply text-lg;
        }
      }
      @media (min-width: 768px) {
        .multiselect-single-label,
        .multiselect-placeholder {
          @apply text-xl;
        }
      }

      @media (max-width: 767px) {
        .multiselect-single-label,
        .multiselect-placeholder {
          @apply text-lg;
        }
      }
      @media (min-width: 768px) {
        .multiselect-single-label,
        .multiselect-placeholder {
          @apply text-xl;
        }
      }

      .multiselect-placeholder {
        padding-left: 0;
      }
    }

    .multiselect-options {
      margin-top: 0;
      margin-left: -1px;
      width: calc(100% + 2px);
      @apply rounded-b;
      @apply rounded-t-none;
      @apply shadow-none;
      @apply border;
      @apply pl-4;
      @apply pr-4;
      @apply pt-4;
      @apply pb-4;
      @apply text-xl;
      @apply leading-7;
      @apply tracking-wide;
      @apply text-gray-900;
      @apply border-gray-300;
      @apply border-t-0;

      &::before {
        content: "";
        position: absolute;
        width: 90%;
        left: 50%;
        transform: translateX(-50%);
        top: 0;
        border-top: 1px solid #e4e4eb;
      }

      .multiselect-no-options {
        @apply pl-4;
        @apply pr-4;
        @apply pt-4;
        @apply pb-4;
        @apply rounded;
        @apply bg-yellow-300;
        @apply bg-opacity-30;
        @apply border-gray-300;
        @apply text-gray-900;
      }

      .multiselect-option {
        &.is-pointed {
          @apply rounded;
          @apply bg-opacity-45;
          @apply text-gray-900;
        }

        &.is-selected {
          @apply bg-blue-200;
          @apply bg-opacity-45;
          @apply text-blue-700;
        }
      }

      .noresults-warning {
        font-size: 1rem;
        line-height: 1.625rem;
        letter-spacing: 0.27px;
      }
    }

    &.is-single {
      .multiselect-search {
        input {
          padding-left: 0;
          @apply text-gray-900;
          @apply text-lg;
          @apply leading-7;
          @apply tracking-wide;
        }
      }
    }
  }

  .is-single .multiselect-search input {
    padding-left: 0px;
    @apply text-gray-900;
    @apply text-lg;
    @apply leading-7;
    @apply tracking-wide;
  }
  .multiselect-no-options {
    @apply pl-4;
    @apply pr-4;
    @apply pt-4;
    @apply pb-4;
    @apply rounded;
    @apply bg-yellow-300;
    @apply bg-opacity-30;
    @apply border-gray-300;
    @apply text-gray-900;
  }
  .noresults-warning {
    font-size: 1rem;
    line-height: 1.625rem;
    letter-spacing: 0.27px;
  }

  .clear-languages {
    right: 0;
    top: 50%;
    margin-right: 35px;
    transform: translateY(-50%);
  }

  /* Scrollbar styling */
  .multiselect-options::-webkit-scrollbar {
    width: 8px;
    border-radius: 3px;
  }
  .multiselect-options::-webkit-scrollbar-track {
    background: #f2f2f580;
    border-radius: 3px;
  }
  .multiselect-options::-webkit-scrollbar-thumb {
    background: #e4e4eb;
    border-radius: 3px;
  }
  .multiselect-options::-webkit-scrollbar-thumb:hover {
    background: #d6d6db;
    border-radius: 3px;
  }
}
</style>
