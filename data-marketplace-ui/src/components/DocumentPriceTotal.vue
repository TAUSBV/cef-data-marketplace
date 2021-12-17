<template>
  <div class="flex flex-col items-center">
    <div class="text-xl font-medium tracking-wide">
      Total document price
    </div>
    <div class="mt-4 px-6 lg:w-3/4 bg-gray-200 bg-opacity-50 rounded">
      <div class="divide-y divide-gray-100">
        <div class="flex flex-col pt-6 pb-4">
          <div class="flex flex-row justify-between items-end pb-2">
            <label for="pricePerWord" class="inline-block align-middle text-xl">
              Price/word:
            </label>
            <div class="flex">
              <div
                class="pt-2 px-3 mb-0 border-t border-l border-b
              rounded-tl rounded-bl bg-gray-100 text-blue-700"
                :class="{ 'border-red-900': $v.pricePerWord.$error }"
              >
                €
              </div>
              <input
                id="pricePerWord"
                name="pricePerWord"
                type="number"
                pattern="\d*"
                size="6"
                maxlength="6"
                class="w-20 text-right outline-none appearance-none block p-2 border rounded-tr rounded-br"
                :class="{ 'border-red-900': $v.pricePerWord.$error }"
                :value="pricePerWord | decimal"
                @change="setPricePerWord(parseFloat($event.target.value))"
                @keypress="onlyNumber($event)"
              />
            </div>
          </div>
          <div class="flex flex-row justify-end text-xs text-red-900" :class="{ invisible: !$v.pricePerWord.$error }">
            Must be between
            {{ $v.pricePerWord.$params.between.min }} and
            {{ $v.pricePerWord.$params.between.max }}
          </div>
          <div class="flex flex-row justify-between items-end py-2">
            <span class="uppercase text-sm"> {{ document.sourceLanguage.displayName }} Words </span>
            <span class="text-xl">{{ totalWords | integer }}</span>
          </div>
          <div class="flex flex-row justify-between items-end py-2 fee">
            <span class="text-xl text-gray-800">Service fee ({{ suggestedPricePerWord.commissionFee * 100 }}%):</span>
            <span class="text-xl text-gray-800">
              {{ serviceFeePrice | currency2Decimals }}
            </span>
          </div>
          <div class="flex flex-row justify-between items-end">
            <span class="text-xs text-gray-800">
              Note: Fee is not charged until 31 October 2021.
            </span>
          </div>
        </div>
        <div class="flex flex-col pt-4 pb-6">
          <div class="flex flex-row justify-between items-end">
            <span class="text-xl font-medium">Total Price:</span>
            <span class="text-xl font-medium">{{ totalPrice | currency2Decimals }}</span>
          </div>
          <p class="text-sm float-left text-gray-800">
            Excluding any taxes, if applicable.
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import { between, required } from "vuelidate/lib/validators";
import { SuggestedPricePerWord, TranslationDocument } from "@/models/models";

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
    useSuggestedPrice: {
      type: Boolean
    }
  },
  data() {
    return {
      pricePerWord: this.document.pricePerWord
    };
  },
  watch: {
    useSuggestedPrice() {
      if (this.useSuggestedPrice) {
        this.pricePerWord = this.suggestedPricePerWord.suggestedPricePerWord;
      }
    }
  },
  validations: {
    pricePerWord: {
      required,
      between: between(0.0005, 0.15)
    }
  },
  computed: {
    totalWords(): number {
      return (
        this.document.currentStatistics.totalSourceOriginalWordCount +
        this.document.currentStatistics.totalSourceReplicaWordCount
      );
    },
    totalPrice(): number {
      let price = 0;
      if (!this.$v.pricePerWord.$error) {
        const totalWords = this.totalWords;
        price = totalWords * this.pricePerWord;
      }
      return price;
    },
    serviceFeePrice(): number {
      return this.pricePerWord * this.totalWords * this.suggestedPricePerWord.commissionFee;
    }
  },
  methods: {
    setPricePerWord(price: number) {
      this.pricePerWord = isNaN(price) ? 0 : price;
      this.$v.pricePerWord.$touch();
      this.$emit("onPriceChange", {
        price: this.pricePerWord,
        isInvalid: this.$v.$invalid
      });
    },
    onlyNumber(event: KeyboardEvent) {
      if (!/\d/.test(event.key) && (event.key !== "." || /\.\d{6}/.test(this.pricePerWord.toString())))
        return event.preventDefault();
    }
  }
});
</script>

<style lang="scss">
.fee {
  position: relative;
  &::before {
    content: " ";
    position: absolute;
    top: 50%;
    left: 0;
    border-radius: 0.5px;
    border-bottom: 1px solid;
    width: 100%;
    @apply border-gray-500;
  }
}
</style>
