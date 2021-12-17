import ApiClient from "@/services/ApiClient";

export default {
  list() {
    return ApiClient.get("/cart");
  },
  update(updatedItemForm: FormData) {
    return ApiClient.put("/cart", updatedItemForm, {
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    });
  },
  delete(product: FormData) {
    return ApiClient.delete("/cart", {
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      data: product
    });
  },
  addSellerDocumentToCart(product: FormData) {
    return ApiClient.put("/cart/document", product, {
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    });
  },
  addVirtualDocumentToCart(product: FormData) {
    return ApiClient.put("/cart/virtual", product, {
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    });
  },
  addMatchingDocumentToCart(product: FormData) {
    return ApiClient.put("/cart/matching-document", product, {
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    });
  }
};
