<template>
  <div class="grid grid-cols-1 md:grid-cols-2 gap-8 mt-6">
    <div class="col-span-full" v-if="showMessage">
      <Alert :messages="[thankYouMessage]" :title="''" type="SUCCESS" />
    </div>
    <div class="flex flex-col items-center">
      <span class="font-medium text-lg tracking-0.3 mb-4">Rate the overall dataset quality</span>
      <StarRating
        :score="reviewInternal.score"
        :size="'lg'"
        :edit-mode="true"
        :show-score="false"
        :show-emojis="true"
        :is-disabled="isReviewSubmitted"
        @set-rating="addRating"
      />
    </div>
    <form class="flex flex-col" novalidate>
      <label :for="`review-comment-${reviewInternal.purchaseItemUid}`" class="font-medium text-lg tracking-0.3 mb-4">
        Leave a comment
      </label>
      <textarea
        :id="`review-comment-${reviewInternal.purchaseItemUid}`"
        :name="`review-comment-${reviewInternal.purchaseItemUid}`"
        :disabled="isReviewSubmitted"
        v-model="reviewInternal.comment"
        class="h-20 w-full rounded border border-gray-300 p-3 mb-5"
        placeholder="Tell us more about how the data met your expectations (optional)"
      />
      <div class="w-full flex justify-between items-center">
        <FormCheckbox
          id="anonymous"
          :disabled="isReviewSubmitted"
          :v-model="reviewInternal.anonymous"
          link-text="Hide my name for this review."
          @change="val => (reviewInternal.anonymous = val)"
        />
        <div class="flex gap-4 self-end" v-if="moderationMode">
          <Button2
            type="submit"
            color="danger"
            :is-loading="isRejectSubmitted"
            :is-disabled="isRejectSubmitted || isApproveSubmitted"
            @click="onModerate('rejected')"
          >
            Reject
          </Button2>
          <Button2
            type="submit"
            :is-loading="isApproveSubmitted"
            :is-disabled="isRejectSubmitted || isApproveSubmitted"
            @click="onModerate('approved')"
          >
            Approve
          </Button2>
        </div>
        <Button2 type="submit" :is-loading="isSubmitting" :is-disabled="isReviewSubmitted" @click="onSubmit" v-else>
          Submit
        </Button2>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import StarRating from "@/components/rating/StarRating.vue";
import Button2 from "@/components/Button2.vue";
import Alert from "@/components/Alert.vue";
import { PurchaseItemReview } from "@/models/models";
import FormCheckbox from "@/components/FormCheckbox.vue";

export default Vue.extend({
  name: "RateAndReview",
  props: {
    review: {
      type: Object as PropType<PurchaseItemReview>,
      required: false
    },
    moderationMode: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  data() {
    return {
      isSubmitting: false,
      showMessage: false,
      isApproveSubmitted: false,
      isRejectSubmitted: false,
      thankYouMessage: "Thank you for your feedback",
      reviewInternal:
        this.review == null ? { purchaseItemUid: null, comment: "", score: 0, anonymous: false } : this.review
    };
  },
  watch: {
    review() {
      this.reviewInternal = this.review;
      this.isSubmitting = false;
      this.isRejectSubmitted = false;
      this.isApproveSubmitted = false;
    }
  },
  methods: {
    addRating(rating: number): void {
      this.reviewInternal.score = rating;
    },
    onSubmit(): void {
      this.$emit("set-review", this.reviewInternal);
      this.showMessage = true;
    },
    onModerate(outcome: string): void {
      if (outcome === "approved") {
        this.isApproveSubmitted = true;
        this.isRejectSubmitted = false;
      } else if (outcome === "rejected") {
        this.isRejectSubmitted = true;
        this.isApproveSubmitted = false;
      }
      this.isSubmitting = true;
      this.$emit("moderate", outcome);
    }
  },
  computed: {
    isReviewSubmitted(): boolean {
      return (
        this.reviewInternal != null &&
        this.reviewInternal.purchaseItemUid != null &&
        this.reviewInternal.purchaseItemUid !== ""
      );
    }
  },
  components: {
    FormCheckbox,
    StarRating,
    Button2,
    Alert
  }
});
</script>
