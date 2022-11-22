import { useEffect } from "react";
import { useRecoilState } from "recoil";

import { searchBarState } from "@/store";

export const useSearchBar = (placeholder?: string) => {
  const [{ value }, setSearchBar] = useRecoilState(searchBarState);
  useEffect(() => {
    setSearchBar({ value, placeholder });
    return () => {
      setSearchBar({ value: "", placeholder: "" });
    };
  }, []);
  return { searchedValue: value };
};
