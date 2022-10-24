import { forwardRef } from "react";

import { CardProps, default as MuiCard } from "@mui/material/Card";

export const Card = forwardRef<HTMLDivElement, CardProps>((props, ref) => {
  return <MuiCard {...props} ref={ref} />;
});
