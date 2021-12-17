<template>
  <div>
    <div class="mb-4 lg:hidden">
      <span class="text-sm leading-0 tracking-0.2"
        >You are viewing a sample of unique segments from all available documents, no duplicates.</span
      >
    </div>

    <div class="border rounded-t border-gray-300 xs:p-1 sm:p-3 md:p-3 lg:p-3 xl:px-3 xl:py-5">
      <div
        class="items-center justify-between mb-5 lg:pb-3 xl:pb-5 border-b border-gray-300 xs:hidden sm:hidden md:hidden lg:flex"
      >
        <span class="text-base leading-6 tracking-0.27"
          >You are viewing a sample of unique segments from all available documents, no duplicates.</span
        >
        <span class="flex items-center text-blue-700 cursor-pointer lg:mr-4 xl:mr-0" @click="showDetails">
          <span class="text-base leading-6 tracking-0.27 mr-2 min-w-20">{{
            hideDetails ? "View more" : "View less"
          }}</span>
          <img
            src="/assets/img/chevron-down-blue.svg"
            alt="view-more"
            class="transition duration-200 ease-in-out"
            :class="hideDetails ? 'transform rotate-0' : 'transform rotate-180'"
          />
        </span>
      </div>

      <div class="segments-container rounded xs:pr-1 sm:pr-2.5 space-y-1 slim-scrollbar static-bar">
        <div class="space-y-1.5" v-for="(sample, index) in segments" :key="index">
          <div class="grid grid-cols-2 xs:gap-1 sm:gap-1.5 rounded">
            <div
              class="col-span-1 rounded xs:p-1 sm:px-3 sm:py-2"
              :class="isOdd(index) ? 'bg-blue-200 bg-opacity-75' : ''"
            >
              <span
                class="xs:text-0.95 xs:leading-3 xs:tracking-0 sm:text-base sm:leading-5 sm:tracking-0.2 xs:break-words"
              >
                {{ sample.sourceValue }}
              </span>
            </div>
            <div
              class="col-span-1 rounded xs:p-1 sm:px-3 sm:py-2"
              :class="isOdd(index) ? 'bg-blue-200 bg-opacity-75' : ''"
            >
              <span
                class="xs:text-0.95 xs:leading-3 xs:tracking-0 sm:text-base sm:leading-5 sm:tracking-0.2 xs:break-words"
              >
                {{ sample.targetValue }}
              </span>
            </div>
          </div>
          <div
            class="flex items-center xs:space-x-2 sm:space-x-4 md:space-x-6 lg:space-x-10 rounded pl-3 pr-3 pt-2 pb-2"
            style="transition: all .2s ease-in-out;"
            :class="[
              isOdd(index) ? 'bg-blue-200 bg-opacity-75' : '',
              hideDetails ? 'segment-details-hidden' : 'segment-details-visible'
            ]"
          >
            <div
              style="width: 35px; height: 35px;"
              class="flex items-center rounded-full bg-gray-100"
              :class="isOdd(index) ? 'border border-gray-100' : 'border border-gray-300'"
            >
              <span class="text-sm tracking-0.23 mx-auto font-medium">{{
                [sample.sellerFirstName, sample.sellerLastName] | initials
              }}</span>
            </div>

            <div
              style="height: 35px;"
              class="flex items-center rounded-full bg-gray-100 pl-2 pr-2"
              :class="isOdd(index) ? 'border border-gray-100' : 'border border-gray-300'"
            >
              <span class="text-sm tracking-0.23 mx-auto font-medium">{{ sample.domain }}</span>
            </div>

            <div
              style="height: 35px;"
              class="flex items-center rounded-full bg-gray-100 pl-2 pr-2"
              :class="isOdd(index) ? 'border border-gray-100' : 'border border-gray-300'"
            >
              <span class="text-sm tracking-0.23 mx-auto font-medium">{{ sample.contentType }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="rounded-b xs:px-3 sm:px-4 md:px-4 lg:px-12 xs:py-3 sm:py-4 md:py-4 lg:py-5 bg-gray-300">
      <div class="flex items-center xs:justify-between sm:justify-between md:justify-between lg:justify-start">
        <div class="flex items-center lg:ml-auto">
          <span
            class="xs:text-base sm:text-lg md:text-lg lg:text-xl leading-1.875 xs:tracking-0.2 sm:tracking-0.37 mr-2"
            >Price:</span
          >
          <span
            class="xs:text-lg sm:text-xl md:text-xl lg:text-1.625 leading-2.25 xs:tracking-0.2 sm:tracking-0.48 font-medium"
            >{{ totalPrice | currency2Decimals }}</span
          >
        </div>
        <div class="lg:ml-20">
          <ShoppingCartButton
            :product="productVirtual"
            :isLabelVisible="responsiveCartBtn"
            :customStyling="responsiveCartBtn ? '' : 'p-0 rounded-full w-11 h-11 mx-auto'"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import ShoppingCartButton from "@/components/shopping-cart/ShoppingCartButton.vue";
import { TranslationUnit, ProductType } from "@/models/models";
import { ref, computed, onUnmounted } from "@vue/composition-api";
import explorer from "@/services/Explorer";

export default Vue.extend({
  name: "SegmentsResults",
  props: {
    segments: {
      type: Array as PropType<Array<TranslationUnit>>
    },
    totalPrice: {
      type: Number
    },
    segmentsExpanded: {
      type: Boolean,
      default: true
    }
  },
  setup(props, context) {
    const { getVirtualProduct } = explorer();
    const productVirtual = computed(() => {
      return { type: ProductType.VIRTUAL_DOCUMENT, ...getVirtualProduct() };
    });
    const hideDetails = ref(props.segmentsExpanded);
    const isOdd = function(index: number) {
      return (index + 1) % 2 == 1;
    };
    const showDetails = function() {
      hideDetails.value = !hideDetails.value;
    };

    const emitViewMoreState = function() {
      context.emit("viewMoreState", hideDetails.value);
    };

    onUnmounted(emitViewMoreState);

    return {
      productVirtual: productVirtual,
      isOdd: isOdd,
      showDetails: showDetails,
      hideDetails: hideDetails,
      responsiveCartBtn: computed(() => (window.innerWidth >= 1023))
    };
  },
  components: {
    ShoppingCartButton
  }
});
</script>

<style lang="scss" scoped>
.segments-container {
  overflow: auto;
  max-height: 65vh;
}
.segment-details-visible {
  max-height: 500px;
  overflow: auto;
}
.segment-details-hidden {
  max-height: 0px;
  overflow: hidden;
  padding-top: 0px;
  padding-bottom: 0px;
}
</style>
