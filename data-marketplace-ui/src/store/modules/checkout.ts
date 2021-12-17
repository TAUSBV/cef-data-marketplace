import { Dispatch } from "vuex";
import { loadStripe } from "@stripe/stripe-js";
import { Cart } from "@/models/models";
import CheckoutService from "@/services/CheckoutService";

const stripeFactory = loadStripe(import.meta.env.VITE_APP_STRIPE_PUBLISHABLE_KEY);

const contactStripe = async (brokerTransactionId: string) => {
  const stripe = await stripeFactory;

  return await stripe.redirectToCheckout({
    sessionId: brokerTransactionId
  });
};

export const namespaced = true;

export const state = {};

export const mutations = {};

export const actions = {
  buyer({ dispatch }: { dispatch: Dispatch }, formData: FormData) {
    return CheckoutService.buyer(formData).then(response => response.data);
  },
  payment({ dispatch }: { dispatch: Dispatch }, formData: FormData) {
    return CheckoutService.payment(formData).then(response => response.data);
  },
  complete({ dispatch }: { dispatch: Dispatch }, formData: FormData): Promise<Cart> {
    return CheckoutService.complete(formData).then(response => response.data);
  },
  stripe({ dispatch }: { dispatch: Dispatch }, brokerTransactionId: string) {
    return contactStripe(brokerTransactionId);
  }
};
