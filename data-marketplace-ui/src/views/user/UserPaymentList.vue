<template>
  <section>
    <div v-if="!isFetchingData && payments.totalElements > 0">
      <div class="flex flex-row justify-between mb-3" v-if="payments.totalPages">
        <span class="self-end text-sm">
          Viewing {{ pageIndex }} - {{ pageOffset }} of {{ payments.totalElements }} results
        </span>
      </div>
      <!-- TODO: Make table a separate reusable component -->
      <div class="overflow-x-auto">
        <table class="w-full table-auto">
          <thead>
            <tr class="h-14 bg-blue-700 uppercase text-left text-white text-sm tracking-wide">
              <th v-for="header in tableColHeaders" v-bind:key="header.id" class="px-4 w-32">
                <div class="flex flex-row items-center justify-between">
                  <span>{{ header.value }}</span>
                  <SortIndicator :field="header.name" v-if="header.value" />
                </div>
              </th>
              <th class="px-4 w-10"></th>
            </tr>
          </thead>
          <tbody v-if="paymentList.length > 0">
            <template v-for="(payment, index) in paymentList">
              <tr
                v-bind:key="index"
                @click="toggleNestedTable(payment.uuid)"
                class="text-sm border-b border-blue-500 cursor-pointer"
              >
                <td class="p-4 break-all">{{ payment.uuid }}</td>
                <td class="p-4 font-bold">{{ payment.sumTotalEarning | currency2Decimals }}</td>
                <td class="p-4">
                  <span v-if="payment.requestedAt">
                    {{ payment.requestedAt | mediumDate }}
                  </span>
                  <span v-else class="text-gray-500">
                    N/A
                  </span>
                </td>
                <td class="p-4">
                  <span v-if="payment.settledAt">
                    {{ payment.settledAt | mediumDate }}
                  </span>
                  <span v-else class="text-gray-500">
                    N/A
                  </span>
                </td>
                <td class="p-4">
                  <!-- TODO: Make a separate status badge component with color variants -->
                  <span
                    :class="[
                      payment.status === 'SETTLED'
                        ? 'bg-green-600'
                        : payment.status === 'PENDING'
                        ? 'bg-yellow-800'
                        : 'bg-red-600'
                    ]"
                    class="rounded-full py-2 px-6 uppercase font-bold text-xs text-white"
                    >{{ payment.status }}</span
                  >
                </td>
                <td class="pr-4">
                  <img
                    alt="expand icon"
                    class="pt-1 w-4 m-auto transition duration-300 ease-in-out"
                    :class="{ 'transform rotate-180': nestedTableRows.includes(payment.uuid) }"
                    src="/assets/img/chevron-down.svg"
                  />
                </td>
              </tr>
              <!-- Row details (nested table)-->
              <template v-if="nestedTableRows.includes(payment.uuid)">
                <tr v-bind:key="payment.uuid" class="border-b border-blue-500 shadow-inner bg-blue-200">
                  <td colspan="7" class="px-6 py-4">
                    <table class="w-full table-fixed">
                      <thead>
                        <tr
                          class="h-14 bg-transparent uppercase text-left text-blue-700 text-sm border-b border-blue-1000"
                        >
                          <th
                            v-for="nestedHeader in nestedTableColHeaders"
                            v-bind:key="nestedHeader.id"
                            class="px-3 overflow-ellipsis overflow-hidden"
                          >
                            <span>{{ nestedHeader.value }}</span>
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr
                          v-for="(sale, index) in payment.items"
                          v-bind:key="index"
                          class="text-sm border-b border-blue-500"
                        >
                          <td class="px-3 py-4">
                            <span v-if="sale.soldAt">
                              {{ sale.soldAt }}
                            </span>
                            <span v-else class="text-gray-500">
                              N/A
                            </span>
                          </td>
                          <td class="px-3 py-4 truncate" :title="sale.docName">{{ sale.docName }}</td>
                          <td class="px-3 py-4">{{ sale.unitPrice | currency2Decimals }}</td>
                          <td class="px-3 py-4">{{ sale.quantity }}</td>
                          <td class="px-3 py-4">{{ sale.totalPrice | currency2Decimals }}</td>
                          <td class="px-3 py-4">{{ sale.totalCommission | currency2Decimals }}</td>
                          <td class="px-3 py-4">{{ sale.totalEarning | currency2Decimals }}</td>
                        </tr>
                      </tbody>
                      <tfoot>
                        <tr>
                          <th colspan="5"></th>
                          <th class="text-left text-sm uppercase font-medium text-gray-500 px-3 pt-5 truncate">
                            Subtotal
                          </th>
                          <td class="px-3 text-gray-500 font-medium text-sm pt-5">
                            {{ payment.sumTotalPrice | currency2Decimals }}
                          </td>
                        </tr>
                        <tr>
                          <th colspan="5"></th>
                          <th class="text-left text-sm text-gray-500 font-medium uppercase px-3 pt-3 truncate">
                            Commissions
                          </th>
                          <td class="px-3 pt-3 text-gray-500 font-medium text-sm">
                            {{ payment.sumTotalCommission | currency2Decimals }}
                          </td>
                        </tr>
                        <tr>
                          <th colspan="5"></th>
                          <th class="text-left text-sm uppercase font-bold px-3 pt-3 truncate">
                            Total
                          </th>
                          <td class="px-3 pt-3 font-bold text-sm">
                            {{ payment.sumTotalEarning | currency2Decimals }}
                          </td>
                        </tr>
                      </tfoot>
                    </table>
                  </td>
                </tr>
              </template>
            </template>
          </tbody>
          <tbody v-else>
            <tr>
              <td class="p-4 text-center text-gray-500 font-bold border-b border-blue-500" colspan="7">
                No results found
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <Pagination v-if="!error && payments.totalPages" v-model="pageNumber" :number-of-pages="payments.totalPages" />
    </div>
    <div
      v-else-if="!isFetchingData && payments.totalElements === 0"
      class="text-lg tracking-0.3 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27"
    >
      No payments have been found. Relax soon you will land a sale. Continue
      <router-link to="/publications" class="text-blue-700">publishing</router-link>
      your translation memories.
    </div>
    <div v-else>
      <TableLoading />
    </div>
  </section>
</template>

<script lang="ts">
// Core
import Vue from "vue";
// Models
import { Page, SellerPayment, SellerSale } from "@/models/models";
// Filters
import { MediumDateFilter } from "@/filters";
// Components
import SortIndicator from "@/components/SortIndicator.vue";
import Pagination from "@/components/Pagination.vue";
import TableLoading from "@/components/layout/TableLoading.vue";

export default Vue.extend({
  name: "UserPaymentList",
  data() {
    return {
      payments: {} as Page<SellerPayment>,
      paymentList: [] as SellerPayment[],
      tableColHeaders: [
        { id: 1, name: "id", value: "ID" },
        { id: 2, name: "amount", value: "Amount" },
        { id: 3, name: "dateRequested", value: "Date Requested" },
        { id: 4, name: "dateProcessed", value: "Date Processed" },
        { id: 5, name: "status", value: "Status" }
      ],
      nestedTableColHeaders: [
        { id: 1, name: "purchaseDate", value: "Purchase Date" },
        { id: 2, name: "item", value: "Item" },
        { id: 3, name: "unitPrice", value: "Unit Price" },
        { id: 4, name: "quantity", value: "Quantity" },
        { id: 5, name: "totalPrice", value: "Total Price" },
        { id: 6, name: "commission", value: "Commission" },
        { id: 7, name: "earnings", value: "Earnings" }
      ],
      nestedTableRows: [],
      pageNumber: 0,
      isFetchingData: true,
      error: null
    };
  },
  mounted() {
    this.fetchSellerPayments();
  },
  methods: {
    toggleNestedTable(id: string) {
      const index = this.nestedTableRows.indexOf(id);
      if (index > -1) {
        this.nestedTableRows.splice(index, 1);
      } else {
        this.nestedTableRows.push(id);
      }
    },
    groupPaymentSales(payments: SellerPayment[]) {
      return payments.map((payment: SellerPayment) => {
        payment.items = payment.items.reduce((reducer: SellerSale[], acc: SellerSale) => {
          const index: number = reducer.findIndex(
            (item: SellerSale) =>
              item.docName === acc.docName && MediumDateFilter(item.soldAt) === MediumDateFilter(acc.soldAt)
          );

          if (index === -1) {
            reducer.push({ ...acc, soldAt: MediumDateFilter(acc.soldAt) });
          } else {
            reducer[index].quantity += acc.quantity;
            reducer[index].totalPrice += acc.totalPrice;
            reducer[index].totalEarning += acc.totalEarning;
            reducer[index].totalCommission += acc.totalCommission;
          }

          return reducer;
        }, []);

        return payment;
      });
    },
    fetchSellerPayments(): void {
      this.$store
        .dispatch("seller/fetchSellerPayments", {
          page: this.pageNumber
        })
        .then((payments: Page<SellerPayment>) => {
          this.payments = payments;
          this.paymentList = payments.content;

          this.paymentList = this.groupPaymentSales(this.paymentList);
        })
        .catch((error: string) => {
          this.error = error;
        })
        .finally(() => (this.isFetchingData = false));
    },
    onPageChange(page: number) {
      this.pageNumber = page;
    }
  },
  watch: {
    pageNumber(newValue: number) {
      this.onPageChange(newValue);
      this.fetchSellerPayments();
    }
  },
  computed: {
    pageIndex(): number {
      return this.payments.number * this.payments.size + 1;
    },
    pageOffset(): number {
      return this.payments.number * this.payments.size + this.payments.numberOfElements;
    },
    totalPages(): number {
      return this.payments.totalPages - 1;
    }
  },
  components: {
    SortIndicator,
    Pagination,
    TableLoading
  }
});
</script>

<style lang="scss">
table {
  th {
    &:first-of-type {
      border-top-left-radius: 0.25rem;
    }
    &:last-of-type {
      border-top-right-radius: 0.25rem;
    }
  }
}
</style>
