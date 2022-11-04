import Link from "@/components/Link";
import { ROUTES } from "@/constant";
import { useAuth, useForm, useHeader } from "@/hooks";
import { LoginFormType } from "@/types";
import { Button, LabelInput } from "@common/components";
import { css } from "@emotion/react";

const { SIGNUP } = ROUTES;

const LoginPage = () => {
  useHeader({ mode: "LOGIN", headingText: "로그인" });
  const { form, onChangeForm } = useForm<LoginFormType>({ email: "", password: "" });
  const { email, password } = form;
  const { handleLogin } = useAuth();

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
      />
      <Button
        css={css`
          width: 100%;
          margin-top: 2rem;
        `}
        onClick={() => handleLogin(form)}
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
