<template>
  <div class="inline">
    <router-link
      :to="profileLink"
      :class="disabledClass"
      class="inline-block text-sm py-2 px-4 leading-none text-gray-100 mt-4 xs:mt-0 md:mt-0"
    >
      <img class="md:w-4 lg:w-6" src="/assets/img/user.svg" alt="user-icon" />
    </router-link>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "Account",
  computed: {
    isAuthenticated(): boolean {
      return this.$store.getters["session/isAuthenticated"];
    },
    disabledClass(): string {
      return this.isAuthenticated ? "opacity-100" : "opacity-25";
    },
    profileLink(): string {
      return this.isAuthenticated ? "/users" : "/sign-in";
    }
  },
  mounted() {
    this.$store.dispatch("session/refresh");
  }
});
</script>

<style scoped></style>
