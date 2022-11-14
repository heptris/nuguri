import { apiInstance } from "./../api/index";
import { ENDPOINT_API } from "@/api";
import { useQuery, useQueryClient } from "@tanstack/react-query";
import { QUERY_KEYS } from "@/constant";
import { UserInfoType } from "@/types";

const { MY_PROFILE } = QUERY_KEYS;
export const useUser = () => {
  const client = useQueryClient();

  const postProfile = async (nickname?: string) => {
    return await apiInstance
      .post(ENDPOINT_API + "/member", { nickname })
      .then(({ data }) => {
        console.log(data.data);
        client.setQueryData<UserInfoType>([MY_PROFILE], data.data);
        client.invalidateQueries([MY_PROFILE]);
        return data.data;
      })
      .catch(e => {
        console.log(e);
      });
  };

  const { data: userInfo } = useQuery<UserInfoType>([MY_PROFILE]);
  userInfo || client.setQueryData([MY_PROFILE], { baseAddress: "전국", localId: 0 });

  return { postProfile, userInfo };
};
