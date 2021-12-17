<template>
  <div
    style="width: 3.25rem;height: 1.625rem;"
    class="toggler relative rounded-full border pl-1 pr-1 pt-1 pb-1 cursor-pointer"
    :class="enabled ? 'bg-blue-700' : 'bg-gray-400'"
    @click="toggle"
  >
    <div
      style="width: .9rem; height: .9rem; margin-top: 1px;"
      class="block absolute rounded-full bg-gray-100"
      :class="enabled ? 'thumb-enabled' : 'thumb-disabled'"
    ></div>
  </div>
</template>

<script>
import Vue from "vue";
import { ref } from "@vue/composition-api";

export default Vue.extend({
  props: {
    isEnabled: {
      type: Boolean,
      default: false
    }
  },
  setup(props, context) {
    const enabled = ref(props.isEnabled);

    const toggle = function() {
      enabled.value = !enabled.value;
      context.emit("toggle", enabled.value);
    };

    return {
      toggle,
      enabled
    };
  }
});
</script>

<style lang="scss" scoped>
.toggler {
  transition: all 0.2s ease-in;

  .thumb-enabled {
    left: 30px;
    transition: all 0.2s ease-in-out;
  }
  .thumb-disabled {
    left: 6px;
    transition: all 0.2s ease-in-out;
  }
}
</style>
