import MatchingDataService from "@/services/MatchingDataService";
import { Dispatch } from "vuex";
import { MatchingData, NotificationType } from "@/models/models";

export const namespaced = true;

export const state = {};

export const mutations = {};

export const actions = {
  uploadSample({ dispatch }: { dispatch: Dispatch }, { formData }: { formData: FormData }) {
    return MatchingDataService.uploadSamples(formData)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        dispatch(
          "notification/add",
          {
            type: NotificationType.DANGER,
            message: error.message
          },
          {
            root: true
          }
        );
        throw error;
      });
  },
  fetchSearchResult({ dispatch }: { dispatch: Dispatch }, id: string) {
    return MatchingDataService.getMDSearchResult(id)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        dispatch(
          "notification/add",
          {
            type: NotificationType.DANGER,
            message: error.message
          },
          {
            root: true
          }
        );
        throw error;
      });
  },
  fetchStatus({ dispatch }: { dispatch: Dispatch }, payload: { id: string; currentStatus: string }) {
    return MatchingDataService.getStatus(payload.id, payload.currentStatus)
      .then(response => {
        return MatchingData.parse(response.data);
      })
      .catch(error => {
        dispatch(
          "notification/add",
          {
            type: NotificationType.DANGER,
            message: error.message
          },
          {
            root: true
          }
        );
        throw error;
      });
  }
};
