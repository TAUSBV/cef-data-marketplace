<template>
  <div
    class="flex flex-col justify-between rounded border bg-gray-100"
    :class="[isSelectedDocument ? 'border-blue-700' : 'border-gray-300']"
    @click.prevent="setDocumentSelected"
  >
    <div class="p-3">
      <div v-if="showRating">
        <StarRating :number-of-reviews="document.reviewStatistics.total" :score="document.reviewStatistics.score" />
      </div>

      <div class="flex justify-center items-center py-8 border-b border-gray-300" v-if="showLanguages">
        <div class="flex flex-col">
          <span class="text-gray-900 font-medium tracking-0.27">
            {{ document.sourceLanguage.displayName }}
          </span>
          <span class="text-gray-800 text-sm tracking-0.23">
            {{ document.sourceLanguage.displayCountry }}
          </span>
        </div>
        <div class="w-7 h-6 mx-6">
          <img src="/assets/img/language.svg" alt="language" class="w-full min-w-full" />
        </div>
        <div class="flex flex-col">
          <span class="text-gray-900 font-medium tracking-0.27">
            {{ document.targetLanguage.displayName }}
          </span>
          <span class="text-gray-800 text-sm tracking-0.23">
            {{ document.targetLanguage.displayCountry }}
          </span>
        </div>
      </div>

      <div class="pt-6 pb-4 document-details">
        <div class="flex items-center" v-if="showSeller">
          <span class="inline-block w-32">Seller</span>
          <router-link
            :to="`/sellers/${document.sellerFirstName}___${document.sellerLastName}___${document.sellerUid}`"
            tag="div"
            class="rounded-full h-1.875 w-1.875 bg-opacity-14 text-sm tracking-0.23 font-medium flex justify-center items-center cursor-pointer"
            :class="sellerRandomColor"
          >
            <span>{{ [document.sellerFirstName, document.sellerLastName] | initials }}</span>
          </router-link>
        </div>
        <div class="flex items-center" v-if="showDomain">
          <span class="inline-block w-32">Domain</span>
          <Tag
            :label="document.domain | truncate(12)"
            variant="compact"
            color="bg-orange-900 bg-opacity-14 text-orange-900"
          />
        </div>
        <div class="flex items-center" v-if="showContentType">
          <span class="inline-block w-32">Content Type</span>
          <Tag
            :label="document.contentType | truncate(12)"
            variant="compact"
            color="bg-purple-700 bg-opacity-14 text-purple-700"
          />
        </div>
        <div class="flex items-center" v-if="showDocument">
          <span class="inline-block w-32">Document</span>
          <router-link
            :to="
              `/sellers/${document.sellerFirstName}___${document.sellerLastName}___${document.sellerUid}/documents/${document.uid}`
            "
            tag="span"
            class="font-medium tracking-0.27 cursor-pointer"
            >{{ document.name | truncate(10) }}</router-link
          >
        </div>
        <div class="flex items-center" v-if="showSegments">
          <span class="inline-block w-32">Segments</span>
          <Tag
            :label="document.unitCount | truncate(14)"
            variant="compact"
            color="text-yellow-1000 bg-yellow-1000 bg-opacity-14"
          />
        </div>
        <div class="flex items-center" v-if="showWordPrice">
          <span class="inline-block w-32">Word price</span>
          <span class="font-medium tracking-0.27">€{{ document.pricePerWord }}</span>
        </div>
      </div>
    </div>

    <div v-if="showPrice" class="bg-gray-200 py-4 px-3 flex justify-between items-center">
      <div class="flex flex-col lg:flex-row lg:items-center w-4/5">
        <span class="text-gray-900 inline-block mr-3">Price</span>
        <span class="font-medium text-2xl text-gray-900 tracking-0.44 truncate inline-block">
          {{ document.price | currency2Decimals }}
        </span>
      </div>
      <ShoppingCartButton :product="{ documentUid: document.uid, type: productType }" variant="compact" />
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import Tag from "@/components/Tag.vue";
import ShoppingCartButton from "@/components/shopping-cart/ShoppingCartButton.vue";
import { ProductType, SellerDocument } from "@/models/models.ts";
import StarRating from "@/components/rating/StarRating.vue";

export default Vue.extend({
  props: {
    document: {
      type: Object as PropType<SellerDocument>,
      required: true
    },
    showRating: {
      type: Boolean,
      default: () => true
    },
    showLanguages: {
      type: Boolean,
      default: () => true
    },
    showDomain: {
      type: Boolean,
      default: () => true
    },
    showContentType: {
      type: Boolean,
      default: () => true
    },
    showSegments: {
      type: Boolean,
      default: () => true
    },
    showSeller: {
      type: Boolean,
      default: () => false
    },
    showWordPrice: {
      type: Boolean,
      default: () => false
    },
    showDocument: {
      type: Boolean,
      default: () => false
    },
    showPrice: {
      type: Boolean,
      default: () => true
    },
    isSelectedDocument: {
      type: Boolean
    }
  },
  methods: {
    setDocumentSelected() {
      this.$emit("setDocumentSelected", this.document);
    }
  },
  computed: {
    productType(): string {
      return ProductType.SELLER_DOCUMENT;
    },
    sellerRandomColor(): string {
      const colors = [
        "text-turquoise-blue bg-turquoise-blue",
        "text-pink-900 bg-pink-900",
        "text-yellow-900 bg-yellow-900",
        "text-green-900 bg-green-900"
      ];
      return colors[Math.floor(Math.random() * colors.length)];
    }
  },
  components: {
    Tag,
    ShoppingCartButton,
    StarRating
  }
});
</script>
<style lang="scss">
.document-details {
  div {
    &:not(:last-child) {
      @apply mb-4;
    }
  }
}
</style>
