<template>
  <div class="w-3/4 shadow py-20 px-24 mx-auto text-center">
    <div class="text-3xl leading-49 tracking-0.76 font-medium xs:text-2xl xs:tracking-0.44  mb-12 text-center">
      <div>
        <img src="/assets/img/download.svg" alt="Purchase Download" class="w-16 h-16 mb-8 mx-auto" />
        Your download will begin shortly
        <br />
        <span class="text-gray-400 text-sm">
          Click
          <a class="text-blue-700 font-medium cursor-pointer" :href="downloadLink" target="_self">
            here
          </a>
          if it does not start.
        </span>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "PurchaseDownload.vue",
  mounted() {
    setTimeout(() => {
      location.href = `${import.meta.env.VITE_APP_API_BASE_URL}/purchases/downloads/${this.$route.params.uid}`;
    }, 1000);
  },
  computed: {
    downloadLink(): string {
      return `${import.meta.env.VITE_APP_API_BASE_URL}/purchases/downloads/${this.$route.params.uid}`;
    }
  }
});
</script>

<style scoped lang="scss"></style>
