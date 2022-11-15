import { ACCESS_TOKEN } from "@/api";
import { deleteCookie, getCookie } from "cookies-next";
import axios from "axios";
import { ENDPOINT_BFF } from "./endpoints";

export * from "./endpoints";
export * from "./utils";

export const apiInstance = axios.create();
axios.interceptors.request.use(config => {
  console.log(config);
  return config;
});
apiInstance.interceptors.request.use(config => {
  console.log("요청 보내는 config", config);
  if (!config.headers.Authorization) {
    const accessToken = getCookie(ACCESS_TOKEN);
    config.headers.Authorization = accessToken ? `Bearer ${accessToken}` : "";
  }
  // console.log(config.headers);
  return config;
});

apiInstance.interceptors.response.use(
  res => res,
  async err => {
    console.error("apiInstance의 header가 만료", err);

    if (err.config && err.response?.status === 401) {
      return await axios
        .get(ENDPOINT_BFF + "/login")
        .then(() => {
          const accessToken = getCookie(ACCESS_TOKEN);
          // axios.defaults.headers.Authorization = `Bearer ${accessToken}`;
          err.config.headers.Authorization = `Bearer ${accessToken}`;
          console.log("error config", err.config.headers);
          return axios.request(err.config);
        })
        .catch(err => {
          console.error("재발행 실패", err);
        });
    }
  },
);
