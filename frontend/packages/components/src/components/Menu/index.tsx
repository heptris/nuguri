import { forwardRef } from "react";
import * as React from "react";
import ListItem from "@mui/material/ListItem";
import MenuItem from "@mui/material/MenuItem";

import { MenuProps, default as MuiMenu } from "@mui/material/Menu";
import { ButtonProps } from "@mui/material/Button";
import Text from "../Text";
import { css } from "@emotion/react";
import { racconsThemes } from "../../styles/theme";
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
  const [categoryName, setCategoryName] = React.useState("");
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
            {categoryName ? categoryName : "취미선택"}
          </Text>
        </ListItem>
      </Button>
      <MuiMenu anchorEl={anchorEl} open={open} onClose={onHandleClose}>
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
