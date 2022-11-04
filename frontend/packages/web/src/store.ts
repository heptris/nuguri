import { ReactNode } from "react";
import { atom } from "recoil";

export type HeaderType = { mode: "EDIT" | "ITEM" | "MAIN" | "LIST" | "SEARCH" | "LOGIN" | "POST" | "DEFAULT"; headingText?: string; HeaderRight?: ReactNode };
type RegionType = string;
type SearchBarType = {
  placeholder: string;
  value: string;
};
type AuthType = {
  isLogined: boolean;
};

const headerState = atom<HeaderType>({
  key: "headerState",
  default: { mode: "MAIN" },
});
const regionState = atom<RegionType>({
  key: "regionState",
  default: "전국",
});
const searchBarState = atom<SearchBarType>({
  key: "searchBarState",
  default: {
    placeholder: "",
    value: "",
  },
});
const authState = atom<AuthType>({
  key: "authState",
  default: {
    isLogined: false,
  },
});

export { headerState, regionState, searchBarState, authState };
