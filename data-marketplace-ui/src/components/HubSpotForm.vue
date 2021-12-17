<template>
  <div v-if="isFormLoading">
    <HubSpotFormLoading />
  </div>
  <div v-else>
    <Alert v-if="errorMessages.length > 0" :messages="errorMessages" type="DANGER" />
    <Alert v-if="successMessages.length > 0" :messages="successMessages" type="SUCCESS" />

    <form novalidate id="hs-form" v-if="!isFormHidden">
      <div
        class="grid grid-cols-1 gap-4 mt-4"
        :class="[group.fields.length === 1 ? 'md:grid-cols-1' : 'md:grid-cols-2']"
        v-for="(group, index) in groupFields"
        :key="index"
      >
        <div v-for="field in group.fields" :key="field.name">
          <div v-if="field.fieldType === 'text'">
            <FormInput
              custom-class="border border-gray-300 shadow-none"
              :type="field.fieldType"
              :id="field.name"
              :placeholder="field.placeholder || field.label"
              v-model="form[field.name]"
              :value="$v.form[field.name]"
              :isRequired="field.required"
              :is-invalid="$v.form[field.name].$error || (isEmailInvalid && field.name.toLowerCase() === 'email')"
              @input="val => setFieldValue(val, field.name)"
            />
            <span class="text-xs text-red-900 py-1" v-if="$v.form[field.name].$error">
              <span v-if="!$v.form[field.name].required">Required field</span>
            </span>
            <span class="text-xs text-red-900 py-1" v-if="isEmailInvalid && field.name.toLowerCase() === 'email'">
              <span>Please provide a valid email address</span>
            </span>
          </div>
          <div v-if="field.fieldType === 'select'">
            <DropDown
              :id="field.name"
              :placeholder="field.placeholder || `${field.label}`"
              :isRequired="field.required"
              :is-invalid="$v.form[field.name].$error"
              :options="field.options"
              trackByKey="label"
              :value="field.selectedOptions"
              @selected="val => setSelectedOptions(val, field.name)"
              @closed="() => $v.form[field.name].$touch()"
            />
          </div>
          <div v-if="field.fieldType === 'textarea'">
            <FormTextarea
              custom-class="border border-gray-300 shadow-none"
              :type="field.fieldType"
              :id="field.name"
              :placeholder="field.placeholder || field.label"
              v-model="form[field.name]"
              :value="$v.form[field.name]"
              :isRequired="field.required"
              :is-invalid="$v.form[field.name].$error"
              @input="val => setFieldValue(val, field.name)"
            />
          </div>
          <div v-if="field.fieldType === 'booleancheckbox'">
            <FormCheckbox
              custom-class="shadow-none"
              :id="field.name"
              :label="termsLabel || field.label"
              :linkText="termsLinkText || 'Privacy & Cookies Policy'"
              linkUrl="https://www.taus.net/privacy-policy"
              :required="field.required"
              :is-invalid="$v.form[field.name].$error"
              @change="val => setCheckboxValue(val, field.name)"
            />
            <span class="text-xs text-red-900 py-1" v-if="$v.form[field.name].$error">
              <span v-if="!$v.form[field.name].sameAs">Required field</span>
            </span>
          </div>
        </div>
      </div>
      <div class="grid grid-cols-1 justify-items-center mt-8">
        <Button type="submit" :isLoading="isSubmitting" :disabled="disableSubmit" @click="submitForm">
          {{ buttonText }}
        </Button>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import FormInput from "@/components/FormInput.vue";
import Button from "@/components/Button.vue";
import FormCheckbox from "@/components/FormCheckbox.vue";
import DropDown from "@/components/DropDown.vue";
import FormTextarea from "@/components/FormTextarea.vue";
import Alert from "@/components/Alert.vue";
import HubSpotFormLoading from "@/components/HubSpotFormLoading.vue";
import {
  HSError,
  HSErrorResponse,
  HSFormData,
  HSFormDataPayload,
  HSFormField,
  HSFormFieldGroup,
  HSFormSelectOption,
  HSSuccessResponse
} from "@/models/models";
import { requiredIf, sameAs } from "vuelidate/lib/validators";

export default Vue.extend({
  name: "HubSpotForm",
  data() {
    return {
      isFormLoading: true,
      isSubmitting: false,
      isFormHidden: false,
      fields: [] as HSFormField[],
      groupFields: [] as HSFormFieldGroup[],
      buttonText: "",
      form: {} as { [key: string]: string | boolean },
      hsErrors: {} as HSErrorResponse,
      errorMessages: [],
      successMessages: [],
      isEmailInvalid: false
    };
  },
  mounted() {
    // clear alerts
    this.errorMessages = [];
    this.successMessages = [];

    this.setUpForm();
  },
  computed: {
    disableSubmit(): boolean {
      return this.$v.$error || this.$v.$invalid || this.isEmailInvalid;
    },
    convertedTermsLabel(): string {
      return this.fields.find(field => field.label.toUpperCase().includes("PRIVACY")).label.replace(/<[^>]+>/g, "");
    },
    termsLabel(): string {
      const index = this.convertedTermsLabel.toUpperCase().indexOf("PRIVACY");
      return this.convertedTermsLabel.substring(0, index) || null;
    },
    termsLinkText(): string {
      const index = this.convertedTermsLabel.toUpperCase().indexOf("PRIVACY");

      // create a temporary HTML Textarea element to get the value
      const tempEl = document.createElement("textarea");
      tempEl.innerHTML = this.convertedTermsLabel?.substring(index);
      return tempEl.value || null;
    }
  },
  methods: {
    getHBForm(): Promise<HSFormData> {
      return this.$store.dispatch("hubspot/getForm");
    },
    async setUpForm() {
      try {
        const hbForm = await this.getHBForm();

        const predefinedLangPairFieldValue = this.$route.query.q;

        this.groupFields = hbForm.formFieldGroups.map(groups => groups);
        this.fields = hbForm.formFieldGroups.flatMap((group: HSFormFieldGroup) => group.fields);

        const fieldNames: Array<string[]> = this.fields.map((field: HSFormField) => {
          if (predefinedLangPairFieldValue && field.label.toUpperCase().includes("LANGUAGE PAIR")) {
            field.defaultValue = `${predefinedLangPairFieldValue}`;
          }
          return [field.name, field.defaultValue];
        });

        this.form = Object.fromEntries(fieldNames);
        this.buttonText = hbForm.submitText;
      } catch (error) {
        this.errorMessages.push("There was an issue fetching the form. Please reload your page and try again.");
        this.isFormHidden = true;
      } finally {
        this.isFormLoading = false;
      }
    },
    setFieldValue(value: string, fieldName: string): void {
      // reset email field
      this.isEmailInvalid = false;

      this.form[fieldName] = value;
      this.$v.form[fieldName].$touch();
    },
    setCheckboxValue(value: boolean, fieldName: string): void {
      this.form[fieldName] = value;
      this.$v.form[fieldName].$touch();
    },
    setSelectedOptions(selectedOption: HSFormSelectOption, fieldName: string): void {
      this.form[fieldName] = selectedOption.value;
    },
    async submitForm() {
      this.isSubmitting = true;
      // reset errors if any
      this.successMessages = [];
      this.errorMessages = [];

      const hsFormData: HSFormDataPayload = { fields: [], context: null };

      for (const [key, value] of Object.entries(this.form)) {
        const fieldValues = { name: `${key}`, value: `${value}` };
        const contextData = {
          // hutk: "",
          pageUri: this.$route.fullPath,
          pageName: this.$route.name
        };
        hsFormData.fields.push(fieldValues);
        hsFormData.context = contextData;
      }

      try {
        const successfulResponse: HSSuccessResponse = await this.$store.dispatch("hubspot/submitForm", hsFormData);
        this.successMessages.push(successfulResponse.inlineMessage);
        this.isFormHidden = true;
      } catch (error) {
        this.hsErrors = error.response?.data;
        this.isEmailInvalid = !!this.hsErrors?.errors.find((error: HSError) => error.errorType === "INVALID_EMAIL");

        if (this.isEmailInvalid) {
          this.errorMessages.push("Invalid email address.");
        } else {
          this.errorMessages.push("There was an issue submitting the form. Please try again.");
        }
      } finally {
        window.scroll(0, 0);
        this.isSubmitting = false;
      }
    }
  },
  validations(this: any) {
    const fieldRules = this.fields.reduce((prevField: HSFormField, currentField: HSFormField) => {
      if (currentField.fieldType === "booleancheckbox") {
        return {
          ...prevField,
          [currentField.name]: {
            sameAs: sameAs(() => true)
          }
        };
      } else if (
        currentField.fieldType === "text" ||
        currentField.fieldType === "select" ||
        currentField.fieldType === "textarea"
      ) {
        return {
          ...prevField,
          [currentField.name]: {
            required: requiredIf(() => currentField.required)
          }
        };
      }
    }, {});

    return {
      form: fieldRules
    };
  },
  components: {
    Alert,
    FormInput,
    FormCheckbox,
    FormTextarea,
    DropDown,
    Button,
    HubSpotFormLoading
  }
});
</script>

<style lang="scss">
#hs-form {
  textarea,
  .multiselect {
    @apply rounded;
  }

  input {
    &:not([type="checkbox"]) {
      height: 3.5rem;
      @apply rounded;
    }
  }

  input[type="checkbox"] {
    @apply rounded-sm;
  }

  .multiselect,
  .multiselect-input {
    height: 3.5rem;

    .multiselect-single-label,
    .multiselect-placeholder {
      line-height: 3.5rem;
    }
  }
}
</style>
