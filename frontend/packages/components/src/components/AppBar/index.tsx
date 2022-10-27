/** @jsxImportSource @emotion/react */
import { forwardRef } from "react";

import { css, useTheme } from "@emotion/react";
import * as React from "react";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";

import { AppBarProps, default as MuiAppBar } from "@mui/material/AppBar";
import { racconsThemes } from "../../styles/theme";

export const AppBar = forwardRef<HTMLDivElement, AppBarProps>((props, ref) => {
  const theme = racconsThemes.darkTheme;
  return (
    <Box sx={{ flexGrow: 1 }}>
      <MuiAppBar
        position="static"
        css={css`
          background-color: ${theme.color.hover.main};
        `}
      >
        <Toolbar>
          <IconButton size="large" edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            News
          </Typography>
          <Button color="inherit">Login</Button>
        </Toolbar>
      </MuiAppBar>
    </Box>
  );
});
