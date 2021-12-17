<template>
  <section>
    <div v-if="paymentDetails" class="text-right">
      <a @click="toggleView" class="inline-block text-blue-700 font-medium mb-6 cursor-pointer ">
        <span v-if="isFormHidden">
          Edit
          <img src="/assets/img/edit-blue.svg" alt="edit" class="inline-block pl-1 mb-1" />
        </span>

        <span v-else class="text-red-900">
          Cancel
          <img src="/assets/img/close-red.svg" alt="cancel" class="inline-block pl-1 mb-px" />
        </span>
      </a>
    </div>

    <Alert v-if="genericErrors.length > 0" :messages="genericErrors" type="DANGER" />

    <div v-if="isFetchingData">
      <UserProfileLoading />
    </div>

    <div v-else>
      <UserPaymentDetailsEmpty
        v-if="!paymentDetails.method && isFormHidden"
        @set-payout-account="toggleView"
      ></UserPaymentDetailsEmpty>

      <UserPaymentDetailsFilled
        :payment-details="paymentDetails"
        :bank-name="bankName"
        :branch-name="branchName"
        v-if="paymentDetails.method && isFormHidden"
      ></UserPaymentDetailsFilled>

      <UserPaymentDetailsForm
        :payment-details="paymentDetails"
        :transfer-wise-supported-countries="transferWiseSupportedCountries"
        :iban-supported-countries="ibanSupportedCountries"
        :transfer-wise-banks="transferWiseBanks"
        :transfer-wise-bank-branches="transferWiseBankBranches"
        :is-loading="isFetchingData"
        @changed-country-code="onUpdateTransferwiseBanks"
        @changed-bank-code="onUpdateTransferwiseBankBranches"
        @update-payment-details="updatePaymentDetails"
        v-if="!isFormHidden"
      ></UserPaymentDetailsForm>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import UserProfileLoading from "@/components/user/UserProfileLoading.vue";
import UserPaymentDetailsEmpty from "@/views/user/UserPaymentDetailsEmpty.vue";
import UserPaymentDetailsForm from "@/views/user/UserPaymentDetailsForm.vue";
import UserPaymentDetailsFilled from "@/views/user/UserPaymentDetailsFilled.vue";
import Alert from "@/components/Alert.vue";
import {
  IbanSupportedCountry,
  PaymentDetailsForm,
  TransferWiseBank,
  TransferWiseBranch,
  TransferWiseSupportedCountry,
  UserPaymentDetailsApiResponse,
  UserPaymentDetailsPayload
} from "@/models/models";

export default Vue.extend({
  name: "UserPaymentDetails",
  data() {
    return {
      paymentDetails: null as UserPaymentDetailsPayload,
      transferWiseSupportedCountries: [] as TransferWiseSupportedCountry[],
      ibanSupportedCountries: [] as IbanSupportedCountry[],
      transferWiseBanks: [] as TransferWiseBank[],
      transferWiseBankBranches: [] as TransferWiseBranch[],
      bankName: "" as string,
      branchName: "" as string,
      isFormHidden: true,
      isFetchingData: true,
      serviceErrors: {} as Record<string, string>,
      genericErrors: []
    };
  },
  mounted() {
    this.setPaymentDetails();
  },
  methods: {
    toggleView() {
      this.isFormHidden = !this.isFormHidden;
    },
    async setPaymentDetails() {
      // reset errors if any
      this.genericErrors = [];

      // get user payment details form the server response
      const paymentDetailsResponse: UserPaymentDetailsApiResponse = await this.$store.dispatch(
        "user/fetchPaymentDetails"
      );

      // extract the needed properties from the response
      const {
        method,
        name,
        addressLine,
        city,
        zipCode,
        countryCode,
        vatStatus,
        vatNumber,
        legalType,
        paypalEmail,
        iban,
        transferwiseType,
        ifscCode,
        accountNumber,
        bankCode,
        branchCode,
        swift,
        paymentTermsConsent,
        transferWiseOptions,
        bankTransferOptions
      } = paymentDetailsResponse;

      // update global paymentDetails object
      this.paymentDetails = {
        method,
        name,
        addressLine,
        city,
        zipCode,
        countryCode,
        vatStatus,
        vatNumber,
        iban,
        legalType,
        transferwiseType,
        paypalEmail,
        ifscCode,
        accountNumber,
        bankCode,
        branchCode,
        swift,
        paymentTermsConsent
      };

      this.transferWiseSupportedCountries = transferWiseOptions.supportedCountries;
      this.ibanSupportedCountries = [
        ...bankTransferOptions.mandatoryIbanCountries,
        ...bankTransferOptions.recommendedIbanCountries
      ];

      this.transferWiseBanks = (await this.getTransferwiseBanks(countryCode)) || [];
      this.transferWiseBankBranches = (await this.getTransferwiseBranches(countryCode, bankCode)) || [];

      this.bankName =
        this.transferWiseBanks.find((bank: TransferWiseBank) => bank?.code === this.paymentDetails?.bankCode)?.title ||
        "";
      this.branchName =
        this.transferWiseBankBranches.find(
          (branch: TransferWiseBranch) => branch?.code === this.paymentDetails?.branchCode
        )?.title || "";

      // hide page loading indicator
      this.isFetchingData = false;
    },
    async updatePaymentDetails(paymentInfo: PaymentDetailsForm) {
      const { country, bank, branch, method, ...details } = paymentInfo;
      const updatedPaymentDetails: UserPaymentDetailsPayload = {
        ...details,
        method: method.id,
        countryCode: country.code,
        bankCode: bank?.code || null,
        branchCode: branch?.code || null
      };

      try {
        this.isFetchingData = true;

        await this.validateTransferWiseFields(paymentInfo);

        await this.$store.dispatch("user/updatePaymentDetails", updatedPaymentDetails);
        await this.setPaymentDetails();

        // on successful request hide the form
        this.isFormHidden = true;
      } catch (error) {
        window.scroll(0, 0);

        this.serviceErrors = error.response?.data;
        this.genericErrors = [];

        // TODO: Refactor if-else block to something more readable
        if (typeof this.serviceErrors === "string") {
          this.genericErrors.push(this.serviceErrors);
        } else if (this.serviceErrors.errors) {
          for (const [key, value] of Object.entries(this.serviceErrors.errors[0])) {
            if (key === "message") {
              this.genericErrors.push(value);
            }
          }
        } else {
          Object.entries(this.serviceErrors).forEach(([serviceKey]) => {
            if (!Object.prototype.hasOwnProperty.call(this.paymentDetails, serviceKey)) {
              this.genericErrors.push(this.serviceErrors[serviceKey]);
            }
          });
        }
      } finally {
        this.isFetchingData = false;
      }
    },
    validateTransferWiseFields(paymentInfo: PaymentDetailsForm) {
      if (paymentInfo.method.value !== "TransferWise") {
        return Promise.resolve();
      }

      if (paymentInfo.transferwiseType === "iban") {
        return this.$store.dispatch("seller/validateIban", paymentInfo.iban);
      }

      if (paymentInfo.transferwiseType === "indian") {
        const promises = [
          this.$store.dispatch("seller/validateIfsc", paymentInfo.ifscCode),
          this.$store.dispatch("seller/validateIndianAccountNumber", paymentInfo.accountNumber)
        ];
        return Promise.all(promises);
      }

      return Promise.resolve();
    },
    async onUpdateTransferwiseBanks(countryCode: string) {
      this.transferWiseBanks = (await this.getTransferwiseBanks(countryCode)) || [];
    },
    async onUpdateTransferwiseBankBranches(countryCode: string, bankCode: string) {
      this.transferWiseBankBranches = (await this.getTransferwiseBranches(countryCode, bankCode)) || [];
    },
    getTransferwiseBanks(countryCode: string) {
      if (countryCode === "NP" || countryCode === "LK" || countryCode === "ZA") {
        return this.$store.dispatch("seller/getTransferwiseBanks", countryCode);
      }

      return Promise.resolve();
    },
    getTransferwiseBranches(countryCode: string, bankCode: string) {
      if (countryCode === "LK") {
        const params: { countryCode: string; bankCode: string } = { countryCode, bankCode };
        return this.$store.dispatch("seller/getTransferwiseBankBranches", params);
      }

      return Promise.resolve();
    }
  },
  components: {
    Alert,
    UserProfileLoading,
    UserPaymentDetailsEmpty,
    UserPaymentDetailsForm,
    UserPaymentDetailsFilled
  }
});
</script>
