<template>
  <div
    class="text-xl leading-10 tracking-5.0 font-normal xs:text-base xs:leading-relaxed xs:tracking-wide uppercase"
    :class="positionStyle"
  >
    {{ title }}
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "SectionTitle",
  props: {
    title: {
      type: String,
      required: true
    },
    position: {
      type: String,
      required: false,
      default: "middle"
    }
  },
  computed: {
    positionStyle() {
      return this.position === "left" ? "text-underline--left" : "text-underline--middle text-center";
    }
  }
});
</script>

<style scoped>
.text-underline--left::after,
.text-underline--middle::after {
  content: "";
  position: absolute;
  display: block;
  margin-top: 4px;
  width: 3.25rem;
  height: 0.25rem;
  border-radius: 2px;
  -webkit-border-radius: 2px;
  -moz-border-radius: 2px;
  @apply bg-yellow-900;
}

.text-underline--middle::after {
  left: 50%;
  transform: translateX(-50%);
}
</style>
