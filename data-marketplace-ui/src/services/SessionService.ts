import { SignInForm } from "@/models/models";
import ApiClient from "@/services/ApiClient";

export default {
  signIn(form: SignInForm) {
    return ApiClient.post("/sessions", form);
  },
  refresh() {
    return ApiClient.get("/sessions");
  },
  destroy() {
    return ApiClient.delete("/sessions");
  }
};
