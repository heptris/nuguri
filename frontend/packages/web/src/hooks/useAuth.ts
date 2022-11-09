import { useEffect, useRef } from "react";
import { ROUTES } from "./../constant/index";
import { useRouter } from "next/router";
import { useMutation } from "@tanstack/react-query";
import { ACCESS_TOKEN, ENDPOINT_BFF, REFRESH_TOKEN } from "@/api";
import { LoginFormType } from "@/types";
import axios from "axios";
import { atom, useRecoilState } from "recoil";
import { deleteCookie, getCookie } from "cookies-next";
import { useAlert } from "./useAlert";

type AuthType = { isLogined: boolean; nickname?: string };
const authState = atom<AuthType>({
  key: "authState",
  default: { isLogined: null, nickname: null },
});
const { HOME } = ROUTES;

export const useAuth = () => {
  const [{ isLogined, nickname }, setAuthState] = useRecoilState(authState);
  const { replace } = useRouter();
  const isRefreshed = useRef(false);
  const { handleAlertOpen } = useAlert();

  useEffect(() => {
    setAuthState({ isLogined: !!getCookie(ACCESS_TOKEN) });
    isRefreshed.current || (!isLogined && handleSilentRefresh());
    isRefreshed.current = true;
  }, []);

  const postLogin = async (form: LoginFormType) => {
    return await axios.post(ENDPOINT_BFF + "/login", form).then(({ data }) => {
      return data;
    });
  };

  const handleSilentRefresh = () => {
    axios
      .get(ENDPOINT_BFF + "/login")
      .then(res => {
        handleLoginProcess(res.data);
      })
      .catch(e => {
        console.error(e);
      });
  };

  const handleLoginProcess = ({ accessTokenExpiresIn, nickname }: { accessTokenExpiresIn: number; nickname: string }) => {
    setAuthState({ isLogined: true, nickname });
    setTimeout(handleSilentRefresh, accessTokenExpiresIn - Date.now() - 1000 * 60 * 5);
  };

  const {
    mutate: handleLogin,
    isError: isLoginError,
    isLoading: isLoginLoading,
  } = useMutation(postLogin, {
    onSuccess: data => {
      handleAlertOpen("로그인에 성공했습니다.", true, 1000);
      handleLoginProcess(data);
      replace(HOME);
    },
  });

  const handleLogout = () => {
    setAuthState({ isLogined: false, nickname: null });
    deleteCookie(ACCESS_TOKEN);
    deleteCookie(REFRESH_TOKEN);
  };

  return { isLogined, nickname, handleLogin, isLoginError, isLoginLoading, handleLogout, handleSilentRefresh };
};