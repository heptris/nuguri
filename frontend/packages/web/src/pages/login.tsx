import Link from "@/components/Link";
import { ROUTES } from "@/constant";
import { useAlert, useAuth, useForm, useHeader } from "@/hooks";
import { LoginFormType } from "@/types";
import { Button, LabelInput } from "@common/components";
import { css } from "@emotion/react";
import { useEffect } from "react";

const { SIGNUP } = ROUTES;

const LoginPage = () => {
  useHeader({ mode: "LOGIN", headingText: "로그인" });
  const {
    form: { email, password },
    onChangeForm,
  } = useForm<LoginFormType>({ email: "", password: "" });
  const { handleLogin, isLoginError } = useAuth();
  const { handleAlertOpen } = useAlert();

  useEffect(() => {
    isLoginError && handleAlertOpen("이메일 또는 비밀번호가 잘못됐습니다.", false, 5000);
  }, [isLoginError]);

  return (
    <form
      css={css`
        width: 90%;
        margin: auto;
        display: flex;
        flex-direction: column;
        align-items: center;
      `}
    >
      <LabelInput
        label={"이메일"}
        variant={"standard"}
        css={css`
          width: 100%;
          margin-top: 1rem;
        `}
        type={"email"}
        name={"email"}
        value={email}
        onChange={onChangeForm}
        error={isLoginError}
      />
      <LabelInput
        label={"비밀번호"}
        variant={"standard"}
        css={css`
          width: 100%;
          margin-top: 1rem;
        `}
        type={"password"}
        name={"password"}
        value={password}
        onChange={onChangeForm}
        error={isLoginError}
      />
      <Button
        css={css`
          width: 100%;
          margin-top: 2rem;
          border-radius: 1rem;
        `}
        onClick={() => handleLogin({ email, password })}
      >
        로그인
      </Button>
    </form>
  );
};

export default LoginPage;
