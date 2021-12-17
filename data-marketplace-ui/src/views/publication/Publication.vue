<template>
  <section class="overflow-x-hidden">
    <PublicationHeader />
    <div class="flex flex-col sm:w-3/4 xs:px-4 pb-32 mx-auto relative">
      <DocumentUpload />
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import DocumentUpload from "@/views/publication/DocumentUpload.vue";
import PublicationHeader from "@/views/publication/PublicationHeader.vue";

export default Vue.extend({
  components: {
    DocumentUpload,
    PublicationHeader
  }
});
</script>
