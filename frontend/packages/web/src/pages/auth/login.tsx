import Link from "@/components/Link";
import { ROUTES } from "@/constant";
import { useForm } from "@/hooks";
import { headerState } from "@/store";
import { Button, LabelInput } from "@common/components";
import { css } from "@emotion/react";
import React, { useEffect } from "react";
import { useRecoilState } from "recoil";

type LoginFormType = {
  email: string;
  password: string;
};
const { SIGNUP } = ROUTES;

const LoginPage = () => {
  const [header, setHeader] = useRecoilState(headerState);
  const {
    form: { email, password },
    onChangeForm,
  } = useForm<LoginFormType>({ email: "", password: "" });

  useEffect(() => {
    setHeader({ mode: "LOGIN", headingText: "로그인" });
    return () => {
      setHeader({ ...header, headingText: undefined });
    };
  }, []);

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
