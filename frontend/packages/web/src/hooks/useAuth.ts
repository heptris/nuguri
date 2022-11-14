import { useEffect } from "react";
import { useRouter } from "next/router";
import axios from "axios";
import { atom, useRecoilState } from "recoil";
import { deleteCookie, getCookie } from "cookies-next";
import { useMutation, useQueryClient } from "@tanstack/react-query";

import { ACCESS_TOKEN, apiInstance, ENDPOINT_BFF, REFRESH_TOKEN, ENDPOINT_AUTH } from "@/api";
import { LoginFormType } from "@/types";
import { QUERY_KEYS, ROUTES } from "@/constant/index";
import { useAlert } from "./useAlert";
import { useUser } from "./useUser";

type AuthType = { isLogined: boolean };
const authState = atom<AuthType>({
  key: "authState",
  default: { isLogined: null },
});
const { HOME } = ROUTES;
const { MY_PROFILE } = QUERY_KEYS;
export const useAuth = () => {
  const [{ isLogined }, setAuthState] = useRecoilState(authState);
  const { replace, push } = useRouter();
  const { handleAlertOpen } = useAlert();
  const client = useQueryClient();
  const { postProfile } = useUser();

  useEffect(() => {
    isLogined || handleLoginProcess();
  }, []);

  useEffect(() => {
    isLogined && postProfile();
  }, [isLogined]);

  const postLogin = async (form: LoginFormType) => {
    return await axios.post(ENDPOINT_BFF + "/login", form).then(({ data }) => {
      return data;
    });
  };

  const postLogout = async () => {
    return await apiInstance.get(ENDPOINT_AUTH + "/logout").then(({ data }) => {
      console.log(data);
      return data;
    });
  };

  const handleLoginProcess = () => {
    setAuthState({ isLogined: !!getCookie(ACCESS_TOKEN) });
  };

  const handleLogoutProcess = () => {
    handleAlertOpen("로그아웃 완료!", true, 1000);
    setAuthState({ isLogined: false });
    deleteCookie(ACCESS_TOKEN);
    deleteCookie(REFRESH_TOKEN);
    client.setQueryData([MY_PROFILE], { baseAddress: "전국", localId: 0 });
  };

  const {
    mutate: handleLogin,
    isError: isLoginError,
    isLoading: isLoginLoading,
  } = useMutation(postLogin, {
    onSuccess: data => {
      handleAlertOpen("로그인에 성공했습니다.", true, 1000);
      handleLoginProcess();
      replace(HOME);
    },
  });
  const { mutate: mutateLogout } = useMutation(postLogout, {
    onSettled: () => {
      handleLogoutProcess();
    },
  });

  const handleLogout = async () => {
    await push(HOME);
    mutateLogout();
  };

  return { isLogined, handleLogin, isLoginError, isLoginLoading, handleLogout };
};
