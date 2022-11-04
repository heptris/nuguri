import { atom } from "recoil";

type HeaderType = { mode: "EDIT" | "ITEM" | "MAIN" | "LIST" | "SEARCH" | "LOGIN"; headingText?: string };
type RegionType = string;
type SearchBarType = {
  placeholder: string;
  value: string;
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

export { headerState, regionState, searchBarState };
