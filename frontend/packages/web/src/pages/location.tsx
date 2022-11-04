import { useHeader } from "@/hooks";
import { searchBarState } from "@/store";
import { useEffect } from "react";
import { useRecoilState } from "recoil";

const LocationPage = () => {
  useHeader({ mode: "SEARCH", headingText: undefined });

  const [{ value }, setSearchBar] = useRecoilState(searchBarState);
  useEffect(() => {
    setSearchBar({ value, placeholder: "내 동네 이름(동, 읍, 면)으로 검색" });
    return () => {
      setSearchBar({ value: "", placeholder: "" });
    };
  }, []);
  return <div>LocationPage</div>;
};

export default LocationPage;
