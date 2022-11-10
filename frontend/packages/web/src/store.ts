import { ReactNode } from "react";
import { atom } from "recoil";

export type HeaderType = { mode: "EDIT" | "ITEM" | "MAIN" | "LIST" | "SEARCH" | "LOGIN" | "POST" | "DEFAULT"; headingText?: string; HeaderRight?: ReactNode };
export type RegionType = {
  regionName: string;
  regionId: number;
};
export type SearchBarType = {
  placeholder: string;
  value: string;
};
export type ProfileType = {
  nickName: string;
  temperature: number;
}

const headerState = atom<HeaderType>({
  key: "headerState",
  default: { mode: "MAIN" },
});
const regionState = atom<RegionType>({
  key: "regionState",
  default: {
    regionName: "전국",
    regionId: null,
  },
});
const searchBarState = atom<SearchBarType>({
  key: "searchBarState",
  default: {
    placeholder: "",
    value: "",
  },
});

const profileState = atom<ProfileType>({
  key: "profileState",
  default: {
    nickName: "",
    temperature: null,
  }
})

export { headerState, regionState, searchBarState, profileState };
