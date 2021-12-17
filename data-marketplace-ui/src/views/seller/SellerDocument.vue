<template>
  <section class="bg-gray-100">
    <div class="mt-8">
      <SectionTitle title="DOCUMENT SAMPLE" />
    </div>
    <div class="flex flex-col w-3/4 pt-16 pb-32 mx-auto relative">
      <div class="grid grid-cols-1 lg:grid-cols-3 pt-6 pb-24" v-if="!isFetchingData">
        <div class="col-span-2 lg:col-span-1 mx-auto lg:px-12">
          <SellerProfile
            :seller="seller"
            :index="0"
            :isBasic="false"
            class="border-b border-gray-200 pb-12 text-center"
          />
          <!-- TODO index -->
          <SellerDocumentInformation
            :seller="seller"
            :contentTypes="[document.contentType]"
            :domains="[document.domain]"
            :languages="[`${document.sourceLanguage.tag}, ${document.targetLanguage.tag}`]"
            :verified="seller.verified"
          />
        </div>
        <div class="col-span-2 border-none">
          <DocumentCard :document="document" :seller="seller" />
        </div>
        <div class="mt-8 col-span-3 sm:p-8 md:p-0">
          <div class="grid grid-cols-3 items-end" v-if="reviews.length > 0">
            <div class="col-span-1"></div>
            <div class="col-span-3 sm:col-span-2 flex items-end justify-between">
              <div>{{ reviews.length }} review<span v-if="reviews.length > 1">s</span></div>
            </div>
          </div>
        </div>
        <figure v-for="r in reviews" :key="r.documentUid" class="col-span-3 sm:p-8 md:p-0">
          <div class="grid grid-cols-3 divide-y divide-gray-300 divide-solid">
            <div class="hidden sm:flex sm:justify-end px-8">
              <div
                :class="sellerProfileColor(1)"
                class="flex justify-center items-center mt-6 col-span-1 rounded-full h-20 w-20 min-w-20 bg-opacity-14 text-3xl tracking-0.7 font-medium relative"
              >
                <img
                  v-if="seller.verified"
                  src="/assets/img/verification.svg"
                  alt="Verified"
                  class="w-6 h-6 absolute right-0 top-0"
                />
                <span>{{ [r.reviewerFirstName, r.reviewerLastName] | initials }}</span>
              </div>
            </div>
            <div class="col-span-3 sm:col-span-2 sm:text-center md:text-left space-y-4">
              <figcaption class="mt-6 font-medium">
                <div class="text-cyan-600">{{ r.reviewerFirstName }} {{ r.reviewerLastName }}</div>
                <div class="text-gray-500">
                  <StarRating :score="r.score" size="sm" :show-score="false" :show-emojis="false" :is-disabled="true" />
                </div>
                <div class="text-sm font-light">{{ r.createdAt | date }}</div>
              </figcaption>
              <blockquote>
                <p class="text-lg font-normal tracking-tight">
                  {{ r.comment }}
                </p>
              </blockquote>
            </div>
          </div>
        </figure>
      </div>

      <!-- Loading placeholder -->
      <div class="grid grid-cols-1 lg:grid-cols-3 pt-6 pb-24" v-else>
        <div class="col-span-1 mx-auto lg:px-12">
          <SellerProfileLoading class="border-b border-gray-200 pb-12 text-center" />
        </div>
        <div class="col-span-2 border-none">
          <DocumentCardLoading />
        </div>
      </div>

      <Alert v-if="error" :messages="[error]" type="DANGER" />
      <router-link :to="{ name: 'Seller', params: { slugId: seller.slugId } }" class="text-blue-700 font-medium">
        <img
          src="/assets/img/chevron-right-blue.svg"
          alt="chevron right blue"
          class="inline pl-1 transform rotate-180"
        />
        Back to Sellers profile
      </router-link>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import DocumentCard from "@/components/DocumentCard.vue";
import DocumentCardLoading from "@/components/DocumentCardLoading.vue";
import SellerDocumentInformation from "@/components/SellerDocumentInformation.vue";
import SellerProfile from "@/components/SellerProfile.vue";
import SellerProfileLoading from "@/components/SellerProfileLoading.vue";
import Alert from "@/components/Alert.vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";
import { Seller, SellerDocument, TranslationDocumentReview } from "@/models/models";
import StarRating from "@/components/rating/StarRating.vue";

export default Vue.extend({
  name: "SellerDocument",
  data() {
    return {
      seller: {} as Seller,
      document: {} as SellerDocument,
      error: null,
      isFetchingData: true,
      reviews: [] as Array<TranslationDocumentReview>
    };
  },
  methods: {
    sellerProfileColor(index: number): string {
      // TODO check colors
      const colors = [
        "text-turquoise-blue bg-turquoise-blue",
        "text-pink-900 bg-pink-900",
        "text-yellow-900 bg-yellow-900",
        "text-green-900 bg-green-900"
      ];
      return colors[index];
    }
  },
  mounted() {
    const promisesArray = [];
    promisesArray.push(this.$store.dispatch("seller/fetchSeller", this.$route.params.slugId));
    promisesArray.push(
      this.$store.dispatch("seller/fetchDocument", {
        slugId: this.$route.params.slugId,
        uid: this.$route.params.uid
      })
    );
    promisesArray.push(
      this.$store.dispatch("document/fetchReviews", {
        uid: this.$route.params.uid
      })
    );

    Promise.all(promisesArray)
      .then(response => {
        this.seller = response[0];
        this.document = response[1];
        this.reviews = response[2];
      })
      .catch(error => {
        this.error = error;
      })
      .finally(() => {
        this.isFetchingData = false;
      });
  },
  components: {
    StarRating,
    DocumentCard,
    DocumentCardLoading,
    SellerDocumentInformation,
    SellerProfile,
    SellerProfileLoading,
    Alert,
    SectionTitle
  }
});
</script>
<style lang="scss"></style>
