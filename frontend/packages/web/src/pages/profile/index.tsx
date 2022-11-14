import { useHeader, useAuth } from "@/hooks";
import withAuth from "@/utils/withAuth";
import { Button } from "@common/components";

const ProfilePage = () => {
  useHeader({ mode: "ITEM", headingText: undefined });
  const { handleLogout } = useAuth();

  return (
    <div>
      <Button onClick={() => handleLogout()}>로그아웃</Button>
    </div>
  );
};

export default withAuth(ProfilePage);
