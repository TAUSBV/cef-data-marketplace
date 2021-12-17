<template>
  <div class="w-full overflow-x-hidden">
    <div class="mt-8 mb-16">
      <SectionTitle title="PURCHASE DETAILS" />
    </div>
    <div class="w-3/4 mt-2 pb-32 mx-auto" v-if="!isFetchingData && serviceErrors.length === 0">
      <section>
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 mb-12">
          <div class="space-y-6">
            <div>
              <div class="text-gray-800">Number</div>
              <div class="text-lg">{{ purchase.uid }}</div>
            </div>
            <div v-if="purchase.payment">
              <div class="text-gray-800">Date of purchase</div>
              <div class="text-lg">
                {{ purchase.payment.updatedAt | mediumDate }}
              </div>
            </div>
            <div v-else>
              <!-- Show date of purchase request when no payment method is selected -->
              <div class="text-gray-800">Date of purchase request</div>
              <div class="text-lg">
                {{ purchase.updatedAt | mediumDate }}
              </div>
            </div>
            <div>
              <div class="text-gray-800">Status</div>
              <Tag
                :label="purchase.status | cleanString"
                variant="compact"
                :color="orderStatusStyle"
                class="inline float-none py-1 px-2 text-xs"
              />
            </div>
          </div>
          <div class="space-y-6">
            <div>
              <div class="text-gray-800">Name</div>
              <div class="text-lg">{{ purchase.buyer.firstName }} {{ purchase.buyer.lastName }}</div>
            </div>
            <div>
              <div class="text-gray-800">Email</div>
              <div class="text-lg">{{ purchase.buyer.email }}</div>
            </div>
          </div>
          <div class="space-y-6">
            <div>
              <div class="text-gray-800" v-if="isPaid">Amount paid</div>
              <div class="text-gray-800" v-else>Amount to be paid</div>
              <div class="text-lg">{{ purchasePrice | currency2Decimals }}</div>
            </div>
            <div v-if="purchase.payment">
              <div class="mb-6">
                <div class="text-gray-800">Payment method</div>
                <div class="text-lg">
                  {{ purchase.payment.method | cleanString }}
                </div>
              </div>
              <div>
                <div class="text-gray-800">Payment status</div>
                <Tag
                  :label="purchase.payment.status | cleanString"
                  variant="compact"
                  :color="paymentStatusStyle"
                  class="inline float-none py-1 px-2 text-xs"
                />
              </div>
            </div>
            <div v-if="isUnpaid">
              <Button type="button" class="my-6" @click="pay()">
                <span class="text-lg font-light w-full">Pay</span>
              </Button>
            </div>
          </div>
        </div>
      </section>
      <section>
        <div class="flex">
          <p class="flex-inline text-lg tracking-0.3 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27 mb-4">
            You have
            <strong>
              {{ purchase.items.length }}
              <span v-if="purchase.items.length === 1">product</span>
              <span v-else>products</span>
            </strong>
            in your purchase
          </p>

          <div class="flex-inline ml-auto" v-if="isPaid">
            <span v-if="isCreatingDownloadLink">Sending...</span>
            <a v-else class="text-lg text-blue-700 font-medium cursor-pointer" @click="sendLink()">
              <img src="/assets/img/download.svg" alt="Download" class="inline mr-2" />
              Send download link
            </a>
          </div>
        </div>
        <div class="border-b border-gray-200">
          <Alert v-if="infoMessage !== ''" :messages="[infoMessage]" type="INFO" />
        </div>

        <div class="mt-6 pb-6" v-if="purchase.items && purchase.items.length > 0">
          <div class="w-full grid grid-cols-10 xs:gap-x-4 sm:gap-x-6 lg:gap-x-6 xl:gap-12">
            <h3
              class="text-xl leading-30 tracking-5.0 font-normal xs:text-base xs:leading-relaxed xs:tracking-wide mb-8 xs:col-span-10 sm:col-span-10 md:col-span-5 lg:col-span-4"
            >
              DATASET
            </h3>
            <h3
              class="text-xl leading-30 tracking-5.0 font-normal xs:text-base xs:leading-relaxed xs:tracking-wide mb-8 xs:col-span-5 sm:col-span-5 md:col-span-3 lg:col-span-3"
            >
              QUANTITY
            </h3>
            <h3
              class="text-xl leading-30 tracking-5.0 font-normal xs:text-base xs:leading-relaxed xs:tracking-wide mb-8 xs:col-span-5 sm:col-span-5 md:col-span-2 lg:col-span-3"
            >
              PRICE
            </h3>
          </div>

          <!-- Cart Items -->
          <div v-for="(cartItem, index) in purchase.items" :key="'cart-item-' + cartItem.uid">
            <ShoppingCartItem
              :class="{
                'border-b border-gray-200': index < purchase.items.length - 1
              }"
              :cartItem="cartItem"
              :isReadOnly="true"
            />
            <div class="border-t border-gray-300 -mt-8 mb-6 pb-8">
              <RateAndReview
                :review="cartItem.review"
                :moderation-mode="moderationMode"
                @set-review="val => setRatingAndReview(cartItem.uid, val)"
                @moderate="val => moderate(cartItem.uid, moderationCode, val)"
              />
            </div>
          </div>
        </div>
      </section>
    </div>
    <!-- Loading placeholder -->
    <div class="w-3/4 mt-2 pb-32 mx-auto" v-else-if="isFetchingData">
      <PurchaseLoading />
    </div>
    <div class="w-3/4 mt-2 mb-16 mx-auto" v-else>
      <Alert v-if="serviceErrors.length > 0" :messages="serviceErrors" type="DANGER" />
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Button from "@/components/Button.vue";
import ShoppingCartItem from "@/components/shopping-cart/ShoppingCartItem.vue";
import Tag from "@/components/Tag.vue";
import PurchaseLoading from "@/views/purchase/PurchaseLoading.vue";
import Alert from "@/components/Alert.vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";

import { Cart, CartStatus, PaymentMethods, PaymentStatus, PurchaseItemReview } from "@/models/models";
import RateAndReview from "@/components/rating/RateAndReview.vue";
import PurchaseService from "@/services/PurchaseService";

export default Vue.extend({
  name: "Purchase",
  data() {
    return {
      isFetchingData: true,
      purchase: {} as Cart,
      serviceErrors: [],
      isCreatingDownloadLink: false,
      infoMessage: "",
      moderationCode: "",
      moderationMode: false
    };
  },
  mounted() {
    const promisesArray = [];
    promisesArray.push(this.$store.dispatch("purchase/get", this.$route.params.uid));
    const moderationCode = this.$route.query.moderation_code as string;
    if (moderationCode !== undefined) {
      promisesArray.push(
        this.$store.dispatch("purchase/checkModerationCode", {
          purchaseUid: this.$route.params.uid,
          moderationCode: moderationCode
        })
      );
    }
    Promise.all(promisesArray)
      .then(responses => {
        this.purchase = responses[0] as Cart;
        const moderationMode = responses[1] as boolean;
        if (moderationMode === true) {
          this.moderationMode = moderationMode;
          this.moderationCode = moderationCode;
          this.infoMessage = "Review moderation mode is enabled";
        }
      })
      .catch(error => {
        this.serviceErrors.push(error.message);
      })
      .finally(() => {
        this.isFetchingData = false;
      });
  },
  computed: {
    purchasePrice(): number {
      return this.purchase.total;
    },
    isUnpaid(): boolean {
      return (
        !this.purchase.payment ||
        (this.purchase.payment.status === PaymentStatus.UNPAID &&
          this.purchase.payment.method !== PaymentMethods.BANK_TRANSFER)
      );
    },
    isPaid(): boolean {
      return this.purchase.payment && this.purchase.payment.status === PaymentStatus.PAID;
    },
    paymentStatusStyle(): string {
      switch (this.purchase.payment.status) {
        case PaymentStatus.PAID:
          return "bg-green-900 bg-opacity-25 text-green-900";
        case PaymentStatus.UNPAID:
          return "bg-orange-900 bg-opacity-25 text-orange-900";
        case PaymentStatus.CANCELED:
          return "bg-red-900 bg-opacity-25 text-red-900";
        default:
          return "";
      }
    },
    orderStatusStyle() {
      switch (this.purchase.status) {
        case CartStatus.RECEIVED:
          return "bg-blue-700 bg-opacity-25 text-blue-700";
        case CartStatus.AWAITING_PAYMENT:
          return "bg-orange-900 bg-opacity-25 text-orange-900";
        case CartStatus.COMPLETED:
          return "bg-green-900 bg-opacity-25 text-green-900";
        default:
          return "";
      }
    }
  },
  methods: {
    pay() {
      this.$router.push({
        name: "Checkout",
        query: {
          p: this.purchase.uid
        }
      });
    },
    sendLink() {
      this.isCreatingDownloadLink = true;
      this.infoMessage = "";
      this.$store
        .dispatch("purchase/createDownload", this.purchase.uid)
        .then(() => {
          this.infoMessage = "Link sent, check your email.";
        })
        .catch(error => {
          this.serviceErrors.push(error.message);
        })
        .finally(() => {
          this.isCreatingDownloadLink = false;
        });
    },
    setRatingAndReview(purchaseItemUid: string, review: PurchaseItemReview): void {
      review.purchaseItemUid = purchaseItemUid;
      this.$store
        .dispatch("purchase/createReview", { review })
        .then(() => this.$store.dispatch("purchase/get", this.purchase.uid))
        .then((response: Cart) => (this.purchase = response))
        .catch(error => this.serviceErrors.push(error.message))
        .finally(() => (this.isFetchingData = false));
    },
    moderate(purchaseItemUid: string, moderationCode: string, outcome: string): void {
      this.$store
        .dispatch("purchase/moderate", { purchaseItemUid, moderationCode, outcome })
        .then(() => this.$store.dispatch("purchase/get", this.purchase.uid))
        .then((response: Cart) => (this.purchase = response))
        .catch(error => this.serviceErrors.push(error.message))
        .finally(() => (this.isFetchingData = false));
    }
  },
  components: {
    Alert,
    Button,
    ShoppingCartItem,
    Tag,
    PurchaseLoading,
    SectionTitle,
    RateAndReview
  }
});
</script>

<style scoped></style>
