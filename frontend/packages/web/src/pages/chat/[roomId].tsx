import { css } from "@emotion/react";
import { Card } from "@mui/material";
import { useEffect, useRef, useState } from "react";

import { useBottom, useChatRoom, useHeader, useUser } from "@/hooks";
import { ChatMessageReceiveType } from "@/types";
import { Button, LabelInput, Text } from "@common/components";

export async function getServerSideProps({ params, query }) {
  const { roomId } = query;
  return {
    props: {
      roomId,
    },
  };
}
const ChatRoomPage = (props: { roomId: string }) => {
  const { roomId } = props;
  const { message, setMessage, sendMessage, chatData, chatHistoryData } = useChatRoom({ roomId });
  const [chatRoomData, setChatRoomData] = useState<ChatMessageReceiveType[]>([]);
  const { userInfo } = useUser();
  const { nickname } = userInfo;
  useHeader({ mode: "ITEM" });
  const scrollRef = useRef(null);
  const historyRef = useRef(null);
  const inputRef = useRef(null);
  const scrollToBottom = () => {
    scrollRef.current?.scrollIntoView({ behavior: "smooth", block: "start" });
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
            onKeyPress={e => {
              e.code === "Enter" && handleSendMessage();
            }}
            size={"medium"}
            css={css`
              float: left;
              width: 100%;
            `}
            autoComplete={"off"}
            autoFocus
            inputRef={inputRef}
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
    scrollToBottom();
  }, [chatData]);
  useEffect(() => {
    setBottom({ children: BottomComponent });
    scrollToBottom();
  }, [message]);

  useEffect(() => {
    console.log("방에서의 히스토리 정보", chatHistoryData);
  }, []);

  const handleSendMessage = () => {
    sendMessage();
    setMessage("");
    inputRef?.current.focus();
  };

  return (
    <>
      <div ref={historyRef} />
      <div>
        {chatHistoryData?.pages.map(page =>
          page.values
            .map(({ message, sender, chatTime }, i) => {
              const curTime = new Date(Date.parse(chatTime));
              const utc = curTime.toLocaleTimeString().slice(0, 7);
              return (
                <div
                  css={css`
                    display: flex;
                    flex-direction: column;
                    align-items: ${sender === nickname ? "flex-end" : "flex-start"};
                    margin: 0.5rem;
                  `}
                  key={i}
                >
                  <Text>{sender}</Text>
                  <Card
                    css={css`
                      padding: 1rem;
                    `}
                  >
                    {message}
                  </Card>
                  <Text
                    css={css`
                      font-size: 0.5rem;
                    `}
                  >
                    {utc}
                  </Text>
                </div>
              );
            })
            .reverse(),
        )}
      </div>
      <div>
        {chatRoomData?.map(({ message, sender, chatTime, messageType }, i) => {
          const curTime = new Date(Date.parse(chatTime));
          const utc = curTime.toLocaleTimeString().slice(0, 7);
          return (
            <div
              key={i}
              css={css`
                display: flex;
                flex-direction: column;
                align-items: ${sender === nickname ? "flex-end" : "flex-start"};
                margin: 0.5rem;
              `}
            >
              <Text>{sender}</Text>
              <Card
                css={css`
                  padding: 1rem;
                `}
              >
                {message}
              </Card>
              <Text
                css={css`
                  font-size: 0.5rem;
                `}
              >
                {utc}
              </Text>
            </div>
          );
        })}
      </div>
      <div
        css={css`
          height: 8rem;
        `}
        ref={scrollRef}
      />
    </>
  );
};

export default ChatRoomPage;
