import { apiInstance, ENDPOINT_API } from "@/api";
import { ChatRoomInfoFindType } from "@/types";
import { useMutation } from "@tanstack/react-query";
import { useRouter } from "next/router";
import { findChatRoom } from "./useChatRoom";
import { useUser } from "./useUser";

export const useDealItem = ({ dealId, sellerId }: { dealId: number; sellerId: number }) => {
  const { userInfo } = useUser();
  console.log(sellerId, dealId, userInfo);
  const router = useRouter();
  const getDealHistoryId = async () => {
    return await apiInstance
      .post(ENDPOINT_API + `/deal-history/${dealId}/create`)
      .then(({ data }) => {
        console.log("history 받아온 정보", data);
        return data;
      })
      .catch(err => {
        console.error("이미 있는 히스토리 에러", err);
      });
  };
  const findDealChatRoom = async (info: ChatRoomInfoFindType<"DEAL">) =>
    await findChatRoom(info).then(({ data }) => {
      console.log("채팅방 찾음", data, info);
      router.push(`/chat/${data}?receiverId=${sellerId}`);
      console.log(data);
    });
  const { mutate: mutateToGetDealHistoryId } = useMutation(getDealHistoryId, {
    onSuccess: (data: { data: { dealHistoryId: number } }) => {
      console.log("히스토리 받아오기 성공", data);
      const { dealHistoryId } = data.data;
      findDealChatRoom({ dealHistoryId, receiverId: sellerId, senderId: userInfo.memberId });
    },
  });
  return { mutateToGetDealHistoryId };
};
