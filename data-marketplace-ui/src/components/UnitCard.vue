<template>
  <div class="shadow-documentCard rounded divide-y divide-gray-200 p-4">
    <!-- Header: seller -->
    <div class="flex mb-4">
      <SellerInitials
        :firstName="translationUnit.sellerFirstName || ''"
        :lastName="translationUnit.sellerLastName || ''"
        :joinedAt="translationUnit.sellerJoinedAt"
      />
    </div>
    <!-- Content: units -->
    <div>
      <Segment :translationUnit="translationUnit" />
    </div>
    <!-- Footer: domain & content type -->
    <div class="flex pt-4">
      <span class="text-sm  tracking-0.23 font-normal xs:text-xs xs:leading-5 xs:tracking-0.2 text-gray-800 mr-2"
        >Domain:</span
      >
      <Tag
        :label="translationUnit.domain | truncate"
        variant="compact"
        color="bg-orange-900 bg-opacity-25 text-orange-900"
        class="inline float-none py-1 px-2 text-xs"
      />
      <span class="text-sm  tracking-0.23 font-normal xs:text-xs xs:leading-5 xs:tracking-0.2 text-gray-800 ml-6 mr-2"
        >Content type:
      </span>
      <Tag
        :label="translationUnit.contentType | truncate"
        variant="compact"
        color="bg-purple-700 bg-opacity-25 text-purple-700"
        class="inline float-none py-1 px-2 text-xs"
      />
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import Tag from "@/components/Tag.vue";
import SellerInitials from "@/components/SellerInitials.vue";
import Segment from "@/components/Segment.vue";
import { TranslationUnit } from "@/models/models";

export default Vue.extend({
  props: {
    translationUnit: {
      type: Object as PropType<TranslationUnit>
    }
  },
  components: {
    Tag,
    SellerInitials,
    Segment
  }
});
</script>

<style lang="scss"></style>
