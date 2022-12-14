import { useInfiniteQuery, useQuery, useQueryClient } from "@tanstack/react-query";
import { useEffect, useRef, useState } from "react";
import { Stomp } from "@stomp/stompjs";
import SockJS from "sockjs-client";

import { ENDPOINT_API, ENDPOINT_WS, apiInstance } from "@/api";
import { QUERY_KEYS } from "@/constant";

import type { ChatRoomInfoFindType, ChatRoomInfoGetHistoryType, ChatRoomMessageInfoType, ChatRoomType, UserInfoType } from "@/types";

const CHAT_API = "/pub/chat";
const { MY_PROFILE, CHAT_ROOM_HISTORY } = QUERY_KEYS;

const JSONToString = <T>(json: T) => JSON.stringify(json);

const useChatRoom = <T extends ChatRoomType>(props: { roomId: string }) => {
  // const getProps = () => {
  //   if ("receiverId" in props) {
  //     const { roomId, receiverId } = props;
  //     return { roomId, receiverId };
  //   }
  //   const { roomId, roomName } = props;
  //   return { roomId, roomName };
  // };
  const { roomId } = props;
  const [message, setMessage] = useState("");
  const queryClient = useQueryClient();
  const { memberId: senderId } = queryClient.getQueryData<UserInfoType>([MY_PROFILE]);
  const wsClient = useRef(null);

  const getChatRoomHistory = async (info: { roomId: string }) => {
    return await apiInstance.post(ENDPOINT_API + "/chat/room/log", info).then(data => {
      console.log("history 불러온 것", data);
      return data.data.data;
    });
  };
  const { data: chatHistoryData, fetchPreviousPage: fetchPreviousHistory } = useInfiniteQuery([CHAT_ROOM_HISTORY, roomId], {
    queryFn: () => getChatRoomHistory({ roomId }),
    getPreviousPageParam: (firstPage, pages) => firstPage.cursorId,
  });
  const [chatData, setChatData] = useState();

  useEffect(() => {
    const socket = new SockJS(ENDPOINT_WS);
    wsClient.current = Stomp.over(socket);
    getChatRoomHistory({ roomId });
    wsClient.current.connect({}, function () {
      wsClient.current.subscribe(`/sub/chat/room/${roomId}`, function (greeting) {
        // console.log(JSON.parse(greeting.body));
        setChatData(JSON.parse(greeting.body));
      });
      // wsClient.current.send(
      //   CHAT_API,
      //   {},
      //   JSONToString<ChatRoomMessageInfoType<"ENTER">>({
      //     senderId,
      //     roomId,
      //     messageType: "ENTER",
      //   }),
      // );
    });

    return () => {
      leaveChatRoom();
      wsClient.current.disconnect();
    };
  }, []);

  const sendMessage = () => {
    !!message &&
      wsClient.current.send(
        CHAT_API,
        {},
        JSONToString<ChatRoomMessageInfoType<"TALK">>({
          message,
          senderId,
          roomId,
          messageType: "TALK",
        }),
      );
  };

  const leaveChatRoom = async () => {
    // wsClient.current.send(
    //   CHAT_API,
    //   {},
    //   JSONToString<ChatRoomMessageInfoType<"LEAVE">>({
    //     messageType: "LEAVE",
    //     roomId,
    //     senderId,
    //   }),
    // );
  };

  return { setMessage, message, sendMessage, leaveChatRoom, chatData, chatHistoryData, fetchPreviousHistory };
};

const findChatRoom = async <T extends ChatRoomType>(info: ChatRoomInfoFindType<T>) => {
  return await apiInstance.post(ENDPOINT_API + "/chat/room", info).then(({ data }) => {
    return data;
  });
};

export { findChatRoom, useChatRoom };
