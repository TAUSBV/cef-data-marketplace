import { computed, ref } from "@vue/composition-api";
import { SignInForm } from "@/models/models";
import ApiClient from "@/services/ApiClient";

const state = ref({
  isAuthenticated: false,
  isLoading: false,
  uid: null,
  isRequestTermsAgreement: false
});

type Authentication = {
  uid: string;
  isRequestTermsAgreement: boolean;
};

export default function user() {
  function setAuthentication(authentication: Authentication) {
    if (authentication !== null) {
      state.value.uid = authentication.uid;
      state.value.isRequestTermsAgreement = authentication.isRequestTermsAgreement;
      state.value.isAuthenticated = true;
    } else {
      state.value.uid = null;
      state.value.isRequestTermsAgreement = null;
      state.value.isAuthenticated = false;
    }
  }

  async function fetchUser() {
    state.value.isLoading = true;
    await ApiClient.get("/sessions");
    state.value.isLoading = false;
  }

  async function signOut() {
    state.value.isLoading = true;
    await ApiClient.delete("/sessions");
    setAuthentication(null);
    state.value.isLoading = false;
  }

  async function signIn(form: SignInForm) {
    state.value.isLoading = true;
    const response: Authentication = await ApiClient.post("/sessions", form, {
      headers: {
        "Content-Type": "application/json"
      }
    });
    setAuthentication(response);
    state.value.isLoading = false;
  }

  return {
    isLoading: computed(() => state.value.isLoading),
    isAuthenticated: computed(() => state.value.isAuthenticated),
    signIn: signIn,
    signOut: signOut,
    fetchUser: fetchUser
  };
}
