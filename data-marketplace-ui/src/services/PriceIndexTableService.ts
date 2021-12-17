import qs from "qs";
import ApiClient from "@/services/ApiClient";

export default {
  load(page?: string, keywords?: string, orders?: Array<string>) {
    const params: Record<string, object | string> = {
      pg: page,
      k: keywords,
      sort: orders
    };
    return ApiClient.get("/price_index", {
      params: params,
      paramsSerializer: params => {
        return qs.stringify(params, { indices: false });
      }
    });
  }
};
