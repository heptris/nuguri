/** @jsxImportSource @emotion/react */
import { forwardRef } from "react";

import { ButtonProps, default as MuiButton } from "@mui/material/Button";

export const Button = forwardRef<HTMLButtonElement, ButtonProps>((props, ref) => {
  return <MuiButton variant="contained" {...props} ref={ref} />;
});
