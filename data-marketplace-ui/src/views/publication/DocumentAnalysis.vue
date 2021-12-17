<template>
  <section class="grid grid-cols-1 lg:grid-cols-3 gap-0 mb-4">
    <div class="lg:col-span-1 m-2 ">
      <DocumentCardPublishing
        :document="document"
        :domain="domain"
        :contentType="contentType"
        :pricePerWord="pricePerWord"
        :description="description"
      />
    </div>
    <div class="lg:col-span-2 border-2 m-2 rounded">
      <DocumentStatistics :document="document" />
    </div>
  </section>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import DocumentCardPublishing from "@/components/DocumentCardPublishing.vue";
import DocumentStatistics from "@/components/DocumentStatistics.vue";
import { ContentType, Domain, TranslationDocument } from "@/models/models";

export default Vue.extend({
  props: {
    document: {
      type: Object as PropType<TranslationDocument>,
      required: true
    },
    domain: {
      type: Object as PropType<Domain>
    },
    contentType: {
      type: Object as PropType<ContentType>
    },
    pricePerWord: {
      type: Number
    },
    description: {
      type: String
    }
  },
  components: {
    DocumentCardPublishing,
    DocumentStatistics
  }
});
</script>

<style lang="scss"></style>
