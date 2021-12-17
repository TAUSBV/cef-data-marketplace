import ApiClient from "@/services/ApiClient";

export default {
  uploadSamples(formData: FormData) {
    return ApiClient.post("/matching-data", formData, {
      headers: {
        "Content-Type": "multipart/form-data"
      }
    });
  },

  getMDSearchResult(id: string) {
    return ApiClient.get("/matching-data/" + id);
  },
  getStatus(id: string, currentStatus: string) {
    return ApiClient.get("/matching-data/" + id, {
      params: { status: currentStatus }
    });
  }
};
