import { useChatRoom } from "@/hooks";
import { ChatRoomInfoGetHistoryType, ChatRoomType } from "@/types";
import React from "react";

export async function getServerSideProps({ params, query }) {
  const { roomId, receiverId } = query;
  return {
    props: {
      roomId,
      receiverId,
    },
  };
}
const ChatRoomPage = <T extends ChatRoomType>(props: ChatRoomInfoGetHistoryType<T>) => {
  const getProps = () => {
    if ("receiverId" in props) {
      const { roomId, receiverId } = props;
      return { roomId, receiverId };
    }
    const { roomId, roomName } = props;
    return { roomId, roomName };
  };
  const { roomId, receiverId, roomName } = getProps();
  const { message, setMessage, sendMessage } = useChatRoom({ roomId, receiverId, roomName });

  return (
    <>
      <input
        value={message}
        onChange={e => {
          setMessage(e.target.value);
        }}
      />
      <button onClick={() => sendMessage()}>메세지 전송</button>
    </>
  );
};

export default ChatRoomPage;
