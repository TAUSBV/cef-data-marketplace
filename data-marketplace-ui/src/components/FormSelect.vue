<template>
  <div>
    <label :for="id" class="w-full text-sm leading-8">
      {{ label }} <span v-if="isRequired" class="text-red-800">*</span>
    </label>
    <select
      :id="id"
      class="rounded-lg border-transparent appearance-none shadow w-full chevron-icon"
      v-model="selectedInternal"
      @change="change"
    >
      <option
        v-for="option in options"
        :value="option.value"
        :key="option.value"
        :selected="option.selected"
        :disabled="option.disabled"
      >
        {{ option.text }}
      </option>
    </select>
  </div>
</template>
<script lang="ts">
import Vue, { PropType } from "vue";

type Option = {
  value: string;
  text: string;
  selected: boolean;
  disable: boolean;
};
export default Vue.extend({
  name: "FormSelect",
  props: {
    options: {
      type: Array as PropType<Array<Option>>
    },
    selected: {
      type: String,
      required: false,
      default: null
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
  data() {
    return {
      selectedInternal: this.selected
    };
  },
  watch: {
    selected() {
      this.selectedInternal = this.selected;
    }
  },
  methods: {
    change(event: UIEvent) {
      this.selectedInternal = null;
      const target = event.target as HTMLOptionElement;
      this.$emit("change", target.value);
    }
  }
});
</script>
<style lang="scss" scoped>
.chevron-icon {
  background: url("data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2220%22%20height%3D%2210%22%20viewBox%3D%220%200%2020%2010%22%3E%0A%20%20%20%20%3Cpath%20fill%3D%22%231C1C28%22%20d%3D%22M8.939%209.735L.594%202.4c-.402-.353-.402-.927%200-1.28l.973-.856C1.97-.088%202.62-.089%203.023.264l6.644%205.812L16.312.264c.402-.353%201.053-.352%201.455.001l.973.856c.403.353.403.927%200%201.28l-8.344%207.334c-.403.353-1.055.353-1.457%200z%22%2F%3E%0A%3C%2Fsvg%3E%0A")
    no-repeat right 15px top 1.25em #fff;
}

/* Target Internet Explorer 9 to undo the custom arrow */
@media screen and (min-width: 0 \0) {
  .chevron-icon {
    background: none \9;
    padding: 5px \9;
  }
}

/* CAUTION: Internet Explorer hackery ahead */
.chevron-icon::-ms-expand {
  display: none; /* Remove default arrow in Internet Explorer 10 and 11 */
}
</style>
