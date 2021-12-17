import DataExplorerService from "@/services/DataExplorerService";
import { DataExplorerRequest, NotificationType } from "@/models/models";
import { Dispatch } from "vuex";

export const namespaced = true;

export const state = {};

export const mutations = {};

export const actions = {
  explore({ dispatch }: { dispatch: Dispatch }, { request }: { request?: DataExplorerRequest }) {
    return DataExplorerService.explore(request)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        dispatch(
          "notification/add",
          {
            type: NotificationType.DANGER,
            notification: error.message
          },
          {
            root: true
          }
        );
        throw error;
      });
  },
  languages({ dispatch }: { dispatch: Dispatch }) {
    return DataExplorerService.languages()
      .then(response => {
        return response.data;
      })
      .catch(error => {
        dispatch(
          "notification/add",
          {
            type: NotificationType.DANGER,
            notification: error.message
          },
          {
            root: true
          }
        );
        throw error;
      });
  }
};
