import axios from "axios";
import { useRouter } from "next/router";
import { useMutation, useQueryClient } from "@tanstack/react-query";

import { QUERY_KEYS, ROUTES } from "@/constant";
import { ENDPOINT_API } from "./../api/endpoints";
import { UserInfoType } from "@/types";
import { useEffect, useState } from "react";

const { HOME } = ROUTES;
const { MY_PROFILE } = QUERY_KEYS;

type LocationInfoType = {
  localId: number;
  baseAddress: string;
};

export const useLocation = () => {
  const client = useQueryClient();
  const userInfo = client.getQueryData<UserInfoType>([MY_PROFILE]);
  const [curBaseAddress, setCurBaseAddress] = useState("전국");
  useEffect(() => {
    userInfo || client.setQueryData([MY_PROFILE], { baseAddress: "전국", localId: 0 });
  }, []);
  useEffect(() => {
    userInfo && setCurBaseAddress(userInfo.baseAddress);
  }, [userInfo]);
  const postToFindBaseAddress = async (keyword: string) => await axios.post(ENDPOINT_API + `/base-address/${keyword}/search`).then(({ data }) => data);
  const { data: searchedData, mutate: handleSearchAddress, isLoading: isSearching, reset: resetSearchedData } = useMutation(postToFindBaseAddress);
  const handleBaseAddress = ({ baseAddress, localId }: LocationInfoType) => {
    client.setQueryData([MY_PROFILE], { ...userInfo, baseAddress, localId });
  };
  const router = useRouter();

  const handleSelectAddress = (locationInfo: LocationInfoType) => {
    handleBaseAddress(locationInfo);
    router.push(HOME);
  };

  return { searchedData, handleSearchAddress, isSearching, handleSelectAddress, curBaseAddress, resetSearchedData };
};
