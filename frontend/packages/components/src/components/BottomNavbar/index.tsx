import { forwardRef } from "react";
import * as React from "react";
import Box from "@mui/material/Box";

import { BottomNavigationProps, default as MuiBottomNavigation } from "@mui/material/BottomNavigation";
import { css } from "@emotion/react";
import { racconsThemes } from "../../styles/theme";

export const BottomNavbar = forwardRef<HTMLDivElement, BottomNavigationProps>((props, ref) => {
  const { children, value } = props;
  const theme = racconsThemes.defaultTheme;
  return (
    <MuiBottomNavigation
      css={css`
        position: fixed;
        bottom: 0;
        left: 0;
        width: 100%;
        border-top: 1px solid ${theme.color.text.hover};
        z-index: 3;
      `}
      {...props}
      ref={ref}
      showLabels
      value={value}
      component={"nav"}
    >
      {children}
    </MuiBottomNavigation>
  );
});
