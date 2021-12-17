<template>
  <div>
    <div v-for="document in documents" :key="document.uid" class="grid grid-cols-12 mb-6">
      <div
        class="border-t border-b-none border-l border-r xl:border-r-0 xl:border-b border-gray-300 rounded-l p-1 md:p-3 col-span-12 xl:col-span-8 2xl:col-span-9"
      >
        <div class="segments-container min-h-full slim-scrollbar overflow-x-hidden overflow-y-auto pr-1 md:pr-3">
          <div v-for="(segment, index) in document.units" :key="segment.id">
            <div class="grid grid-cols-2 gap-1">
              <div class="col-span-1 rounded p-3" :class="isOdd(index) ? 'bg-blue-200 bg-opacity-75' : ''">
                <span class="tracking-0.2 md:tracking-0.3 text-xs md:text-base lg:text-lg break-words">
                  {{ segment.sourceValue }}
                </span>
              </div>
              <div class="col-span-1 rounded p-3" :class="isOdd(index) ? 'bg-blue-200 bg-opacity-75' : ''">
                <span class="tracking-0.2 md:tracking-0.3 text-xs md:text-base lg:text-lg break-words">
                  {{ segment.targetValue }}
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-span-12 xl:col-span-4 2xl:col-span-3">
        <DocumentCardCompact
          :show-languages="false"
          :show-seller="true"
          :show-document="true"
          :show-word-price="true"
          :document="document"
          class="rounded-l-none hidden xl:block"
        />
        <DocumentCardMinimal :document="document" class="rounded-t-none block xl:hidden" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import { Page, SellerDocument } from "@/models/models";
import { ref } from "@vue/composition-api";
import DocumentCardCompact from "@/components/DocumentCardCompact.vue";
import DocumentCardMinimal from "@/components/DocumentCardMinimal.vue";

export default Vue.extend({
  name: "DocumentsResults",
  props: {
    payload: {
      type: Object as PropType<Page<SellerDocument>>
    }
  },
  setup(props) {
    const documents = ref((props.payload as any).content);
    const isOdd = function(index: number) {
      return (index + 1) % 2 === 1;
    };

    return {
      documents: documents,
      isOdd: isOdd
    };
  },
  components: {
    DocumentCardCompact,
    DocumentCardMinimal
  }
});
</script>

<style lang="scss" scoped>
.segments-container {
  height: 16rem;

  @media screen and (min-width: 1024px) {
    height: calc(17.875rem - 0.75rem * 2 - 2px);
  }
}
</style>
