import { apiInstance, ENDPOINT_API } from "@/api";
import { QUERY_KEYS } from "@/constant";
import { useQuery } from "@tanstack/react-query";
import { useUser } from "./useUser";

const { CHAT_LIST } = QUERY_KEYS;
const useChatList = () => {
  const {
    userInfo: { memberId },
  } = useUser();
  const getChatList = async () => {
    return await apiInstance.get(ENDPOINT_API + `/chat/room/${memberId}`).then(res => {
      console.log(res);
      return res.data;
    });
  };
  const { data: chatListData } = useQuery([CHAT_LIST], getChatList);
  return { chatListData };
};

export { useChatList };
