<template>
  <section class="flex flex-col sm:w-3/4 xs:px-4 pt-16 xs:pt-8 sm:pb-24 xs:pb-10  mx-auto relative">
    <div class="flex justify-center mb-8">
      <SectionTitle title="Data Preparation" />
    </div>
    <div class="flex justify-center text-34px leading-49 tracking-0.76 font-medium xs:text-2xl xs:tracking-0.44">
      Make Your Data MT-Ready
    </div>
    <div
      class="flex justify-center text-center text-lg tracking-0.3 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27"
    >
      Try it out now. Simply drag & drop your dataset for the cleaned and anonymized version
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";

export default Vue.extend({
  components: {
    SectionTitle
  }
});
</script>
