import React from "react";
import { Avatar, Badge, ListItem } from "@common/components";
import { css } from "@emotion/react";
import { ChatListItemType } from "@/types/props";

const ChatListItem = (props: ChatListItemType) => {
  const { content, date, title, unread, chatRoomImg = "" } = props;
  return (
    <ListItem
      css={css`
        display: flex;
        flex-direction: row;
      `}
    >
      <div
        css={css`
          position: relative;
        `}
      >
        <Avatar alt={title} src={chatRoomImg} />
        {unread && (
          <Badge
            color="primary"
            variant="dot"
            css={css`
              position: absolute;
              @keyframes blink-effect {
                50% {
                  opacity: 0;
                }
              }
              animation: blink-effect 1s step-end 5;
              transform: translate(0.3rem, -2rem);
            `}
          />
        )}
      </div>
      <div
        css={css`
          display: flex;
        `}
      >
        <div>
          <div>{title}</div>
          <div>{content}</div>
        </div>
        <div>
          <div>{date}</div>
        </div>
      </div>
    </ListItem>
  );
};

export default ChatListItem;
