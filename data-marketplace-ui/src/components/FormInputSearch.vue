<template>
  <input
    class="ts-input-search rounded-lg"
    :type="type"
    :placeholder="placeholder"
    :value="value"
    v-on:keypress.enter="submit"
    v-on:input="updateValue"
  />
</template>
<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "FormInputSearch",
  props: {
    value: {
      type: String
    },
    placeholder: {
      type: String
    },
    type: {
      type: String,
      default: "search"
    }
  },
  methods: {
    submit() {
      this.$emit("submit", this.value);
    },
    updateValue(event: InputEvent) {
      const input = event.target as HTMLInputElement;
      this.$emit("input", input.value);
    }
  }
});
</script>
<style lang="scss" scoped>
.ts-input-search {
  padding: 0.5rem;
  text-indent: 2.5rem;
  box-shadow: inset 0 0.5px 4px 0 rgba(28, 28, 40, 0.25);
  background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='21' height='20' viewBox='0 0 21 20'%3E%3Cpath fill='%238F90A6' d='M20.517 17.291l-4.05-3.894c-.183-.176-.431-.273-.691-.273h-.662c1.12-1.38 1.787-3.113 1.787-5C16.901 3.636 13.12 0 8.451 0 3.782 0 0 3.636 0 8.124s3.782 8.124 8.45 8.124c1.963 0 3.767-.64 5.201-1.718v.636c0 .25.102.489.284.664l4.051 3.895c.382.367 1 .367 1.377 0l1.15-1.106c.382-.367.382-.96.004-1.328zM8.451 13.124c-2.873 0-5.2-2.234-5.2-5 0-2.761 2.323-5 5.2-5 2.872 0 5.2 2.235 5.2 5 0 2.762-2.324 5-5.2 5z'/%3E%3C/svg%3E%0A")
    no-repeat left 1rem top 1rem #fff;
}

#input_container {
  position: relative;
  padding-left: 2.5rem;
  margin: 0 1rem;
  background: #ddd;
  width: 200px;
}

#input {
  height: 20px;
  margin: 0;
  padding-left: 15px;
  width: 100%;
}

#input_img {
  position: absolute;
  top: 1rem;
  left: 0.5rem;
  width: 24px;
  height: 24px;
}
</style>
