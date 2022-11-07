import { ACCESS_TOKEN } from "@/api";
import { getCookie } from "cookies-next";
import axios from "axios";

export * from "./endpoints";
export * from "./utils";

export const apiInstance = axios.create();
apiInstance.interceptors.request.use(config => {
  if (!config.headers.Authorization) {
    const accessToken = getCookie(ACCESS_TOKEN);
    config.headers.Authorization = accessToken ? `Bearer ${accessToken}` : "";
  }
  return config;
});
