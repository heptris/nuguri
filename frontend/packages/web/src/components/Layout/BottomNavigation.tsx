import { useRouter } from "next/router";
import { HomeOutlined, ChatBubbleOutlineOutlined, AddBoxOutlined, PersonOutlineOutlined } from "@mui/icons-material";

import { ROUTES } from "@/constant";
import { BottomNavbar, BottomNavbarItem } from "@common/components";
import Link from "@/components/Link";
import { useRecoilState } from "recoil";
import { bottomState } from "@/store";
import withAuth from "@/utils/withAuth";

const { HOME, CHAT, LOUNGE, PROFILE } = ROUTES;

const BottomNavigation = () => {
  const [bottom] = useRecoilState(bottomState);
  const { children: BottomComponent } = bottom;
  const router = useRouter();
  return (
    <>
      {BottomComponent !== null ? (
        <BottomNavbar>{BottomComponent}</BottomNavbar>
      ) : BottomComponent === <></> ? (
        { BottomComponent }
      ) : (
        <BottomNavbar value={router.pathname}>
          <BottomNavbarItem label={"홈"} value={HOME} icon={<HomeOutlined />} component={Link} noLinkStyle href={HOME} />
          <BottomNavbarItem label={"채팅"} value={CHAT} icon={<ChatBubbleOutlineOutlined />} component={Link} noLinkStyle href={CHAT} />
          {/* <BottomNavbarItem label={"라운지"} value={LOUNGE} icon={<AddBoxOutlined />} component={Link} noLinkStyle href={LOUNGE} /> */}
          <BottomNavbarItem label={"프로필"} value={PROFILE} icon={<PersonOutlineOutlined />} component={Link} noLinkStyle href={PROFILE} />
        </BottomNavbar>
      )}
    </>
  );
};

export default withAuth(BottomNavigation);
