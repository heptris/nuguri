import { forwardRef } from "react";

import { BadgeProps, default as MuiBadge } from "@mui/material/Badge";

export const Badge = forwardRef<HTMLDivElement, BadgeProps>((props, ref) => {
  return <MuiBadge {...props} ref={ref} />;
});
