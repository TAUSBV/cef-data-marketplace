<template>
  <div>
    <div class="z-40">
      <button
        v-for="tab in tabs"
        v-bind:key="tab"
        v-bind:class="[
          'button-search p-4 mr-4 border-0 outline-none text-xl xs:text-lg text-gray-100 leading-30 tracking-0.37 font-medium',
          { active: currentTab === tab }
        ]"
        v-on:click="currentTab = tab"
      >
        {{ tab }}
      </button>

      <div class="border border-gray-200 bg-gray-100 xs:text-gray-900 rounded" style="max-width: 769px;">
        <component v-bind:is="currentTabComponent" :hasBorder="false" class="tab"></component>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import SearchBar from "@/components/search/SearchBar.vue";

export default Vue.extend({
  name: "LandingPgSearch",
  data: function() {
    return {
      currentTab: "Search",
      component: ["SearchBar"],
      tabs: ["Search"]
    };
  },
  components: {
    SearchBar
  },
  computed: {
    currentTabComponent: function() {
      return this.component[0];
    }
  }
});
</script>

<style scoped>
.button-search {
  border: none;
  outline: 0;
  background-color: transparent;
  @apply opacity-50;
}
.active,
.button-search:focus {
  @apply text-gray-100;
  @apply opacity-100;
}

/*@media (max-width: 520px) {*/
/*  .active,*/
/*  .button-search:focus {*/
/*    @apply text-gray-100;*/
/*    background-color: transparent;*/
/*  }*/
/*}*/
</style>
