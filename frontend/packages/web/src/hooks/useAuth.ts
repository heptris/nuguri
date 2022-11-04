import { useMutation } from "@tanstack/react-query";
import { ENDPOINT_BFF } from "@/api";
import { LoginFormType } from "@/types";
import axios from "axios";
import { atom, useRecoilState } from "recoil";

type AuthType = { isLogined: boolean };
const authState = atom<AuthType>({
  key: "authState",
  default: { isLogined: false },
});

export const useAuth = () => {
  const [{ isLogined }, setAuthState] = useRecoilState(authState);
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
    },
  });
  return { isLogined, handleLogin, isLoginError, isLoginLoading };
};
