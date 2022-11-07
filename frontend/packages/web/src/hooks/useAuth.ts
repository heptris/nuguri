import { useEffect } from "react";
import { ROUTES } from "./../constant/index";
import { useRouter } from "next/router";
import { useMutation } from "@tanstack/react-query";
import { ACCESS_TOKEN, ENDPOINT_BFF, REFRESH_TOKEN } from "@/api";
import { LoginFormType } from "@/types";
import axios from "axios";
import { atom, useRecoilState } from "recoil";
import { deleteCookie, getCookie } from "cookies-next";

type AuthType = { isLogined: boolean };
const authState = atom<AuthType>({
  key: "authState",
  default: { isLogined: null },
});
const { HOME } = ROUTES;

export const useAuth = () => {
  const [{ isLogined }, setAuthState] = useRecoilState(authState);
  const { replace } = useRouter();
  useEffect(() => {
    setAuthState({ isLogined: !!getCookie(ACCESS_TOKEN) });
  }, []);

  const postLogin = async (form: LoginFormType) => {
    return await axios.post(ENDPOINT_BFF + "/login", form).then(({ data }) => {
      return data;
    });
  };

  const handleSilentRefresh = () => {
    axios.get(ENDPOINT_BFF + "/login").then(data => {
      console.log(data);
      handleLoginProcess(data.data.accessTokenExpiresIn);
    });
  };

  const handleLoginProcess = (expiresIn: number) => {
    setAuthState({ isLogined: true });
    setTimeout(handleSilentRefresh, expiresIn - Date.now());
  };

  const {
    mutate: handleLogin,
    isError: isLoginError,
    isLoading: isLoginLoading,
  } = useMutation(postLogin, {
    onSuccess: data => {
      handleLoginProcess(data.accessTokenExpiresIn);
      replace(HOME);
    },
  });

  const handleLogout = () => {
    setAuthState({ isLogined: false });
    deleteCookie(ACCESS_TOKEN);
    deleteCookie(REFRESH_TOKEN);
  };

  return { isLogined, handleLogin, isLoginError, isLoginLoading, handleLogout };
};
