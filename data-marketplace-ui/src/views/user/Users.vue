<template>
  <section class="bg-gray-100">
    <div class="mt-8">
      <SectionTitle :title="pageTitle" />
    </div>
    <div class="flex flex-col w-3/4 pt-16 pb-32 mx-auto relative">
      <div class="grid grid-cols-1 lg:grid-cols-4 gap-8 py-8">
        <div class="col-span-1">
          <UserProfileMenu @get-route-title="onSetPageTitle($event)" />
        </div>
        <div class="col-span-1 lg:col-span-3">
          <router-view></router-view>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";
import UserProfileMenu from "@/components/user/UserProfileMenu.vue";

export default Vue.extend({
  name: "Users",
  components: {
    SectionTitle,
    UserProfileMenu
  },
  data() {
    return {
      pageTitle: "User Profile"
    };
  },
  methods: {
    onSetPageTitle(title: string) {
      this.pageTitle = `User ${title}`;
    }
  }
});
</script>
<style lang="scss"></style>
