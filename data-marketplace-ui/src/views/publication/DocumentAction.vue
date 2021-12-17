<template>
  <section class="mt-8">
    <div class="flex items-center border border-blue-700 rounded text-lg mb-8 py-3 px-6">
      <img src="/assets/img/bullhorn-outline.svg" alt="Bullhorn" class="flex-initials mr-6 w-6 h-6" />
      <div class="flex-initials">
        Would you like to purchase the cleaned and anonymized (coming soon) version of your dataset or publish it for
        sale on the Data Marketplace?
      </div>
    </div>
    <div class="grid grid-cols-2 mx-auto gap-4 divide-x divide-gray-200 mb-10">
      <div class="px-2 md:px-12">
        <div class="text-xl font-medium mb-2">Data Sellers</div>
        <div class="text-lg">
          You can purchase the cleaned version and anonymized (coming soon) of your dataset directly without sharing it
          with a third party or publishing it for sale.
        </div>
      </div>
      <div class="px-2 md:px-12">
        <div class="text-xl font-medium mb-2">Data Publishers</div>
        <div class="text-lg">
          If you publish the cleaned and anonymized version of your dataset for sale on the Data Marketplace, you can
          download it for your own use for free.
        </div>
      </div>
    </div>
    <div class="grid grid-cols-2 mx-auto gap-4">
      <div class="px-2 md:px-12 text-center">
        <Button @click="buyBackDocument" disabled>
          Buy back
        </Button>
      </div>
      <div class="px-2 md:px-12 text-center">
        <Button @click="publishDocument" :isLoading="isLoading">
          Publish
        </Button>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Button from "@/components/Button.vue";

export default Vue.extend({
  props: {
    isLoading: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  data() {
    return {
      selectedAction: []
    };
  },
  methods: {
    publishDocument() {
      this.$emit("setDocumentToPublish", true);
    },
    buyBackDocument() {
      this.$emit("setDocumentToPublish", false);
    }
  },
  computed: {
    isSubmitDisabled(): boolean {
      return this.selectedAction.length === 0;
    }
  },
  components: {
    Button
  }
});
</script>
