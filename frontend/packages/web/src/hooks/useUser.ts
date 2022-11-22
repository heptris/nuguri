import { apiInstance } from "./../api/index";
import { ENDPOINT_API } from "@/api";
import { useQuery, useQueryClient } from "@tanstack/react-query";
import { QUERY_KEYS } from "@/constant";
import { UserInfoType } from "@/types";

const { MY_PROFILE } = QUERY_KEYS;
export const useUser = () => {
  const client = useQueryClient();

  const setUserInfo = (userInfo: UserInfoType) => {
    client.setQueryData<UserInfoType>([MY_PROFILE], userInfo);
    client.refetchQueries([MY_PROFILE]);
  };

  const postProfile = async (nickname?: string) => {
    return await apiInstance.post(ENDPOINT_API + "/member", { nickname }).then(({ data }) => {
      console.log(data.data);
      setUserInfo(data.data);
      return data.data;
    });
  };

  const { data: userInfo } = useQuery<UserInfoType>([MY_PROFILE]);
  userInfo || setUserInfo({ ...userInfo, baseAddress: "전국", localId: 0 });

  return { postProfile, userInfo };
};
