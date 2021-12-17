<template>
  <div class="shadow-documentCard rounded">
    <div
      class="flex flex-col justify-between bg-gray-100 py-4"
      :class="{ 'divide-y divide-gray-200': !hasMultipleUnits }"
    >
      <div class="seller-details-holder grid grid-cols-2 items-center gap-4 h-full overflow-hidden pb-4 px-4">
        <div
          class="overflow-hidden"
          :class="{ 'flex-auto': !hasMultipleUnits }"
        >
          Segments
          <span
            class="inline-block text-xs lg:text-lg text-yellow-1000 tracking-widest 
                  bg-yellow-300 border border-transparent rounded-full px-3 w-auto"
          >
            <strong>{{ document.unitCount }}</strong>
          </span>
        </div>
        <div class="col-span-1 justify-self-end" v-if="hasMultipleUnits">
          <StarRating :score="document.reviewStatistics.score" :number-of-reviews="document.reviewStatistics.total" />
        </div>
        <div
          class="xs:col-span-2 sm:col-span-1 md:col-span-2 lg:col-span-1 my-auto seller-holder flex items-center"
          v-if="!hasMultipleUnits"
        >
          <div
            class="rounded-full h-10 w-10 flex items-center justify-center bg-opacity-25 text-sm font-medium relative float-left xs:mr-2 sm:mr-4 md:mr-4 lg:mr4 bg-turquoise-blue text-turquoise-blue"
          >
            {{ [document.sellerFirstName, document.sellerLastName] | initials }}
          </div>
          <div class="float-left" style="width: fit-content;">
            <p class="text-base leading-relaxed tracking-0.27 font-medium xs:text-sm  xs:tracking-0.23 text-gray-900">
              {{ document.sellerFirstName }} {{ document.sellerLastName }}
            </p>
            <p class="text-sm text-green-900">
              {{ document.sellerJoinedAt | mediumDate }}
            </p>
          </div>
        </div>
      </div>
      <div class="pt-4 pl-2 pr-2">
        <div class="language-pairs-holder grid grid-cols-9 items-center text-center pb-4">
          <div class="col-span-4">
            <div class="text-base leading-relaxed tracking-0.27 font-medium xs:text-sm xs:tracking-0.23 text-gray-900">
              {{ document.sourceLanguage.displayName }}
            </div>
            <div class="text-sm text-gray-800">
              {{ document.sourceLanguage.displayCountry }}
            </div>
            <div class="text-sm text-gray-800">{{ document.sourceWordCount }} words</div>
          </div>
          <div class="col-span-1 m-auto">
            <img src="/assets/img/language.svg" alt="language" />
          </div>
          <div class="col-span-4">
            <div class="text-lg font-medium text-gray-900">
              {{ document.targetLanguage.displayName }}
            </div>
            <div class="text-sm text-gray-800">
              {{ document.targetLanguage.displayCountry }}
            </div>
            <div class="text-sm text-gray-800">{{ document.targetWordCount }} words</div>
          </div>
        </div>
        <div
          v-if="!hasMultipleUnits"
          class="grid grid-cols-2 divide-x divide-gray-200 h-40 mb-4 overflow-hidden ts-text-gradient"
        >
          <div class="text-base text-gray-900 pl-2 pr-4" v-if="document.units.length > 0">
            {{ document.units[0].sourceValue }}
          </div>
          <div class="text-base text-gray-900 pl-4 pr-2" v-if="document.units.length > 0">
            {{ document.units[0].targetValue }}
          </div>
        </div>
        <div v-else class="overflow-y-auto segments-container mt-4" style="max-height: 57rem;">
          <table
            class="w-full table-fixed bg-gray-100 px-1"
            style="border-spacing: 5px 0px; border-collapse: separate;"
          >
            <tbody>
              <tr
                class="h-14 text-base tracking-wide leading-snug rounded odd:bg-blue-200"
                v-for="(unit, index) in document.units"
                v-bind:key="index"
              >
                <td class="p-2 align-baseline rounded">
                  {{ unit.sourceValue }}
                </td>
                <td class="p-2 align-baseline rounded">
                  {{ unit.targetValue }}
                </td>
              </tr>
            </tbody>
          </table>
        </div>

        <div class="flex-initials mx-auto mb-2 overflow-hidden" v-if="!hasMultipleUnits">
          <router-link
            :to="{
              name: 'SellerDocument',
              params: { slugId: document.sellerSlugId, uid: document.uid }
            }"
            class="text-sm text-blue-700 font-medium float-right mr-2"
          >
            View more
            <img src="/assets/img/chevron-right-blue.svg" alt="chevron down blue" class="inline ml-1" />
          </router-link>
        </div>
      </div>
      <div v-if="!hasMultipleUnits" class="tags-holder grid grid-cols-2 items-center pt-4 px-4">
        <div class="xs:col-span-2 sm:col-span-1 md:col-span-1 lg:col-span-1 xs:text-center">
          <span class="text-xs sm:text-sm text-gray-800 mr-2">Domain: </span>
          <Tag
            :label="document.domain | truncate"
            variant="compact"
            color="bg-orange-900 bg-opacity-25 text-orange-900"
            class="inline float-none py-1 px-2 text-xs"
          />
        </div>
        <div class="xs:col-span-2 sm:col-span-1 md:col-span-1 lg:col-span-1 xs:text-center">
          <span class="text-xs sm:text-sm text-gray-800 mr-2">Content type: </span>
          <Tag
            :label="document.contentType | truncate"
            variant="compact"
            color="bg-purple-700 bg-opacity-25 text-purple-700"
            class="inline float-none py-1 px-2 text-xs"
          />
        </div>
      </div>
    </div>
    <div class="flex bg-gray-300 border rounded-b p-4">
      <div class="flex-initials my-auto ml-auto mr-8">
        <span class="text-sm text-gray-800 inline-block">Price: </span>
        <span class="font-medium text-gray-900">
          {{ document.price | currency2Decimals }}
        </span>
      </div>
      <div class="float-right" :class="{ 'flex-initials': !hasMultipleUnits }">
        <ShoppingCartButton :product="{ documentUid: document.uid, type: productType }" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import ShoppingCartButton from "@/components/shopping-cart/ShoppingCartButton.vue";
import Tag from "@/components/Tag.vue";
import StarRating from "@/components/rating/StarRating.vue";
import { SellerDocument, ProductType } from "@/models/models";

export default Vue.extend({
  props: {
    document: {
      type: Object as PropType<SellerDocument>
    }
  },
  data() {
    return {
      hasMultipleUnits: this.document?.units.length > 1
    };
  },
  computed: {
    productType(): string {
      return ProductType.SELLER_DOCUMENT;
    }
  },
  components: {
    StarRating,
    Tag,
    ShoppingCartButton
  }
});
</script>

<style lang="scss">
.ts-text-gradient {
  -webkit-mask-image: -webkit-gradient(linear, left top, left bottom, from(rgba(0, 0, 0, 1)), to(rgba(0, 0, 0, 0)));
  mask-image: -webkit-gradient(linear, left top, left bottom, from(rgba(0, 0, 0, 1)), to(rgba(0, 0, 0, 0)));
}

.segments-container::-webkit-scrollbar {
  width: 8px;
  height: 8px;
}
.segments-container::-webkit-scrollbar-thumb {
  background: #e4e4eb;
  border-radius: 8px;
}
.segments-container::-webkit-scrollbar-track {
  background: rgba(242, 242, 245, 0.5);
  border: 0px none rgba(242, 242, 245, 0.5);
  border-radius: 8px;
}

@media (min-width: 768px) and (max-width: 1023px) {
  .segments-holder {
    order: 2;
  }
  .seller-holder {
    order: 1;
  }
}
@media (max-width: 640px) {
  .segments-holder {
    order: 2;
  }
  .seller-holder {
    order: 1;
  }
}

@media (min-width: 1024px) {
  .seller-details-holder {
    height: 84px;
  }
  .language-pairs-holder {
    height: 89px;
  }
  .tags-holder {
    height: 65px;
  }
}
@media (min-width: 768px) and (max-width: 1023px) {
  .seller-details-holder {
    height: 115px;
  }
  .language-pairs-holder {
    height: 89px;
  }
}
@media (min-width: 640px) and (max-width: 767px) {
  .seller-details-holder {
    height: 84px;
  }
  .language-pairs-holder {
    height: 69px;
  }
}
</style>
