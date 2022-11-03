import React, { forwardRef } from "react";
import { ListItem as MuiListItem, ListItemProps, ListItemTypeMap } from "@mui/material";

export const ListItem = forwardRef<HTMLLIElement, ListItemProps>((props, ref) => {
  return (
    <MuiListItem {...props} ref={ref}>
      {props.children}
    </MuiListItem>
  );
});
