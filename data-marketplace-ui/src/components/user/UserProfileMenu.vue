<template>
  <section class="user-menu border border-gray-300 rounded divide-y divide-gray-300">
    <router-link :to="{ name: 'UserProfile' }">
      <span class="block p-4" @click="emitRouteTitle('Profile')">Profile</span>
    </router-link>
    <router-link :to="{ name: 'UserDocuments' }">
      <span class="block p-4" @click="emitRouteTitle('Documents')">Documents</span>
    </router-link>
    <router-link :to="{ name: 'UserPaymentDetails' }">
      <span class="block p-4" @click="emitRouteTitle('Payment Details')">Payment Details</span>
    </router-link>
    <router-link :to="{ name: 'UserPaymentList' }">
      <span class="block p-4" @click="emitRouteTitle('Payment History')">Payment History</span>
    </router-link>
    <a class="cursor-pointer p-4" @click.prevent="signOut">
      Sign out
    </a>
  </section>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "UserProfileMenu",
  methods: {
    emitRouteTitle(title: string) {
      this.$emit("get-route-title", title);
    },
    signOut() {
      this.$store.dispatch("session/destroy").then(() => {
        this.$router.push({
          name: "Sign In"
        });
      });
    }
  }
});
</script>
<style lang="scss">
.user-menu {
  a {
    display: block;

    &.router-link-active {
      @apply text-blue-700;
    }
  }
}
</style>
