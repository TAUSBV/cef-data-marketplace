<template>
  <div class="w-full grid grid-cols-3 xs:gap-x-24 sm:gap-x-24 md:gap-x-24 lg:gap-x-10 xl:gap-x-24">
    <form class="col-span-2 xs:col-span-3 sm:col-span-3 md:col-span-3 lg:col-span-2 grid grid-cols-1 gap-4 w-full">
      <div class="">
        <p>
          Entity type
        </p>
      </div>
      <div class="grid grid-cols-2 mb-2">
        <div class="grid grid-cols-2">
          <div>
            <label for="private" class="flex items-center space-x-3 pr-5">
              <input
                type="radio"
                id="private"
                value="PRIVATE"
                v-model="form.type"
                class="appearance-none cursor-pointer h-5 w-5 shadow rounded-full border
            border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
              />
              <span class="cursor-pointer">Private</span>
            </label>
          </div>
          <div>
            <label for="business" class="flex items-center space-x-3 pr-5">
              <input
                type="radio"
                id="business"
                value="BUSINESS"
                v-model="form.type"
                class="appearance-none cursor-pointer h-5 w-5 shadow rounded-full border
            border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
              />
              <span class="cursor-pointer">Business</span>
            </label>
          </div>
        </div>
      </div>

      <div class="grid grid-cols-2 gap-8 mb-2 ">
        <div class="grid grid-cols-1 items-center justify-center">
          <FormInput
            type="text"
            id="vat"
            class="mb-2"
            v-model.trim="form.vat"
            label="VAT"
            @blur="touchVAT"
            :isInvalid="
              (form.type === 'BUSINESS' && form.vat === '' && vatTouched) ||
                !$v.form.vat.minLength ||
                (this.serviceErrors && this.serviceErrors.vat)
            "
            :isRequired="form.type === 'BUSINESS'"
            @input="val => updateVAT(val)"
          />
          <div
            class="text-xs text-red-900 flex items-center h-0"
            v-if="form.type === 'BUSINESS' && form.vat === '' && vatTouched"
          >
            Required field
          </div>
          <div class="text-xs text-red-900 flex items-center h-0" v-if="!$v.form.vat.minLength">
            VAT number cannot be less than 6 characters
          </div>
          <div class="text-xs text-red-900 flex items-center h-0" v-if="this.serviceErrors">
            <div v-for="(serviceError, index) in this.serviceErrors.vat" :key="`errorVat-${index}`">
              {{ serviceError }}
            </div>
          </div>
        </div>

        <div class="grid grid-cols-1 items-center justify-center">
          <FormInput
            type="text"
            id="company"
            class="mb-2"
            v-model.trim="form.company"
            label="Company name"
            @blur="touchCompany"
            :isInvalid="
              (form.type === 'BUSINESS' && form.company === '' && companyTouched) ||
                (this.serviceErrors && this.serviceErrors.company)
            "
            :isRequired="form.type === 'BUSINESS'"
            @input="val => updateCompany(val)"
          />
          <div
            class="text-xs text-red-900 flex items-center h-0"
            v-if="form.type === 'BUSINESS' && form.company === '' && companyTouched"
          >
            Required field
          </div>
        </div>
      </div>

      <div class="grid grid-cols-2 gap-8 mb-2 ">
        <div class="grid grid-cols-1 items-center justify-center">
          <FormInput
            type="text"
            id="firstName"
            class="mb-2"
            v-model="form.firstName"
            label="First Name"
            :isRequired="true"
            :isInvalid="$v.form.firstName.$error || (this.serviceErrors && this.serviceErrors.firstName)"
            @input="val => updateFirstName(val)"
          />
          <div class="text-xs text-red-900 flex items-center h-0">
            <div v-if="$v.form.firstName.$error">
              Required field
            </div>
            <div v-if="this.serviceErrors">
              <div v-for="(serviceError, index) in this.serviceErrors.firstName" :key="`errorFirstName-${index}`">
                {{ serviceError }}
              </div>
            </div>
          </div>
        </div>

        <div class="grid grid-cols-1 items-center">
          <FormInput
            type="text"
            id="lastName"
            class="mb-2"
            v-model="form.lastName"
            label="Last Name"
            :isRequired="true"
            :isInvalid="$v.form.lastName.$error || (this.serviceErrors && this.serviceErrors.lastName)"
            @input="val => updateLastName(val)"
          />
          <div class="text-xs text-red-900 flex items-center h-0">
            <div v-if="$v.form.lastName.$error">
              Required field
            </div>
            <div v-if="this.serviceErrors">
              <div v-for="(serviceError, index) in this.serviceErrors.lastName" :key="`errorLastName-${index}`">
                {{ serviceError }}
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="grid grid-cols-2 gap-8 mb-2">
        <div class="grid grid-cols-1 items-center justify-center">
          <FormInput
            type="email"
            id="email"
            class="mb-2"
            v-model="form.email"
            label="Email address"
            :isRequired="true"
            :isInvalid="$v.form.email.$error || (this.serviceErrors && this.serviceErrors.email)"
            @input="val => updateEmail(val)"
          />
          <div class="text-xs text-red-900 flex items-center h-0" v-if="$v.form.email.$dirty && !$v.form.email.email">
            Email is not a properly formatted email address
          </div>
          <div class="text-xs text-red-900 flex items-center h-0">
            <div v-if="$v.form.email.$dirty && !$v.form.email.required">
              Required field
            </div>
            <div v-for="(serviceError, index) in this.serviceErrors.email" :key="`errorEmail-${index}`">
              {{ serviceError }}
            </div>
          </div>
        </div>
      </div>

      <div class="grid grid-cols-1 items-center justify-center mb-2">
        <FormInput
          type="text"
          id="address"
          class="mb-2"
          v-model="form.address"
          label="Address"
          :isRequired="true"
          :isInvalid="$v.form.address.$error || (this.serviceErrors && this.serviceErrors.address)"
          @input="val => updateAddress(val)"
        />
        <div class="text-xs text-red-900 flex items-center h-0">
          <div v-if="$v.form.address.$error">
            Required field
          </div>
          <div v-for="(serviceError, index) in this.serviceErrors.address" :key="`errorAddress-${index}`">
            {{ serviceError }}
          </div>
        </div>
      </div>

      <div class="grid grid-cols-2 gap-8 mb-2">
        <div class="grid grid-cols-1 items-center justify-center">
          <FormInput
            type="text"
            id="city"
            class="mb-2"
            v-model="form.city"
            label="City"
            :isRequired="true"
            :isInvalid="$v.form.city.$error || (this.serviceErrors && this.serviceErrors.city)"
            @input="val => updateCity(val)"
          />
          <div class="text-xs text-red-900 flex items-center h-0">
            <div v-if="$v.form.city.$error">
              Required field
            </div>
            <div v-for="(serviceError, index) in this.serviceErrors.city" :key="`errorCity-${index}`">
              {{ serviceError }}
            </div>
          </div>
        </div>

        <div class="grid grid-cols-1 items-center justify-center">
          <FormInput
            type="text"
            id="state"
            class="mb-2"
            v-model="form.state"
            label="State/Province"
            @input="val => updateState(val)"
          />
        </div>
      </div>

      <div class="grid grid-cols-2 gap-8">
        <div class="grid grid-cols-1 items-center justify-center">
          <FormInput
            type="text"
            id="postalCode"
            class="mb-2"
            v-model="form.postalCode"
            label="Postal code"
            :isRequired="true"
            :isInvalid="$v.form.postalCode.$error || (this.serviceErrors && this.serviceErrors.postalCode)"
            @input="val => updatePostalCode(val)"
          />
          <div class="text-xs text-red-900 flex items-center h-0">
            <div v-if="$v.form.postalCode.$error">
              Required field
            </div>
            <div v-for="(serviceError, index) in this.serviceErrors.postalCode" :key="`errorPostalCode-${index}`">
              {{ serviceError }}
            </div>
          </div>
        </div>

        <div class="grid grid-cols-1 items-center justify-center">
          <DropDown
            id="country"
            label="Country"
            placeholder="Select or type in your country"
            :is-required="true"
            :is-invalid="$v.form.country.$error"
            :options="countryOptions"
            trackByKey="name"
            :value="countrySelected"
            @selected="val => selectCountry(val)"
            @closed="() => $v.form.country.$touch()"
          />
        </div>
      </div>

      <div class="grid grid-cols-1">
        <div class="mb-5">
          <FormCheckbox
            id="termsOfUseConsent"
            :required="true"
            label="I agree to the"
            linkText="Terms of Use"
            linkUrl="/tou"
            v-model="form.termsOfUseConsent"
          />
        </div>
        <div>
          <FormCheckbox
            id="privacyPolicyConsent"
            :required="true"
            label="I agree to the"
            linkText="Privacy Policy"
            linkUrl="https://www.taus.net/privacy-policy"
            v-model="form.privacyPolicyConsent"
          />
        </div>
      </div>

      <Alert v-if="genericErrors.length > 0" :messages="genericErrors" type="DANGER" />
    </form>

    <div
      class="col-span-1 xs:col-span-3 sm:col-span-3 md:col-span-3 lg:col-span-1 xs:mt-24 sm:mt-24 md:mt-24 lg:mt-24 xl:mt-24"
    >
      <PriceBox :price="this.price" :serviceFee="serviceFee" />
    </div>

    <div
      class="col-span-2 xs:col-span-3 sm:col-span-3 md:col-span-3 lg:col-span-2 grid grid-cols-2 items-center w-full mt-24"
    >
      <div>
        <router-link to="/sellers" class="float-left text-base text-blue-700 font-semibold cursor-pointer leading-none">
          <img src="/assets/img/chevron-right-blue.svg" alt="Back" class="inline pl-1 transform rotate-180" />
          Continue shopping
        </router-link>
      </div>
      <div>
        <Button
          type="button"
          class="float-right"
          :disabled="disableContinue"
          :isLoading="isLoading"
          @click="submitForm()"
        >
          Continue
        </Button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import Button from "@/components/Button.vue";
import FormInput from "@/components/FormInput.vue";
import DropDown from "@/components/DropDown.vue";
import FormCheckbox from "@/components/FormCheckbox.vue";
import PriceBox from "@/components/checkout/PriceBox.vue";
import Alert from "@/components/Alert.vue";
import { email, minLength, required, requiredIf } from "vuelidate/lib/validators";
import { Buyer, Country } from "@/models/models";

export default Vue.extend({
  name: "CheckoutForm",
  components: {
    Alert,
    Button,
    DropDown,
    FormInput,
    FormCheckbox,
    PriceBox
  },
  props: {
    buyer: {
      type: Object as PropType<Buyer>
    },
    price: {
      type: Number
    },
    serviceFee: {
      type: Number
    }
  },
  data() {
    return {
      vatTouched: false,
      companyTouched: false,
      form: {
        type: this.buyer ? this.buyer.entityType : "PRIVATE",
        firstName: this.buyer ? this.buyer.firstName : "",
        lastName: this.buyer ? this.buyer.lastName : "",
        email: this.buyer ? this.buyer.email : "",
        company: this.buyer ? this.buyer.company : "",
        address: this.buyer ? this.buyer.address : "",
        city: this.buyer ? this.buyer.address : "",
        state: this.buyer ? this.buyer.state : "",
        postalCode: this.buyer ? this.buyer.postalCode : "",
        country: this.buyer ? this.buyer.country : "",
        vat: this.buyer ? this.buyer.vat : "",
        privacyPolicyConsent: this.buyer ? this.buyer.privacyPolicyConsent : false,
        termsOfUseConsent: this.buyer ? this.buyer.termsOfUseConsent : false
      },
      countrySelected: this.buyer ? { code: this.buyer.country, name: this.buyer.country } : "",
      isLoading: false,
      serviceErrors: {} as Record<string, string>,
      genericErrors: []
    };
  },
  validations() {
    return {
      form: {
        type: {
          required
        },
        company: {
          required: requiredIf(function(form) {
            return form.type === "BUSINESS";
          })
        },
        vat: {
          required: requiredIf(function(form) {
            return form.type === "BUSINESS";
          }),
          minLength: minLength(6)
        },
        firstName: {
          required
        },
        lastName: {
          required
        },
        address: {
          required
        },
        city: {
          required
        },
        postalCode: {
          required
        },
        country: {
          required
        },
        email: {
          required,
          email
        },
        privacyPolicyConsent: {
          required
        },
        termsOfUseConsent: {
          required
        }
      }
    };
  },
  methods: {
    updateType(value: string) {
      this.form.type = value;
      this.$v.form.type.$touch();
    },
    updateCompany(value: string) {
      this.form.company = value;
      this.$v.form.company.$touch();
    },
    updateVAT(value: string) {
      this.form.vat = value;
      this.$v.form.vat.$touch();
    },
    updateFirstName(value: string) {
      this.form.firstName = value;
      this.$v.form.firstName.$touch();
    },
    updateLastName(value: string) {
      this.form.lastName = value;
      this.$v.form.lastName.$touch();
    },
    updateEmail(value: string) {
      this.form.email = value;
      this.$v.form.email.$touch();
    },
    updateAddress(value: string) {
      this.form.address = value;
      this.$v.form.address.$touch();
    },
    updateCity(value: string) {
      this.form.city = value;
      this.$v.form.city.$touch();
    },
    updateState(value: string) {
      this.form.state = value;
    },
    updatePostalCode(value: string) {
      this.form.postalCode = value;
      this.$v.form.postalCode.$touch();
    },
    selectCountry(country: Country) {
      this.form.country = country.name;
      this.countrySelected = country;
    },
    touchVAT(): void {
      this.vatTouched = true;
    },
    touchCompany(): void {
      this.companyTouched = true;
    },
    submitForm() {
      this.isLoading = true;
      this.genericErrors = [];

      const formData = new URLSearchParams();

      formData.append("type", this.form.type);
      if (this.form.vat !== "" && this.form.vat !== null) {
        formData.append("vat", this.form.vat);
      }
      if (this.form.company !== "" && this.form.company !== null) {
        formData.append("company", this.form.company);
      }
      formData.append("firstName", this.form.firstName);
      formData.append("lastName", this.form.lastName);
      formData.append("email", this.form.email);
      formData.append("address", this.form.address);
      formData.append("city", this.form.city);
      formData.append("state", this.form.state);
      formData.append("postalCode", this.form.postalCode);
      formData.append("country", this.form.country);
      formData.append("privacyPolicyConsent", this.form.privacyPolicyConsent.toString());
      formData.append("termsOfUseConsent", this.form.termsOfUseConsent.toString());

      this.$store
        .dispatch("checkout/buyer", formData)
        .then(response => {
          this.$emit("onSuccess", response);
        })
        .catch(error => {
          window.scroll(0, 0);
          this.serviceErrors = error.response.data;
          this.genericErrors = [];
          if (typeof this.serviceErrors === "string") {
            this.genericErrors.push(this.serviceErrors);
          } else {
            Object.entries(this.serviceErrors).forEach(([serviceKey]) => {
              if (!Object.prototype.hasOwnProperty.call(this.form, serviceKey)) {
                this.genericErrors.push(this.serviceErrors[serviceKey]);
              }
            });
          }
        })
        .finally(() => {
          this.isLoading = false;
        });
    }
  },
  computed: {
    disableContinue(): boolean {
      return this.$v.$error || this.$v.$invalid || !this.form.privacyPolicyConsent || !this.form.termsOfUseConsent;
    },
    countryOptions(): Country[] {
      return this.$store.getters["baseline/isoCountries"];
    }
  },
  created() {
    this.$store.dispatch("baseline/getCountries");
  }
});
</script>

<style scoped></style>
