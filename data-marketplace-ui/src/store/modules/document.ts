import DocumentService from "@/services/DocumentService";
import { Dispatch } from "vuex";
import { NotificationType, Publication, TranslationDocumentReview } from "@/models/models";

export const namespaced = true;

export const state = {};

export const mutations = {};

export const actions = {
  uploadDocument(
    { dispatch }: { dispatch: Dispatch },
    { formData, onProgress }: { formData: FormData; onProgress: Function }
  ) {
    return DocumentService.uploadFiles(formData, onProgress)
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
  publishDocument(
    { dispatch }: { dispatch: Dispatch },
    {
      uid,
      domainId,
      contentTypeId,
      price,
      description,
      anonymize
    }: {
      uid: string;
      domainId: string;
      contentTypeId: string;
      price: number;
      description: string;
      anonymize: boolean;
    }
  ) {
    return DocumentService.publishDocument(uid, domainId, contentTypeId, price, description, anonymize)
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
  fetchPublications({ dispatch }: { dispatch: Dispatch }, id: string) {
    return DocumentService.getPublication(id)
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
  fetchPublicationStatus({ dispatch }: { dispatch: Dispatch }, payload: { id: string; currentStatus: string }) {
    return DocumentService.getPublicationStatus(payload.id, payload.currentStatus)
      .then(response => {
        return Publication.parse(response.data);
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
  fetchSuggestedPricePerWord(
    { dispatch }: { dispatch: Dispatch },
    {
      id,
      domainId,
      contentTypeId
    }: {
      id: string;
      domainId: string;
      contentTypeId: string;
    }
  ) {
    return DocumentService.getSuggestedPricePerWord(id, domainId, contentTypeId)
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
  setPublicationNotify(
    { dispatch }: { dispatch: Dispatch },
    {
      uid,
      email
    }: {
      uid: string;
      email: string;
    }
  ) {
    return DocumentService.setPublicationNotify(uid, email)
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
  fetchReviews({ dispatch }: { dispatch: Dispatch }, { uid }: { uid: string }) {
    return DocumentService.getReviews(uid)
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
