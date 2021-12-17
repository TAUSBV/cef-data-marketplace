// External Modules
import { AxiosResponse } from "axios";
import { Dispatch } from "vuex";
// Services
import UserService from "@/services/UserService";
// Models
import { SignUpForm, UserPaymentDetailsApiResponse, UserPaymentDetailsPayload } from "@/models/models";

export const namespaced = true;

export const state = {};

export const mutations = {};

export const actions = {
  signUp({ dispatch }: { dispatch: Dispatch }, form: SignUpForm) {
    return UserService.signUp(form)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },
  fetch() {
    return UserService.fetch()
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },
  fetchDocuments({ dispatch }: { dispatch: Dispatch }, { page, keywords }: { page?: string; keywords?: string }) {
    return UserService.fetchDocuments(page, keywords)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },
  fetchDocument({ dispatch }: { dispatch: Dispatch }, uid: string) {
    return UserService.fetchDocument(uid)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },
  updateDocument(
    { dispatch }: { dispatch: Dispatch },
    {
      uid,
      domainId,
      contentTypeId,
      price,
      description
    }: {
      uid: string;
      domainId: string;
      contentTypeId: string;
      price: number;
      description: string;
    }
  ) {
    return UserService.updateDocument(uid, domainId, contentTypeId, price, description)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },
  fetchPaymentDetails({ dispatch }: { dispatch: Dispatch }): Promise<UserPaymentDetailsApiResponse> {
    return UserService.fetchPaymentDetails()
      .then((response: AxiosResponse<UserPaymentDetailsApiResponse>) => response.data)
      .catch(error => {
        throw error;
      });
  },
  updatePaymentDetails(
    { dispatch }: { dispatch: Dispatch },
    paymentDetails: UserPaymentDetailsPayload
  ): Promise<UserPaymentDetailsApiResponse> {
    // remove method from the request payload
    const { method, ...details } = paymentDetails;

    return UserService.updatePaymentDetails(method, details)
      .then((response: AxiosResponse<UserPaymentDetailsApiResponse>) => response.data)
      .catch(error => {
        throw error;
      });
  }
};
