<template>
  <div>
    <div
      class="rounded-full h-10 w-10 flex items-center justify-center bg-opacity-25 text-sm font-medium relative float-left xs:mr-2 sm:mr-4 md:mr-4 lg:mr4 bg-turquoise-blue text-turquoise-blue"
    >
      {{ [firstName, lastName] | initials }}
    </div>
    <div class="float-left" style="width: fit-content;">
      <p class="text-base leading-relaxed tracking-0.27 font-medium xs:text-sm  xs:tracking-0.23 text-gray-900">
        {{ firstName }} {{ lastName }}
      </p>
      <p class="text-sm text-green-900">
        {{ joinedAt | mediumDate }}
      </p>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import { SellerDocument } from "@/models/models";

export default Vue.extend({
  props: {
    firstName: {
      type: String,
      required: true
    },
    lastName: {
      type: String,
      required: true
    },
    joinedAt: {
      type: String,
      required: true
    }
  }
});
</script>
