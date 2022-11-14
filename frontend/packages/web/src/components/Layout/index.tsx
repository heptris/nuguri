import { css } from "@emotion/react";
import { Alert } from "@common/components";

import { useAlert, useAuth, useLoading, useUser } from "@/hooks";
import Header from "./Header";
import BottomNavigation from "./BottomNavigation";
import { useEffect } from "react";

const Layout = ({ children }) => {
  const {
    alertInfo: { isOpened, isSuccess, message },
  } = useAlert();
  const { pageLoading } = useLoading();
  const { isLogined } = useAuth();
  const { postProfile } = useUser();
  useEffect(() => {
    isLogined && postProfile();
  }, [isLogined]);

  return (
    <>
      {pageLoading && (
        <div
          css={css`
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            background-color: rgba(255, 255, 255, 0.8);
            z-index: 1000;
          `}
        >
          Loading...
        </div>
      )}
      <Header />
      <main
        css={css`
          position: relative;
          top: 4rem;
          padding-bottom: 4rem;
        `}
      >
        {isOpened && <Alert color={isSuccess ? "success" : "error"}>{message}</Alert>}
        {children}
      </main>
      <BottomNavigation />
    </>
  );
};

export default Layout;
