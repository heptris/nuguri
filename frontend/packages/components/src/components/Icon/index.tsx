import { css } from "@emotion/react";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";
import AddCircleIcon from "@mui/icons-material/AddCircle";
import SearchIcon from "@mui/icons-material/Search";
import MyLocationIcon from "@mui/icons-material/MyLocation";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import { IconButton } from "@mui/material";

import React from "react";
import { racconsThemes } from "../../styles/theme";
import { IconProps } from "../../types/props";

export const Icon = (props: IconProps) => {
  const { mode, ...rest } = props;
  const theme = racconsThemes.defaultTheme;
  return (
    <IconButton
      css={css`
        background-color: ${theme.color.background.submain};
      `}
      {...rest}
    >
      {mode === "GOBACK" ? (
        <ArrowBackIcon />
      ) : mode === "ADDCIRCLE" ? (
        <AddCircleIcon />
      ) : mode === "NOTIFICATION" ? (
        <NotificationsNoneIcon />
      ) : mode === "SEARCH" ? (
        <SearchIcon />
      ) : mode === "MYLOCATION" ? (
        <MyLocationIcon />
      ) : mode === "MENU" ? (
        <MoreVertIcon />
      ) : (
        <></>
      )}
    </IconButton>
  );
};
