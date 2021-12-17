<template>
  <div class="md:flex my-auto">
    <div class="xs:w-full sm:w-full md:flex-auto inline-block xs:text-center sm:text-center">
      <div
        class="xl:text-base leading-relaxed tracking-0.27 font-medium text-gray-900"
        v-html="getLanguageName(sourceLanguage.displayName)"
      ></div>
      <div class="text-sm text-gray-800">
        {{ sourceLanguage.displayCountry }}
      </div>
    </div>
    <div class="md:flex-auto md:inline-block my-auto lg:mx-0 xl:mx-8">
      <img src="/assets/img/language.svg" alt="language" class="xl:w-20 w-8 xs:w-6 mx-auto" />
    </div>
    <div class="xs:w-full sm:w-full md:flex-auto inline-block md:float-right xs:text-center sm:text-center">
      <div
        class=" text-base leading-relaxed tracking-0.27 font-medium text-gray-900"
        v-html="getLanguageName(targetLanguage.displayName)"
      ></div>
      <div class="text-sm text-gray-800">
        {{ targetLanguage.displayCountry }}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import { Language } from "@/models/models.ts";

export default Vue.extend({
  props: {
    sourceLanguage: {
      type: Object as PropType<Language>,
      required: true
    },
    targetLanguage: {
      type: Object as PropType<Language>,
      required: true
    }
  },
  methods: {
    getLanguageName(languageName: string): string {
      return languageName.replace(/ \([\s\S]*?\)/g, "");
    }
  }
});
</script>
