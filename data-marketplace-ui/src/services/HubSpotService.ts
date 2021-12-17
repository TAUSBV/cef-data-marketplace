import { AxiosResponse } from "axios";
import ApiClient from "@/services/ApiClient";
import HubSpotFormSubmissionApiClient from "@/services/HubSpotFormSubmissionApiClient";
import { HSFormData, HSFormDataPayload, HSSuccessResponse } from "@/models/models";

export default {
  getForm(): Promise<AxiosResponse<HSFormData>> {
    return ApiClient.get(`/crm/form/${import.meta.env.VITE_APP_HUBSPOT_CONTACT_US_FORM_ID}`);
  },
  submitForm(formData: HSFormDataPayload): Promise<AxiosResponse<HSSuccessResponse>> {
    return HubSpotFormSubmissionApiClient.post(
      `/${import.meta.env.VITE_APP_HUBSPOT_CONTACT_US_FORM_PORTAL_ID}/${
        import.meta.env.VITE_APP_HUBSPOT_CONTACT_US_FORM_ID
      }`,
      formData
    );
  }
};
