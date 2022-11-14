import { ReactNode } from "react";
import { atom } from "recoil";

export type HeaderType = { mode: "EDIT" | "ITEM" | "MAIN" | "LIST" | "SEARCH" | "LOGIN" | "POST" | "DEFAULT"; headingText?: string; HeaderRight?: ReactNode };

export type SearchBarType = {
  placeholder: string;
  value: string;
};
export type HobbyType = string;

const headerState = atom<HeaderType>({
  key: "headerState",
  default: { mode: "MAIN" },
});
const searchBarState = atom<SearchBarType>({
  key: "searchBarState",
  default: {
    placeholder: "",
    value: "",
  },
});

const hobbyState = atom<HobbyType>({
  key: "hobbyState",
  default: null,
})

export { headerState, searchBarState, hobbyState };
