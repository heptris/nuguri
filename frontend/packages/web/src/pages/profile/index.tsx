import { headerState } from "@/store";
import React, { useEffect } from "react";
import { useRecoilState } from "recoil";

const ProfilePage = () => {
  const [header, setHeader] = useRecoilState(headerState);
  useEffect(() => {
    setHeader({ mode: "ITEM", headingText: undefined });
  }, []);
  return <div>ProfilePage</div>;
};

export default ProfilePage;
