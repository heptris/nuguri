import React from "react";
import { Avatar, Badge, List, ListItem } from "@common/components";
import { css } from "@emotion/react";

const ChatListPage = () => {
  return (
    <List>
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
          <Avatar />
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
        </div>
      </ListItem>
    </List>
  );
};

export default ChatListPage;
