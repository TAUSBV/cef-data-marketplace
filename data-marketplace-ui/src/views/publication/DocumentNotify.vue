<template>
  <div>
    <div class="mb-4 text-center">
      The analysis of the submitted file can take few minutes. Please fill in your email address and we will notify you
      as soon as the analysis is completed.
    </div>
    <form novalidate class="mb-4" v-if="!isNotified">
      <section class="flex justify-center w-2/3 m-auto">
        <div class="mr-4 w-2/3">
          <FormInput
            type="email"
            placeholder="Email"
            v-model="form.email"
            @input="val => updateEmail(val)"
            :customClass="{ 'border border-red-900': $v.form.email.$error }"
          />
          <div class="text-xs text-red-900" v-if="$v.form.email.$error">
            <div v-if="!$v.form.email.required">Required field</div>
            <div v-if="!$v.form.email.email">
              Email is not a properly formatted email address
            </div>
          </div>
        </div>
        <Button type="button" :disabled="disable" :isLoading="isLoading" @click="setNotifyEmail()">
          Send
        </Button>
      </section>
    </form>
    <Alert
      v-if="isNotified"
      :messages="[`Thanks! We will send a notification to ${form.email} when the file analysis is completed.`]"
      type="INFO"
    />
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import { email, required } from "vuelidate/lib/validators";
import Button from "@/components/Button.vue";
import FormInput from "@/components/FormInput.vue";
import Alert from "@/components/Alert.vue";

export default Vue.extend({
  props: {
    documentId: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      form: {
        email: ""
      },
      isNotified: false,
      isLoading: false
    };
  },
  validations: {
    form: {
      email: {
        required,
        email
      }
    }
  },
  computed: {
    disable(): boolean {
      return this.$v.$error || this.$v.$invalid;
    }
  },
  methods: {
    updateEmail(value: string) {
      this.form.email = value;
      this.$v.form.email.$touch();
    },
    setNotifyEmail() {
      this.isLoading = true;
      this.$store
        .dispatch("document/setPublicationNotify", {
          uid: this.documentId,
          email: this.form.email
        })
        .then(() => {
          this.isNotified = true;
        })
        .finally(() => {
          this.isLoading = false;
        });
    }
  },
  components: {
    Button,
    FormInput,
    Alert
  }
});
</script>
