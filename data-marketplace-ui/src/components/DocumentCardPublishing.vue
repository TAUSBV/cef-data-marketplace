<template>
  <div class="divide-y border-2 rounded divide-gray-200 p-6">
    <DocumentCardLanguage
      :sourceLanguage="document.sourceLanguage"
      :targetLanguage="document.targetLanguage"
      v-if="document.sourceLanguage != null && document.targetLanguage != null"
      class="bg-blue-200 bg-opacity-75 rounded mb-4 lg:p-4 md:py-4 xs:py-4 "
    />
    <div>
      <div class="mb-2">
        <div class="inline-block mt-6 mr-6">
          <img src="/assets/img/file-tmx-big-icon.svg" alt="TMX File" class="w-8" />
        </div>
        <div class="inline-block">
          <div class="text-lg font-medium">
            {{ document.name | truncate(30) }}
          </div>
          <div class="text-sm text-gray-600 text-opacity-75" v-show="document.size !== null">
            {{ document.size | fileSize }}
          </div>
        </div>
      </div>
      <div class="flex" v-if="domain && contentType">
        <Tag
          :label="domain.description"
          variant="compact"
          color="bg-orange-900 bg-opacity-25 text-orange-900"
          class="inline float-left mr-3 pb-1 text-sm"
        />
        <Tag
          :label="contentType.description"
          variant="compact"
          color="bg-purple-700 bg-opacity-25 text-purple-700"
          class="inline float-left mr-3 pb-1 text-sm"
        />
      </div>
      <div class="mt-5" v-if="pricePerWord">
        <div class="text-lg font-medium">
          {{ pricePerWord | currency }}
        </div>
        <div class="text-sm text-gray-600 text-opacity-75">
          Price per word
        </div>
      </div>
      <div class="mt-5" v-if="description">
        <div class="text-lg font-medium">
          {{ description }}
        </div>
        <div class="text-sm text-gray-600 text-opacity-75">
          Description
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import DocumentCardLanguage from "@/components/DocumentCardLanguage.vue";
import Tag from "@/components/Tag.vue";
import { ContentType, Domain, TranslationDocument } from "@/models/models";

export default Vue.extend({
  props: {
    document: {
      type: Object as PropType<TranslationDocument>,
      required: true
    },
    domain: {
      type: Object as PropType<Domain>
    },
    contentType: {
      type: Object as PropType<ContentType>
    },
    pricePerWord: {
      type: Number
    },
    description: {
      type: String
    }
  },
  components: {
    DocumentCardLanguage,
    Tag
  }
});
</script>

<style lang="scss"></style>
