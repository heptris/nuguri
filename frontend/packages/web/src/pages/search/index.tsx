import { useHeader } from "@/hooks/useHeader";
import { headerState, searchBarState } from "@/store";
import React, { useEffect } from "react";
import { useRecoilState } from "recoil";

const SearchPage = () => {
  useHeader({ mode: "SEARCH", headingText: undefined });
  const [{ value }, setSearchBar] = useRecoilState(searchBarState);
  useEffect(() => {
    setSearchBar({ value, placeholder: "취미, 중고, 공구 관련 키워드 검색" });
    return () => {
      setSearchBar({ value: "", placeholder: "" });
    };
  }, []);
  return <div>SearchPage</div>;
};

export default SearchPage;
