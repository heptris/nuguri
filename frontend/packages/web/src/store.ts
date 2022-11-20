import { ReactNode } from "react";
import { atom } from "recoil";

export type HeaderType = { mode: "EDIT" | "ITEM" | "MAIN" | "LIST" | "SEARCH" | "LOGIN" | "POST" | "DEFAULT" | "NOTHING"; headingText?: string; HeaderRight?: ReactNode };
export type SearchBarType = { placeholder: string; value: string };
export type BottomType = { children: ReactNode };
export type ListCategoryType = number;
export type PostType = number;
export type DealDetilType = number;

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
const bottomState = atom<BottomType>({
  key: "bottomState",
  default: { children: null },
});
const menuCategoryState = atom<ListCategoryType>({
  key: "hobbyState",
  default: null,
});
const postState = atom<PostType>({
  key: "postState",
  default: null,
});

const dealDetailCategoryId = atom<DealDetilType>({
  key: "dealCategoryIdState",
  default: null,
});
export { headerState, searchBarState, menuCategoryState, postState, bottomState, dealDetailCategoryId };
