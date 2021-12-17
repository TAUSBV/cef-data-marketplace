<template>
  <label
    class="align-top space-x-3 pr-5 block w-full overflow-hidden"
    :class="disabled ? 'cursor-not-allowed' : 'cursor-pointer'"
    :for="id"
  >
    <div class="inline-block float-left" style="width: 20px;">
      <input
        :id="id"
        :value="value"
        type="checkbox"
        style="min-width: 20px; min-height: 20px;max-width: 20px; max-height: 20px;"
        class="appearance-none cursor-pointer shadow rounded-sm border border-gray-300 checked:bg-blue-300 checked:border-blue-600 focus:outline-none"
        :class="[customClass, headerClasses, disabled ? 'cursor-not-allowed' : 'cursor-pointer']"
        v-model="vModelLocal"
        :disabled="disabled"
        @change="updateInput"
      />
    </div>
    <div class="inline-block overflow-hidden" style="width: calc(100% - 40px);">
      <span :style="labelStyling">{{ label }}</span>
      <a class="text-base text-blue-700" v-bind:href="linkUrl" target="_blank"> {{ linkText }}</a>
      <span v-if="required" class="text-red-800 ml-2">*</span>
    </div>
  </label>
</template>
<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "FormCheckbox",
  model: {
    event: "change"
  },
  props: {
    label: {
      type: String
    },
    labelStyling: {
      type: String
    },
    linkText: {
      type: String
    },
    linkUrl: {
      type: String
    },
    id: {
      type: String
    },
    value: {
      type: String
    },
    // FIXME misuse of Vue v-model directive. The prop vModel is unnecessary
    vModel: {
      type: [Array, Boolean]
    },
    required: {
      type: Boolean
    },
    index: {
      type: Number,
      default: 0
    },
    isChecked: {
      type: Boolean,
      default: false
    },
    disabled: {
      type: Boolean,
      default: false
    },
    isInvalid: {
      type: Boolean,
      required: false,
      default: false
    },
    customClass: {
      type: [Object, String]
    }
  },
  data() {
    return {
      checked: false
    };
  },
  computed: {
    headerClasses(): string {
      const validFieldStyle = "border border-blue-500 text-blue-900";
      const invalidFieldStyle = "border-2 border-red-700";
      return this.isInvalid ? invalidFieldStyle : this.checked ? validFieldStyle : "border border-gray-300";
    },
    vModelLocal: {
      get: function() {
        return this.vModel;
      },
      set: function(value) {
        this.$emit("vModelListChange", value);
      }
    }
  },
  methods: {
    updateInput(event: UIEvent) {
      const isChecked = (event.target as HTMLInputElement).checked;
      this.$emit("change", isChecked, this.$props.index);
    }
  },
  created() {
    if (this.$props.isChecked) {
      this.checked = !this.checked;
    }
  }
});
</script>

<style scoped></style>
