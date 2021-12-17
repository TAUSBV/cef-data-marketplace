<template>
  <span class="flex flex-col ml-2">
    <span class="text-xs cursor-pointer sort-asc" :class="{ 'text-blue-300': isDesc }" @click="sort('asc')"></span>
    <span
      class="-mt-1 text-xs cursor-pointer sort-desc"
      style="margin-top: -0.35rem"
      :class="{ 'text-blue-300': isAsc }"
      @click="sort('desc')"
    ></span>
  </span>
</template>
<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "SortIndicator",
  props: {
    field: {
      type: String,
      required: true
    },
    direction: {
      type: String,
      required: false
    }
  },
  computed: {
    isAsc(): boolean {
      return this.direction === "asc";
    },
    isDesc(): boolean {
      return this.direction === "desc";
    }
  },
  methods: {
    sort(direction: string) {
      this.$emit("sort", this.field, direction);
    }
  }
});
</script>

<style lang="scss" scoped>
.sort-asc::after {
  content: "\25B2";
}

.sort-desc::after {
  content: "\25BC";
}
</style>
