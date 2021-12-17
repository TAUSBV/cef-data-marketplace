<template>
  <div class="w-4/5 mx-auto my-20">
    <div class="mb-12">
      <SectionTitle title="DataSets" />
    </div>

    <template v-if="serviceErrors.length === 0">
      <div class="flex items-center justify-center pb-4 mb-6 border-b border-gray-200 lg:hidden">
        <img src="/assets/img/file-outline.svg" alt="File" class="inline-block mr-3" />
        <span class="text-sm text-gray-800 mr-3">
          {{ seller.documentCount }}
        </span>
        <span class="font-medium tracking-0.27">Documents</span>
      </div>

      <div class="flex flex-col items-center lg:flex-row lg:items-start pb-14">
        <!-- Seller Sidebar -->
        <div class="w-full lg:w-96 lg:mr-14 3xl:w-1/3 lg:mt-24">
          <div v-if="!isFetchingSellerData">
            <SellerProfile :seller="seller" :index="0" :isBasic="false" class="pb-12 border-b border-gray-200" />
            <SellerDocumentInformation
              :contentTypes="seller.contentTypes"
              :domains="seller.domains"
              :languages="seller.languages"
              :verified="seller.verified"
            />
          </div>
          <div v-else>
            <SellerProfileLoading :is-basic="false" />
          </div>
        </div>
        <!-- Document Cards Grid -->
        <div class="w-full">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
            <template v-if="!isFetchingDocumentsData">
              <div class="flex items-center justify-center pb-4 border-b border-gray-200 col-span-full xs:hidden">
                <img src="/assets/img/file-outline.svg" alt="File" class="inline-block mr-3" />
                <span class="text-sm text-gray-800 mr-3">
                  {{ seller.documentCount }}
                </span>
                <span class="font-medium tracking-0.27">Documents</span>
              </div>

              <div v-for="document in documents.content" :key="document.uid">
                <DocumentCardCompact
                  :document="document"
                  class="cursor-pointer"
                  @setDocumentSelected="setDocumentSelected"
                />
              </div>
            </template>
            <!-- Loading placeholder -->
            <template v-else>
              <div v-for="index in 6" :key="`placeholder-document-${index}`">
                <DocumentCardCompactLoading />
              </div>
            </template>
          </div>
        </div>
      </div>
      <Pagination
        v-if="documents.totalPages"
        v-model="pageNumber"
        :number-of-pages="documents.totalPages"
        class="mb-24"
      />
    </template>
    <template v-else>
      <Alert :messages="serviceErrors" type="DANGER" />
    </template>
    <router-link to="/sellers" class="text-blue-700 font-medium">
      <img src="/assets/img/chevron-right-blue.svg" alt="chevron right blue" class="inline pl-1 transform rotate-180" />
      Back to Sellers page
    </router-link>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import DocumentCardCompact from "@/components/DocumentCardCompact.vue";
import DocumentCardCompactLoading from "@/components/DocumentCardCompactLoading.vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";
import Pagination from "@/components/Pagination.vue";
import SellerDocumentInformation from "@/components/SellerDocumentInformation.vue";
import SellerProfile from "@/components/SellerProfile.vue";
import SellerProfileLoading from "@/components/SellerProfileLoading.vue";
import Alert from "@/components/Alert.vue";
import { Page, Seller, SellerDocument } from "@/models/models";

export default Vue.extend({
  name: "Seller",
  data() {
    return {
      seller: {} as Seller,
      documents: {} as Page<SellerDocument>,
      pageNumber: 0,
      serviceErrors: [],
      isFetchingSellerData: true,
      isFetchingDocumentsData: true
    };
  },
  mounted() {
    this.serviceErrors = [];
    this.fetchSeller();
    this.fetchSellerDocuments();
  },
  methods: {
    fetchSeller() {
      this.isFetchingSellerData = true;
      this.$store
        .dispatch("seller/fetchSeller", this.$route.params.slugId)
        .then((r: Page<SellerDocument>) => {
          Vue.set(this, "seller", r);
        })
        .catch((error: Error) => {
          if (!this.serviceErrors.includes(error.message)) {
            this.serviceErrors.push(error.message);
          }
        })
        .finally(() => {
          this.isFetchingSellerData = false;
        });
    },
    fetchSellerDocuments() {
      this.isFetchingDocumentsData = true;
      this.$store
        .dispatch("seller/fetchDocuments", {
          slugId: this.$route.params.slugId,
          page: this.pageNumber
        })
        .then((r: Page<SellerDocument>) => {
          Vue.set(this, "documents", r);
        })
        .catch((error: Error) => {
          if (!this.serviceErrors.includes(error.message)) {
            this.serviceErrors.push(error.message);
          }
        })
        .finally(() => {
          this.isFetchingDocumentsData = false;
        });
    },
    page(page: number) {
      this.pageNumber = page;
      this.fetchSellerDocuments();
    },
    setDocumentSelected(document: SellerDocument) {
      this.$router.push({
        name: "SellerDocument",
        params: {
          slugId: document.sellerSlugId,
          uid: document.uid
        }
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
    SectionTitle,
    Pagination,
    SellerDocumentInformation,
    SellerProfile,
    SellerProfileLoading,
    Alert
  }
});
</script>
<style lang="scss">
.document-count {
  margin-left: calc(100% - 33.333333% + 7.25rem);
}
</style>
