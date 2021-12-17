import queryString from "qs";
import ApiClient from "@/services/ApiClient";
import { TranslationDocumentReview } from "@/models/models";

export default {
  uploadFiles(formData: FormData, onProgress: Function) {
    return ApiClient.post("/publications/upload", formData, {
      headers: {
        "Content-Type": "multipart/form-data"
      },
      onUploadProgress: progressEvent => {
        onProgress(Math.round((progressEvent.loaded / progressEvent.total) * 100));
      }
    });
  },
  publishDocument(
    uid: string,
    domainId: string,
    contentTypeId: string,
    price: number,
    description: string,
    anonymize: boolean
  ) {
    return ApiClient.put(
      "/publications/" + uid,
      queryString.stringify({
        domainId: domainId,
        contentTypeId: contentTypeId,
        price: price,
        description: description,
        anonymize: anonymize
      }),
      {
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
    );
  },
  getPublication(id: string) {
    return ApiClient.get("/publications/" + id);
  },
  getPublicationStatus(id: string, currentStatus: string) {
    return ApiClient.get("/publications/" + id + "/status", {
      params: { status: currentStatus }
    });
  },
  getSuggestedPricePerWord(id: string, domainId: string, contentTypeId: string) {
    return ApiClient.get("/price", {
      params: {
        documentUid: id,
        domainId: domainId,
        contentTypeId: contentTypeId
      }
    });
  },
  setPublicationNotify(uid: string, email: string) {
    return ApiClient.put(
      "/publications/" + uid + "/notify",
      queryString.stringify({
        r: email
      }),
      {
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
    );
  },
  getReviews(uid: string) {
    return ApiClient.get("/documents/" + uid + "/reviews");
  }
};
