<template>
  <button
    class="inline-flex gap-2 items-center border border-transparent text-xl
    text-gray-100 leading-tight tracking-wide focus:outline-none
    disabled:opacity-50 disabled:cursor-not-allowed isLoading:opacity-50 isLoading:cursor-not-allowed
    bg-blue-900"
    :class="[
      disabled ? '' : 'hover:bg-blue-800',
      customStyling ? customStyling : isCompacted ? 'h-14 px-2 rounded' : 'h-14 py-3 px-8 rounded'
    ]"
    :disabled="disabled || isLoading"
    @click.prevent="click"
  >
    <Spinner :is-active="isLoading" />
    <slot name="left-icon"></slot>
    <slot v-if="isLabelVisible">Click Me</slot>
    <slot name="right-icon"></slot>
  </button>
</template>

<script lang="ts">
import Vue from "vue";
import Spinner from "@/components/ui/Spinner.vue";

export default Vue.extend({
  components: { Spinner },
  props: {
    disabled: {
      type: Boolean,
      required: false,
      default: false
    },
    isLoading: {
      type: Boolean,
      required: false,
      default: false
    },
    variant: {
      type: String
    },
    isLabelVisible: {
      type: Boolean,
      default: true
    },
    customStyling: {
      type: String
    }
  },
  computed: {
    isCompacted(): boolean {
      return this.variant === "compact";
    }
  },
  methods: {
    click() {
      if (!this.disabled) {
        this.$emit("click");
      }
    }
  }
});
</script>

<style></style>
