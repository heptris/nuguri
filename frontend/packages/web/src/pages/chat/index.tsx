import { List } from "@common/components";
import ChatListItem from "@/components/ChatListItem";
import { useChatList, useHeader, useUser } from "@/hooks";
import withAuth from "@/utils/withAuth";
import { ROUTES } from "@/constant";
import Link from "@/components/Link";

const { CHAT } = ROUTES;
const ChatListPage = () => {
  useHeader({ mode: "ITEM", headingText: "채팅" });
  const { chatListData } = useChatList();
  return (
    <List>
      {chatListData?.map(({ lastChatMessage, lastChatTime, roomId, roomName }) => {
        return (
          <Link noLinkStyle href={CHAT + `/${roomId}`}>
            <ChatListItem chatRoomImg="" content={lastChatMessage} date={lastChatTime} title={roomName ?? roomId} unread />;
          </Link>
        );
      })}
    </List>
  );
};

export default withAuth(ChatListPage);
