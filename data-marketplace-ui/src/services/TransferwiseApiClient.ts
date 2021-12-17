import axios from "axios";

const TransferwiseApiClient = axios.create({
  baseURL: import.meta.env.VITE_APP_TRANSFERWISE_API_URL,
  headers: {
    Authorization: `Bearer ${import.meta.env.VITE_APP_TRANSFERWISE_ACCESS_TOKEN}`,
    Accept: "application/json"
  }
});

export default TransferwiseApiClient;
