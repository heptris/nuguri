import { css } from "@emotion/react";
import { Alert } from "@common/components";

import { useAlert, useAuth, useLoading, useSSE } from "@/hooks";
import Header from "./Header";
import BottomNavigation from "./BottomNavigation";
import { useRouter } from "next/router";
import { ROUTES } from "@/constant";

const { MAIN } = ROUTES;
const Layout = ({ children }) => {
  const {
    alertInfo: { isOpened, isSuccess, message },
  } = useAlert();
  const { pageLoading } = useLoading();
  useAuth();
  const { pathname } = useRouter();
  useSSE();
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
          top: ${pathname === MAIN ? "0" : "4rem"};
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
