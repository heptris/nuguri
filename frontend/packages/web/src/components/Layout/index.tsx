import React, { useMemo } from "react";
import { useRouter } from "next/router";
import { HomeOutlined, ChatBubbleOutlineOutlined, AddBoxOutlined, PersonOutlineOutlined } from "@mui/icons-material";
import { css } from "@emotion/react";
import { useRecoilState } from "recoil";
import { BottomNavbar, BottomNavbarItem, Button, Icon, LabelInput, Text } from "@common/components";

import { headerState, regionState, searchBarState } from "@/store";
import Link from "@/components/Link";
import { ROUTES } from "@/constant";
import Header from "./Header";

const { HOME, CHAT, LOUNGE, PROFILE, LOCATION, SEARCH, NOTIFICATION } = ROUTES;

const Layout = ({ children }) => {
  const [{ mode: appBarMode, headingText }] = useRecoilState(headerState);
  const [region] = useRecoilState(regionState);
  const [searchBar, setSearchBar] = useRecoilState(searchBarState);
  const router = useRouter();

  const LeftChild = useMemo(() => {
    const GoBackComponent = <Icon mode={"GOBACK"} onClick={() => router.back()} />;
    switch (appBarMode) {
      case "EDIT":
      case "ITEM":
        return GoBackComponent;
      case "SEARCH":
        const { placeholder, value } = searchBar;
        return (
          <>
            {GoBackComponent}
            <LabelInput
              size="small"
              css={css`
                width: 100%;
              `}
              placeholder={placeholder}
              value={value}
              onChange={e => setSearchBar({ placeholder, value: e.target.value })}
            />
          </>
        );
      case "LIST":
        return (
          <div>
            {GoBackComponent}
            <Text>{region}</Text>
          </div>
        );
      case "MAIN":
        return (
          <Link href={LOCATION}>
            <Icon mode={"MYLOCATION"} />
            <Text>{region}</Text>
          </Link>
        );
      default:
        return <>{GoBackComponent}</>;
    }
  }, [appBarMode, searchBar]);
  const RightChild = useMemo(() => {
    const NotificaationComponent = <Icon mode={"NOTIFICATION"} component={Link} noLinkStyle href={NOTIFICATION} />;
    switch (appBarMode) {
      case "EDIT":
        return <Button>완료</Button>;
      case "ITEM":
        return (
          <div>
            {NotificaationComponent}
            <Icon mode={"MENU"} />
          </div>
        );
      case "LIST":
      case "MAIN":
        return (
          <div>
            {NotificaationComponent}
            <Icon mode={"ADDCIRCLE"} />
            <Icon mode={"SEARCH"} component={Link} noLinkStyle href={SEARCH} />
          </div>
        );
      default:
        return <></>;
    }
  }, [appBarMode]);

  return (
    <>
      <Header LeftChild={LeftChild} RightChild={RightChild} heading={headingText} />
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
        <BottomNavbarItem label={"프로필"} value={PROFILE} icon={<PersonOutlineOutlined />} component={Link} noLinkStyle href={PROFILE} />
      </BottomNavbar>
    </>
  );
};

export default Layout;
