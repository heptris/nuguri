/** @jsxImportSource @emotion/react */
import { forwardRef } from "react";

import { css } from "@emotion/react";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";

import { racconsThemes } from "../../styles/theme";
import { AppBarProps } from "../../types/props";
import Text from "../Text";

export const AppBar = forwardRef<HTMLDivElement, AppBarProps>((props, ref) => {
  const { LeftChild, RightChild, heading } = props;
  const theme = racconsThemes.defaultTheme;
  return (
    <Box
      css={css`
        position: fixed;
        width: 100%;
        z-index: 3;
        border-bottom: 1px solid ${theme.color.text.hover};
        background-color: ${theme.color.background.submain};
        top: 0;
      `}
      component={"header"}
    >
      <Toolbar
        css={css`
          display: flex;
          justify-content: space-between;
        `}
      >
        {LeftChild}
        {heading !== undefined && (
          <Text
            as={"h1"}
            css={css`
              position: fixed;
              left: 50%;
              transform: translate3d(-50%, 0, 0);
              z-index: 100;
            `}
          >
            {heading}
          </Text>
        )}
        {RightChild}
      </Toolbar>
    </Box>
  );
});
