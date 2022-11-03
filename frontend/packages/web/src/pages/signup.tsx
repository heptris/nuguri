import { headerState } from "@/store";
import React, { useEffect } from "react";
import { useRecoilState } from "recoil";

const SignUpPage = () => {
  const [header, setHeader] = useRecoilState(headerState);
  useEffect(() => {
    setHeader({ mode: "LOGIN", headingText: "이메일로 회원가입" });
    return () => {
      setHeader({ ...header, headingText: undefined });
    };
  }, []);
  return <div>SignUpPage</div>;
};

export default SignUpPage;
