import { useEffect } from "react";
import { ROUTES } from "./../constant/index";
import { useRouter } from "next/router";
import { useMutation } from "@tanstack/react-query";
import { ACCESS_TOKEN, ENDPOINT_BFF } from "@/api";
import { LoginFormType } from "@/types";
import axios from "axios";
import { atom, useRecoilState } from "recoil";
import { getCookie } from "cookies-next";

type AuthType = { isLogined: boolean };
const authState = atom<AuthType>({
  key: "authState",
  default: { isLogined: false },
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
  const {
    mutate: handleLogin,
    isError: isLoginError,
    isLoading: isLoginLoading,
  } = useMutation(postLogin, {
    onSuccess: () => {
      setAuthState({ isLogined: true });
      replace(HOME);
    },
  });
  return { isLogined, handleLogin, isLoginError, isLoginLoading };
};
