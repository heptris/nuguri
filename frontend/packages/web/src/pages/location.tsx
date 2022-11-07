import { useSearchBar, useHeader } from "@/hooks";

const LocationPage = () => {
  useHeader({ mode: "SEARCH", headingText: undefined });
  const { searchedValue } = useSearchBar("내 동네 이름(동, 읍, 면)으로 검색");

  return <div>LocationPage</div>;
};

export default LocationPage;
