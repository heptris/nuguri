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
    handleMenuItemClick: (categoryId: number) => void;
    onCloseHandler: () => void;
    categoryId: number;
    options: any[];
  };

export const Menu = forwardRef<HTMLDivElement, MenuButtomProps>(({ handleClickListItem, handleMenuItemClick, onCloseHandler, categoryId, options, open, anchorEl }, ref) => {
  const theme = racconsThemes.defaultTheme;
  const [categoryName, setCategoryName] = React.useState("취미선택");
  const onHandleClose = () => {
    onCloseHandler();
  };
  const onHandleListItemClick = (event: React.MouseEvent<HTMLElement>) => {
    handleClickListItem(event.currentTarget);
  };

  return (
    <div>
      <Button
        css={css`
          color: ${theme.color.background.main};
          margin-bottom: 2rem;
        `}
      >
        <ListItem button aria-expanded={open ? "true" : undefined} onClick={onHandleListItemClick}>
          <Text
            css={css`
              color: ${theme.color.background.submain};
            `}
          >
            {categoryName}
          </Text>
        </ListItem>
      </Button>
      <MuiMenu
        id="lock-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={onHandleClose}
        MenuListProps={{
          "aria-labelledby": "lock-button",
          role: "listbox",
        }}
      >
        {options?.map((option, index) => (
          <MenuItem
            key={option.categoryId}
            selected={index === categoryId - 1}
            onClick={() => {
              handleMenuItemClick(option.categoryId);
              setCategoryName(option.categoryName);
            }}
          >
            <Text>{option.categoryName}</Text>
          </MenuItem>
        ))}
      </MuiMenu>
    </div>
  );
});
