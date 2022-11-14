import { useRouter } from "next/router";
import React from "react";
import { HomeOutlined, ChatBubbleOutlineOutlined, AddBoxOutlined, PersonOutlineOutlined, LoginOutlined } from "@mui/icons-material";

import { ROUTES } from "@/constant";
import { useAuth } from "@/hooks";
import { BottomNavbar, BottomNavbarItem } from "@common/components";
import Link from "@/components/Link";

const { HOME, CHAT, LOUNGE, PROFILE, LOGIN } = ROUTES;

const BottomNavigation = () => {
  const router = useRouter();
  return (
    <BottomNavbar value={router.pathname}>
      <BottomNavbarItem label={"홈"} value={HOME} icon={<HomeOutlined />} component={Link} noLinkStyle href={HOME} />
      <BottomNavbarItem label={"채팅"} value={CHAT} icon={<ChatBubbleOutlineOutlined />} component={Link} noLinkStyle href={CHAT} />
      <BottomNavbarItem label={"라운지"} value={LOUNGE} icon={<AddBoxOutlined />} component={Link} noLinkStyle href={LOUNGE} />
      <BottomNavbarItem label={"프로필"} value={PROFILE} icon={<PersonOutlineOutlined />} component={Link} noLinkStyle href={PROFILE} />
    </BottomNavbar>
  );
};

export default BottomNavigation;
