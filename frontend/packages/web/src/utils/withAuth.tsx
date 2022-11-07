import { NextPage } from "next";
import { useRouter } from "next/router";

import { ROUTES } from "@/constant";
import { useAlert, useAuth } from "@/hooks";
import { useEffect } from "react";

const { LOGIN } = ROUTES;
const withAuth = (Component: NextPage | React.FC) => {
  const Auth = () => {
    const { isLogined } = useAuth();
    const router = useRouter();
    const { handleAlertOpen } = useAlert();
    useEffect(() => {
      isLogined !== null && !isLogined && router.replace(LOGIN) && handleAlertOpen("로그인이 필요한 서비스입니다.", false, 1000);
    }, [isLogined]);
    if (isLogined) return <Component />;
  };

  return Auth;
};

export default withAuth;
