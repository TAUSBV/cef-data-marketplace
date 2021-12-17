<template>
  <div
    class="card-icon w-card-icon sm:w-auto md:h-card-icon lg:w-auto xl:w-card-icon bg-gray-100 py-4 md:py-8 lg:py-6 px-6 md:px-12 lg:px-6"
  >
    <div class="w-20 h-20 flex border border-transparent rounded m-auto mb-6">
      <img :src="`/assets/img/${img}.svg`" :alt="img" class="m-auto" />
    </div>
    <div class="text-xl leading-30 tracking-0.37 xs:text-lg font-medium text-center mb-6">
      {{ title }}
    </div>
    <div class="text-lg tracking-0.3 xs:text-base xs:leading-relaxed xs:tracking-0.27 xs:text-center">{{ text }}</div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  props: {
    img: {
      type: String,
      required: true
    },
    title: {
      type: String,
      required: true
    },
    text: {
      type: String,
      required: true
    }
  }
});
</script>

<style lang="scss">
.card-icon {
  border-radius: 4px;
  box-shadow: 0 1px 5px 0 rgba(0, 0, 0, 0.1);
  z-index: 40;
}
</style>
