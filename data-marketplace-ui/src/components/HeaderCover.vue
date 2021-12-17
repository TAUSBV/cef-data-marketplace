<template>
  <section class="header-cover">
    <img
      src="/assets/img/background-title.jpg"
      srcset="/assets/img/background-title@2x.jpg 2x, /assets/img/background-title@3x.jpg 3x"
      class="w-screen h-64"
      style="object-fit: cover;object-position: 50% 100%;"
    />
    <div class="header-cover--title text-center w-screen">
      <h1 class="text-4xl sm:text-5xl text-gray-100 mb-4">{{ title }}</h1>
      <div class="mb-6 header-cover--line">
        <div class="inline-block w-6 h-1 float-left bg-blue-900 shadow"></div>
        <div class="inline-block w-6 h-1 float-left bg-pink-900 shadow"></div>
        <div class="inline-block w-6 h-1 float-left bg-red-900 shadow"></div>
        <div class="inline-block w-6 h-1 float-left bg-yellow-900 shadow"></div>
        <div class="inline-block w-6 h-1 float-left bg-green-900 shadow"></div>
        <div class="flow-root"></div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "HeaderCover",
  props: {
    title: {
      type: String,
      required: true
    }
  }
});
</script>

<style lang="scss">
.header-cover {
  position: relative;

  & .header-cover--title {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    & .header-cover--line {
      position: absolute;
      left: 50%;
      transform: translate(-50%, -50%);
    }
  }
}
</style>
