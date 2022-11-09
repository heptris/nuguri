import axios from "axios";
import { useRouter } from "next/router";
import { useMutation } from "@tanstack/react-query";
import { useRecoilState } from "recoil";

import { regionState, RegionType } from "@/store";
import { ROUTES } from "@/constant";
import { ENDPOINT_API } from "./../api/endpoints";

const { HOME } = ROUTES;
export const useLocation = () => {
  const [, setLocation] = useRecoilState(regionState);
  const postBaseAddress = async (keyword: string) => await axios.post(ENDPOINT_API + `/base-address/${keyword}/search`).then(({ data }) => data);
  const { data: searchedData, mutate: handleSearchAddress, isLoading: isSearching } = useMutation(postBaseAddress);
  const handleBaseAddress = (local: RegionType) => {
    setLocation(local);
  };
  const router = useRouter();

  const handleSelectAddress = (local: RegionType) => {
    handleBaseAddress(local);
    router.push(HOME);
  };
  return { searchedData, handleSearchAddress, isSearching, handleSelectAddress };
};
