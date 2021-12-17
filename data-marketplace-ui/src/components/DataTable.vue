<template>
  <table class="w-full table-fixed">
    <thead>
      <tr class="h-14 text-white text-base font-medium tracking-wide leading-snug">
        <th class="pl-4 rounded-tl-lg bg-blue-700">
          <div class="flex flex-row items-center">
            <span>SOURCE LANGUAGE</span>
            <span class="flex flex-col ml-2">
              <span class="text-xs text-blue-300 cursor-pointer sort-asc" @click="sort"></span>
              <span class="-mt-1 text-xs cursor-pointer sort-desc" style="margin-top: -0.35rem" @click="sort"></span>
            </span>
          </div>
        </th>
        <th class="pl-4 bg-blue-700">TARGET LANGUAGE</th>
        <th class="pl-4 bg-blue-700">DOMAIN</th>
        <th class="pl-4 bg-blue-700">CONTENT TYPE</th>
        <th class="pl-4 bg-blue-700">AVAILABILITY</th>
        <th class="w-1/6 pl-4 rounded-tr-lg bg-blue-700">
          PRICE PER WORD
        </th>
      </tr>
    </thead>
    <tbody>
      <tr
        class="h-14 text-base tracking-wide leading-snug even:bg-blue-500"
        v-for="(entry, index) in tableInternal.content"
        v-bind:key="index"
      >
        <td class="pl-4 ">{{ entry.sourceLanguage.name }}</td>
        <td class="pl-4 ">{{ entry.targetLanguage.name }}</td>
        <td class="pl-4 ">{{ entry.domain }}</td>
        <td class="pl-4 ">{{ entry.contentType }}</td>
        <td class="pl-4 ">
          <LanguageDataAvailabilityIndicator :category="entry.availabilityCategory" />
        </td>
        <td class="pl-4 ">{{ entry.suggestedPricePerWord }}</td>
      </tr>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="6">
          <ul class="flex flex-row justify-center mt-10 mx-auto w-full">
            <li class="flex justify-center items-center mx-2 w-10 h-10 border rounded">
              <a href="#" class="no-underline" @click.prevent="page">
                <span
                  class="chevron left"
                  :class="{
                    'border-gray-600': tableInternal.number === 0,
                    'border-black': tableInternal.number > 0
                  }"
                ></span>
              </a>
            </li>
            <li
              class="flex justify-center items-center mx-2 w-10 h-10 border rounded"
              v-for="index in oneClickPages"
              :key="index"
              :class="{ 'bg-blue-500': index === tableInternal.number }"
            >
              <a href="#" class="hover:no-underline" @click.prevent="page">
                {{ index + 1 }}
              </a>
            </li>
            <li class="flex flex-row justify-center items-center" v-if="!isAboutToEnd">
              <span class="m-1 h-1 w-1 rounded-full bg-black"></span>
              <span class="m-1 h-1 w-1 rounded-full bg-black"></span>
              <span class="m-1 h-1 w-1 rounded-full bg-black"></span>
            </li>
            <li class="flex justify-center items-center mx-2 w-10 h-10 border rounded">
              <a href="#" class="no-underline" @click.prevent="page">
                {{ totalPages + 1 }}
              </a>
            </li>
            <li class="flex justify-center items-center mx-2 w-10 h-10 border rounded">
              <a href="#" class="no-underline" @click.prevent="page">
                <span
                  class="chevron right"
                  :class="{
                    'border-gray-600': tableInternal.number === totalPages,
                    'border-black': tableInternal.number !== totalPages
                  }"
                ></span>
              </a>
            </li>
          </ul>
        </td>
      </tr>
    </tfoot>
  </table>
</template>
<script lang="ts">
import LanguageDataAvailabilityIndicator from "@/components/LanguageDataAvailabilityIndicator.vue";

export default {
  name: "DataTable",
  components: { LanguageDataAvailabilityIndicator },
  props: {
    isAboutToEnd: {},
    oneClickPages: {},
    page: {},
    sort: {},
    tableInternal: {},
    totalPages: {}
  }
};
</script>
<style lang="scss" scoped>
.sort-asc::after {
  content: "\25B2";
}

.sort-desc::after {
  content: "\25BC";
}

.chevron {
  border-style: solid;
  border-width: 0 0.1875rem 0.1875rem 0;
  display: inline-block;
  padding: 0.25rem;
}
</style>
