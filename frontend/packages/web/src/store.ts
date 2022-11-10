import { ReactNode } from "react";
import { atom } from "recoil";

export type HeaderType = { mode: "EDIT" | "ITEM" | "MAIN" | "LIST" | "SEARCH" | "LOGIN" | "POST" | "DEFAULT"; headingText?: string; HeaderRight?: ReactNode };
export type RegionType = {
  baseAddress: string;
  localId: number;
};
export type SearchBarType = {
  placeholder: string;
  value: string;
};
export type ProfileType = {
  nickName: string;
  temperature: number;
}
export type HobbyType = string;

const headerState = atom<HeaderType>({
  key: "headerState",
  default: { mode: "MAIN" },
});
const regionState = atom<RegionType>({
  key: "regionState",
  default: {
    baseAddress: "전국",
    localId: null,
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

const hobbyState = atom<HobbyType>({
  key: "hobbyState",
  default: null,
})

export { headerState, regionState, searchBarState, profileState, hobbyState };
