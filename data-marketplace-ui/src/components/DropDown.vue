<template>
  <div>
    <label v-if="label" :for="id" class="w-full text-sm leading-8">
      {{ label }}
      <span v-if="isRequired" class="text-red-800">*</span>
      <span v-else class="text-gray-500 font-medium text-xs">(Optional)</span>
    </label>
    <Multiselect
      :id="id"
      :ref="id"
      :options="options"
      :placeholder="placeholder"
      :searchable="searchable"
      :disabled="disabled"
      :track-by="trackByKey"
      :label="trackByKey"
      :valueProp="trackByKey"
      :value="value"
      :mode="mode"
      :required="isRequired"
      :max="maxNumberOfSelectedOptions"
      :limit="maxNumberOfDisplayedOptions"
      :loading="loading"
      :noOptionsText="noOptionsText"
      :noResultsText="noResultsText"
      :object="true"
      :class="[isInvalid ? 'border-2 border-red-700' : value !== '' ? 'border border-blue-500 text-blue-900' : '']"
      @open="onOpen"
      @close="onClose"
      @select="onSelect"
      @deselect="onDeselect"
      @tag="onTag"
      @change="onChange"
      @search-change="onSearchChange"
    >
      <template v-slot:noresults>
        <slot name="noresults" />
      </template>
    </Multiselect>
    <span class="text-xs text-red-900 py-1" v-if="isInvalid">
      <span v-if="isRequired">Required field</span>
    </span>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
// Library repo: https://github.com/vueform/multiselect
import Multiselect from "@vueform/multiselect/dist/multiselect.vue2.js";

export type Option = {
  [key: string]: string;
};

export default Vue.extend({
  name: "DropDown",
  props: {
    label: {
      type: String,
      required: false
    },
    id: {
      type: [String, Number],
      required: true
    },
    placeholder: {
      type: String,
      required: false,
      default: "Select an option from the list"
    },
    // When mode prop is 'multiple' or 'tags', value must be an array
    value: {
      type: [Object, Array, String],
      required: true
    },
    options: {
      type: Array as PropType<Array<Option>>,
      required: true,
      default: (): Option[] => []
    },
    trackByKey: {
      type: String,
      required: true
    },
    // The maximum number of options that can be selected when using multiple or tags mode.
    maxNumberOfSelectedOptions: {
      type: Number,
      required: false,
      default: -1 // If -1 the number of options won't be limited.
    },
    // The maximum number of options that should be displayed.
    maxNumberOfDisplayedOptions: {
      type: Number,
      required: false,
      default: -1 // If -1 the number of options won't be limited.
    },
    loading: {
      type: Boolean,
      required: false,
      default: false
    },
    mode: {
      type: String,
      required: false,
      validator: prop => ["single", "multiple", "tags"].includes(prop),
      default: "single" // Must one of single|multiple|tags
    },
    noOptionsText: {
      type: String,
      required: false,
      default: "The list is empty"
    },
    noResultsText: {
      type: String,
      required: false,
      default: "No results found"
    },
    createTag: {
      type: Boolean,
      required: false,
      default: true
    },
    addTagOn: {
      type: Array,
      required: false,
      default: () => ["enter"]
    },
    // Whether it should append new tag automatically to option list when using tags mode with createTag.
    appendNewTag: {
      type: Boolean,
      required: false,
      default: true // If set to false you need to take care of appending a new tag to the provided :options list upon @tag event.
    },
    searchable: {
      type: Boolean,
      required: false,
      default: true
    },
    disabled: {
      type: Boolean,
      required: false,
      default: false
    },
    isRequired: {
      type: Boolean,
      required: true
    },
    isInvalid: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  methods: {
    onSelect(selectedOption: Option) {
      this.$emit("selected", selectedOption);
    },
    onChange(value: string | Option[]) {
      this.$emit("changed", value);
    },
    onOpen() {
      this.$emit("opened");
    },
    onClose() {
      this.$emit("closed");
    },
    onTag(query: string) {
      this.$emit("tagged", query);
    },
    onSearchChange(searchQuery: string) {
      this.$emit("searched", searchQuery);
    },
    onDeselect(deselectedOption: Option) {
      this.$emit("removed", deselectedOption);
    }
  },
  components: {
    Multiselect
  }
});
</script>

<style src="@vueform/multiselect/themes/default.css"></style>
<style lang="scss">
.multiselect {
  display: block;
  position: relative;
  width: 100%;
  height: 3rem;
  @apply font-medium;
  @apply shadow;
  @apply rounded-lg;

  &.is-disabled {
    border: none;

    .multiselect-input {
      cursor: not-allowed;

      &::before {
        opacity: 0.4;
      }

      .multiselect-tags {
        .multiselect-tag {
          @apply bg-gray-400;
        }
      }

      .multiselect-multiple-label,
      .multiselect-single-label {
        @apply text-gray-500;
      }
    }
  }

  .multiselect-input {
    border: none;
    height: 3rem;

    &::before {
      border: none;
      content: url("data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2220%22%20height%3D%2210%22%20viewBox%3D%220%200%2020%2010%22%3E%0A%20%20%20%20%3Cpath%20fill%3D%22%231C1C28%22%20d%3D%22M8.939%209.735L.594%202.4c-.402-.353-.402-.927%200-1.28l.973-.856C1.97-.088%202.62-.089%203.023.264l6.644%205.812L16.312.264c.402-.353%201.053-.352%201.455.001l.973.856c.403.353.403.927%200%201.28l-8.344%207.334c-.403.353-1.055.353-1.457%200z%22%2F%3E%0A%3C%2Fsvg%3E%0A");
    }

    .multiselect-fake-input {
      height: 0;
    }

    .multiselect-tags {
      margin-top: 0;
      flex-wrap: nowrap;
      @apply truncate;

      .multiselect-search {
        input {
          margin-bottom: 0;
          background-color: transparent;
        }
      }

      .multiselect-tag {
        margin-bottom: 0;
        @apply text-sm;
        @apply rounded;
        @apply bg-blue-700;

        i {
          &::before {
            color: white;
            margin-left: 2px;
            padding: 2px 8px 2px 8px;
          }
        }
      }
    }

    .multiselect-single-label,
    .multiselect-placeholder {
      display: inline-block;
      line-height: 3rem;
      width: 80%;
      @apply truncate;
    }

    .multiselect-placeholder {
      @apply font-medium;
      @apply text-gray-400;
    }

    .multiselect-clear {
      top: 6px;

      &::before,
      &::after {
        top: 10px;
        left: 12px;
        height: 16px;
        width: 3px;
        background-color: #1c1c28;
        @apply rounded-lg;
      }
    }
  }

  .multiselect-options {
    top: 100%;
    margin-top: 4px;
    display: block;
    width: 100%;
    z-index: 3;
    max-height: 240px !important;
    overflow: auto;
    @apply bg-white;
    @apply rounded-lg;
    @apply shadow-lg;
    @apply border;
    @apply border-blue-500;

    .multiselect-option {
      &.is-pointed {
        cursor: pointer;
        transition: ease-in-out all 300ms;
        @apply bg-blue-200;
        @apply text-blue-900;
      }

      &.is-selected {
        @apply bg-blue-900;
        @apply text-white;
      }
    }
  }
}
</style>
