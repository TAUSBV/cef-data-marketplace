<template>
  <section class="bg-gray-100" style="min-height: 40rem">
    <div class="mt-8">
      <SectionTitle title="Find The Data That You Need" />
    </div>
    <div class="sm:w-3/4 xs:px-4 mx-auto my-12">
      <ExploreInitiator :s="sourceLanguage" :t="targetLanguage" />
      <ExploreFilter />
    </div>
    <div>
      <ExploreStatistics />
      <ExploreSample />
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";
import ExploreInitiator from "@/views/explorer/ExplorerInitiator.vue";
import ExploreFilter from "@/views/explorer/ExplorerFilter.vue";
import ExploreStatistics from "@/views/explorer/ExplorerStatistics.vue";
import ExploreSample from "@/views/explorer/ExplorerSample.vue";

export default Vue.extend({
  name: "Explore",
  data() {
    const sourceLanguage = this.$route.query.s ? this.$route.query.s.toString() : "";
    const targetLanguage = this.$route.query.t ? this.$route.query.t.toString() : "";
    return {
      sourceLanguage: sourceLanguage,
      targetLanguage: targetLanguage
    };
  },
  components: {
    SectionTitle,
    ExploreInitiator,
    ExploreFilter,
    ExploreStatistics,
    ExploreSample
  }
});
</script>

<style lang="scss" scoped></style>
