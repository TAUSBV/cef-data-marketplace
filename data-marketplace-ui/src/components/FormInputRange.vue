<template>
  <div>
    <section class="range-group" :class="[isDisabled ? 'disabled' : '']">
      <div>
        <input
          class="w-full h-1.5 appearance-none bg-transparent pointer-events-none outline-none"
          type="range"
          ref="minRange"
          :id="minId"
          :min="minValue"
          :max="maxValue"
          :value="minValOnInput ? minValOnInput : selectedMinValue"
          :step="step"
          :name="minId"
          :disabled="isDisabled"
          @input="onInput"
          @change="onChange"
        />
        <label class="block text-sm py-2 font-medium" :for="minId" v-if="minLabel">
          {{ minLabel }}
        </label>
      </div>
      <div>
        <input
          class="w-full h-1.5 appearance-none bg-transparent pointer-events-none outline-none"
          type="range"
          ref="maxRange"
          :id="maxId"
          :min="minValue"
          :max="maxValue"
          :value="maxValOnInput ? maxValOnInput : selectedMaxValue"
          :step="step"
          :name="maxId"
          :disabled="isDisabled"
          @input="onInput"
          @change="onChange"
        />
        <label class="block text-sm py-2 font-medium text-right" :for="maxId" v-if="maxLabel">
          {{ maxLabel }}
        </label>
      </div>
    </section>
    <section class="grid grid-cols-2 gap-6 mt-2 range-fields" :class="[isDisabled ? 'disabled' : '']">
      <input
        class="shadow px-4 w-full rounded-lg"
        type="number"
        :id="minId"
        :min="minValue"
        :max="maxValue"
        :value="minValOnInput ? minValOnInput : selectedMinValue"
        :step="step"
        :name="minId"
        :disabled="isDisabled"
        @keypress="onKeypress"
        @input="onInput"
      />
      <input
        class="shadow px-4 w-full rounded-lg"
        type="number"
        :id="maxId"
        :min="minValue"
        :max="maxValue"
        :value="maxValOnInput ? maxValOnInput : selectedMaxValue"
        :step="step"
        :name="maxId"
        :disabled="isDisabled"
        @keypress="onKeypress"
        @input="onInput"
      />
    </section>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import { Price } from "@/models/models";

export default Vue.extend({
  name: "FormInputRange",
  props: {
    isDisabled: {
      type: Boolean,
      required: false,
      default: false
    },
    // TODO: If "false", change the layout to be single range input and hide the input fields
    isDouble: {
      type: Boolean,
      required: false,
      default: true
    },
    minLabel: {
      type: String,
      required: false,
      default: "Min:"
    },
    maxLabel: {
      type: String,
      required: false,
      default: "Max:"
    },
    minId: {
      type: String,
      required: true
    },
    maxId: {
      type: String,
      required: true
    },
    minValue: {
      type: [String, Number],
      required: true
    },
    maxValue: {
      type: [String, Number],
      required: true
    },
    selectedMinValue: {
      type: [String, Number],
      required: true
    },
    selectedMaxValue: {
      type: [String, Number],
      required: true
    }
  },
  data() {
    return {
      minValOnInput: "",
      maxValOnInput: "",
      minLimit: "0",
      maxLimit: "0"
    };
  },
  watch: {
    selectedMinValue: function() {
      this.minValOnInput = this.selectedMinValue.toString();
    },
    selectedMaxValue: function() {
      this.maxValOnInput = this.selectedMaxValue.toString();
    }
  },
  computed: {
    decimals(): number {
      if (this.$props.maxValue % 1 !== 0 || this.$props.minValue % 1 !== 0) {
        const maxValueDecimals = this.$props.maxValue.toString().split(".")[1]?.length || 0;
        const minValueDecimals = this.$props.minValue.toString().split(".")[1]?.length || 0;

        return Math.max(minValueDecimals, maxValueDecimals);
      }

      return 3;
    },
    step(): string {
      return Math.pow(10, -this.decimals).toFixed(this.decimals);
    }
  },
  methods: {
    // Called each time user moves range input handles
    onInput(event: InputEvent) {
      const input = event.target as HTMLInputElement;
      const minRange = this.$refs.minRange as HTMLInputElement;
      const maxRange = this.$refs.maxRange as HTMLInputElement;

      const rangeValue: {
        min: Price;
        max: Price;
      } =
        input.id === this.minId
          ? {
              min: { text: input.value.toString(), value: parseFloat(input.value) as number },
              max: { text: maxRange.value.toString() as string, value: parseFloat(maxRange.value) }
            }
          : {
              min: { text: minRange.value.toString() as string, value: parseFloat(minRange.value) },
              max: { text: input.value.toString(), value: parseFloat(input.value) as number }
            };

      // Prevents min/max thumbs to cross each other. Min cannot go above max and max cannot go below min
      if (input.id === this.minId) {
        this.maxLimit = "0";
        if (input.value >= maxRange.value) {
          if (this.minLimit === "0") {
            this.minLimit = input.value;
          }
          input.value = this.minLimit;
        } else {
          this.minValOnInput = input.value;
        }
      } else {
        this.minLimit = "0";
        if (input.value <= minRange.value) {
          if (this.maxLimit === "0") {
            this.maxLimit = input.value;
          }
          input.value = this.maxLimit;
        } else {
          this.maxValOnInput = input.value;
        }
      }

      if (input.type === "number") {
        this.onChange(event);
      }

      this.$emit("input", rangeValue);
    },
    // Called when the user releases the handles of the input range
    onChange(event: InputEvent) {
      const input = event.target as HTMLInputElement;
      const minRange = this.$refs.minRange as HTMLInputElement;
      const maxRange = this.$refs.maxRange as HTMLInputElement;

      const rangeValue: {
        min: Price;
        max: Price;
      } =
        input.id === this.minId
          ? {
              min: { text: input.value.toString(), value: parseFloat(input.value) as number },
              max: { text: maxRange.value.toString() as string, value: parseFloat(maxRange.value) }
            }
          : {
              min: { text: minRange.value.toString() as string, value: parseFloat(minRange.value) },
              max: { text: input.value.toString(), value: parseFloat(input.value) as number }
            };

      this.$emit("changed", rangeValue);
    },
    // Disables user from typing a value
    onKeypress(event: InputEvent) {
      event.preventDefault();
    }
  },
  created() {
    this.minValOnInput = this.selectedMinValue.toString();
    this.maxValOnInput = this.selectedMaxValue.toString();
  }
});
</script>
<style lang="scss">
// Note: TailWind's @apply rules have no effect on input pseudo classes
@mixin range-thumb() {
  pointer-events: all;
  appearance: none;
  cursor: pointer;
  position: relative;
  z-index: 1;
  outline: none;
  border-radius: 100%;
  background-color: white;
  width: 24px;
  height: 24px;
  margin: 5px 0;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06); // shadow-md
  border: 1px solid #e4e4eb; //border, border-gray-300
}

.range-group {
  position: relative;
  height: 50px;
  @apply w-full;

  &::before {
    position: absolute;
    content: "";
    top: 12px;
    @apply w-full;
    @apply h-1.5;
    @apply rounded;
    @apply shadow-inner;
    @apply bg-blue-700;
    left: 0;
  }

  div {
    position: absolute;
    overflow: hidden;
    min-width: calc(100% - 28px);
    top: 0;

    &:nth-child(1) {
      left: 0;
      margin-right: -28px;
    }

    &:nth-child(2) {
      right: 0;
      margin-left: -28px;
    }

    input[type="range"] {
      &::-webkit-slider-thumb {
        @include range-thumb;
      }

      &::-moz-range-thumb {
        @include range-thumb;
      }

      &::-ms-thumb {
        @include range-thumb;
      }
    }
  }

  &.disabled {
    &::before {
      @apply bg-gray-300;
    }

    div {
      input[type="range"] {
        &::-webkit-slider-thumb {
          cursor: not-allowed;
        }

        &::-moz-range-thumb {
          cursor: not-allowed;
        }

        &::-ms-thumb {
          cursor: not-allowed;
        }
      }
    }
  }
}

.range-fields {
  &.disabled {
    input[type="number"] {
      @apply text-gray-500;
      @apply cursor-not-allowed;
    }
  }
}
</style>
