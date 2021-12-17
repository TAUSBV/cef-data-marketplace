<template>
  <div
    class="card-story xs:w-auto xs:bg-gray-100 sm:w-auto xs:mb-8 bg-blue-200 bg-opacity-50 border transform text-gray-900 motion-reduce:transform-none"
  >
    <div class="my-6">
      <div class="w-24 h-24 object-contain border border-transparent m-auto rounded-3xl overflow-hidden">
        <img :src="`/assets/img/${img}`" :alt="img" class="m-auto" />
      </div>
    </div>
    <div class="text-lg tracking-0.3 font-medium text-center cursor-pointer">
      <a v-bind:href="sellerprofile"> {{ title }} </a>
    </div>
    <div class="text-lg tracking-0.3 text-center mb-1 text-gray-900">
      {{ role }}
    </div>
    <div class="text-base leading-relaxed tracking-0.27 font-normal p-4 px-5 mb-2 xs:text-center">
      {{ text }}
    </div>
    <div class="text-center m-auto pb-6">
      <a v-bind:href="linkref" target="_blank"
        ><img src="/assets/img/icons-arrows-right-circle.svg" class="inline justify-center cursor-pointer"
      /></a>
    </div>
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
    linkref: {
      type: String,
      required: true
    },
    role: {
      type: String,
      required: true
    },
    sellerprofile: {
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
.card-story {
  border-radius: 4px;
  box-shadow: 0 1px 5px 0 rgba(0, 0, 0, 0.1);
  z-index: 10;
}
</style>
