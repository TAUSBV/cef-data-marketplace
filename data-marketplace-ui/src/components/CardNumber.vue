<template>
  <div class="relative inline-block py-4 pl-20 pr-6 md:pr-4 lg:pr-6">
    <div class="text-md lg:text-xl tracking-widest mb-2 step-line leading-10" :class="`step-line--` + colorBorder">
      {{ title }}
    </div>
    <div class="text-md lg:text-lg">{{ text }}</div>
    <div
      class="step-square flex justify-center border border-solid rounded bg-opacity-25"
      :class="[colorBorder, colorBackground]"
    >
      <span class="inline-flex items-center justify-center text-2xl font-light" :class="colorText">{{ number }}</span>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  props: {
    number: {
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
    },
    colorText: {
      type: String,
      required: true
    },
    colorBackground: {
      type: String,
      required: true
    },
    colorBorder: {
      type: String,
      required: true
    }
  }
});
</script>

<style lang="scss">
.step-square {
  width: 3.375rem;
  height: 3.438rem;
  position: absolute;
  top: 30px;
  left: 0;
  z-index: 40;

  &::before {
    content: "";
    position: absolute;
    display: block;
    width: 20%;
    height: 0.063rem;
    border-radius: 4px;
    -webkit-border-radius: 4px;
    -moz-border-radius: 4px;
    top: 50%;
    left: -10px;
  }
}

.step-line {
  &::after {
    content: "";
    position: absolute;
    display: block;
    width: 90%;
    height: 0.063rem;
    border-radius: 4px;
    -webkit-border-radius: 4px;
    -moz-border-radius: 4px;
    left: 9%;
  }
}

.step-line--border-blue-700::after,
.step-square.border-blue-700::before {
  @apply bg-blue-700;
}

.step-line--border-yellow-900::after,
.step-square.border-yellow-900::before {
  @apply bg-yellow-900;
}

@media (max-width: 1024px) {
  .step-line::after,
  .step-square.border-blue-700::before,
  .step-square.border-yellow-900::before {
    @apply bg-transparent;
  }
}
</style>
