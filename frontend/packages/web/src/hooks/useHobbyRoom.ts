import { apiInstance, ENDPOINT_API } from "@/api";
import { QUERY_KEYS } from "@/constant";
import { HobbyRoomType } from "@/types";
import { useQuery } from "@tanstack/react-query";

const { HOBBY_ROOM } = QUERY_KEYS;
export const useHobbyRoom = (hobbyRoomDefaultInfo: HobbyRoomType) => {
  const { hobbyId } = hobbyRoomDefaultInfo;
  const getHobbyDetail = async () => {
    return await apiInstance.get(ENDPOINT_API + `/hobby/${hobbyId}/detail`).then(({ data }) => {
      console.log(data);
      return data.data;
    });
  };
  const { data: hobbyRoomInfo, refetch: refetchHobbyRoomInfo } = useQuery<HobbyRoomType>([HOBBY_ROOM, hobbyId], getHobbyDetail, { initialData: hobbyRoomDefaultInfo });
  return { hobbyRoomInfo, refetchHobbyRoomInfo };
};
