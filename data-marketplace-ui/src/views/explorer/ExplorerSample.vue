<template>
  <section class="w-3/4 mx-auto mb-32 mt-16" v-if="result && result.sample.length > 0">
    <div>
      <span
        class="text-lg tracking-1.6 font-normal xs:text-base xs:leading-relaxed xs:tracking-wide pb-3 border-b border-gray-200 block mb-6"
        >DATASET SAMPLE</span
      >
    </div>
    <div class="masonry">
      <template v-if="!isLoading">
        <div class="mb-8" v-for="(sample, index) in result.sample" :key="index">
          <UnitCard :translationUnit="sample" />
        </div>
      </template>
      <template v-else>
        <UnitCardLoading v-for="(items, index) in 4" :key="index" />
      </template>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import UnitCard from "@/components/UnitCard.vue";
import UnitCardLoading from "@/components/UnitCardLoading.vue";
import explorer from "@/services/Explorer";
import { computed, ref, watch } from "@vue/composition-api";

export default Vue.extend({
  name: "ExploreSample",
  setup() {
    const { result, isLoading } = explorer();
    const state = ref({
      result: null
    });

    const updateResult = async () => {
      state.value.result = result;
    };

    watch(result, updateResult);

    return {
      result: computed(() => state.value.result),
      isLoading: isLoading
    };
  },
  components: {
    UnitCard,
    UnitCardLoading
  }
});
</script>

<style lang="scss" scoped>
.masonry {
  columns: 2 auto;
  column-gap: 2rem;

  div {
    display: inline-block;
    width: 100%;
    //height: 100%;
    //line-height: 100%;
  }
}
</style>
