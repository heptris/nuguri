import { useRouter } from "next/router";
import { regionState, RegionType } from "@/store";
import { useRecoilState } from "recoil";
import { ENDPOINT_API } from "./../api/endpoints";
import { useMutation } from "@tanstack/react-query";
import axios from "axios";
import { ROUTES } from "@/constant";


const { HOME } = ROUTES;
export const useLocation = () => {
  const [, setLocation] = useRecoilState(regionState);
  const postBaseAddress = async (keyword: string) => await axios.post(ENDPOINT_API + `/base-address/${keyword}/search`).then(({ data }) => data);
  const { data, mutate: handleSearchAddress, isLoading: isSearching } = useMutation(postBaseAddress);
  const handleBaseAddress = (address: RegionType) => {
    setLocation(address);
  };
  const router = useRouter();

  const handleSelectAddress = (address: RegionType) => {
    handleBaseAddress(address);
    router.push(HOME);
  };
  return { searchedData: data, handleSearchAddress, isSearching, handleSelectAddress };
};
