<template>
  <div>
    <label :for="id" class="w-full text-sm leading-8" v-if="label">
      {{ label }}
      <span v-if="isRequired" class="text-red-800">*</span>
      <span v-else class="text-gray-500 font-medium text-xs">(Optional)</span>
    </label>
    <input
      :id="id"
      class="ts-input shadow px-4 w-full rounded-lg"
      :class="[customClass, headerClasses]"
      :type="type"
      :placeholder="placeholder"
      :value="value"
      @input="updateValue"
      @blur="$emit('blur', $event.target.value)"
    />
  </div>
</template>
<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "FormInput",
  props: {
    value: {
      type: String
    },
    placeholder: {
      type: String
    },
    type: {
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
    id: {
      type: String
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
