import { Dispatch } from "vuex";
import SellerService from "@/services/SellerService";
import { AxiosResponse } from "axios";
import {
  NotificationType,
  Page,
  SellerPayment,
  TransferWiseBank,
  TransferWiseBranch,
  TransferWiseResponseEnvelope
} from "@/models/models";
import TransferwiseService from "@/services/TransferwiseService";

export const namespaced = true;

export const state = {};

export const mutations = {};

export const actions = {
  fetchSellers(
    { dispatch }: { dispatch: Dispatch },
    {
      page,
      size
    }: {
      page?: string;
      size?: string;
    }
  ) {
    return SellerService.getSellers(page, size)
      .then(response => response.data)
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
  fetchSeller({ dispatch }: { dispatch: Dispatch }, slugId: string) {
    return SellerService.getSeller(slugId)
      .then(response => response.data)
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
  fetchDocuments({ dispatch }: { dispatch: Dispatch }, { slugId, page }: { slugId: string; page?: string }) {
    return SellerService.getSellerDocuments(slugId, page)
      .then(response => response.data)
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
  fetchDocument({ dispatch }: { dispatch: Dispatch }, { slugId, uid }: { slugId: string; uid: string }) {
    return SellerService.getSellerDocument(slugId, uid)
      .then(response => response.data)
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
  fetchSellersFeatured({ dispatch }: { dispatch: Dispatch }) {
    return SellerService.getSellerFeatured()
      .then(response => response.data)
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
  fetchSellerPayments(
    { dispatch }: { dispatch: Dispatch },
    {
      page,
      size
    }: {
      page?: string;
      size?: string;
    }
  ): Promise<Page<SellerPayment>> {
    return SellerService.getSellerPayments(page, size)
      .then((response: AxiosResponse<Page<SellerPayment>>) => response.data)
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
  getTransferwiseBanks({ dispatch }: { dispatch: Dispatch }, countryCode: string): Promise<TransferWiseBank[]> {
    return TransferwiseService.getBanks(countryCode)
      .then((response: AxiosResponse<TransferWiseResponseEnvelope<TransferWiseBank>>) => response.data.values)
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
  getTransferwiseBankBranches(
    { dispatch }: { dispatch: Dispatch },
    params: { countryCode: string; bankCode: string }
  ): Promise<TransferWiseBranch[]> {
    const { countryCode, bankCode } = params;

    return TransferwiseService.getBankBranches(countryCode, bankCode)
      .then((response: AxiosResponse<TransferWiseResponseEnvelope<TransferWiseBranch>>) => response.data.values)
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
  validateIban({ dispatch }: { dispatch: Dispatch }, iban: string): Promise<{ validate: string }> {
    return TransferwiseService.validateIban(iban)
      .then((response: AxiosResponse<{ validate: string }>) => response.data)
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
  validateIfsc({ dispatch }: { dispatch: Dispatch }, ifsc: string): Promise<{ validate: string }> {
    return TransferwiseService.validateIfsc(ifsc)
      .then((response: AxiosResponse<{ validate: string }>) => response.data)
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
  validateIndianAccountNumber(
    { dispatch }: { dispatch: Dispatch },
    accountNumber: string
  ): Promise<{ validate: string }> {
    return TransferwiseService.validateIndianAccountNumber(accountNumber)
      .then((response: AxiosResponse<{ validate: string }>) => response.data)
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
