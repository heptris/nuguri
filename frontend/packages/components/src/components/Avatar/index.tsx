import { forwardRef } from "react";

import { AvatarProps, default as MuiAvatar } from "@mui/material/Avatar";

export const Avatar = forwardRef<HTMLDivElement, AvatarProps>((props, ref) => {
  return <MuiAvatar {...props} ref={ref} />;
});
