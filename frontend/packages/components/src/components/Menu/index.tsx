import { forwardRef } from "react";
import * as React from "react";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";
import MenuItem from "@mui/material/MenuItem";

import { MenuProps, default as MuiMenu } from "@mui/material/Menu";
import { ButtonProps } from "@mui/material/Button";
import Text from "../Text";
import { css } from "@emotion/react";
import { racconsThemes } from "../../styles/theme";
import styled from "@emotion/styled";
import { Button } from "../Button";

export type MenuButtomProps = MenuProps &
  ButtonProps & {
    handleClickListItem: (selectedMenu: HTMLElement) => void;
    handleMenuItemClick: (index: number) => void;
    onCloseHandler: () => void;
    selectedIndex: number;
  };

const options = ["전체", "문화, 예술", "운동, 액티비티", "푸드, 드링크", "여행, 나들이", "창작", "성장, 자기계발"];

export const Menu = forwardRef<HTMLDivElement, MenuButtomProps>((props, ref) => {
  const theme = racconsThemes.defaultTheme;
  const [hobby, setHobby] = React.useState("취미선택");
  const onHandleClose = () => {
    props.onCloseHandler();
  };
  const onHandleListItemClick = (event: React.MouseEvent<HTMLElement>) => {
    props.handleClickListItem(event.currentTarget);
  };

  const onHandleMenuItemClick = (event: React.MouseEvent<HTMLElement>, index: number) => {
    props.handleMenuItemClick(index);
  };

  return (
    <div>
      <Button
        css={css`
          color: ${theme.color.background.main};
          margin-bottom: 2rem;
        `}
      >
        <ListItem
          button
          id="lock-button"
          aria-haspopup="listbox"
          aria-controls="lock-menu"
          aria-label="when device is locked"
          aria-expanded={props.open ? "true" : undefined}
          onClick={onHandleListItemClick}
        >
          <Text
            css={css`
              color: ${theme.color.background.submain};
            `}
          >
            {hobby}
          </Text>
        </ListItem>
      </Button>
      <MuiMenu
        id="lock-menu"
        anchorEl={props.anchorEl}
        open={props.open}
        onClose={onHandleClose}
        MenuListProps={{
          "aria-labelledby": "lock-button",
          role: "listbox",
        }}
      >
        {options.map((option, index) => (
          <MenuItem
            key={option}
            selected={index === props.selectedIndex}
            onClick={event => {
              onHandleMenuItemClick(event, index);
              setHobby(options[index]);
            }}
          >
            <Text>{option}</Text>
          </MenuItem>
        ))}
      </MuiMenu>
    </div>
    // <div
    //   css={css`
    //     margin-bottom: 2rem;
    //   `}
    // >
    //   <Button
    //     css={css`
    //       background-color: ${theme.color.background.main};
    //       &:hover {
    //         background-color: ${theme.color.background.main};
    //       }
    //     `}
    //     id="basic-button"
    //     aria-controls={props.open ? "basic-menu" : undefined}
    //     aria-haspopup="true"
    //     aria-expanded={props.open ? "true" : undefined}
    //     onClick={onHandleClick}
    //   >
    //     <Text
    //       css={css`
    //         color: ${theme.color.background.submain};
    //       `}
    //     >
    //       취미선택
    //     </Text>
    //   </Button>
    //   <MuiMenu
    //     {...props}
    //     ref={ref}
    //     id="basic-menu"
    //     anchorEl={props.anchorEl}
    //     open={props.open}
    //     onClose={onHandleClose}
    //     MenuListProps={{
    //       "aria-labelledby": "basic-button",
    //     }}
    //   >
    //     <MunuItemWrapper onClick={onHandleClose}>
    //       <Text>문화, 예술</Text>
    //     </MunuItemWrapper>
    //     <MunuItemWrapper onClick={onHandleClose}>
    //       <Text>운동, 액티비티</Text>
    //     </MunuItemWrapper>
    //     <MunuItemWrapper onClick={onHandleClose}>
    //       <Text>푸드, 드링크</Text>
    //     </MunuItemWrapper>
    //     <MunuItemWrapper onClick={onHandleClose}>
    //       <Text>여행, 나들이</Text>
    //     </MunuItemWrapper>
    //     <MunuItemWrapper onClick={onHandleClose}>
    //       <Text>창작</Text>
    //     </MunuItemWrapper>
    //     <MunuItemWrapper onClick={onHandleClose}>
    //       <Text>성장, 자기계발</Text>
    //     </MunuItemWrapper>
    //   </MuiMenu>
    // </div>
  );
});

const theme = racconsThemes.defaultTheme;
const MunuItemWrapper = styled(MenuItem)`
  &:hover {
    background-color: ${theme.color.status.disabled};
  }
`;
