import ApiClient from "@/services/ApiClient";
import { PurchaseItemReview } from "@/models/models";

export default {
  getPurchase(uid: string) {
    return ApiClient.get("/purchases/" + uid);
  },
  createDownload(uid: string) {
    return ApiClient.post("/purchases/" + uid + "/downloads");
  },
  createReview(review: PurchaseItemReview) {
    const formData = new URLSearchParams();
    formData.append("score", review.score.toString());
    formData.append("comment", review.comment);
    formData.append("anonymous", String(review.anonymous));
    return ApiClient.post("/purchases/items/" + review.purchaseItemUid + "/reviews", formData, {
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    });
  },
  checkModerationCode(purchaseUid: string, moderationCode: string) {
    const endpoint = "/purchases/items/reviews/moderation/check";
    return ApiClient.get(endpoint + "?purchaseUid=" + purchaseUid + "&moderationCode=" + moderationCode);
  },
  moderate(purchaseItemUid: string, moderationCode: string, outcome: string) {
    const endpoint = "/purchases/items/" + purchaseItemUid + "/reviews/moderation";
    const formData = new URLSearchParams();
    formData.append("moderationCode", moderationCode);
    formData.append("outcome", outcome);
    return ApiClient.post(endpoint, formData, {
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    });
  }
};
