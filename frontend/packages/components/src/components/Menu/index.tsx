import { forwardRef } from "react";
import * as React from "react";
import Button from "@mui/material/Button";
import MenuItem from "@mui/material/MenuItem";

import { MenuProps, default as MuiMenu } from "@mui/material/Menu";
import { ButtonProps } from "@mui/material/Button";
import Text from "../Text";
import { css } from "@emotion/react";
import { racconsThemes } from "../../styles/theme";
import styled from "@emotion/styled";

export type MenuButtomProps = MenuProps &
  ButtonProps & {
    onClickHandler: (selectedMenu: HTMLElement) => void;
    onCloseHandler: () => void;
  };

export const Menu = forwardRef<HTMLDivElement, MenuButtomProps>((props, ref) => {
  const theme = racconsThemes.defaultTheme;
  const onHandleClose = () => {
    props.onCloseHandler();
  };
  const onHandleClick = event => {
    props.onClickHandler(event.currentTarget);
  };

  return (
    <div
      css={css`
        margin-bottom: 2rem;
      `}
    >
      <Button
        css={css`
          background-color: ${theme.color.background.main};
          &:hover {
            background-color: ${theme.color.background.main};
          }
        `}
        id="basic-button"
        aria-controls={props.open ? "basic-menu" : undefined}
        aria-haspopup="true"
        aria-expanded={props.open ? "true" : undefined}
        onClick={onHandleClick}
      >
        <Text
          css={css`
            color: ${theme.color.background.submain};
          `}
        >
          취미선택
        </Text>
      </Button>
      <MuiMenu
        {...props}
        ref={ref}
        id="basic-menu"
        anchorEl={props.anchorEl}
        open={props.open}
        onClose={onHandleClose}
        MenuListProps={{
          "aria-labelledby": "basic-button",
        }}
      >
        <MunuItemWrapper onClick={onHandleClose}>
          <Text>문화, 예술</Text>
        </MunuItemWrapper>
        <MunuItemWrapper onClick={onHandleClose}>
          <Text>운동, 액티비티</Text>
        </MunuItemWrapper>
        <MunuItemWrapper onClick={onHandleClose}>
          <Text>푸드, 드링크</Text>
        </MunuItemWrapper>
        <MunuItemWrapper onClick={onHandleClose}>
          <Text>여행, 나들이</Text>
        </MunuItemWrapper>
        <MunuItemWrapper onClick={onHandleClose}>
          <Text>창작</Text>
        </MunuItemWrapper>
        <MunuItemWrapper onClick={onHandleClose}>
          <Text>성장, 자기계발</Text>
        </MunuItemWrapper>
      </MuiMenu>
    </div>
  );
});

const theme = racconsThemes.defaultTheme;
const MunuItemWrapper = styled(MenuItem)`
  &:hover {
    background-color: ${theme.color.status.disabled};
  }
`;
