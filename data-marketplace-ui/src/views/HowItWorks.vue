<template>
  <section class="bg-gray-100 overflow-x-hidden">
    <div class="flex flex-col w-3/4 py-8 mx-auto relative">
      <div class="mx-auto mb-10">
        <SectionTitle title="Data Marketplace Process" />
      </div>
      <div class="lg:w-3/5 mx-auto text-center mb-10">
        The Data Marketplace is <span class="font-bold">easy-to-use</span>,
        <span class="font-bold">easy-to-explore</span>, <span class="font-bold">easy-to-trade</span>, and
        <span class="font-bold">easy-to-trust</span>, with features that add value for data sellers and buyers equally.
      </div>
      <img src="/assets/img/data-process.png" alt="data process" />
    </div>
    <div class="flex flex-col w-3/4 py-8 lg:py-16 mx-auto">
      <div class="mb-10">
        <SectionTitle title="SELLING ON DATA MARKETPLACE" position="left" />
      </div>
      <div class="grid grid-cols-1 lg:grid-cols-2 gap-12 lg:gap-24">
        <div class="flex flex-col">
          <CardNumber
            number="1"
            title="UPLOAD DATA"
            text="Put your data up for sale in a few easy steps"
            colorText="text-blue-700"
            colorBackground="bg-blue-500"
            colorBorder="border-blue-700"
          />
          <CardNumber
            number="2"
            title="CLEAN & ANONYMIZE"
            text="Prepare your data with our cleaning tools and optional anonymization (coming soon)"
            colorText="text-blue-700"
            colorBackground="bg-blue-500"
            colorBorder="border-blue-700"
          />
          <CardNumber
            number="3"
            title="SET THE PRICE"
            text="Decide on the right price based on our smart price recommendations"
            colorText="text-blue-700"
            colorBackground="bg-blue-500"
            colorBorder="border-blue-700"
          />
          <CardNumber
            number="4"
            title="PUBLISH"
            text="Make the data available on the marketplace and searchable by the buyers"
            colorText="text-blue-700"
            colorBackground="bg-blue-500"
            colorBorder="border-blue-700"
          />
        </div>
        <div class="flex flex-col">
          <img src="/assets/img/selling-on-data-marketplace.png" alt="selling on data marketplace" />
        </div>
      </div>
    </div>
    <div class="flex flex-col w-3/4 py-8 lg:py-16 mx-auto">
      <div class="grid grid-cols-1 lg:grid-cols-2 gap-12 lg:gap-24">
        <div class="flex flex-col order-last lg:order-first">
          <img src="/assets/img/buying-on-data-marketplace.png" alt="buying on data marketplace" />
        </div>
        <div class="flex flex-col">
          <div class="mb-10">
            <SectionTitle title="BUYING FROM DATA MARKETPLACE" position="left" />
          </div>
          <CardNumber
            number="1"
            title="EXPLORE"
            text="Choose the language pair to get a glimpse of the available data"
            colorText="text-yellow-900"
            colorBackground="bg-yellow-300"
            colorBorder="border-yellow-900"
          />
          <CardNumber
            number="2"
            title="FILTER RESULTS"
            text="Filter by domain, content type and price or find datasets from specific sellers"
            colorText="text-yellow-900"
            colorBackground="bg-yellow-300"
            colorBorder="border-yellow-900"
          />
          <CardNumber
            number="3"
            title="PURCHASE & DOWNLOAD"
            text="Found the dataset you like? Complete the payment and download instantly"
            colorText="text-yellow-900"
            colorBackground="bg-yellow-300"
            colorBorder="border-yellow-900"
          />
          <CardNumber
            number="4"
            title="RATE & REVIEW"
            text="Validate the quality of purchased data by giving it a score and a review"
            colorText="text-yellow-900"
            colorBackground="bg-yellow-300"
            colorBorder="border-yellow-900"
          />
        </div>
      </div>
    </div>
    <div class="flex flex-col w-3/4 py-32 mx-auto relative">
      <div class="mx-auto mb-10">
        <SectionTitle title="DATA PREPARATION" />
      </div>
      <div class="text-4xl mb-3 mx-auto">How We Ensure Data Quality</div>
      <div class="lg:w-3/5 mx-auto text-center mb-16">
        "Garbage in, garbage out" is the rule: poor-quality data generate poor output. Data Marketplace defines
        high-quality data as ready-to-use for MT training, requiring minimal to no effort for fine-tuning.
      </div>
      <img src="/assets/img/data-quality-process.png" alt="data quality process" class="w-screen lg:w-3/5 mx-auto" />
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import CardNumber from "@/components/CardNumber.vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";

export default Vue.extend({
  name: "HowItWorks",
  components: {
    CardNumber,
    SectionTitle
  }
});
</script>
<style lang="scss"></style>
