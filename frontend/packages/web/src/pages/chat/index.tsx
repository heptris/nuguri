import { useEffect } from "react";
import { List } from "@common/components";
import { useRecoilState } from "recoil";
import { headerState } from "@/store";
import ChatListItem from "@/components/ChatListItem";

const ChatListPage = () => {
  const [header, setHeader] = useRecoilState(headerState);
  useEffect(() => {
    setHeader({ mode: "ITEM", headingText: "채팅" });
    return () => {
      setHeader({ ...header, headingText: undefined });
    };
  }, []);
  return (
    <List>
      <ChatListItem chatRoomImg="" content="채팅방 내용" date="2022.11.03" title="채팅방 제목" unread />
    </List>
  );
};

export default ChatListPage;
