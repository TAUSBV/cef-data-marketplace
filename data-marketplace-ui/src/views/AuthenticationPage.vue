<template>
  <section class="flex flex-col w-3/4 p-4 md:p-8 mx-auto shadow-input">
    <SectionTitle title="Welcome" />
    <Authentication
      @setAuthenticationSignIn="setAuthenticationSignIn"
      @setAuthenticationSignUp="setAuthenticationSignUp"
      :isNewUser="isNewUser"
      :isLoading="isLoading"
      :serviceErrors="serviceErrors"
    />
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Authentication from "@/views/Authentication.vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";
import { SignInForm, SignUpForm } from "@/models/models";

export default Vue.extend({
  data() {
    return {
      isLoading: false,
      isNewUser: false,
      serviceErrors: null
    };
  },
  methods: {
    setAuthenticationSignIn(form: SignInForm) {
      this.isLoading = true;
      this.isNewUser = false;
      this.serviceErrors = null;
      this.$store
        .dispatch("session/signIn", form)
        .then(() => {
          this.$router.push({
            name: "Users"
          });
        })
        .catch(error => {
          this.serviceErrors = error.response.data;
        })
        .finally(() => {
          this.isLoading = false;
        });
    },
    setAuthenticationSignUp(form: SignUpForm) {
      this.isLoading = true;
      this.serviceErrors = null;
      this.$store
        .dispatch("user/signUp", form)
        .then(() => {
          this.isNewUser = true;
        })
        .catch(error => {
          this.serviceErrors = error.response.data;
        })
        .finally(() => {
          this.isLoading = false;
        });
    }
  },
  components: {
    Authentication,
    SectionTitle
  }
});
</script>
