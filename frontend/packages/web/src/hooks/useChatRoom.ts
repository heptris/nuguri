import { useQueryClient } from "@tanstack/react-query";
import { useEffect, useRef, useState } from "react";
import { Stomp } from "@stomp/stompjs";
import SockJS from "sockjs-client";

import { ENDPOINT_API, ENDPOINT_WS, apiInstance } from "@/api";
import { QUERY_KEYS } from "@/constant";

import type { ChatRoomInfoFindType, ChatRoomInfoGetHistoryType, ChatRoomMessageInfoType, ChatRoomType, UserInfoType } from "@/types";

const CHAT_API = "/pub/chat";
const { MY_PROFILE } = QUERY_KEYS;

const JSONToString = <T>(json: T) => JSON.stringify(json);

const useChatRoom = <T extends ChatRoomType>(props: ChatRoomInfoGetHistoryType<T>) => {
  const getProps = () => {
    if ("receiverId" in props) {
      const { roomId, receiverId } = props;
      return { roomId, receiverId };
    }
    const { roomId, roomName } = props;
    return { roomId, roomName };
  };
  const { roomId, receiverId, roomName } = getProps();
  const [message, setMessage] = useState("");
  const queryClient = useQueryClient();
  const { memberId: senderId } = queryClient.getQueryData<UserInfoType>([MY_PROFILE]);
  const wsClient = useRef(null);

  const getChatRoomHistory = async (info: ChatRoomInfoGetHistoryType<T>) => {
    return await apiInstance.post(ENDPOINT_API + "/chat/room/log", info).then(data => {
      console.log(data);
      return data;
    });
  };

  useEffect(() => {
    const socket = new SockJS(ENDPOINT_WS);

    wsClient.current = Stomp.over(socket);
    getChatRoomHistory({ receiverId, roomId, senderId, roomName });
    wsClient.current.connect({}, function () {
      wsClient.current.subscribe(`/sub/chat/room/${roomId}`, function (greeting) {
        console.log("연결시", JSON.parse(greeting.body));
      });
      wsClient.current.send(
        CHAT_API,
        {},
        JSONToString<ChatRoomMessageInfoType<"ENTER">>({
          senderId,
          roomId,
          messageType: "ENTER",
        }),
      );
    });

    return () => {
      wsClient.current.disconnect();
    };
  }, []);

  const sendMessage = () => {
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
    wsClient.current.send(
      CHAT_API,
      {},
      JSONToString<ChatRoomMessageInfoType<"LEAVE">>({
        messageType: "LEAVE",
        roomId,
        senderId,
      }),
    );
  };

  return { setMessage, message, sendMessage, leaveChatRoom };
};

const findChatRoom = async <T extends ChatRoomType>(info: ChatRoomInfoFindType<T>) => {
  return await apiInstance.post(ENDPOINT_API + "/chat/room", info).then(({ data }) => {
    return data;
  });
};

export { findChatRoom, useChatRoom };