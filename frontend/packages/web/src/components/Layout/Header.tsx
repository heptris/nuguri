import React, { useMemo } from "react";
import { useRecoilState } from "recoil";
import { css } from "@emotion/react";
import { AppBar, Icon, LabelInput, Text } from "@common/components";
import { useRouter } from "next/router";

import { useLocation } from "@/hooks";
import { headerState, searchBarState } from "@/store";
import Link from "../Link";
import { ROUTES } from "@/constant";

const { LOCATION, SEARCH, NOTIFICATION, POST } = ROUTES;

const Header = () => {
  const [{ mode: appBarMode, headingText, HeaderRight }] = useRecoilState(headerState);
  const [searchBar, setSearchBar] = useRecoilState(searchBarState);
  const router = useRouter();
  const { curBaseAddress: baseAddress } = useLocation();

  const LeftChild = useMemo(() => {
    const GoBackComponent = <Icon mode={"GOBACK"} onClick={() => router.back()} />;
    switch (appBarMode) {
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
            <Text>{baseAddress}</Text>
          </div>
        );
      case "MAIN":
        return (
          <Link href={LOCATION} noLinkStyle>
            <Icon mode={"MYLOCATION"} />
            <Text>{baseAddress}</Text>
          </Link>
        );
      default:
        return GoBackComponent;
    }
  }, [appBarMode, searchBar, baseAddress]);
  const RightChild = useMemo(() => {
    const NotificaationComponent = <Icon mode={"NOTIFICATION"} component={Link} noLinkStyle href={NOTIFICATION} />;
    switch (appBarMode) {
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
            <Icon mode={"ADDCIRCLE"} component={Link} noLinkStyle href={POST} />
            <Icon mode={"SEARCH"} component={Link} noLinkStyle href={SEARCH} />
          </div>
        );
      default:
        return HeaderRight;
    }
  }, [appBarMode]);

  return <>{appBarMode === "NOTHING" ? <></> : <AppBar LeftChild={LeftChild} RightChild={RightChild} heading={headingText} />}</>;
};

export default Header;
