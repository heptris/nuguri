import { headerState } from "@/store";
import React, { useEffect } from "react";
import { useRecoilState } from "recoil";

const NotificationPage = () => {
  const [header, setHeader] = useRecoilState(headerState);
  useEffect(() => {
    setHeader({ mode: "ITEM", headingText: "알림" });
    return () => {
      setHeader({ ...header, headingText: undefined });
    };
  }, []);
  return <div>NotificationPage</div>;
};

export default NotificationPage;
