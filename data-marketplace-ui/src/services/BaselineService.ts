import ApiClient from "@/services/ApiClient";

export default {
  getCountries() {
    return ApiClient.get("/base/countries");
  },
  getDomains() {
    return ApiClient.get("/base/domains");
  },
  getContentTypes() {
    return ApiClient.get("/base/content_types");
  }
};
