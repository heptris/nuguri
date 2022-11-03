import { headerState } from "@/store";
import React, { useEffect } from "react";
import { useRecoilState } from "recoil";

const LoungePage = () => {
  const [header, setHeader] = useRecoilState(headerState);
  useEffect(() => {
    setHeader({ mode: "LIST", headingText: "너구리 라운지" });
    return () => {
      setHeader({ ...header, headingText: undefined });
    };
  }, []);
  return <div>LoungePage</div>;
};

export default LoungePage;
