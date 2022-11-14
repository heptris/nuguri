import { bottomState } from "@/store";
import { ReactNode, useEffect } from "react";
import { useRecoilState } from "recoil";

export const useBottom = (BottomComponent: ReactNode) => {
  const [, setBottom] = useRecoilState(bottomState);
  useEffect(() => {
    setBottom({ children: BottomComponent });
    return () => {
      setBottom({ children: null });
    };
  }, []);
};
