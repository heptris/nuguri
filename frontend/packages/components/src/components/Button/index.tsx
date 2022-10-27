import { forwardRef } from "react";

import { ButtonTypeMap, default as MuiButton } from "@mui/material/Button";

export const Button = forwardRef<HTMLButtonElement, ButtonTypeMap["props"]>((props, ref) => {
  return <MuiButton {...props} ref={ref} />;
});
