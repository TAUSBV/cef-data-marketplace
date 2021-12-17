import qs from "qs";
import ApiClient from "@/services/ApiClient";
import { AxiosResponse } from "axios";
import { Page, SellerPayment } from "@/models/models";

export default {
  getSellers(page?: string, size?: string) {
    const params: Record<string, string> = {
      pg: page,
      size: size
    };
    return ApiClient.get("/sellers", {
      params: params,
      paramsSerializer: params => {
        return qs.stringify(params, { indices: false });
      }
    });
  },
  getSeller(slugId: string) {
    return ApiClient.get("/sellers/" + slugId);
  },
  getSellerDocuments(slugId: string, page?: string) {
    const params: Record<string, string> = {
      pg: page
    };
    return ApiClient.get("/sellers/" + slugId + "/documents", {
      params: params,
      paramsSerializer: params => {
        return qs.stringify(params, { indices: false });
      }
    });
  },
  getSellerDocument(slugId: string, uid: string) {
    return ApiClient.get("/sellers/" + slugId + "/documents/" + uid);
  },
  getSellerFeatured() {
    return ApiClient.get("/documents/featured");
  },
  getSellerPayments(page?: string, size?: string): Promise<AxiosResponse<Page<SellerPayment>>> {
    const params: Record<string, string> = {
      pg: page,
      size: size
    };
    return ApiClient.get("/users/payments", {
      params,
      paramsSerializer: params => {
        return qs.stringify(params, { indices: false });
      }
    });
  }
};
