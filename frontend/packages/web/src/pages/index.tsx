import { headerState } from "@/store";
import { useEffect } from "react";
import { useRecoilState } from "recoil";

const HomePage = () => {
  const [, setHeader] = useRecoilState(headerState);
  useEffect(() => {
    setHeader({ mode: "MAIN", headingText: undefined });
  }, []);
  return <div>Home</div>;
};
export default HomePage;
