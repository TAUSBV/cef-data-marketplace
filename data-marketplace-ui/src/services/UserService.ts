// External modules
import qs from "qs";
import { AxiosResponse } from "axios";
// Models
import { SignUpForm, UserPaymentDetailsApiResponse, UserPaymentDetailsPayload } from "@/models/models";
// Services
import ApiClient from "@/services/ApiClient";

export default {
  signUp(form: SignUpForm) {
    return ApiClient.post("/users", form);
  },
  fetch() {
    return ApiClient.get("/users");
  },
  fetchDocuments(page?: string, keywords?: string) {
    const params: Record<string, string> = {
      pg: page,
      k: keywords
    };
    return ApiClient.get("/users/documents", {
      params: params,
      paramsSerializer: params => {
        return qs.stringify(params, { indices: false });
      }
    });
  },
  fetchDocument(uid: string) {
    return ApiClient.get("/users/documents/" + uid);
  },
  updateDocument(uid: string, domainId: string, contentTypeId: string, price: number, description: string) {
    return ApiClient.put(
      `/users/documents/${uid}`,
      qs.stringify({
        domainId: domainId,
        contentTypeId: contentTypeId,
        price: price,
        description: description
      }),
      {
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
    );
  },
  fetchPaymentDetails(): Promise<AxiosResponse<UserPaymentDetailsApiResponse>> {
    return ApiClient.get("/users/payments/profile");
  },
  updatePaymentDetails(
    method: string,
    details: UserPaymentDetailsPayload
  ): Promise<AxiosResponse<UserPaymentDetailsApiResponse>> {
    return ApiClient.put(`/users/payments/profile/${method}`, details);
  }
};
