import { useHeader } from "@/hooks";
import withAuth from "@/utils/withAuth";

const ProfilePage = () => {
  useHeader({ mode: "ITEM", headingText: undefined });

  return <div>ProfilePage</div>;
};

export default withAuth(ProfilePage);
