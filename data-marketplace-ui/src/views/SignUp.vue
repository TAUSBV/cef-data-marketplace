<template>
  <div class="pt-24 pb-32">
    <div class="grid grid-cols-3 items-center xs:gap-2 sm:gap-4 md:gap-8 lg:gap-8 grid-flow-row mb-8">
      <div class="xs:col-span-3 sm:col-span-1 md:col-span-1 lg:col-span-1 text-lg xs:text-center">
        SIGN UP
      </div>
      <div
        class="xs:col-span-3 sm:col-span-2 md:col-span-2 lg:col-span-2 text-base text-gray-900 xs:text-center sm:text-right md:text-right lg:text-right"
      >
        Do you have an account?
        <a @click="toggleAuthentication" class="text-blue-700 cursor-pointer">Sign in</a>
      </div>
    </div>
    <form novalidate>
      <div class="grid xs:grid-cols-5 sm:grid-cols-5 md:grid-cols-3 lg:grid-cols-3 gap-4 grid-flow-row">
        <div class="xs:col-span-2 sm:col-span-2 md:col-span-1 lg:col-span-1">
          <DropDown
            id="prefix"
            label="Prefix"
            placeholder="Prefix"
            :isRequired="true"
            :is-invalid="$v.form.prefix.$error"
            :options="prefixOptions"
            trackByKey="text"
            :value="prefixSelected"
            @selected="val => selectPrefix(val)"
            @closed="() => $v.form.prefix.$touch()"
          />
        </div>
        <div class="xs:col-span-3 sm:col-span-3 md:col-span-2 lg:col-span-2">
          <FormInput
            type="text"
            id="firstName"
            v-model="form.firstName"
            label="First name"
            :isRequired="true"
            :isInvalid="$v.form.firstName.$error || (this.serviceErrors && this.serviceErrors.firstName)"
            @input="val => updateFirstName(val)"
          />
          <div class="text-xs text-red-900">
            <div v-if="$v.form.firstName.$error">Required field</div>
            <div v-if="this.serviceErrors">
              <div v-for="(serviceError, index) in this.serviceErrors.firstName" :key="`errorFirstName-${index}`">
                {{ serviceError }}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div>
        <FormInput
          type="text"
          id="lastName"
          class="my-2"
          v-model="form.lastName"
          label="Last name"
          :isRequired="true"
          :isInvalid="$v.form.lastName.$error || (this.serviceErrors && this.serviceErrors.lastName)"
          @input="val => updateLastName(val)"
        />
        <div class="text-xs text-red-900">
          <div v-if="$v.form.lastName.$error">Required field</div>
          <div v-if="this.serviceErrors">
            <div v-for="(serviceError, index) in this.serviceErrors.lastName" :key="`errorLastName-${index}`">
              {{ serviceError }}
            </div>
          </div>
        </div>
      </div>
      <div>
        <FormInput
          type="email"
          id="email"
          class="my-2"
          v-model="form.email"
          label="Email"
          :isRequired="true"
          :isInvalid="$v.form.email.$error || (this.serviceErrors && this.serviceErrors.email)"
          @input="val => updateEmail(val)"
        />
        <div class="text-xs text-red-900" v-if="$v.form.email.$error || this.serviceErrors">
          <div v-if="!$v.form.email.required">Required field</div>
          <div v-if="!$v.form.email.email">
            Email is not a properly formatted email address
          </div>
          <div v-if="this.serviceErrors">
            <div v-for="(serviceError, index) in this.serviceErrors.email" :key="`errorEmail-${index}`">
              {{ serviceError }}
            </div>
          </div>
        </div>
      </div>
      <div>
        <FormInput
          type="password"
          id="password"
          class="my-2"
          v-model="form.password"
          label="Password"
          :isRequired="true"
          :isInvalid="$v.form.password.$error || (this.serviceErrors && this.serviceErrors.password)"
          @input="val => updatePassword(val)"
        />
        <div class="text-xs text-red-900" v-if="$v.form.password.$error || this.serviceErrors">
          <div v-if="!$v.form.password.required">Required field</div>
          <div v-if="this.serviceErrors">
            <div v-for="(serviceError, index) in this.serviceErrors.password" :key="`errorPassword-${index}`">
              {{ serviceError }}
            </div>
          </div>
        </div>
      </div>
      <div>
        <FormInput
          type="password"
          id="matchingPassword"
          class="my-2"
          v-model="form.matchingPassword"
          label="Retype password"
          :isRequired="true"
          :isInvalid="$v.form.matchingPassword.$error"
          @input="val => updateMatchingPassword(val)"
        />
        <div class="text-xs text-red-900" v-if="$v.form.matchingPassword.$error">
          <div v-if="!$v.form.matchingPassword.required">Required field</div>
          <div v-if="!$v.form.matchingPassword.sameAs">
            Passwords should match
          </div>
        </div>
      </div>
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
      <FormCheckbox
        id="termsUseConsent"
        label="I agree to the"
        linkText="Terms of Use"
        linkUrl="/tou"
        :required="true"
        v-model="form.termsOfUseConsent"
        @change="val => updateAgreeTerms(val)"
        class="my-4"
      />
      <FormCheckbox
        id="privacyPolicyConsent"
        label="I agree to the"
        linkText="Privacy Policy"
        linkUrl="https://www.taus.net/privacy-policy"
        :required="true"
        v-model="form.privacyPolicyConsent"
        @change="val => updateAgreePrivacy(val)"
        class="my-4"
      />
      <Alert v-if="genericErrors.length > 0" :messages="genericErrors" type="DANGER" />
      <div class="flex justify-center mt-8">
        <Button type="button" :disabled="disableSignUp" :isLoading="isLoading" @click="signUp()">
          Sign up
        </Button>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import { email, required, sameAs } from "vuelidate/lib/validators";

import Button from "@/components/Button.vue";
import DropDown from "@/components/DropDown.vue";
import FormCheckbox from "@/components/FormCheckbox.vue";
import FormInput from "@/components/FormInput.vue";
import Alert from "@/components/Alert.vue";
import { Country } from "@/models/models";

export default Vue.extend({
  name: "SignUp",
  props: {
    isLoading: {
      type: Boolean,
      required: false,
      default: false
    },
    serviceErrors: {
      type: Object
    }
  },
  data() {
    return {
      form: {
        prefix: "",
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        matchingPassword: "",
        country: "",
        termsOfUseConsent: false,
        privacyPolicyConsent: false
      },
      countries: [],
      countrySelected: "" as any,
      prefix: ["Mr.", "Ms."],
      prefixSelected: "" as any,
      genericErrors: []
    };
  },
  validations: {
    form: {
      prefix: {
        required
      },
      firstName: {
        required
      },
      lastName: {
        required
      },
      email: {
        required,
        email
      },
      password: {
        required
      },
      matchingPassword: {
        required,
        sameAs: sameAs("password")
      },
      country: {
        required
      },
      termsOfUseConsent: {
        required
      },
      privacyPolicyConsent: {
        required
      }
    }
  },
  computed: {
    disableSignUp(): boolean {
      return this.$v.$error || this.$v.$invalid || !this.form.termsOfUseConsent || !this.form.privacyPolicyConsent;
    },
    prefixOptions(): Array<{ value: string; text: string }> {
      return this.prefix.map((i, index) => ({
        value: index.toString(),
        text: i
      }));
    },
    countryOptions(): Country[] {
      return this.$store.getters["baseline/countries"];
    }
  },
  created() {
    this.$store.dispatch("baseline/getCountries");
  },
  methods: {
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
    updatePassword(value: string) {
      this.form.password = value;
      this.$v.form.password.$touch();
    },
    updateMatchingPassword(value: string) {
      this.form.matchingPassword = value;
      this.$v.form.matchingPassword.$touch();
    },
    updateAgreeTerms(value: boolean) {
      this.form.termsOfUseConsent = value;
      this.$v.form.termsOfUseConsent.$touch();
    },
    updateAgreePrivacy(value: boolean) {
      this.form.privacyPolicyConsent = value;
      this.$v.form.privacyPolicyConsent.$touch();
    },
    selectPrefix(prefix: { value: string; text: string }) {
      this.form.prefix = prefix.text;
      this.prefixSelected = prefix;
    },
    selectCountry(country: Country) {
      this.form.country = country.name;
      this.countrySelected = country;
    },
    signUp() {
      this.$emit("onSignUp", this.form);
    },
    toggleAuthentication() {
      this.$emit("toggleAuthentication");
    }
  },
  watch: {
    serviceErrors() {
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
    }
  },
  components: {
    Alert,
    Button,
    DropDown,
    FormCheckbox,
    FormInput
  }
});
</script>
