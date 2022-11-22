import { forwardRef } from "react";

import { AlertProps, default as MuiAlert } from "@mui/material/Alert";

export const Alert = forwardRef<HTMLDivElement, AlertProps>((props, ref) => {
  return <MuiAlert {...props} ref={ref} />;
});
