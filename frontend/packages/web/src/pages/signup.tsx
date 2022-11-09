import { useHeader } from "@/hooks";

const SignUpPage = () => {
  useHeader({ mode: "LOGIN", headingText: "이메일로 회원가입" });
  return <div>SignUpPage</div>;
};

export default SignUpPage;
