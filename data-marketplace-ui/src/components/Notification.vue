<template>
  <div
    class="absolute mx-auto my-auto left-0 right-0 w-auto flex max-w-sm mb-4 shadow-lg"
    v-if="show"
    :class="info.bgLight"
  >
    <div class="w-16 opacity-90" :class="info.bgBold">
      <div class="flex items-center justify-center h-full">
        <img
          src="/assets/img/notification-success.svg"
          alt="Notification"
          class="h-8 w-8 text-white fill-current"
          v-if="notification.isSuccess()"
        />
        <img
          src="/assets/img/notification-warning.svg"
          alt="Notification"
          class="h-8 w-8 text-white fill-current"
          v-if="notification.isWarning()"
        />
        <img
          src="/assets/img/notification-danger.svg"
          alt="Notification"
          class="h-8 w-8 text-white fill-current"
          v-if="notification.isDanger()"
        />
        <img
          src="/assets/img/notification-info.svg"
          alt="Notification"
          class="h-8 w-8 text-white fill-current"
          v-if="notification.isInfo()"
        />
      </div>
    </div>
    <div class="w-auto text-grey items-center p-4">
      <span class="text-lg font-bold pb-4">
        {{ info.title }}
      </span>
      <p class="leading-tight">
        {{ message }}
      </p>
    </div>
  </div>
</template>

<script lang="ts">
import { mapState } from "vuex";
import Vue from "vue";

export default Vue.extend({
  watch: {
    message(notification) {
      if (notification) {
        setTimeout(this.dismiss, 5000);
      }
    }
  },
  methods: {
    dismiss(): void {
      this.$store.dispatch("notification/remove");
    }
  },
  computed: {
    ...mapState({
      notification: state => state.notification.notification as Notification
    }),
    show(): boolean {
      return this.notification != null;
    },
    message(): string {
      return this.show ? this.notification.message : "";
    },
    info(): {} {
      const data = {
        title: "",
        bgBold: "",
        bgLight: ""
      };
      if (this.notification.isDanger()) {
        data.title = "Error";
        data.bgBold = "bg-red-900";
        data.bgLight = "bg-red-300";
      }
      if (this.notification.isInfo()) {
        data.title = "Information";
        data.bgBold = "bg-blue-900";
        data.bgLight = "bg-blue-300";
      }
      if (this.notification.isWarning()) {
        data.title = "Warning";
        data.bgBold = "bg-red-900";
        data.bgLight = "bg-red-300";
      }
      if (this.notification.isSuccess()) {
        data.title = "Success";
        data.bgBold = "bg-green-900";
        data.bgLight = "bg-green-300";
      }
      return data;
    }
  }
});
</script>
