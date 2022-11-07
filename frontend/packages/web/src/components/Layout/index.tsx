import { css } from "@emotion/react";
import { Alert } from "@common/components";

import { useAlert, useLoading } from "@/hooks";
import Header from "./Header";
import BottomNavigation from "./BottomNavigation";

const Layout = ({ children }) => {
  const {
    alertInfo: { isOpened, isSuccess, message },
  } = useAlert();
  const { pageLoading } = useLoading();

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
