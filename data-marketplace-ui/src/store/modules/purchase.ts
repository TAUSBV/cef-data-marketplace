import PurchaseService from "@/services/PurchaseService";
import { Dispatch } from "vuex";
import { NotificationType, PurchaseItemReview } from "@/models/models";

export const namespaced = true;

export const state = {};

export const mutations = {};

export const actions = {
  get({ dispatch }: { dispatch: Dispatch }, uid: string) {
    return PurchaseService.getPurchase(uid)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },
  createDownload({ dispatch }: { dispatch: Dispatch }, uid: string) {
    return PurchaseService.createDownload(uid)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },
  createReview({ dispatch }: { dispatch: Dispatch }, { review }: { review: PurchaseItemReview }) {
    return PurchaseService.createReview(review)
      .then(response => response.data)
      .catch(error => {
        dispatch(
          "notification/add",
          {
            type: NotificationType.DANGER,
            message: error.response.data
          },
          {
            root: true
          }
        );
        throw error;
      });
  },
  checkModerationCode(
    { dispatch }: { dispatch: Dispatch },
    { purchaseUid, moderationCode }: { purchaseUid: string; moderationCode: string }
  ) {
    return PurchaseService.checkModerationCode(purchaseUid, moderationCode)
      .then(response => response.data)
      .catch(error => {
        dispatch(
          "notification/add",
          {
            type: NotificationType.DANGER,
            message: error.response.data
          },
          {
            root: true
          }
        );
        throw error;
      });
  },
  moderate(
    { dispatch }: { dispatch: Dispatch },
    { purchaseItemUid, moderationCode, outcome }: { purchaseItemUid: string; moderationCode: string, outcome: string }
  ) {
    return PurchaseService.moderate(purchaseItemUid, moderationCode, outcome)
      .then(response => response.data)
      .catch(error => {
        dispatch(
          "notification/add",
          {
            type: NotificationType.DANGER,
            message: error.response.data
          },
          {
            root: true
          }
        );
        throw error;
      });
  }
};
