<template>
  <div class="flex items-center border rounded text-lg mb-8 py-3 px-6" :class="messageTypeClasses">
    <img
      src="/assets/img/alert-circle-outline.svg"
      alt="Alert"
      class="flex-initials mr-6 w-6 h-6"
      v-if="type === 'DANGER'"
    />
    <img
      src="/assets/img/check-mark-green.svg"
      alt="Alert"
      class="flex-initials mr-6 w-6 h-6"
      v-else-if="type === 'SUCCESS'"
    />
    <img src="/assets/img/info.svg" alt="Info" class="flex-initials mr-6 w-6 h-6" v-else-if="type === 'INFO'" />
    <div>
      <div :class="titleStyle">{{ titleInternal }}</div>
      <div v-for="(message, index) in messages" :key="index" class="text-gray-900">
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";

enum AlertType {
  DANGER = "bg-red-100 border-red-700 text-red-700",
  INFO = "bg-blue-100 border-blue-700 text-blue-700",
  SUCCESS = "bg-green-100 border-green-700 text-green-700"
}

export default Vue.extend({
  name: "Alert",
  props: {
    title: {
      type: String,
      required: false
    },
    messages: {
      type: Array as PropType<Array<string>>,
      required: true
    },
    type: {
      type: String,
      required: true
    }
  },
  computed: {
    titleStyle(): string {
      let style = "";
      switch (this.type) {
        case "DANGER":
          style = "text-red-700";
          break;
        case "INFO":
          style = "text-blue-700";
          break;
        case "SUCCESS":
          style = "text-green-700";
          break;
      }
      return style;
    },
    titleInternal(): string {
      const title = this.title;
      if (title === undefined) {
        switch (this.type) {
          case "DANGER":
            return "Error";
          case "INFO":
            return "Information";
          case "SUCCESS":
            return "Success";
        }
      }
      return title;
    },
    messageTypeClasses(): string {
      return this.type === "DANGER" ? AlertType.DANGER : this.type === "INFO" ? AlertType.INFO : AlertType.SUCCESS;
    }
  }
});
</script>
