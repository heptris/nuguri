import { useEffect } from "react";
import { useSearchBar, useHeader, useLocation } from "@/hooks";

const LocationPage = () => {
  useHeader({ mode: "SEARCH", headingText: undefined });
  const { searchedValue } = useSearchBar("내 동네 이름(동, 읍, 면)으로 검색");
  const { handleSearchAddress, searchedData, isSearching, handleSelectAddress } = useLocation();
  useEffect(() => {
    handleSearchAddress(searchedValue);
  }, [searchedValue]);

  if (isSearching) return <div>Loading...</div>;

  return (
    <>
      <div>{searchedData?.message}</div>
      <div>
        {searchedData?.data.map(({ baseAddress, localId }) => {
          return (
            <div key={localId} onClick={() => handleSelectAddress(baseAddress)}>
              {baseAddress}
            </div>
          );
        })}
      </div>
    </>
  );
};

export default LocationPage;
