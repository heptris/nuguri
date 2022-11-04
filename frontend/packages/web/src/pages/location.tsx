import { headerState, searchBarState } from "@/store";
import React, { useEffect } from "react";
import { useRecoilState } from "recoil";

const LocationPage = () => {
  const [, setHeader] = useRecoilState(headerState);
  const [{ value }, setSearchBar] = useRecoilState(searchBarState);
  useEffect(() => {
    setHeader({ mode: "SEARCH", headingText: undefined });
    setSearchBar({ value, placeholder: "내 동네 이름(동, 읍, 면)으로 검색" });
    return () => {
      setSearchBar({ value: "", placeholder: "" });
    };
  }, []);
  return <div>LocationPage</div>;
};

export default LocationPage;
