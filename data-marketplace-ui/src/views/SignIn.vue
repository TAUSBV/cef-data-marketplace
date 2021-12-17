<template>
  <div class="pb-32 pt-24">
    <div class="grid grid-cols-1 md:grid-cols-2 gap-8 grid-flow-row mb-8">
      <div class="text-lg">SIGN IN</div>
      <div class="text-base text-gray-900 text-right">
        Don't have an account?
        <a @click="toggleAuthentication" class="text-blue-700 cursor-pointer">Sign up</a>
      </div>
    </div>
    <form novalidate>
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4 grid-flow-row mb-8">
        <div>
          <FormInput
            type="email"
            id="email"
            v-model="form.email"
            label="Email"
            :isRequired="true"
            :isInvalid="$v.form.email.$error || (this.serviceErrors && this.serviceErrors.email)"
            @input="val => updateEmail(val)"
          />
          <div class="text-xs text-red-900 h-0" v-if="$v.form.email.$error || this.serviceErrors">
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
            v-model="form.password"
            label="Password"
            :isRequired="true"
            :isInvalid="$v.form.password.$error || (this.serviceErrors && this.serviceErrors.password)"
            @input="val => updatePassword(val)"
          />
          <div class="text-xs text-red-900 h-0" v-if="$v.form.password.$error || this.serviceErrors">
            <div v-if="!$v.form.password.required">Required field</div>
            <div v-if="this.serviceErrors">
              <div v-for="(serviceError, index) in this.serviceErrors.password" :key="`errorPassword-${index}`">
                {{ serviceError }}
              </div>
            </div>
          </div>
        </div>
      </div>
      <Alert v-if="genericErrors.length > 0" :messages="genericErrors" type="DANGER" />
      <Alert
        v-if="isNewUser"
        :messages="['Your account was created. An email has been sent with a link to verify your account.']"
        type="INFO"
      />
      <div class="text-center">
        <Button type="button" :disabled="disableSignIn" :isLoading="isLoading" @click="signIn()">
          Sign in
        </Button>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import { required, email } from "vuelidate/lib/validators";

import Button from "@/components/Button.vue";
import FormInput from "@/components/FormInput.vue";
import Alert from "@/components/Alert.vue";

export default Vue.extend({
  name: "SignIn",
  props: {
    isNewUser: {
      type: Boolean
    },
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
        email: "",
        password: ""
      },
      genericErrors: []
    };
  },
  validations: {
    form: {
      email: {
        required,
        email
      },
      password: {
        required
      }
    }
  },
  computed: {
    disableSignIn(): boolean {
      return this.$v.$error || this.$v.$invalid;
    }
  },
  methods: {
    updateEmail(value: string) {
      this.form.email = value;
      this.$v.form.email.$touch();
    },
    updatePassword(value: string) {
      this.form.password = value;
      this.$v.form.password.$touch();
    },
    signIn() {
      this.$emit("onSignIn", this.form);
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
    Button,
    FormInput,
    Alert
  }
});
</script>
