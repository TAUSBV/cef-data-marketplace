<template>
  <div class="w-4/5 mx-auto my-20">
    <div class="mb-12">
      <SectionTitle title="COMMUNITY OF DATA COLLECTORS" />
    </div>
    <div class="lg:text-2.125 md:text-3xl text-2xl tracking-0.76 font-medium mb-3 text-center">
      Find the seller with the right data
    </div>
    <div class="lg:w-3/4 lg:text-lg mx-auto tracking-0.3 mb-20 text-center">
      Data Marketplace connects language data sellers to businesses seeking to acquire specialized datasets. Find the
      best match to grow your business and complete the process of acquiring the data securely within the platform.
    </div>
    <template v-if="!isFetchingData">
      <div v-for="(seller, index) in sellers.content" v-bind:key="seller.uid">
        <SellerProfile
          :seller="seller"
          :index="index"
          :isBasic="true"
          @sellerSelected="onSellerSelected(seller.slugId)"
        />
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mt-10 pb-14">
          <div v-for="document in seller.documents.slice(0, 3)" :key="document.uid">
            <DocumentCardCompact
              :document="document"
              @setDocumentSelected="setDocumentSelected"
              class="cursor-pointer"
            />
          </div>
        </div>
      </div>
      <Alert v-if="error" :messages="[error]" type="DANGER" />
      <Pagination v-if="!error && sellers.totalPages" v-model="pageNumber" :number-of-pages="sellers.totalPages" />
    </template>
    <!-- Loading placeholder -->
    <template v-else>
      <div v-for="indexSeller in 4" :key="`placeholder-seller-profile-${indexSeller}`">
        <SellerProfileLoading />
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mt-10 pb-14">
          <DocumentCardCompactLoading
            v-for="indexDocument in 3"
            :key="`placeholder-seller-document-${indexSeller}-${indexDocument}`"
          />
        </div>
      </div>
    </template>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import DocumentCardCompact from "@/components/DocumentCardCompact.vue";
import DocumentCardCompactLoading from "@/components/DocumentCardCompactLoading.vue";
import Pagination from "@/components/Pagination.vue";
import SellerProfile from "@/components/SellerProfile.vue";
import SellerProfileLoading from "@/components/SellerProfileLoading.vue";
import Alert from "@/components/Alert.vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";
import { Page, Seller, SellerDocument } from "@/models/models";

export default Vue.extend({
  name: "Sellers",
  data() {
    return {
      sellers: {} as Page<Seller>,
      pageNumber: 0,
      error: null,
      isFetchingData: true
    };
  },
  mounted() {
    this.$store
      .dispatch("seller/fetchSellers", { page: 0, size: 4 })
      .then((r: Page<Seller>) => {
        this.sellers = r;
      })
      .catch((error: string) => {
        this.error = error;
      })
      .finally(() => {
        this.isFetchingData = false;
      });
  },
  methods: {
    search() {
      this.isFetchingData = true;
      this.$store
        .dispatch("seller/fetchSellers", {
          page: this.pageNumber
        })
        .then((r: Page<Seller>) => {
          Vue.set(this, "sellers", r);
        })
        .finally(() => {
          this.isFetchingData = false;
        });
    },
    setDocumentSelected(document: SellerDocument) {
      this.$router.push({
        name: "SellerDocument",
        params: {
          slugId: document.sellerSlugId,
          uid: document.uid
        }
      });
    },
    page(page: number) {
      this.pageNumber = page;
      this.search();
    },
    onSellerSelected(slugId: string) {
      this.$router.push({
        name: "Seller",
        params: { slugId }
      });
    }
  },
  watch: {
    pageNumber(newValue: number) {
      this.page(newValue);
    }
  },
  components: {
    DocumentCardCompact,
    DocumentCardCompactLoading,
    Pagination,
    SellerProfile,
    SellerProfileLoading,
    Alert,
    SectionTitle
  }
});
</script>
<style lang="scss"></style>
