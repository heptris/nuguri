import { useEffect } from "react";
import { useSearchBar, useHeader, useLocation, useDebounce } from "@/hooks";
import { css } from "@emotion/react";
import SearchedItem from "@/components/Searcheditem";
import { Button, Text } from "@common/components";

const LocationPage = () => {
  useHeader({ mode: "SEARCH", headingText: undefined });
  const { searchedValue } = useSearchBar("내 동네 이름(동, 읍, 면)으로 검색");
  const debouncedSearchedValue = useDebounce(searchedValue);
  const { handleSearchAddress, searchedData, isSearching, handleSelectAddress } = useLocation();
  useEffect(() => {
    debouncedSearchedValue && handleSearchAddress(debouncedSearchedValue);
  }, [debouncedSearchedValue]);
  console.log(searchedData);

  return (
    <div
      css={css`
        display: flex;
        flex-direction: column;
        margin: 0.5rem;
      `}
    >
      <Button size={"large"}>현재 위치로 찾기</Button>
      <Text
        css={css`
          font-size: 1.2rem;
          margin-top: 1.2rem;
          font-weight: bold;
        `}
      >
        {!!debouncedSearchedValue ? `'${debouncedSearchedValue}' 검색 결과` : isSearching ? "검색 중..." : "내 동네 검색하기"}
      </Text>
      <>
        {!!debouncedSearchedValue &&
          searchedData?.data.map(({ baseAddress, localId }) => {
            return (
              <SearchedItem key={localId} onClick={() => handleSelectAddress({ baseAddress, localId })}>
                {baseAddress}
              </SearchedItem>
            );
          })}
      </>
    </div>
  );
};

export default LocationPage;
