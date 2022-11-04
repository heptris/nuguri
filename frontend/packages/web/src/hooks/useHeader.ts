import { useEffect } from "react";
import { useRecoilState } from "recoil";
import { headerState, HeaderType } from "@/store";

const useHeader = (headerInfo: HeaderType) => {
  const [, setHeader] = useRecoilState(headerState);
  useEffect(() => {
    setHeader(headerInfo);
    return () => {
      setHeader({ mode: "DEFAULT" });
    };
  }, []);
};

export { useHeader };
