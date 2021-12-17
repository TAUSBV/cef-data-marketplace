<template>
  <div>
    <label :for="id" class="w-full text-sm leading-8" v-if="label">
      {{ label }}
      <span v-if="isRequired" class="text-red-800">*</span>
      <span v-else class="text-gray-500 font-medium text-xs">(Optional)</span>
    </label>
    <textarea
      :id="id"
      :rows="rows"
      :value="value"
      :placeholder="placeholder"
      class="ts-input shadow px-4 w-full rounded-lg py-4"
      :class="[customClass, headerClasses]"
      @input="updateValue"
    >
    </textarea>
  </div>
</template>
<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "FormTextarea",
  props: {
    id: {
      type: String
    },
    customClass: {
      type: [Object, String]
    },
    isInvalid: {
      type: Boolean
    },
    label: {
      type: String
    },
    isRequired: {
      type: Boolean
    },
    placeholder: {
      type: String
    },
    value: {
      type: String
    },
    rows: {
      type: Number,
      default: 5
    }
  },
  computed: {
    headerClasses(): string {
      const validFieldStyle = "border border-blue-500 text-blue-900";
      const invalidFieldStyle = "border-2 border-red-700";
      return this.isInvalid ? invalidFieldStyle : this.value != "" ? validFieldStyle : "";
    }
  },
  methods: {
    updateValue(event: InputEvent) {
      const input = event.target as HTMLInputElement;
      this.$emit("input", input.value);
    }
  }
});
</script>
