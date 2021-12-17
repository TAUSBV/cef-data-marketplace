<template>
  <section>
    <div class="border-b border-gray-200 pt-6 pb-5">
      <div class="text-lg tracking-widest mb-4">LANGUAGES</div>
      <div class="flex flex-wrap items-center">
        <Tag
          variant="compact"
          class="mb-3 mr-3"
          color="bg-blue-700 bg-opacity-14 text-blue-700"
          v-for="(language, index) in languages"
          v-bind:key="`language-${index}`"
        >
          <span class="text-sm">
            {{ language.split(",")[0] }}
          </span>
          <img src="/assets/img/chevron-right-blue.svg" alt="Chevron right blue" class="inline mx-2 w-2 h-4" />
          <span class="text-sm">
            {{ language.split(",")[1] }}
          </span>
        </Tag>
      </div>
    </div>
    <div class="border-b border-gray-200 pt-6 pb-5">
      <div class="text-lg tracking-widest mb-4">DOMAINS</div>
      <div class="flex flex-wrap items-center">
        <Tag
          variant="compact"
          class="mb-3 mr-3"
          :label="domain"
          color="bg-orange-900 bg-opacity-14 text-orange-900"
          v-for="(domain, index) in domains"
          v-bind:key="`domain-${index}`"
        />
      </div>
    </div>
    <div class="pt-6 pb-5">
      <div class="text-lg tracking-widest mb-4">CONTENT TYPE</div>
      <div class="flex flex-wrap items-center">
        <Tag
          variant="compact"
          class="mb-3 mr-3"
          :label="contentType"
          color="bg-purple-700 bg-opacity-14 text-purple-700"
          v-for="(contentType, index) in contentTypes"
          v-bind:key="`contentType-${index}`"
        />
      </div>
    </div>
    <div class="border-t border-gray-200 pt-6 pb-8" v-if="verified">
      <div class="font-medium pb-2">
        <img src="/assets/img/verification.svg" alt="Verified" class="inline w-6 h-6" />
        Verified by TAUS
      </div>
      <div class="text-sm text-gray-800">
        The dataset has been reviewed and approved by the TAUS Data team.
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import Tag from "@/components/Tag.vue";

export default Vue.extend({
  name: "SellerDocumentInformation",
  props: {
    contentTypes: {
      type: Array as PropType<Array<string>>
    },
    domains: {
      type: Array as PropType<Array<string>>
    },
    languages: {
      type: Array as PropType<Array<string>>
    },
    verified: {
      type: Boolean
    }
  },
  components: {
    Tag
  }
});
</script>
<style lang="scss"></style>
