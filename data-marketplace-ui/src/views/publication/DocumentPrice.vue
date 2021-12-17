<template>
  <form novalidate class="mt-8">
    <div class="grid grid-cols-1 md:grid-cols-2 grid-flow-row">
      <DocumentPriceSuggested
        :document="document"
        :suggestedPricePerWord="suggestedPricePerWord"
        @setUseSuggestedPrice="setUseSuggestedPrice"
      />
      <DocumentPriceTotal
        :document="document"
        :suggestedPricePerWord="suggestedPricePerWord"
        :useSuggestedPrice="useSuggestedPrice"
        @onPriceChange="setTotalPrice"
      />
    </div>

    <div class="mt-20 grid md:grid-cols-3 grid-cols-2">
      <div class="self-center">
        <BackToPreviousPage label="to Domain Specification" @getBackToLastStep="backToDomainSpecification" />
      </div>
      <div class="text-center">
        <Button @click="setDocumentPrice" :disabled="isSubmitDisabled" :isLoading="isLoading">
          Next
        </Button>
      </div>
    </div>
  </form>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import DocumentPriceSuggested from "@/components/DocumentPriceSuggested.vue";
import DocumentPriceTotal from "@/components/DocumentPriceTotal.vue";
import Button from "@/components/Button.vue";
import { SuggestedPricePerWord, TranslationDocument } from "@/models/models";
import BackToPreviousPage from "@/components/BackToPreviousPage.vue";

export default Vue.extend({
  props: {
    document: {
      type: Object as PropType<TranslationDocument>,
      required: true
    },
    suggestedPricePerWord: {
      type: Object as PropType<SuggestedPricePerWord>,
      required: true
    },
    isLoading: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  data() {
    return {
      pricePerWord: this.document.pricePerWord,
      useSuggestedPrice: false,
      isSubmitDisabled: true
    };
  },
  methods: {
    setUseSuggestedPrice() {
      this.useSuggestedPrice = true;
      this.isSubmitDisabled = false;
      this.setPricePerWord(this.suggestedPricePerWord.suggestedPricePerWord);
    },
    setTotalPrice({ price, isInvalid }: { price: number; isInvalid?: boolean }) {
      this.useSuggestedPrice = false;
      this.isSubmitDisabled = isInvalid;
      this.setPricePerWord(price);
    },
    setPricePerWord(price: number) {
      this.pricePerWord = isNaN(price) ? 0 : parseFloat(price.toString());
    },
    setDocumentPrice() {
      this.$emit("setDocumentPrice", this.pricePerWord);
    },
    backToDomainSpecification() {
      this.$emit("getBackToLastStep", true);
    }
  },
  components: {
    DocumentPriceSuggested,
    DocumentPriceTotal,
    Button,
    BackToPreviousPage
  }
});
</script>

<style lang="scss"></style>
