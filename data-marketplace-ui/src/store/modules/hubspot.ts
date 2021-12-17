import HubSpotService from "@/services/HubSpotService";
import { Dispatch } from "vuex";
import { HSFormData, HSFormDataPayload, HSSuccessResponse } from "@/models/models";
import { AxiosResponse } from "axios";

export const namespaced = true;

export const state = {};

export const mutations = {};

export const actions = {
  getForm({ dispatch }: { dispatch: Dispatch }): Promise<HSFormData> {
    return HubSpotService.getForm()
      .then((response: AxiosResponse<HSFormData>) => response.data)
      .catch(error => {
        throw error;
      });
  },
  submitForm({ dispatch }: { dispatch: Dispatch }, formDataPayload: HSFormDataPayload): Promise<HSSuccessResponse> {
    return HubSpotService.submitForm(formDataPayload)
      .then((response: AxiosResponse<HSSuccessResponse>) => response.data)
      .catch(error => {
        throw error;
      });
  }
};
