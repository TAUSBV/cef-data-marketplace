<template>
  <section>
    <div v-if="!isFetchingData">
      <div class="text-right">
        <a
          href="https://www.taus.net/component/users"
          target="_blank"
          class="inline-block text-blue-700 font-medium mb-6"
        >
          Edit my profile
          <img src="/assets/img/chevron-right-blue.svg" alt="chevron right blue" class="inline pl-1" />
        </a>
      </div>
      <div class="grid grid-cols-2">
        <div>
          <div class="text-xl font-medium mb-2">
            {{ user.details.prefix }} {{ user.details.firstName }}
            {{ user.details.lastName }}
          </div>
          <div class="mb-2">
            <img src="/assets/img/email.svg" alt="email" class="inline w-6 h-6" />
            {{ user.details.email }}
          </div>
          <div class="mb-2" v-if="user.details.country">
            <img src="/assets/img/location.svg" alt="location" class="inline w-6 h-6" />
            {{ user.details.country }}
          </div>
          <div class="text-sm text-gray-800">Joined: {{ user.createdAt | date }}</div>
        </div>
        <div v-if="user.verified">
          <div class="font-medium pb-2">
            <img src="/assets/img/verification.svg" alt="Verified" class="inline w-6 h-6" />
            Verified by TAUS
          </div>
          <div class="text-sm text-gray-800">
            The user profile has been reviewed and approved by the TAUS Data team.
          </div>
        </div>
      </div>
    </div>
    <!-- Loading placeholder -->
    <div v-else>
      <UserProfileLoading />
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import UserProfileLoading from "@/components/user/UserProfileLoading.vue";
import { User } from "@/models/models";

export default Vue.extend({
  name: "UserProfile",
  data() {
    return {
      user: {} as User,
      isFetchingData: true
    };
  },
  mounted() {
    this.$store
      .dispatch("user/fetch")
      .then((r: User) => {
        this.user = r;
      })
      .finally(() => {
        this.isFetchingData = false;
      });
  },
  components: {
    UserProfileLoading
  }
});
</script>
<style lang="scss"></style>
