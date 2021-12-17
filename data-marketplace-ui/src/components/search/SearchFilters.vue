<template>
  <section v-if="result && result.filters">
    <div class="flex items-center mb-5">
      <span
        class="text-lg tracking-1.6 xs:text-base xs:leading-relaxed xs:tracking-wide xs:ml-8 sm:ml-8 md:ml-8 uppercase"
      >
        FILTERS
      </span>
      <div class="ml-auto">
        <span class="cursor-pointer text-blue-700 text-lg" v-on:click="clearAll">
          Clear all
        </span>
      </div>
    </div>
    <div class="lg:border lg:border-b-0 rounded border-gray-300">
      <div class="p-4 xs:border-t sm:border-t md:border-t lg:border-t-0 lg:border-b border-gray-300">
        <Accordion :title="'Domain'" :isOpen="expandedFilters">
          <template v-slot:content>
            <div v-for="(option, index) in domainOptions" :key="index">
              <FormCheckbox
                :custom-label-class="'text-base tracking-wide'"
                :value="option.description"
                :v-model="checkedFilters"
                :label="option.description"
                :index="index"
                :disabled="domainOptions.length === 1"
                @change="domainChange"
              />
            </div>
          </template>
        </Accordion>
      </div>

      <div class="p-4 xs:border-t sm:border-t md:border-t lg:border-t-0 lg:border-b border-gray-300">
        <Accordion :title="'Content Type'" :isOpen="expandedFilters">
          <template v-slot:content>
            <div v-for="(option, index) in contentTypeOptions" :key="index">
              <FormCheckbox
                :custom-label-class="'text-base tracking-wide'"
                :value="option.description"
                :v-model="checkedFilters"
                :label="option.description"
                :index="index"
                :disabled="contentTypeOptions.length === 1"
                @change="contentTypeChange"
              />
            </div>
          </template>
        </Accordion>
      </div>

      <div class="p-4 xs:border-t sm:border-t md:border-t lg:border-t-0 lg:border-b border-gray-300">
        <Accordion :title="'Sellers'" :isOpen="expandedFilters">
          <template v-slot:content>
            <div v-for="(option, index) in sellerOptions" :key="index">
              <FormCheckbox
                :custom-label-class="'text-base tracking-wide'"
                :value="option.fullName"
                :v-model="checkedFilters"
                :label="option.fullName"
                :index="index"
                :disabled="sellerOptions.length === 1"
                @change="sellersChange"
              />
            </div>
          </template>
        </Accordion>
      </div>

      <div class="p-4 xs:border-t sm:border-t md:border-t lg:border-t-0 lg:border-b border-gray-300">
        <Accordion :title="'Price per word'" :isOpen="expandedFilters">
          <template v-slot:content>
            <FormInputRange
              :isDouble="true"
              min-id="minPricePerWord"
              max-id="maxPricePerWord"
              max-label="Max"
              min-label="Min"
              :is-disabled="priceOptions.length === 1"
              :minValue="priceOptions[0].text"
              :maxValue="priceOptions[priceOptions.length - 1].text"
              :selectedMinValue="
                selectedFilters.selectedPrices.min ? selectedFilters.selectedPrices.min : priceOptions[0].text
              "
              :selectedMaxValue="
                selectedFilters.selectedPrices.max
                  ? selectedFilters.selectedPrices.max
                  : priceOptions[priceOptions.length - 1].text
              "
              @changed="pricesChange($event)"
            />
          </template>
        </Accordion>
      </div>
    </div>

    <div class="lg:hidden mt-16">
      <Button class="w-full" @click="closeResponsiveFilters">
        <span class="mx-auto font-normal">Apply</span>
      </Button>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import { computed, ref, watch, onBeforeUpdate } from "@vue/composition-api";
import Button from "@/components/Button.vue";
import Accordion from "@/components/Accordion.vue";
import FormCheckbox from "@/components/FormCheckbox.vue";
import FormInputRange from "@/components/FormInputRange.vue";
import explorer from "@/services/Explorer";
import { ContentType, Domain, Seller, PriceRange } from "@/models/models";

export default Vue.extend({
  name: "SearchFilters",
  setup(_, context) {
    const {
      result,
      setDomains,
      deselectDomain,
      setContentTypes,
      deselectContentType,
      setSellers,
      deselectSeller,
      setPrices,
      setMinPrice,
      setMaxPrice,
      explore,
      domainsSelected,
      contentTypesSelected,
      sellersSelected
    } = explorer();
    const state = ref({
      result: null
    });

    const domainOptions = computed(() => state.value.result && state.value.result.filters.domains);
    const contentTypeOptions = computed(() => state.value.result && state.value.result.filters.contentTypes);
    const sellerOptions = computed(() => state.value.result && state.value.result.filters.sellers);
    const priceOptions = computed(
      () =>
        state.value.result &&
        state.value.result.filters.prices.map((f: string) => ({
          value: f,
          text: f.toString()
        }))
    );

    const minPriceOption = computed(
      () => state.value.result && Math.min(...state.value.result.filters.prices.map((price: number) => price))
    );
    const maxPriceOption = computed(
      () => state.value.result && Math.max(...state.value.result.filters.prices.map((price: number) => price))
    );

    const isDomainUnique = computed(
      () => state.value.result.filters && state.value.result.filters.domains.length === 1
    );
    const isContentTypeUnique = computed(
      () => state.value.result.filters && state.value.result.filters.contentTypes.length === 1
    );
    const isSellerUnique = computed(
      () => state.value.result.filters && state.value.result.filters.sellers.length === 1
    );
    const isPriceUnique = computed(() => state.value.result.filters && state.value.result.filters.prices.length === 1);
    const showClearFilter = computed(() => {
      return !isDomainUnique.value || !isContentTypeUnique.value || !isSellerUnique.value || !isPriceUnique.value;
    });

    const checkedFilters = ref([]);
    const selectedFilters = ref({
      selectedDomains: [],
      selectedContentTypes: [],
      selectedSellers: [],
      selectedPrices: { min: "", max: "" }
    });
    const prevSource = computed(() =>
      context.root.$route.query.s ? context.root.$route.query.s : state.value.result.statistics.sourceLanguage.id
    );
    const prevTarget = computed(() =>
      context.root.$route.query.t ? context.root.$route.query.t : state.value.result.statistics.targetLanguage.id
    );
    const Source = ref();
    const Target = ref();
    const timesRefreshed = ref(0);

    const closeResponsiveFilters = function() {
      context.emit("closeResponsiveFilters");
    };

    const setRoute = function(param: object) {
      context.root.$router.push({ query: Object.assign({}, context.root.$route.query, param) });
    };

    function domainChange(isChecked: boolean, optionID: number) {
      if (isChecked) {
        setDomains([domainOptions.value[optionID]]);
        selectedFilters.value.selectedDomains.push(domainOptions.value[optionID].id);
        if (checkedFilters.value.indexOf(domainOptions.value[optionID].description) === -1) {
          checkedFilters.value.push(domainOptions.value[optionID].description);
        }
      } else {
        deselectDomain(domainOptions.value[optionID]);
        selectedFilters.value.selectedDomains.splice(
          selectedFilters.value.selectedDomains.indexOf(domainOptions.value[optionID].id),
          1
        );
        checkedFilters.value.splice(checkedFilters.value.indexOf(domainOptions.value[optionID].description), 1);
      }
      setRoute({ d: selectedFilters.value.selectedDomains });
      explore();
    }

    function contentTypeChange(isChecked: boolean, optionID: number) {
      if (isChecked) {
        setContentTypes([contentTypeOptions.value[optionID]]);
        selectedFilters.value.selectedContentTypes.push(contentTypeOptions.value[optionID].id);
        if (checkedFilters.value.indexOf(contentTypeOptions.value[optionID].description) === -1) {
          checkedFilters.value.push(contentTypeOptions.value[optionID].description);
        }
      } else {
        deselectContentType(contentTypeOptions.value[optionID]);
        selectedFilters.value.selectedContentTypes.splice(
          selectedFilters.value.selectedContentTypes.indexOf(contentTypeOptions.value[optionID].id),
          1
        );
        checkedFilters.value.splice(checkedFilters.value.indexOf(contentTypeOptions.value[optionID].description), 1);
      }
      setRoute({ c: selectedFilters.value.selectedContentTypes });
      explore();
    }

    function sellersChange(isChecked: boolean, optionID: number) {
      if (isChecked) {
        setSellers([sellerOptions.value[optionID]]);
        selectedFilters.value.selectedSellers.push(sellerOptions.value[optionID].uid);
        if (checkedFilters.value.indexOf(sellerOptions.value[optionID].fullName) === -1) {
          checkedFilters.value.push(sellerOptions.value[optionID].fullName);
        }
      } else {
        deselectSeller(sellerOptions.value[optionID]);
        selectedFilters.value.selectedSellers.splice(
          selectedFilters.value.selectedSellers.indexOf(sellerOptions.value[optionID].uid),
          1
        );
        checkedFilters.value.splice(checkedFilters.value.indexOf(sellerOptions.value[optionID].fullName), 1);
      }
      setRoute({ u: selectedFilters.value.selectedSellers });
      explore();
    }

    function pricesChange(rangeValue: PriceRange) {
      setPrices([rangeValue.min, rangeValue.max]);
      selectedFilters.value.selectedPrices.min = rangeValue.min.text;
      selectedFilters.value.selectedPrices.max = rangeValue.max.text;

      if (context.root.$route.query.pmin) {
        rangeValue.min.text !== context.root.$route.query.pmin
          ? setRoute({ pmin: selectedFilters.value.selectedPrices.min })
          : "";
      } else {
        setRoute({ pmin: selectedFilters.value.selectedPrices.min });
      }

      if (context.root.$route.query.pmax) {
        rangeValue.max.text !== context.root.$route.query.pmax
          ? setRoute({ pmax: selectedFilters.value.selectedPrices.max })
          : "";
      } else {
        setRoute({ pmax: selectedFilters.value.selectedPrices.max });
      }

      explore();
    }

    // Get the filter parameters from the URL (if exist) and load them to the selectedFilters ref
    if (context.root.$route.query.d) {
      Array.isArray(context.root.$route.query.d)
        ? selectedFilters.value.selectedDomains.push(...context.root.$route.query.d)
        : selectedFilters.value.selectedDomains.push(context.root.$route.query.d);
    }

    if (context.root.$route.query.c) {
      Array.isArray(context.root.$route.query.c)
        ? selectedFilters.value.selectedContentTypes.push(...context.root.$route.query.c)
        : selectedFilters.value.selectedContentTypes.push(context.root.$route.query.c);
    }

    if (context.root.$route.query.u) {
      Array.isArray(context.root.$route.query.u)
        ? selectedFilters.value.selectedSellers.push(...context.root.$route.query.u)
        : selectedFilters.value.selectedSellers.push(context.root.$route.query.u);
    }

    if (context.root.$route.query.pmin && !context.root.$route.query.pmax) {
      selectedFilters.value.selectedPrices.min = context.root.$route.query.pmin as string;
      setMinPrice(selectedFilters.value.selectedPrices.min);
    }

    if (context.root.$route.query.pmax && !context.root.$route.query.pmin) {
      selectedFilters.value.selectedPrices.max = context.root.$route.query.pmax as string;
      setMaxPrice(selectedFilters.value.selectedPrices.max);
    }

    if (context.root.$route.query.pmin && context.root.$route.query.pmax) {
      selectedFilters.value.selectedPrices.min = context.root.$route.query.pmin as string;
      selectedFilters.value.selectedPrices.max = context.root.$route.query.pmax as string;
    }

    onBeforeUpdate(() => {
      // This is done on before updated because the filters become available to the state after the component is mounted
      // To prevent this from running on every filter change (every time a filter is selected, onBeforeUpdate is triggered)
      // we check if the language pair is the same and if it's not, only then we update the array of all the available filters
      // and we do so because different language pairs have different filters
      // Also, we keep track of how many times the state has been refreshed upon filter selections
      // because the prev and current values of languages will be the same on first load and the if statement would not enter

      // Get filters params from the URL and load the filters to the state
      if (prevSource.value !== Source.value || prevTarget.value !== Target.value || timesRefreshed.value <= 1) {
        const tempDomains = Array<Domain>();
        for (let i = 0; i < selectedFilters.value.selectedDomains.length; i++) {
          domainOptions.value.find(function(item: Domain) {
            if (item.id == selectedFilters.value.selectedDomains[i]) {
              tempDomains.push(item);
              if (checkedFilters.value.indexOf(item.description) === -1) {
                checkedFilters.value.push(item.description);
              }
            }
          });
        }
        setDomains(tempDomains);

        const tempContentTypes = Array<ContentType>();
        for (let i = 0; i < selectedFilters.value.selectedContentTypes.length; i++) {
          contentTypeOptions.value.find(function(item: ContentType) {
            if (item.id == selectedFilters.value.selectedContentTypes[i]) {
              tempContentTypes.push(item);
              if (checkedFilters.value.indexOf(item.description) === -1) {
                checkedFilters.value.push(item.description);
              }
            }
          });
        }
        setContentTypes(tempContentTypes);

        const tempSellers = Array<Seller>();
        for (let i = 0; i < selectedFilters.value.selectedSellers.length; i++) {
          sellerOptions.value.find(function(item: Seller) {
            if (item.uid == selectedFilters.value.selectedSellers[i]) {
              tempSellers.push(item);
              if (checkedFilters.value.indexOf(item.fullName) === -1) {
                checkedFilters.value.push(item.fullName);
              }
            }
          });
        }
        setSellers(tempSellers);

        if (selectedFilters.value.selectedPrices.min && selectedFilters.value.selectedPrices.max) {
          setPrices([
            {
              text: selectedFilters.value.selectedPrices.min.toString(),
              value: parseFloat(selectedFilters.value.selectedPrices.min)
            },
            {
              text: selectedFilters.value.selectedPrices.max.toString(),
              value: parseFloat(selectedFilters.value.selectedPrices.max)
            }
          ]);
        }
      }
    });

    // When clearing filters, we only need to clear URL params regarding the filters. We keep view and page params.
    const clearRoute = function() {
      const s = context.root.$route.query.s;
      const t = context.root.$route.query.t;
      const v = context.root.$route.query.v;
      const pg = context.root.$route.query.pg;

      context.root.$router.push({
        name: "Search",
        query: {}
      });

      context.root.$router.push({
        name: "Search",
        query: {
          s: s,
          t: t,
          v: v,
          pg: pg
        }
      });
    };

    const clearFilters = function() {
      selectedFilters.value.selectedDomains = [];
      selectedFilters.value.selectedContentTypes = [];
      selectedFilters.value.selectedSellers = [];
      selectedFilters.value.selectedPrices.min = "";
      selectedFilters.value.selectedPrices.max = "";
      checkedFilters.value = [];

      clearRoute();
    };

    function clearAll() {
      if (!isDomainUnique.value) {
        setDomains([]);
      }
      if (!isContentTypeUnique.value) {
        setContentTypes([]);
      }
      if (!isSellerUnique.value) {
        setSellers([]);
      }
      if (!isPriceUnique.value) {
        setPrices([]);
      }

      clearFilters();
      explore();
    }

    // Clear filters if user selects different language pair
    watch(result, () => {
      timesRefreshed.value += 1;
      if (!Source.value || !Target.value) {
        Source.value = prevSource.value;
        Target.value = prevTarget.value;
      }

      if (prevSource.value !== Source.value || prevTarget.value !== Target.value) {
        clearFilters();
        timesRefreshed.value = 0;

        Source.value = prevSource.value;
        Target.value = prevTarget.value;
      }
    });

    const updateResult = async () => {
      state.value.result = result;

      // Preselect the item when it is only one
      if (state.value.result) {
        if (isDomainUnique.value) {
          setDomains(state.value.result.filters.domains);
          if (checkedFilters.value.indexOf(state.value.result.filters.domains[0].description) === -1) {
            checkedFilters.value.push(state.value.result.filters.domains[0].description);
          }
        }

        if (isContentTypeUnique.value) {
          setContentTypes(state.value.result.filters.contentTypes);
          if (checkedFilters.value.indexOf(state.value.result.filters.contentTypes[0].description) === -1) {
            checkedFilters.value.push(state.value.result.filters.contentTypes[0].description);
          }
        }

        if (isSellerUnique.value) {
          setSellers(state.value.result.filters.sellers);
          if (checkedFilters.value.indexOf(state.value.result.filters.sellers[0].fullName) === -1) {
            checkedFilters.value.push(state.value.result.filters.sellers[0].fullName);
          }
        }

        if (isPriceUnique.value) {
          setPrices([
            {
              value: state.value.result.filters.prices[0],
              text: state.value.result.filters.prices[0].toString()
            }
          ]);
        }
      }
    };

    watch(result, updateResult);

    return {
      result: computed(() => state.value.result),
      domainOptions: domainOptions,
      contentTypeOptions: contentTypeOptions,
      sellerOptions: sellerOptions,
      priceOptions: priceOptions,
      maxPriceOption: maxPriceOption,
      minPriceOption: minPriceOption,
      domainsSelected: domainsSelected,
      contentTypesSelected: contentTypesSelected,
      sellersSelected: sellersSelected,
      pricesChange: pricesChange,
      clearAll: clearAll,
      showClearFilter: showClearFilter,
      domainChange: domainChange,
      contentTypeChange: contentTypeChange,
      sellersChange: sellersChange,
      selectedFilters: selectedFilters,
      checkedFilters: checkedFilters,
      closeResponsiveFilters: closeResponsiveFilters,
      expandedFilters: computed(() => window.innerWidth > 1280)
    };
  },
  components: {
    Button,
    Accordion,
    FormCheckbox,
    FormInputRange
  }
});
</script>

<style lang="scss" scoped></style>
