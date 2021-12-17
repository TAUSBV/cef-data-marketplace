import { Dispatch } from "vuex";
import PriceIndexTableService from "@/services/PriceIndexTableService";
import { NotificationType } from "@/models/models";

export const namespaced = true;

export const state = {};

export const mutations = {};

export const actions = {
  load(
    { dispatch }: { dispatch: Dispatch },
    {
      page,
      keywords,
      orders
    }: {
      page?: string;
      keywords?: string;
      orders?: Array<string>;
    }
  ) {
    return PriceIndexTableService.load(page, keywords, orders)
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
