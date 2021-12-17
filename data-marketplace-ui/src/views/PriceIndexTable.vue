<template>
  <div class="w-full overflow-x-hidden">
    <div class="mt-8">
      <SectionTitle title="Price Index" />
    </div>
    <div class="mx-auto w-3/4 mt-24 mb-32">
      <div class="mt-2">
        <div class="flex flex-row justify-between mb-3">
          <span class="self-end text-sm">
            Viewing {{ pageIndex }} - {{ pageOffset }} of {{ tableInternal.totalElements }} results
          </span>
          <span>
            <FormInputSearch placeholder="Search" v-model="keywords" @submit="filter" />
          </span>
        </div>
        <section class="mb-4 pricing-table-container">
          <table class="pricing-table table-fixed">
            <thead>
              <tr
                class="h-14 text-white text-base
                font-medium tracking-wide leading-snug"
              >
                <th class="pl-4 rounded-tl-lg bg-blue-700">
                  <div class="flex flex-row items-center">
                    <span>SOURCE LANGUAGE</span>
                    <SortIndicator field="sourceLanguage" :direction="orders.get('sourceLanguage')" @sort="sort" />
                  </div>
                </th>
                <th class="pl-4 bg-blue-700">
                  <div class="flex flex-row items-center">
                    <span>TARGET LANGUAGE</span>
                    <SortIndicator field="targetLanguage" :direction="orders.get('targetLanguage')" @sort="sort" />
                  </div>
                </th>
                <th class="pl-4 bg-blue-700">
                  <div class="flex flex-row items-center">
                    <span>DOMAIN</span>
                    <SortIndicator field="domain" :direction="orders.get('domain')" @sort="sort" />
                  </div>
                </th>
                <th class="pl-4 bg-blue-700">
                  <div class="flex flex-row items-center">
                    <span>CONTENT TYPE</span>
                    <SortIndicator field="contentType" :direction="orders.get('contentType')" @sort="sort" />
                  </div>
                </th>
                <th class="pl-4 bg-blue-700">
                  <div class="flex flex-row items-center">
                    <span>AVAILABILITY</span>
                    <SortIndicator field="availability" :direction="orders.get('availability')" @sort="sort" />
                  </div>
                </th>
                <th class="w-1/6 pl-4 rounded-tr-lg bg-blue-700">
                  <div class="flex flex-row items-center">
                    <span>PRICE PER WORD</span>
                    <SortIndicator field="suggestedPrice" :direction="orders.get('suggestedPrice')" @sort="sort" />
                  </div>
                </th>
              </tr>
            </thead>
            <tbody>
              <tr
                class="h-14 text-base tracking-wide leading-snug border-l
                border-b border-r border-blue-500 even:bg-blue-500"
                v-for="(entry, index) in tableInternal.content"
                v-bind:key="index"
              >
                <td class="pl-4 ">
                  {{ entry.sourceLanguage.displayName }} ({{ entry.sourceLanguage.displayCountry }})
                </td>
                <td class="pl-4 ">
                  {{ entry.targetLanguage.displayName }} ({{ entry.targetLanguage.displayCountry }})
                </td>
                <td class="pl-4 ">{{ entry.domain }}</td>
                <td class="pl-4 ">{{ entry.contentType }}</td>
                <td class="pl-4 ">
                  <LanguageDataAvailabilityIndicator :category="entry.availabilityCategory" />
                </td>
                <td class="pl-4 ">{{ entry.suggestedPricePerWord | currency }}</td>
              </tr>
            </tbody>
          </table>
        </section>
        <div>
          <Pagination v-model="pageNumber" :number-of-pages="tableInternal.totalPages" />
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";
import FormInputSearch from "@/components/FormInputSearch.vue";
import LanguageDataAvailabilityIndicator from "@/components/LanguageDataAvailabilityIndicator.vue";
import Pagination from "@/components/Pagination.vue";
import { Page, PriceIndexEntry } from "@/models/models";
import SortIndicator from "@/components/SortIndicator.vue";

export default Vue.extend({
  name: "PriceIndexTable",
  props: {
    table: {
      type: Object as PropType<Page<PriceIndexEntry>>,
      required: true
    }
  },
  methods: {
    search() {
      const orders: Array<string> = [];
      this.orders.forEach((v, k) => {
        orders.push(k + "," + v);
      });
      this.$store
        .dispatch("priceindex/load", {
          page: this.pageNumber,
          keywords: this.keywords,
          orders: orders
        })
        .then(r => {
          this.$set(this, "tableInternal", r);
        });
    },
    sort(field: string, direction: string) {
      this.orders.clear();
      this.orders.set(field, direction);
      this.search();
    },
    page(page: number) {
      this.pageNumber = page;
      this.search();
    },
    filter() {
      this.pageNumber = 0;
      this.search();
    }
  },
  watch: {
    pageNumber(newValue: number) {
      this.page(newValue);
    }
  },
  data() {
    return {
      pageNumber: 0,
      keywords: "",
      orders: new Map(),
      tableInternal: this.table
    };
  },
  computed: {
    lowLanguage(): PriceIndexEntry {
      return this.table != null ? this.table.content[0] : null;
    },
    highLanguage(): PriceIndexEntry {
      return this.table != null ? this.table.content[this.table.content.length - 1] : null;
    },
    pageIndex(): number {
      const table = this.tableInternal;
      return table.number * table.size + 1;
    },
    pageOffset(): number {
      const table = this.tableInternal;
      return table.number * table.size + table.numberOfElements;
    },
    totalPages(): number {
      return this.tableInternal.totalPages - 1;
    }
  },
  components: {
    SortIndicator,
    FormInputSearch,
    Pagination,
    LanguageDataAvailabilityIndicator,
    SectionTitle
  }
});
</script>

<style scoped>
@media (min-width: 1024px) {
  .pricing-table {
    width: 100%;
  }
}
@media (min-width: 768px) and (max-width: 1023px) {
  .pricing-table-container {
    overflow-x: scroll;
  }
  .pricing-table {
    width: 200%;
  }
}
@media (min-width: 525px) and (max-width: 767px) {
  .pricing-table-container {
    overflow-x: scroll;
  }
  .pricing-table {
    width: 300%;
  }
}
@media (max-width: 524px) {
  .pricing-table-container {
    overflow-x: scroll;
  }
  .pricing-table {
    width: 400%;
  }
}
</style>
