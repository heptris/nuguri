import React from "react";
import { Avatar, Badge, ListItem } from "@common/components";
import { css } from "@emotion/react";
import { ChatListItemType } from "@/types/props";

const ChatListItem = (props: ChatListItemType) => {
  const { content, date, title, unread, chatRoomImg } = props;
  const curTime = new Date(Date.parse(date));
  const utc = curTime.toLocaleTimeString().slice(0, 7);
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
        <Avatar
          alt={title}
          src={chatRoomImg}
          css={css`
            margin-right: 0.5rem;
          `}
        />
        {/* {unread && (
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
        )} */}
      </div>
      <div
        css={css`
          display: flex;
          justify-content: space-between;
          align-items: center;
          width: 100%;
        `}
      >
        <div>
          <div>{title}</div>
          <div>{content}</div>
        </div>
        <div>
          <div>{utc}</div>
        </div>
      </div>
    </ListItem>
  );
};

export default ChatListItem;
