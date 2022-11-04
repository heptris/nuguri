import { useRouter } from "next/router";
import { HomeOutlined, ChatBubbleOutlineOutlined, AddBoxOutlined, PersonOutlineOutlined, LoginOutlined } from "@mui/icons-material";
import { css } from "@emotion/react";
import { BottomNavbar, BottomNavbarItem } from "@common/components";

import Link from "@/components/Link";
import { ROUTES } from "@/constant";
import Header from "./Header";
import { useAuth } from "@/hooks";

const { HOME, CHAT, LOUNGE, PROFILE, LOGIN } = ROUTES;

const Layout = ({ children }) => {
  const { isLogined } = useAuth();
  const router = useRouter();
  return (
    <>
      <Header />
      <main
        css={css`
          position: relative;
          top: 4rem;
        `}
      >
        {children}
      </main>
      <BottomNavbar value={router.pathname}>
        <BottomNavbarItem label={"홈"} value={HOME} icon={<HomeOutlined />} component={Link} noLinkStyle href={HOME} />
        <BottomNavbarItem label={"채팅"} value={CHAT} icon={<ChatBubbleOutlineOutlined />} component={Link} noLinkStyle href={CHAT} />
        <BottomNavbarItem label={"라운지"} value={LOUNGE} icon={<AddBoxOutlined />} component={Link} noLinkStyle href={LOUNGE} />
        {isLogined ? (
          <BottomNavbarItem label={"프로필"} value={PROFILE} icon={<PersonOutlineOutlined />} component={Link} noLinkStyle href={PROFILE} />
        ) : (
          <BottomNavbarItem label={"로그인"} value={LOGIN} icon={<LoginOutlined />} component={Link} noLinkStyle href={LOGIN} />
        )}
      </BottomNavbar>
    </>
  );
};

export default Layout;
