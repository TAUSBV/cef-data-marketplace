<template>
  <div class="flex justify-center items-center" v-if="isBasic">
    <div class="bg-opacity-14 h-px w-full hidden sm:block" :class="dividerBgColor(index)"></div>
    <div class="flex justify-center items-center min-w-22.75 mx-6">
      <div
        :class="sellerProfileColor(index)"
        class="rounded-full h-20 w-20 min-w-20 bg-opacity-14 text-3xl tracking-0.7 font-medium relative flex justify-center items-center"
      >
        <img
          v-if="seller.verified"
          src="/assets/img/verification.svg"
          alt="Verified"
          class="w-6 h-6 absolute right-0 top-0"
        />
        <span>{{ [seller.firstName, seller.lastName] | initials }}</span>
      </div>
      <div class="ml-6 text-center">
        <div class="text-xl font-medium tracking-0.44 leading-1.875">{{ seller.firstName }} {{ seller.lastName }}</div>
        <div class="text-lg tracking-0.3 text-blue-700 cursor-pointer" @click.prevent="sellerSelected()">
          <div v-if="seller.documentCount > 3">
            <span>See all {{ seller.documentCount }} documents</span>
            <img src="/assets/img/chevron-right-blue.svg" alt="" class="inline ml-2" />
          </div>
          <div v-else>
            <span> {{ seller.documentCount }} documents </span>
          </div>
        </div>
      </div>
    </div>
    <div class="bg-opacity-14 h-px w-full hidden sm:block" :class="dividerBgColor(index)"></div>
  </div>

  <div class="flex flex-col justify-center items-center" v-else>
    <div
      :class="sellerProfileColor(index)"
      class="rounded-full h-32 w-32 bg-opacity-14 text-5xl tracking-0.7 mb-6 font-medium relative flex justify-center items-center"
    >
      <img
        v-if="seller.verified"
        src="/assets/img/verification.svg"
        alt="Verified"
        class="w-6 h-6 absolute right-2 top-2"
      />
      <span>{{ [seller.firstName, seller.lastName] | initials }}</span>
    </div>
    <div class="text-xl font-medium tracking-0.44 leading-1.875 mb-2">{{ seller.firstName }} {{ seller.lastName }}</div>
    <div class="mb-1 text-sm tracking-0.23 leading-1.375">{{ seller.country }}</div>
    <div class="text-sm text-gray-800 tracking-0.23">Joined: {{ seller.joinedAt | date }}</div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import { Seller } from "@/models/models";

export default Vue.extend({
  name: "SellerProfile",
  props: {
    seller: {
      type: Object as PropType<Seller>,
      required: true
    },
    index: {
      type: Number,
      required: true
    },
    isBasic: {
      type: Boolean
    }
  },
  methods: {
    sellerSelected() {
      this.$emit("sellerSelected");
    },
    sellerProfileColor(index: number): string {
      // TODO check colors
      const colors = [
        "text-turquoise-blue bg-turquoise-blue",
        "text-pink-900 bg-pink-900",
        "text-yellow-900 bg-yellow-900",
        "text-green-900 bg-green-900"
      ];
      return colors[index];
    },
    dividerBgColor(index: number): string {
      const colors = ["bg-turquoise-blue", "bg-pink-900", "bg-yellow-900", "bg-green-900"];
      return colors[index];
    }
  }
});
</script>
<style lang="scss"></style>
