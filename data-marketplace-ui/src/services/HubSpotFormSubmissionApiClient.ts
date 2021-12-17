import axios from "axios";

const HubSpotFormSubmissionApiClient = axios.create({
  baseURL: import.meta.env.VITE_APP_HUBSPOT_FORM_SUBMISSION_API_URL,
  headers: {
    Accept: "application/json"
  }
});

export default HubSpotFormSubmissionApiClient;
