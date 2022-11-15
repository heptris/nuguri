import { useBottom, useChatRoom, useHeader, useUser } from "@/hooks";
import { ChatMessageReceiveType, ChatRoomInfoGetHistoryType, ChatRoomMessageInfoType, ChatRoomType } from "@/types";
import { Button, LabelInput } from "@common/components";
import { css } from "@emotion/react";
import React, { useEffect, useRef, useState } from "react";

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
  const { message, setMessage, sendMessage, chatData } = useChatRoom({ roomId, receiverId, roomName });
  const [chatRoomData, setChatRoomData] = useState<ChatMessageReceiveType[]>([]);
  const { userInfo } = useUser();
  const { nickname } = userInfo;
  useHeader({ mode: "ITEM" });
  const ref = useRef(null);
  const scrollToBottom = () => {
    ref.current?.scrollIntoView({ behavior: "smooth" });
  };
  const BottomComponent = (
    <div
      css={css`
        position: fixed;
        bottom: 0;
        left: 0;
        right: 0;
      `}
    >
      <div>
        <div
          css={css`
            display: flex;
            justify-content: space-between;
          `}
        >
          <LabelInput
            placeholder="메세지 입력하기"
            value={message}
            onChange={e => {
              setMessage(e.target.value);
            }}
            onKeyDown={e => {
              e.code === "Enter" && handleSendMessage();
            }}
            size={"medium"}
            css={css`
              float: left;
              width: 100%;
            `}
          />
          <Button onClick={() => handleSendMessage()} size={"small"} type={"button"}>
            메세지 전송
          </Button>
        </div>
      </div>
    </div>
  );
  const { setBottom } = useBottom(<>{BottomComponent}</>);

  useEffect(() => {
    chatData && setChatRoomData([...chatRoomData, chatData]);
    console.log(chatRoomData);
  }, [chatData]);
  useEffect(() => {
    setBottom({ children: BottomComponent });
    scrollToBottom();
  }, [message]);

  const handleSendMessage = () => {
    sendMessage();
    setMessage("");
  };

  return (
    <>
      <div
        css={css`
          padding-bottom: 2rem;
        `}
      >
        {chatRoomData?.map(({ message, sender, chatTime, messageType }) => {
          const curTime = new Date(Date.parse(chatTime));
          const utc = curTime.toLocaleDateString() + curTime.toLocaleTimeString();
          return (
            <div
              css={css`
                overflow: hidden;
              `}
            >
              <div
                css={css`
                  float: ${sender === nickname ? `right` : `left`};
                `}
              >
                <span>{sender}</span>
                <span>{message}</span>
                <div>{utc}</div>
              </div>
            </div>
          );
        })}
      </div>
      <div ref={ref} />
    </>
  );
};

export default ChatRoomPage;
