import { QUERY_KEYS } from "@/constant";
import { useAuth, useHeader } from "@/hooks";
import withAuth from "@/utils/withAuth";
import { Button } from "@common/components";
import { useQueryClient } from "@tanstack/react-query";
import { useEffect } from "react";

const { MY_PROFILE } = QUERY_KEYS;
const ProfilePage = () => {
  useHeader({ mode: "ITEM", headingText: undefined });
  const { handleLogout } = useAuth();
  const client = useQueryClient();
  useEffect(() => {
    console.log(client.getQueryData([MY_PROFILE]));
  }, []);

  return (
    <div>
      <Button onClick={() => handleLogout()}>로그아웃</Button>
    </div>
  );
};

export default withAuth(ProfilePage);
