<template>
  <div class="grid lg:grid-cols-4 md:cols-span-1 xs:cols-span-1 gap-x-6 md:gap-y-10 xs:gap-y-8 mt-12">
    <div class="lg:col-span-1 md:col-span-1 xs:col-span-1 items-stretch justify-items-center">
      <div class="border xl:mt-8 lg:mt-14 rounded-md flex flex-col animate-pulse">
        <div class="lg:mb-3 md:mb-6 sm:mb-6 xs:mb-6">
          <div class="py-3 md:py-5 sm:py-5 xs:py-5 md:text-center xs:text-center ">
            <div class="h-8 mx-4 bg-gray-200 rounded"></div>
          </div>
          <div
            class="flex flex-row  lg:flex-col md:flex-wrap md:gap-x-6 md:items-center md:justify-center xs:justify-center xs: gap-x-12 lg:justify-start lg:items-start gap-y-3"
          >
            <div class="lg:ml-8">
              <div class="flex flex-row items-center">
                <div class="w-5 h-5 bg-gray-200 "></div>
                <div class="ml-1 h-5 bg-gray-200 w-12 md:w-24"></div>
              </div>
              <div class="ml-4 md:ml-6 mt-1 h-4 bg-gray-200 w-3/4"></div>
            </div>
            <div class="lg:ml-8">
              <div class="flex flex-row items-center">
                <div class=" w-5 h-5 bg-gray-200 "></div>
                <div class="ml-1 h-5 bg-gray-200 w-12 md:w-24"></div>
              </div>
              <div class="ml-4 md:ml-6 mt-1 h-4 bg-gray-200 w-3/4"></div>
            </div>
            <div class="lg:ml-8">
              <div class="flex flex-row items-center">
                <div class=" w-5 h-5 bg-gray-200 "></div>
                <div class="ml-1 h-5 bg-gray-200 w-12 md:w-24"></div>
              </div>
              <div class="ml-4 md:ml-6 mt-1 h-4 bg-gray-200 w-3/4"></div>
            </div>
          </div>
        </div>
        <div class="animate-pulse space-y-6">
          <div class="border-t-2">
            <div class=" py-3">
              <div class="h-8 mx-4 bg-gray-200 rounded"></div>
            </div>
            <div class="grid lg:grid-cols-2 md:grid-cols-4 xs:grid-cols-4 gap-y-3 ml-4 ">
              <div class="h-6 bg-gray-200 w-3/4"></div>
              <div class="h-6 bg-gray-200 w-3/4"></div>
              <div class="h-6 bg-gray-200 w-3/4"></div>
              <div class="h-6 bg-gray-200 w-3/4"></div>
            </div>
          </div>
          <div
            class="border-t-2 mx-4 py-3 flex xl:flex-row lg:flex-row flex-row md:justify-center md:gap-x-12 sm:justify-center sm:gap-x-12 xs:justify-center xs:gap-x-12 lg:gap-x-2 items-center "
          >
            <div class="h-6 mx-4 bg-gray-200 rounded w-1/4"></div>
            <div class="h-6 bg-gray-200 w-1/4 self-center"></div>
            <div class="bg-gray-200 rounded-full w-10 h-10 "></div>
          </div>
        </div>
      </div>
    </div>
    <div class="lg:col-span-3 animate-pulse">
      <div class="h-5 bg-gray-200 rounded w-1/2 animate-pulse"></div>
      <div class="grid my-3 grid-cols-2 border border-gray-200 pt-3 rounded h-full">
        <div class="flex-1 space-y-2 px-3 animate-pulse">
          <div v-for="(item, index) in 3" :key="index" class="space-y-2">
            <div class="h-6 bg-gray-200 rounded"></div>
            <div class="h-20 bg-gray-200 rounded"></div>
            <div class="h-10 bg-gray-200 rounded"></div>
            <div class="h-6 bg-gray-200 rounded"></div>
          </div>
        </div>
        <div class="flex-1 space-y-2 px-3 animate-pulse">
          <div v-for="(item, index) in 3" :key="index" class="space-y-2">
            <div class="h-6 bg-gray-200 rounded"></div>
            <div class="h-20 bg-gray-200 rounded"></div>
            <div class="h-10 bg-gray-200 rounded"></div>
            <div class="h-6 bg-gray-200 rounded"></div>
          </div>
        </div>
        <div class="col-span-2 animate-pulse"><div class="h-14 bg-gray-200"></div></div>
        <div class="col-span-2 mb-3 md:ml-20 flex flex-row lg:gap-x-6 xs:gap-x-4 md:gap-x-4 items-center animate-pulse">
          <div class=" col-span-1 h-6 bg-gray-200 rounded w-10"></div>
          <div class="h-6 bg-gray-200 lg:ml-52 rounded w-52"></div>
          <div class="h-10 bg-gray-200 lg:ml-8 rounded w-44"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "MDSearchResultLoading"
});
</script>

<style lang="scss"></style>
