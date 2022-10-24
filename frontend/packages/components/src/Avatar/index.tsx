import { forwardRef } from "react";

import { AvatarTypeMap, default as MuiAvatar } from "@mui/material/Avatar";

export const Avatar = forwardRef<HTMLDivElement, AvatarTypeMap["props"]>((props, ref) => {
  return <MuiAvatar {...props} ref={ref} />;
});
