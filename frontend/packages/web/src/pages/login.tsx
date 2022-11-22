import Link from "@/components/Link";
import { ROUTES } from "@/constant";
import { useAlert, useAuth, useBottom, useForm, useHeader } from "@/hooks";
import { LoginFormType } from "@/types";
import { Button, LabelInput } from "@common/components";
import { css } from "@emotion/react";
import { useEffect } from "react";

const { SIGNUP } = ROUTES;

const LoginPage = () => {
  useHeader({ mode: "LOGIN", headingText: "로그인" });
  useBottom(<></>);
  const {
    form: { email, password },
    onChangeForm,
  } = useForm<LoginFormType>({ email: "", password: "" });
  const { handleLogin, isLoginError } = useAuth();
  const { handleAlertOpen } = useAlert();

  useEffect(() => {
    isLoginError && handleAlertOpen("이메일 또는 비밀번호가 잘못됐습니다.", false, 5000);
  }, [isLoginError]);

  const handleSubmitLogin = () => handleLogin({ email, password });

  return (
    <form
      css={css`
        width: 90%;
        margin: auto;
        display: flex;
        flex-direction: column;
        align-items: center;
      `}
      onSubmit={e => e.preventDefault()}
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
        onKeyDown={e => {
          e.key === "Enter" && handleSubmitLogin();
        }}
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
        onKeyDown={e => {
          e.key === "Enter" && handleSubmitLogin();
        }}
      />
      <Button
        css={css`
          width: 100%;
          margin-top: 2rem;
          border-radius: 1rem;
        `}
        type={"button"}
        onClick={handleSubmitLogin}
      >
        로그인
      </Button>
      <Link
        href={SIGNUP}
        noLinkStyle
        css={css`
          margin-top: 2rem;
        `}
      >
        이메일로 회원가입
      </Link>
    </form>
  );
};

export default LoginPage;
