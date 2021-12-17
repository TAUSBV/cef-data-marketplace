import ApiClient from "@/services/ApiClient";

export default {
  buyer(formData: FormData) {
    return ApiClient.post("/checkout", formData, {
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    });
  },
  payment(formData: FormData) {
    return ApiClient.put("/checkout/payment", formData, {
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    });
  },
  complete(formData: FormData) {
    return ApiClient.put("/checkout/complete", formData, {
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    });
  }
};
