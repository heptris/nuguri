import React, { forwardRef } from "react";
import { List as MuiList, ListTypeMap } from "@mui/material";

export const List = forwardRef<HTMLUListElement, ListTypeMap["props"]>((props, ref) => {
  return (
    <MuiList ref={ref} {...props}>
      {props.children}
    </MuiList>
  );
});
