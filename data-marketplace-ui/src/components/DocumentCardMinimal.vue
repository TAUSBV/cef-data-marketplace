<template>
  <div
    class="flex flex-col justify-between rounded border bg-gray-100"
    :class="[isSelectedDocument ? 'border-blue-700' : 'border-gray-300']"
    @click.prevent="setDocumentSelected"
  >
    <div class="p-2">
      <div v-if="showRating">
        <span class="text-sm tracking-0.23 text-gray-800" v-if="document.score === 0">No reviews yet</span>
        <!-- TODO: Replace hardcoded values with those coming from the BE -->
        <StarRating v-else :number-of-reviews="5" :score="3.25" />
      </div>

      <div class="pt-3 flex flex-wrap items-center md:justify-between w-full">
        <!-- Seller -->
        <router-link
          :to="`/sellers/${document.sellerFirstName}___${document.sellerLastName}___${document.sellerUid}`"
          tag="div"
          class="mb-3 mr-3 rounded-full h-8 w-8 bg-opacity-14 text-base tracking-0.27 font-medium flex justify-center items-center cursor-pointer"
          :class="sellerRandomColor"
        >
          <span>{{ [document.sellerFirstName, document.sellerLastName] | initials }}</span>
        </router-link>
        <!-- Domain -->
        <Tag
          :label="document.domain"
          variant="compact"
          color="bg-orange-900 bg-opacity-14 text-orange-900"
          class="mb-3 mr-3"
        />
        <!-- Content Type -->
        <Tag
          :label="document.contentType"
          variant="compact"
          color="bg-purple-700 bg-opacity-14 text-purple-700"
          class="mb-3 mr-3"
        />
        <!-- Segments -->
        <Tag
          :label="`${document.unitCount} segments`"
          variant="compact"
          color="text-yellow-1000 bg-yellow-1000 bg-opacity-14"
          class="mb-3 mr-3"
        />
      </div>
    </div>

    <div class="bg-gray-200 py-4 px-2 flex justify-between items-center">
      <div>
        <div class="flex items-center">
          <span class="text-gray-900 inline-block tracking-0.27 mr-3">Total Price</span>
          <span class="font-medium text-lg text-gray-900 tracking-0.3 truncate inline-block w-24">
            {{ document.price | currency2Decimals }}
          </span>
        </div>
        <div class="flex items-center">
          <span class="text-gray-800 inline-block tracking-0.2 text-xs mr-3">Price per word</span>
          <span class="text-gray-800 inline-block tracking-0.2 text-xs">€{{ document.pricePerWord }}</span>
        </div>
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
