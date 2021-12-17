import qs from "qs";
import { DataExplorerRequest, DataExplorerResult, LanguageSourceTargetCountComposite } from "@/models/models";
import ApiClient from "@/services/ApiClient";
import { AxiosResponse } from "axios";

export default {
  explore(request: DataExplorerRequest): Promise<AxiosResponse<DataExplorerResult>> {
    const params: Record<string, object | string> = {
      s: request.sourceLanguage,
      t: request.targetLanguage
    };
    const d = Array.from(request.domains);
    if (d.length > 0) {
      params["d"] = d;
    }
    const c = Array.from(request.contentTypes);
    if (c.length > 0) {
      params["c"] = c;
    }
    const u = Array.from(request.sellers);
    if (u.length > 0) {
      params["u"] = u;
    }
    if (request.priceMin) {
      params["pmin"] = request.priceMin;
    }
    if (request.priceMax) {
      params["pmax"] = request.priceMax;
    }
    let endpoint = "" as string;
    if (request.segmentsView) {
      endpoint = "units";
    } else {
      endpoint = "documents";
    }
    if (request.page && endpoint === "documents") {
      params["pg"] = request.page.toString();
    }
    return ApiClient.get("/search/" + endpoint, {
      params: params,
      paramsSerializer: params => {
        return qs.stringify(params, { indices: false });
      }
    });
  },
  languages(): Promise<LanguageSourceTargetCountComposite> {
    return ApiClient.get("/base/languages");
  }
};
