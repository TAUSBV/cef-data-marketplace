<template>
  <button
    class="border border-transparent text-xl text-gray-100 leading-tight tracking-wide focus:outline-none
    disabled:opacity-50 disabled:cursor-not-allowed
    isLoading:opacity-50 isLoading:cursor-not-allowed"
    :class="[getClasses]"
    :disabled="isDisabled || isLoading"
    @click.prevent="click"
  >
    <span class="flex items-center justify-center" v-if="isLoading">
      <Spinner :is-active="isLoading" />
    </span>
    <slot v-if="!isLoading" name="left-icon"></slot>
    <slot></slot>
    <slot v-if="!isLoading" name="right-icon"></slot>
  </button>
</template>

<script lang="ts">
import Vue from "vue";
import Spinner from "@/components/ui/Spinner.vue";

enum ButtonColor {
  NORMAL = "normal",
  PRIMARY = "primary",
  SECONDARY = "secondary",
  DANGER = "danger"
}
enum ButtonVariant {
  NORMAL = "normal",
  PILL = "pill",
  ICON = "icon",
  COMPACT = "compact" // legacy
}
export default Vue.extend({
  components: { Spinner },
  props: {
    isDisabled: {
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
      type: String,
      default: ButtonVariant.NORMAL
    },
    color: {
      type: String,
      default: ButtonColor.PRIMARY
    }
  },
  computed: {
    getClasses(): { string: boolean } {
      return {
        "h-14 py-3 px-6 rounded inline-flex gap-3.5 items-center justify-center": this.variant == ButtonVariant.NORMAL,
        "h-14 py-3 px-6 rounded-full inline-flex gap-3.5 items-center justify-center":
          this.variant == ButtonVariant.PILL,
        "h-11 w-11 rounded-full": this.variant == ButtonVariant.ICON,
        "h-14 py-3 px-2 rounded": this.variant == ButtonVariant.COMPACT,
        "bg-blue-900": this.color === ButtonColor.PRIMARY,
        "hover:bg-blue-800": !this.isDisabled && this.color === ButtonColor.PRIMARY,
        "bg-blue-400": this.color === ButtonColor.SECONDARY,
        "hover:bg-red-300": !this.isDisabled && this.color === ButtonColor.SECONDARY,
        "bg-red-900": this.color === ButtonColor.DANGER,
        "hover:bg-red-800": !this.isDisabled && this.color === ButtonColor.DANGER,
        "bg-gray-300": this.color === ButtonColor.NORMAL,
        "hover:bg-gray-200": !this.isDisabled && this.color === ButtonColor.NORMAL
      };
    }
  },
  methods: {
    click() {
      if (!this.isDisabled) {
        this.$emit("click");
      }
    }
  }
});
</script>

<style></style>
