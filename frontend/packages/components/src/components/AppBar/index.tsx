/** @jsxImportSource @emotion/react */
import { forwardRef } from "react";

import { css } from "@emotion/react";
import * as React from "react";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";

import { AppBarProps, default as MuiAppBar } from "@mui/material/AppBar";
import MyLocationIcon from "@mui/icons-material/MyLocation";
import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";
import AddCircleIcon from "@mui/icons-material/AddCircle";
import SearchIcon from "@mui/icons-material/Search";
import { racconsThemes } from "../../styles/theme";
import Text from "../Text";
import styled from "@emotion/styled";

export const AppBar = forwardRef<HTMLDivElement, AppBarProps>((props, ref) => {
  const theme = racconsThemes.darkTheme;
  return (
    <Box
      css={css`
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        z-index: 3;
        border-bottom: 1px solid ${theme.color.text.hover};
      `}
    >
      <Box
        css={css`
          background-color: ${theme.color.background.submain};
        `}
      >
        <Toolbar>
          <IconWrapper>
            <MyLocationIcon />
          </IconWrapper>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            <Button>
              <Text
                css={css`
                  font-size: 1.2rem;
                  font-weight: bold;
                `}
              >
                역삼동
              </Text>
            </Button>
          </Typography>
          <IconWrapper>
            <NotificationsNoneIcon />
          </IconWrapper>
          <IconWrapper>
            <AddCircleIcon />
          </IconWrapper>
          <IconWrapper>
            <SearchIcon />
          </IconWrapper>
        </Toolbar>
      </Box>
    </Box>
  );
});

const theme = racconsThemes.darkTheme;
const IconWrapper = styled(IconButton)`
  background-color: ${theme.color.background.submain};
`;
