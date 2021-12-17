import { computed, ref } from "@vue/composition-api";
import DataExplorerService from "@/services/DataExplorerService";
import { ContentType, DataExplorerRequest, DataExplorerResult, Domain, Language, Price, Seller } from "@/models/models";

const state = ref({
  sourceLanguage: null,
  targetLanguage: null,
  domains: [] as Domain[],
  contentTypes: [] as ContentType[],
  sellers: [] as Seller[],
  prices: [] as Price[],
  priceMin: null,
  priceMax: null,
  isLoading: false,
  segmentsView: false,
  page: null as number,
  result: null as DataExplorerResult,
  request: null as DataExplorerRequest
});

export default function explorer() {
  function setSourceLanguage(sourceLanguage: Language) {
    state.value.sourceLanguage = sourceLanguage;
  }
  function setTargetLanguage(targetLanguage: Language) {
    state.value.targetLanguage = targetLanguage;
  }
  function setDomains(domains: Array<Domain>) {
    if (domains.length !== 0 && state.value.result.filters.domains.length !== 1) {
      if (state.value.domains.length >= 1) {
        domains.forEach(item1 => {
          state.value.domains.forEach(item2 => {
            if (item1.id !== item2.id) {
              state.value.domains.push(...domains);
            }
          });
        });
      } else {
        state.value.domains.push(...domains);
      }
    } else {
      state.value.domains = domains;
    }
  }
  function deselectDomain(deselectedDomain: Domain) {
    state.value.domains = state.value.domains.filter((domain: Domain) => domain.id !== deselectedDomain.id);
  }
  function setContentTypes(contentTypes: Array<ContentType>) {
    if (contentTypes.length !== 0 && state.value.result.filters.contentTypes.length !== 1) {
      if (state.value.contentTypes.length >= 1) {
        contentTypes.forEach(item1 => {
          state.value.contentTypes.forEach(item2 => {
            if (item1.id !== item2.id) {
              state.value.contentTypes.push(...contentTypes);
            }
          });
        });
      } else {
        state.value.contentTypes.push(...contentTypes);
      }
    } else {
      state.value.contentTypes = contentTypes;
    }
  }
  function deselectContentType(deselectedContentType: ContentType) {
    state.value.contentTypes = state.value.contentTypes.filter(
      (type: ContentType) => type.id !== deselectedContentType.id
    );
  }
  function setSellers(sellers: Array<Seller>) {
    if (sellers.length !== 0 && state.value.result.filters.sellers.length !== 1) {
      if (state.value.sellers.length >= 1) {
        sellers.forEach(item1 => {
          state.value.sellers.forEach(item2 => {
            if (item1.uid !== item2.uid) {
              state.value.sellers.push(...sellers);
            }
          });
        });
      } else {
        state.value.sellers.push(...sellers);
      }
    } else {
      state.value.sellers = sellers;
    }
  }
  function deselectSeller(deselectedSeller: Seller) {
    state.value.sellers = state.value.sellers.filter((seller: Seller) => seller.uid !== deselectedSeller.uid);
  }
  function setPrices(prices: Array<Price>) {
    if (prices.length > 0 && state.value.result.filters.prices.length !== 1) {
      state.value.priceMin = Math.min(...prices.map(price => price.value));
      state.value.priceMax = Math.max(...prices.map(price => price.value));
    } else {
      state.value.priceMin = null;
      state.value.priceMax = null;
    }
  }
  function setMinPrice(minPrice: string) {
    state.value.priceMin = minPrice;
  }
  function setMaxPrice(maxPrice: string) {
    state.value.priceMax = maxPrice;
  }
  function deselectPrice(deselectedPrice: Price) {
    state.value.prices = state.value.prices.filter((price: Price) => price.value !== deselectedPrice.value);
  }
  function setResultsView(selectedView: boolean) {
    state.value.segmentsView = selectedView;
  }
  function setPage(pageNumber: number) {
    state.value.page = pageNumber;
  }
  function clearResult() {
    state.value.result = null;
  }
  function getVirtualProduct() {
    return {
      domains: state.value.request.domains,
      contentTypes: state.value.request.contentTypes,
      sellers: state.value.request.sellers,
      priceMax: state.value.request.priceMin,
      priceMin: state.value.request.priceMax,
      sourceLanguage: state.value.request.sourceLanguage,
      targetLanguage: state.value.request.targetLanguage
    };
  }
  function resetFilters() {
    setDomains([]);
    setContentTypes([]);
    setSellers([]);
    setPrices([]);
  }

  async function explore() {
    if (state.value.sourceLanguage && state.value.targetLanguage) {
      state.value.isLoading = true;
      const request = new DataExplorerRequest(state.value.sourceLanguage.id, state.value.targetLanguage.id);
      if (state.value.domains) {
        state.value.domains.forEach(d => request.domains.push(d.id));
      }
      if (state.value.contentTypes) {
        state.value.contentTypes.forEach(c => request.contentTypes.push(c.id));
      }
      if (state.value.sellers) {
        state.value.sellers.forEach(u => request.sellers.push(u.uid));
      }
      if (state.value.priceMin) {
        request.priceMin = state.value.priceMin.toString();
      }
      if (state.value.priceMax) {
        request.priceMax = state.value.priceMax.toString();
      }
      if (state.value.segmentsView) {
        request.segmentsView = state.value.segmentsView;
      }
      if (state.value.page !== null) {
        request.page = state.value.page;
      }
      state.value.request = request;
      state.value.result = await DataExplorerService.explore(request).then(r => r.data);
      state.value.isLoading = false;
    }
  }

  async function exploreWithFilters(
    s: string,
    t: string,
    domains: Array<string>,
    contentTypes: Array<string>,
    sellers: string[],
    priceMin: string,
    priceMax: string,
    resultsView: boolean
  ) {
    state.value.isLoading = true;
    const request = new DataExplorerRequest(s, t);
    if (domains) {
      domains.forEach(item => {
        request.domains.push(parseInt(item.toString()));
      });
    }
    if (contentTypes) {
      contentTypes.forEach(item => {
        request.contentTypes.push(parseInt(item.toString()));
      });
    }
    if (sellers) {
      sellers.forEach(item => {
        request.sellers.push(item);
      });
    }
    if (priceMin) {
      request.priceMin = priceMin;
    }
    if (priceMax) {
      request.priceMax = priceMax;
    }
    if (resultsView) {
      request.segmentsView = true;
      state.value.segmentsView = true;
    } else {
      request.segmentsView = false;
      state.value.segmentsView = false;
    }
    state.value.request = request;
    state.value.result = await DataExplorerService.explore(request).then(r => r.data);
    state.value.isLoading = false;
  }

  return {
    isLoading: computed(() => state.value.isLoading),
    result: computed(() => state.value.result),
    explore: explore,
    exploreWithFilters: exploreWithFilters,
    setSourceLanguage: setSourceLanguage,
    setTargetLanguage: setTargetLanguage,
    setDomains: setDomains,
    deselectDomain: deselectDomain,
    setContentTypes: setContentTypes,
    deselectContentType: deselectContentType,
    setSellers: setSellers,
    deselectSeller: deselectSeller,
    setPrices: setPrices,
    setMinPrice: setMinPrice,
    setMaxPrice: setMaxPrice,
    deselectPrice: deselectPrice,
    setResultsView: setResultsView,
    setPage: setPage,
    domainsSelected: computed(() => state.value.domains),
    contentTypesSelected: computed(() => state.value.contentTypes),
    sellersSelected: computed(() => state.value.sellers),
    clearResult: clearResult,
    getVirtualProduct: getVirtualProduct,
    resetFilters: resetFilters
  };
}
