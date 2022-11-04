import { forwardRef } from "react";
import * as React from "react";
import Button from "@mui/material/Button";
import MenuItem from "@mui/material/MenuItem";

import { MenuProps, default as MuiMenu } from "@mui/material/Menu";
import Text from "../Text";
import { css } from "@emotion/react";
import { racconsThemes } from "../../styles/theme";
import styled from "@emotion/styled";

export const Menu = forwardRef<HTMLDivElement, MenuProps>((props, ref) => {
  const theme = racconsThemes.defaultTheme;
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);
  const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
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
        aria-controls={open ? "basic-menu" : undefined}
        aria-haspopup="true"
        aria-expanded={open ? "true" : undefined}
        onClick={handleClick}
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
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        MenuListProps={{
          "aria-labelledby": "basic-button",
        }}
      >
        <MunuItemWrapper onClick={handleClose}>
          <Text>문화, 예술</Text>
        </MunuItemWrapper>
        <MunuItemWrapper onClick={handleClose}>
          <Text>운동, 액티비티</Text>
        </MunuItemWrapper>
        <MunuItemWrapper onClick={handleClose}>
          <Text>푸드, 드링크</Text>
        </MunuItemWrapper>
        <MunuItemWrapper onClick={handleClose}>
          <Text>여행, 나들이</Text>
        </MunuItemWrapper>
        <MunuItemWrapper onClick={handleClose}>
          <Text>창작</Text>
        </MunuItemWrapper>
        <MunuItemWrapper onClick={handleClose}>
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
