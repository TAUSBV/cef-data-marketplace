import axios from "axios";

const ExpressApiClient = axios.create({
  baseURL: import.meta.env.VITE_APP_EXPRESS_SERVER_URL,
  withCredentials: false,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json"
  }
});

export default ExpressApiClient;
