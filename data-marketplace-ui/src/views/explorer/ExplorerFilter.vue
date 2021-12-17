<template>
  <section v-if="result">
    <div class="flex justify-between">
      <div class="text-lg tracking-1.6 font-normal xs:text-base xs:leading-relaxed xs:tracking-wide uppercase">
        FILTERS
      </div>
      <div v-if="showClearFilter">
        <Badge :value="selectedCount" />
        <span class="cursor-pointer text-blue-900 font-medium" v-on:click="clearAll">
          Clear all
        </span>
      </div>
    </div>
    <div class="grid gap-4 pt-10 grid-cols-4">
      <div class="xs:col-span-4 sm:col-span-4 md:col-span-2 lg:col-span-1">
        <DropDown
          id="domain"
          placeholder="Domain"
          :disabled="domainOptions.length === 1"
          :options="domainOptions"
          :value="domainsSelected"
          :is-required="false"
          :mode="domainOptions.length === 1 ? 'tags' : 'multiple'"
          trackByKey="description"
          @selected="val => selectDomain(val)"
          @removed="val => removeDomain(val)"
          @changed="val => clearAllDomains(val)"
        />
      </div>
      <div class="xs:col-span-4 sm:col-span-4 md:col-span-2 lg:col-span-1">
        <DropDown
          id="contentType"
          placeholder="Content Type"
          :disabled="contentTypeOptions.length === 1"
          :options="contentTypeOptions"
          :value="contentTypesSelected"
          :is-required="false"
          :mode="contentTypeOptions.length === 1 ? 'tags' : 'multiple'"
          trackByKey="description"
          @selected="val => selectContentType(val)"
          @removed="val => removeContentType(val)"
          @changed="val => clearAllContentTypes(val)"
        />
      </div>
      <div class="xs:col-span-4 sm:col-span-4 md:col-span-2 lg:col-span-1">
        <DropDown
          id="sellers"
          placeholder="Sellers"
          :disabled="sellerOptions.length === 1"
          :is-required="false"
          :options="sellerOptions"
          :value="sellersSelected"
          :mode="sellerOptions.length === 1 ? 'tags' : 'multiple'"
          trackByKey="fullName"
          @selected="val => selectSeller(val)"
          @removed="val => removeSeller(val)"
          @changed="val => clearAllSellers(val)"
        />
      </div>
      <div class="xs:col-span-4 sm:col-span-4 md:col-span-2 lg:col-span-1">
        <DropDown
          id="pricePerWord"
          placeholder="Price Per Word"
          :is-required="false"
          :disabled="priceOptions.length === 1"
          :options="priceOptions"
          :value="pricesSelected"
          :mode="priceOptions.length === 1 ? 'tags' : 'multiple'"
          trackByKey="text"
          @selected="val => selectPrice(val)"
          @removed="val => removePrice(val)"
          @changed="val => clearAllPrices(val)"
        />
      </div>
    </div>
    <div v-if="!result.statistics">
      <h1 class="mt-12 text-xl text-center">Nothing found for the chosen filters.</h1>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import { computed, ref, watch } from "@vue/composition-api";
import Badge from "@/components/Badge.vue";
import DropDown from "@/components/DropDown.vue";
import explorer from "@/services/Explorer";
import { ContentType, Domain, Price, Seller } from "@/models/models";

export default Vue.extend({
  name: "ExploreFilter",
  setup() {
    const {
      result,
      setDomains,
      deselectDomain,
      setContentTypes,
      deselectContentType,
      setSellers,
      deselectSeller,
      setPrices,
      deselectPrice,
      explore,
      domainsSelected,
      contentTypesSelected,
      sellersSelected
      /*pricesSelected*/
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
    const isDomainUnique = computed(() => state.value.result.filters.domains.length === 1);
    const isContentTypeUnique = computed(() => state.value.result.filters.contentTypes.length === 1);
    const isSellerUnique = computed(() => state.value.result.filters.sellers.length === 1);
    const isPriceUnique = computed(() => state.value.result.filters.prices.length === 1);
    const showClearFilter = computed(() => {
      return !isDomainUnique.value || !isContentTypeUnique.value || !isSellerUnique.value || !isPriceUnique.value;
    });
    const selectedCount = computed(() => {
      let count = 0;
      if (!isDomainUnique.value) {
        count += domainsSelected.value.length;
      }
      if (!isContentTypeUnique.value) {
        count += contentTypesSelected.value.length;
      }
      if (!isSellerUnique.value) {
        count += sellersSelected.value.length;
      }
      // if (!isPriceUnique.value) {
      //   count += pricesSelected.value.length;
      // }
      return count;
    });

    function selectDomain(selectedDomain: Domain) {
      setDomains([selectedDomain]);
      explore();
    }

    function removeDomain(selectedDomain: Domain) {
      deselectDomain(selectedDomain);
      explore();
    }

    function clearAllDomains(domains: Domain[]) {
      if (domains.length === 0) {
        setDomains([]);
      }
    }

    function selectContentType(selectedContentType: ContentType) {
      setContentTypes([selectedContentType]);
      explore();
    }

    function removeContentType(selectedContentType: ContentType) {
      deselectContentType(selectedContentType);
      explore();
    }

    function clearAllContentTypes(contentTypes: ContentType[]) {
      if (contentTypes.length === 0) {
        setContentTypes([]);
      }
    }

    function selectSeller(selectedSeller: Seller) {
      setSellers([selectedSeller]);
      explore();
    }

    function removeSeller(selectedSeller: Seller) {
      deselectSeller(selectedSeller);
      explore();
    }

    function clearAllSellers(sellers: Seller[]) {
      if (sellers.length === 0) {
        setSellers([]);
      }
    }

    function selectPrice(selectedPrice: Price) {
      setPrices([selectedPrice]);
      explore();
    }

    function removePrice(selectedPrice: Price) {
      deselectPrice(selectedPrice);
      explore();
    }

    function clearAllPrices(prices: Price[]) {
      if (prices.length === 0) {
        setPrices([]);
      }
    }

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
      explore();
    }

    const updateResult = async () => {
      state.value.result = result;

      // Preselect the item when it is only one
      if (state.value.result) {
        if (isDomainUnique.value) {
          setDomains(state.value.result.filters.domains);
        }
        if (isContentTypeUnique.value) {
          setContentTypes(state.value.result.filters.contentTypes);
        }
        if (isSellerUnique.value) {
          setSellers(state.value.result.filters.sellers);
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
      domainsSelected: domainsSelected,
      contentTypesSelected: contentTypesSelected,
      sellersSelected: sellersSelected,
      // pricesSelected: pricesSelected,
      selectDomain: selectDomain,
      removeDomain: removeDomain,
      clearAllDomains: clearAllDomains,
      selectContentType: selectContentType,
      removeContentType: removeContentType,
      clearAllContentTypes: clearAllContentTypes,
      selectSeller: selectSeller,
      removeSeller: removeSeller,
      clearAllSellers: clearAllSellers,
      selectPrice: selectPrice,
      removePrice: removePrice,
      clearAllPrices: clearAllPrices,
      clearAll: clearAll,
      selectedCount: selectedCount,
      showClearFilter: showClearFilter
    };
  },
  components: {
    Badge,
    DropDown
  }
});
</script>

<style lang="scss" scoped></style>
