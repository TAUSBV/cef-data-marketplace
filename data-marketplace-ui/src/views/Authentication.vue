<template>
  <section class="flex flex-col xs:w-full sm:w-full md:w-11/12 lg:w-3/4 mx-auto mt-8">
    <SignIn
      v-if="showSignIn"
      :isNewUser="isNewUser"
      :isLoading="isLoading"
      :serviceErrors="serviceErrorsInternal"
      @toggleAuthentication="toggleAuthentication"
      @onSignIn="setAuthenticationSignIn"
    />
    <SignUp
      v-if="!showSignIn"
      :isLoading="isLoading"
      :serviceErrors="serviceErrorsInternal"
      @toggleAuthentication="toggleAuthentication"
      @onSignUp="setAuthenticationSignUp"
    />
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import SignIn from "@/views/SignIn.vue";
import SignUp from "@/views/SignUp.vue";
import { SignInForm, SignUpForm } from "@/models/models";

export default Vue.extend({
  props: {
    isLoading: {
      type: Boolean,
      required: false,
      default: false
    },
    serviceErrors: {
      type: Object
    },
    isNewUser: {
      type: Boolean
    }
  },
  data() {
    return {
      showSignIn: true,
      serviceErrorsInternal: this.serviceErrors
    };
  },
  methods: {
    toggleAuthentication() {
      this.showSignIn = !this.showSignIn;
      this.serviceErrorsInternal = null;
    },
    setAuthenticationSignIn(form: SignInForm) {
      this.$emit("setAuthenticationSignIn", form);
    },
    setAuthenticationSignUp(form: SignUpForm) {
      this.$emit("setAuthenticationSignUp", form);
    }
  },
  watch: {
    serviceErrors() {
      this.serviceErrorsInternal = this.serviceErrors;
    },
    isNewUser() {
      this.showSignIn = true;
      this.serviceErrorsInternal = null;
    }
  },
  components: {
    SignIn,
    SignUp
  }
});
</script>
