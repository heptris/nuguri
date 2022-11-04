import { List } from "@common/components";
import ChatListItem from "@/components/ChatListItem";
import { useHeader } from "@/hooks";

const ChatListPage = () => {
  useHeader({ mode: "ITEM", headingText: "채팅" });
  return (
    <List>
      <ChatListItem chatRoomImg="" content="채팅방 내용" date="2022.11.03" title="채팅방 제목" unread />
    </List>
  );
};

export default ChatListPage;
