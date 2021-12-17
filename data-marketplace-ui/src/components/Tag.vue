<template>
  <div
    class="rounded-full font-medium tracking-0.27 text-sm leading-relaxed px-3 py-0.5"
    :class="[!!color ? color : 'bg-blue-600 text-blue-600 bg-opacity-14', customizeLabel ? customizeLabel : '']"
  >
    <span class="text-center">{{ label }}</span>
    <span @click="remove" v-if="!isCompacted">
      <img
        src="/assets/img/close.png"
        srcset="/assets/img/close@2x.png 2x, /assets/img/close@3x.png 3x"
        class="ml-2 cursor-pointer"
      />
    </span>
    <slot></slot>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "Tag.vue",
  props: {
    label: {
      type: [String, Number]
    },
    labelFontSize: {
      type: String
    },
    customizeLabel: {
      type: String
    },
    variant: {
      type: String
    },
    color: {
      type: String
    }
  },
  computed: {
    isCompacted(): boolean {
      return this.variant === "compact";
    }
  },
  methods: {
    remove(): void {
      this.$emit("remove", this.label);
    }
  }
});
</script>
