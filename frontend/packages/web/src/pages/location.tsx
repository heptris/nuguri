import { useEffect } from "react";
import { useSearchBar, useHeader, useLocation } from "@/hooks";
import { css } from "@emotion/react";

const LocationPage = () => {
  useHeader({ mode: "SEARCH", headingText: undefined });
  const { searchedValue } = useSearchBar("내 동네 이름(동, 읍, 면)으로 검색");
  const { handleSearchAddress, searchedData, isSearching, handleSelectAddress } = useLocation();
  useEffect(() => {
    searchedValue && handleSearchAddress(searchedValue);
  }, [searchedValue]);

  if (isSearching) return <div>Loading...</div>;

  return (
    <>
      <div> {!!searchedValue ? searchedData?.message : "내 동네 검색하기"}</div>
      <>
        {searchedData?.data.map(({ baseAddress, localId }) => {
          return (
            <div
              css={css`
                cursor: pointer;
                border: 1px black solid;
                margin-bottom: 1rem;
              `}
              key={localId}
              onClick={() => handleSelectAddress(baseAddress)}
            >
              {baseAddress}
            </div>
          );
        })}
      </>
    </>
  );
};

export default LocationPage;
