import { useHeader, useSearchBar } from "@/hooks";

const SearchPage = () => {
  useHeader({ mode: "SEARCH", headingText: undefined });
  const { searchedValue } = useSearchBar("취미, 중고, 공구 관련 키워드 검색");

  return <div>SearchPage</div>;
};

export default SearchPage;
