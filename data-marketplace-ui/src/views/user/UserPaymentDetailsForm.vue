<template>
  <form novalidate>
    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
      <!-- Payment Methods -->
      <div>
        <DropDown
          id="paymentMethod"
          placeholder="Select your preferred payment method"
          label="Payment Method"
          :value="selectedMethod || ''"
          :options="paymentOptions"
          trackByKey="value"
          :is-required="true"
          :is-invalid="$v.form.method.$error"
          @selected="val => setPaymentMethod(val)"
          @closed="() => $v.form.method.$touch()"
        />
      </div>
      <!-- PayPal Email -->
      <div v-if="isPaypalEmailFieldDisplayed">
        <FormInput
          type="email"
          id="paypalEmail"
          placeholder="Type in your email"
          v-model="form.paypalEmail"
          label="Paypal Email"
          :value="$v.form.paypalEmail"
          :isRequired="true"
          :isInvalid="$v.form.paypalEmail.$error"
          @input="val => setPaypalEmail(val)"
        />
        <span class="text-xs text-red-900 py-1" v-if="$v.form.paypalEmail.$error">
          <span v-if="!$v.form.paypalEmail.required">Required field</span>
          <span v-if="!$v.form.paypalEmail.email">
            Email is not properly formatted
          </span>
        </span>
      </div>
      <!-- Legal Type -->
      <div>
        <label class="w-full text-sm leading-8">Legal Type <span class="text-red-800">*</span></label>
        <div class="flex h-12">
          <label for="private" class="flex items-center space-x-3 pr-5">
            <input
              type="radio"
              id="private"
              name="legalType"
              value="Private"
              v-model="form.legalType"
              class="appearance-none cursor-pointer h-5 w-5 shadow rounded-full border
            border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
            />
            <span class="font-medium cursor-pointer">Private</span>
          </label>
          <label for="business" class="flex items-center space-x-3 pr-5">
            <input
              type="radio"
              id="business"
              name="legalType"
              value="Business"
              v-model="form.legalType"
              class="appearance-none cursor-pointer h-5 w-5 shadow rounded-full border border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
            />
            <span class="font-medium cursor-pointer">Business</span>
          </label>
        </div>
      </div>
      <!-- Country -->
      <div>
        <DropDown
          id="country"
          placeholder="Select or type in your country"
          label="Country"
          :value="selectedCountry || ''"
          :options="countryOptions"
          trackByKey="name"
          :is-required="true"
          :is-invalid="$v.form.country.$error"
          @selected="val => onCountryChange(val)"
          @closed="() => $v.form.country.$touch()"
        />
      </div>
      <!-- Full Name -->
      <div>
        <FormInput
          type="text"
          id="name"
          placeholder="Type in your full name"
          v-model="form.name"
          label="Full Name"
          :value="$v.form.name"
          :isRequired="true"
          :isInvalid="$v.form.name.$error"
          @input="val => setName(val)"
        />
        <div class="text-xs text-red-900 py-1" v-if="$v.form.name.$error">
          <span v-if="!$v.form.name.required">Required field</span>
        </div>
      </div>
      <!-- VAT Number -->
      <div>
        <FormInput
          type="text"
          id="vatNumber"
          placeholder="Type in your VAT number"
          v-model="form.vatNumber"
          label="VAT Number"
          :value="$v.form.vatNumber"
          :isRequired="isVatNumberFieldRequired"
          :isInvalid="$v.form.vatNumber.$error"
          @input="val => setVatNumber(val)"
        />
        <div class="text-xs text-red-900 py-1" v-if="$v.form.vatNumber.$error">
          <span v-if="!$v.form.vatNumber.requiredIf">Required field</span>
        </div>
      </div>
      <!-- IBAN -->
      <div v-if="isIbanFieldDisplayed">
        <FormInput
          type="text"
          id="iban"
          placeholder="Type in your IBAN"
          v-model="form.iban"
          label="IBAN"
          :value="$v.form.iban"
          :isRequired="isIbanFieldDisplayed"
          :isInvalid="$v.form.iban.$error"
          @input="val => setIban(val)"
        />
        <div class="text-xs text-red-900 py-1" v-if="$v.form.iban.$error">
          <span v-if="!$v.form.iban.required">Required field</span>
          <span v-if="!$v.form.iban.minLength || !$v.form.iban.maxLength"
            >The IBAN of your country should be {{ this.ibanLength }} digits</span
          >
          <span v-if="!(!$v.form.iban.minLength || !$v.form.iban.maxLength) && !$v.form.iban.ibanFormat"
            >Invalid IBAN format</span
          >
        </div>
      </div>
      <!-- IFSC Code -->
      <div v-if="isIfscCodeFieldDisplayed">
        <FormInput
          type="text"
          id="ifscCode"
          placeholder="Type in your IFSC code"
          v-model="form.ifscCode"
          label="IFSC Code"
          :value="$v.form.ifscCode"
          :isRequired="isIfscCodeFieldDisplayed"
          :isInvalid="$v.form.ifscCode.$error"
          @input="val => setIfscCode(val)"
        />
        <div class="text-xs text-red-900 py-1" v-if="$v.form.ifscCode.$error">
          <span v-if="!$v.form.ifscCode.requiredIf">IFSC code is required for Indian transactions</span>
          <span v-if="!$v.form.ifscCode.ifscCodeFormat"
            >Should be an 11-character code with the first four alphabetic characters representing the bank name, and
            the last six characters representing the branch. The fifth character is always 0 (zero)</span
          >
        </div>
      </div>
      <!-- Bank -->
      <div v-if="isBankFieldDisplayed">
        <DropDown
          id="bank"
          placeholder="Select or type in your bank"
          label="Bank"
          :value="selectedBank || ''"
          :options="bankOptions"
          trackByKey="title"
          :is-required="isBankFieldRequired"
          :is-invalid="$v.form.bank.$error"
          @selected="val => setBankCode(val)"
          @closed="() => $v.form.bank.$touch()"
        />
      </div>
      <!-- Bank Branches -->
      <div v-if="isBranchFieldDisplayed">
        <DropDown
          id="branch"
          placeholder="Select or type in your bank branch"
          label="Branch"
          :value="selectedBranch || ''"
          :options="branchOptions"
          trackByKey="title"
          :is-required="true"
          :is-invalid="$v.form.branch.$error"
          @selected="val => setBranchCode(val)"
          @closed="() => $v.form.branch.$touch()"
        />
      </div>
      <!-- Account Number -->
      <div v-if="isAccountNumberFieldDisplayed">
        <FormInput
          type="text"
          id="accountNumber"
          placeholder="Type in your account number"
          v-model="form.accountNumber"
          label="Account Number"
          :value="$v.form.accountNumber"
          :isRequired="isAccountNumberFieldDisplayed"
          :isInvalid="$v.form.accountNumber.$error"
          @input="val => setAccountNumber(val)"
        />
        <div class="text-xs text-red-900 py-1" v-if="$v.form.accountNumber.$error">
          <span v-if="!$v.form.accountNumber.required">Account number is required for TransferWise</span>
          <span v-if="!$v.form.accountNumber.minLength || !$v.form.accountNumber.maxLength"
            >Account number must be at least {{ this.accountNumberMinLength }} and no more than
            {{ accountNumberMaxLength }} characters in length</span
          >
        </div>
      </div>
      <!-- SWIFT Code -->
      <div v-if="isSwiftCodeFieldDisplayed">
        <FormInput
          type="text"
          id="swift"
          placeholder="Type in your SWIFT/BIC code (e.g. AAAABBCC123)"
          v-model="form.swift"
          label="SWIFT/BIC Code"
          :value="$v.form.swift"
          :isRequired="isSwiftCodeFieldDisplayed"
          :isInvalid="$v.form.swift.$error"
          @input="val => setSwiftCode(val)"
        />
        <div class="text-xs text-red-900 py-1" v-if="$v.form.swift.$error">
          <span v-if="!$v.form.swift.required">SWIFT code is required for South African transactions</span>
          <span v-if="!$v.form.swift.minLength || !$v.form.swift.maxLength"
            >SWIFT code must be at least 8 and no more than 11 characters in length</span
          >
          <span v-if="!(!$v.form.swift.minLength || !$v.form.swift.maxLength) && !$v.form.swift.swiftCodeFormat">
            Invalid SWIFT/BIC code format. It should be an 8-11-character code of this format: AAAABBCC123</span
          >
        </div>
      </div>
      <!-- Address Line -->
      <div>
        <FormInput
          type="text"
          id="addressLine"
          placeholder="Type in your address"
          v-model="form.addressLine"
          label="Address"
          :value="$v.form.addressLine"
          :isRequired="true"
          :isInvalid="$v.form.addressLine.$error"
          @input="val => setAddressLine(val)"
        />
        <div class="text-xs text-red-900 py-1" v-if="$v.form.addressLine.$error">
          <span v-if="!$v.form.addressLine.required">Required field</span>
        </div>
      </div>
      <!-- City -->
      <div>
        <FormInput
          type="text"
          id="city"
          placeholder="Type in your city"
          v-model="form.city"
          label="City"
          :value="$v.form.city"
          :isRequired="true"
          :isInvalid="$v.form.city.$error"
          @input="val => setCity(val)"
        />
        <div class="text-xs text-red-900 py-1" v-if="$v.form.city.$error">
          <span v-if="!$v.form.city.required">Required field</span>
        </div>
      </div>
      <!-- ZIP Cpde -->
      <div>
        <FormInput
          type="text"
          id="zipCode"
          placeholder="Type in your ZIP code"
          v-model="form.zipCode"
          label="ZIP Code"
          :value="$v.form.zipCode"
          :isRequired="true"
          :isInvalid="$v.form.zipCode.$error"
          @input="val => setZipCode(val)"
        />
        <div class="text-xs text-red-900 py-1" v-if="$v.form.zipCode.$error">
          <span v-if="!$v.form.zipCode.required">Required field</span>
        </div>
      </div>
      <!-- Terms -->
      <div class="md:col-span-2 border-t border-b border-gray-200 mt-4">
        <div class="flex flex-wrap py-6 content-center justify-between">
          <label for="paymentTerms" class="flex items-center space-x-3 pr-5">
            <input
              type="checkbox"
              id="paymentTerms"
              name="paymentTermsConsent"
              v-model="form.paymentTermsConsent"
              :value="form.paymentTermsConsent"
              @change="setTerms(form.paymentTermsConsent)"
              class="appearance-none cursor-pointer h-5 w-5 shadow rounded-sm border border-gray-300 checked:bg-blue-300 checked:border-blue-600 focus:outline-none"
            />
            <span class="font-medium cursor-pointer"
              >I agree to
              <router-link :to="{ name: 'PaymentTerms' }" target="_blank" class="inline-block text-blue-700 font-medium"
                >Payment terms and conditions
              </router-link>
              <span class="text-red-800">*</span>
            </span>
          </label>
        </div>
      </div>
    </div>
    <div class="grid grid-cols-1 justify-items-end mt-8">
      <Button type="submit" :disabled="disableSubmit" :isLoading="isLoading" @click="onUpdatePaymentDetails">
        Update
      </Button>
    </div>
  </form>
</template>

<script lang="ts">
import Vue from "vue";
import { email, helpers, maxLength, minLength, required, requiredIf, sameAs } from "vuelidate/lib/validators";
import FormInput from "@/components/FormInput.vue";
import Button from "@/components/Button.vue";
import DropDown from "@/components/DropDown.vue";
import {
  Country,
  PaymentDetailsForm,
  UserPaymentDetailsPayload,
  TransferWiseSupportedCountry,
  IbanSupportedCountry,
  TransferWiseBank,
  TransferWiseBranch,
  PaymentMethod
} from "@/models/models";

// Custom Validators
const ibanFormat = helpers.regex(
  "alphanumeric",
  /^([A-Z]{2}[ -]?[0-9]{2})(?=(?:[ -]?[A-Z0-9]){9,30}$)((?:[ -]?[A-Z0-9]{3,5}){2,7})([ -]?[A-Z0-9]{1,3})?$/
);

const ifscCodeFormat = helpers.regex("alphanumeric", /^[A-Za-z]{4}0[A-Z0-9a-z]{6}$/);
const swiftCodeFormat = helpers.regex("alphanumeric", /^([A-Z]{4})([A-Z]{2})([A-Z0-9]{2})([A-Z0-9]{3})?$/);

export default Vue.extend({
  name: "UserPaymentDetailsForm",
  props: {
    paymentDetails: {
      type: Object,
      required: true
    },
    transferWiseSupportedCountries: {
      type: Array,
      required: true
    },
    ibanSupportedCountries: {
      type: Array,
      required: true
    },
    transferWiseBanks: {
      type: Array,
      required: true
    },
    transferWiseBankBranches: {
      type: Array,
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
      paymentOptions: [
        {
          id: "paypal",
          value: "PayPal"
        },
        {
          id: "transferwise",
          value: "TransferWise"
        }
      ] as PaymentMethod[],
      countryOptions: [] as Country[],
      bankOptions: [] as TransferWiseBank[],
      branchOptions: [] as TransferWiseBranch[],
      transferWiseCountries: [] as TransferWiseSupportedCountry[],
      ibanCountries: [] as IbanSupportedCountry[],
      selectedMethod: "" as any,
      selectedCountry: "" as any,
      selectedBank: "" as any,
      selectedBranch: "" as any,
      // default form values
      form: {
        method: null,
        paypalEmail: "",
        legalType: "",
        addressLine: "",
        city: "",
        zipCode: "",
        country: null,
        name: "",
        vatNumber: "",
        vatStatus: "",
        transferwiseType: "",
        iban: "",
        ifscCode: "",
        accountNumber: "",
        bank: null,
        branch: null,
        swift: null,
        paymentTermsConsent: false
      } as PaymentDetailsForm
    };
  },
  created() {
    this.$store.dispatch("baseline/getCountries");
  },
  mounted() {
    if (this.paymentDetails !== null) {
      const {
        method,
        paypalEmail,
        legalType,
        addressLine,
        city,
        zipCode,
        name,
        vatNumber,
        vatStatus,
        iban,
        swift,
        transferwiseType,
        ifscCode,
        accountNumber,
        paymentTermsConsent
      }: UserPaymentDetailsPayload = this.paymentDetails;

      this.bankOptions = this.transferWiseBanks as TransferWiseBank[];
      this.branchOptions = this.transferWiseBankBranches as TransferWiseBranch[];
      this.selectedBank = this.bankOptions.find((bank: TransferWiseBank) => bank.code === this.paymentDetails.bankCode);
      this.selectedBranch = this.branchOptions.find(
        (branch: TransferWiseBranch) => branch.code === this.paymentDetails.branchCode
      );
      this.selectedMethod = this.paymentOptions.find((option: PaymentMethod) => option.value === method);

      // initialize form values
      this.form = {
        paypalEmail,
        legalType,
        addressLine,
        city,
        zipCode,
        name,
        vatNumber,
        vatStatus,
        iban,
        swift,
        transferwiseType,
        ifscCode,
        accountNumber,
        paymentTermsConsent,
        method: this.selectedMethod,
        country: this.selectedCountry,
        bank: this.selectedBank,
        branch: this.selectedBranch
      };

      this.transferWiseCountries = this.transferWiseSupportedCountries as TransferWiseSupportedCountry[];
      this.ibanCountries = this.ibanSupportedCountries as IbanSupportedCountry[];
    }
  },
  methods: {
    setPaymentMethod(method: PaymentMethod): void {
      this.selectedMethod = method;
      this.form = { ...this.form, method };
    },
    setPaypalEmail(paypalEmail: string): void {
      this.form = { ...this.form, paypalEmail };
      this.$v.form.paypalEmail.$touch();
    },
    setAddressLine(addressLine: string): void {
      this.form = { ...this.form, addressLine };
      this.$v.form.addressLine.$touch();
    },
    setCity(city: string): void {
      this.form = { ...this.form, city };
      this.$v.form.city.$touch();
    },
    setZipCode(zipCode: string): void {
      this.form = { ...this.form, zipCode };
      this.$v.form.zipCode.$touch();
    },
    onCountryChange(country: Country): void {
      this.selectedCountry = country;
      this.selectedBank = null;
      // reset fields in order to re-validate
      const resettedFields = {
        iban: null as string,
        swift: null as string,
        ifscCode: null as string,
        accountNumber: null as string,
        vatNumber: null as string,
        bank: null as null,
        branch: null as null
      };

      this.form = { ...this.form, ...resettedFields, country };
    },
    setName(name: string): void {
      this.form = { ...this.form, name };
      this.$v.form.name.$touch();
    },
    setVatNumber(vatNumber: string): void {
      this.form = { ...this.form, vatNumber };
      this.$v.form.vatNumber.$touch();
    },
    setVatStatus(countryCode: string): string {
      return countryCode === "NL" ? "NL" : this.isEuropean ? "EU" : "NON_EU";
    },
    setIban(iban: string): void {
      this.form = { ...this.form, iban };
      this.$v.form.iban.$touch();
    },
    setTransferWiseTransactionType(supportedCountries: TransferWiseSupportedCountry[], countryCode: string): string {
      return (
        supportedCountries?.find((country: TransferWiseSupportedCountry) => country?.countryCode === countryCode)
          ?.type || null
      );
    },
    setIfscCode(ifscCode: string): void {
      this.form = { ...this.form, ifscCode };
      this.$v.form.ifscCode.$touch();
    },
    setAccountNumber(accountNumber: string): void {
      this.form = { ...this.form, accountNumber };
      this.$v.form.accountNumber.$touch();
    },
    setBankCode(bank: TransferWiseBank): void {
      this.selectedBank = bank;
      this.selectedBranch = null;

      // reset fields in order to re-validate
      const resettedFields = {
        iban: null as string,
        swift: null as string,
        ifscCode: null as string,
        accountNumber: null as string,
        vatNumber: null as string,
        branch: null as null
      };
      this.form = { ...this.form, ...resettedFields, bank };
    },
    setBranchCode(branch: TransferWiseBranch): void {
      this.selectedBranch = branch;
      this.form = { ...this.form, branch };
    },
    setSwiftCode(swift: string): void {
      this.form = { ...this.form, swift };
      this.$v.form.swift.$touch();
    },
    setTerms(paymentTermsConsent: boolean): void {
      this.form = { ...this.form, paymentTermsConsent };
      this.$v.form.paymentTermsConsent.$touch();
    },
    onUpdatePaymentDetails(): void {
      // emit the form values to the parent component
      this.$emit("update-payment-details", this.form);
    }
  },
  computed: {
    disableSubmit(): boolean {
      return this.$v.$error || this.$v.$invalid;
    },
    /* Checks whether a country's isEuropeanUnion property is true */
    isEuropean(): boolean {
      const country: Country = this.countryOptions.find((country: Country) => country.code === this.form.country?.code);

      return country?.isEuropeanUnion === "true";
    },
    isPaypalEmailFieldDisplayed(): boolean {
      return this.selectedMethod?.value === "PayPal";
    },
    isVatNumberFieldRequired(): boolean {
      return this.form.legalType === "Business" && this.isEuropean;
    },
    isIbanFieldDisplayed(): boolean {
      return (
        (this.form.transferwiseType === "iban" || this.form.transferwiseType === "turkish_earthport") &&
        this.selectedMethod?.value === "TransferWise"
      );
    },
    isSwiftCodeFieldDisplayed(): boolean {
      return this.form.transferwiseType === "southafrica" && this.selectedMethod?.value === "TransferWise";
    },
    ibanLength(): number {
      return this.ibanCountries?.find(country => country?.countryCode === this.selectedCountry?.code)?.length || null;
    },
    accountNumberMinLength(): number {
      return this.form.transferwiseType === "indian"
        ? 5
        : this.form.transferwiseType === "nepal"
        ? 9
        : this.form.transferwiseType === "srilanka"
        ? 6
        : 7;
    },
    accountNumberMaxLength(): number {
      return this.form.transferwiseType === "indian" || this.form.transferwiseType === "nepal"
        ? 20
        : this.form.transferwiseType === "srilanka"
        ? 15
        : 11;
    },
    isIfscCodeFieldDisplayed(): boolean {
      return this.selectedMethod?.value === "TransferWise" && this.form.transferwiseType === "indian";
    },
    isAccountNumberFieldDisplayed(): boolean {
      return (
        this.selectedMethod?.value === "TransferWise" &&
        (this.form.transferwiseType === "srilanka" ||
          this.form.transferwiseType === "nepal" ||
          this.form.transferwiseType === "indian" ||
          this.form.transferwiseType === "southafrica")
      );
    },
    isBankFieldDisplayed(): boolean {
      return (
        this.selectedMethod?.value === "TransferWise" &&
        (this.form.transferwiseType === "nepal" ||
          this.form.transferwiseType === "srilanka" ||
          this.form.transferwiseType === "southafrica")
      );
    },
    isBankFieldRequired(): boolean {
      return (
        this.selectedMethod?.value === "TransferWise" &&
        (this.form.transferwiseType === "nepal" || this.form.transferwiseType === "srilanka")
      );
    },
    isBranchFieldDisplayed(): boolean {
      return this.selectedMethod?.value === "TransferWise" && this.form.transferwiseType === "srilanka";
    },
    countries(): Country[] {
      return this.$store.getters["baseline/isoCountries"];
    }
  },
  watch: {
    countries(value: Country[]) {
      if (value.length > 0) {
        const transferWiseCountryOptions: Country[] = this.transferWiseCountries?.map(
          (transferWiseCountry: TransferWiseSupportedCountry) => {
            return value.find((country: Country) => country.code === transferWiseCountry.countryCode);
          }
        );

        this.countryOptions = this.selectedMethod?.value === "TransferWise" ? transferWiseCountryOptions : value;

        this.selectedCountry = this.countryOptions.find(
          (country: Country) => country.code === this.paymentDetails.countryCode
        );
        this.form = {
          ...this.form,
          country: this.selectedCountry
        };
      }
    },
    selectedCountry(newVal: Country, oldVal: Country) {
      if (newVal !== oldVal) {
        const transferwiseType: string = this.setTransferWiseTransactionType(
          this.transferWiseCountries,
          this.selectedCountry?.code
        );
        const vatStatus: string = this.setVatStatus(this.form.country?.code);

        this.form = { ...this.form, transferwiseType, vatStatus };

        // emit the new country code value to the PaymentDetails component
        this.$emit("changed-country-code", newVal?.code);
      }
    },
    selectedBank(newVal: TransferWiseBank, oldVal: TransferWiseBank) {
      if (newVal !== oldVal) {
        // emit the new bank code value to the PaymentDetails component
        this.$emit("changed-bank-code", this.selectedCountry?.code, newVal?.code);
      }
    },
    selectedMethod(newVal: PaymentMethod, oldVal: PaymentMethod) {
      if (newVal && newVal?.id !== oldVal?.id) {
        const transferWiseCountryOptions: Country[] = this.transferWiseCountries?.map(
          (transferWiseCountry: TransferWiseSupportedCountry) => {
            return this.countries.find((country: Country) => country.code === transferWiseCountry.countryCode);
          }
        );

        this.form.method = newVal;
        this.countryOptions = newVal.value === "TransferWise" ? transferWiseCountryOptions : this.countries;
      }
    },
    transferWiseBanks(value: TransferWiseBank[]) {
      this.bankOptions = value;
    },
    transferWiseBankBranches(value: TransferWiseBranch[]) {
      this.branchOptions = value;
    }
  },
  // TODO: Move to a separate validation schema file
  validations(this: any) {
    return {
      form: {
        method: {
          required
        },
        paypalEmail: {
          required: requiredIf(() => this.isPaypalEmailFieldDisplayed),
          email
        },
        legalType: {
          required
        },
        addressLine: {
          required
        },
        city: {
          required
        },
        zipCode: {
          required
        },
        country: {
          required
        },
        name: {
          required
        },
        vatNumber: {
          required: requiredIf(() => this.isVatNumberFieldRequired)
        },
        iban: {
          required: requiredIf(() => this.isIbanFieldDisplayed),
          minLength: minLength(this.ibanLength),
          maxLength: maxLength(this.ibanLength),
          ibanFormat
        },
        swift: {
          required: requiredIf(() => this.isSwiftCodeFieldDisplayed),
          minLength: minLength(8),
          maxLength: maxLength(11),
          swiftCodeFormat
        },
        ifscCode: {
          required: requiredIf(() => this.isIfscCodeFieldDisplayed),
          ifscCodeFormat
        },
        accountNumber: {
          required: requiredIf(() => this.isAccountNumberFieldDisplayed),
          minLength: minLength(this.accountNumberMinLength),
          maxLength: maxLength(this.accountNumberMaxLength)
        },
        bank: {
          required: requiredIf(() => this.isBankFieldRequired)
        },
        branch: {
          required: requiredIf(() => this.isBranchFieldDisplayed)
        },
        paymentTermsConsent: {
          sameAs: sameAs(() => true)
        }
      }
    };
  },
  components: {
    FormInput,
    Button,
    DropDown
  }
});
</script>
