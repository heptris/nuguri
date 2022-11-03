import { headerState, searchBarState } from "@/store";
import React, { useEffect } from "react";
import { useRecoilState } from "recoil";

const SearchPage = () => {
  const [, setHeader] = useRecoilState(headerState);
  const [{ value }, setSearchBar] = useRecoilState(searchBarState);
  useEffect(() => {
    setHeader({ mode: "SEARCH", headingText: undefined });
    setSearchBar({ value, placeholder: "취미, 중고, 공구 관련 키워드 검색" });
    return () => {
      setSearchBar({ value: "", placeholder: "" });
    };
  }, []);
  return <div>SearchPage</div>;
};

export default SearchPage;
