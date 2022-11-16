/** @jsxImportSource @emotion/react */

import { forwardRef, ReactNode } from "react";

import { CardContent, CardActions } from "@mui/material";

import { CardProps, default as MuiCard } from "@mui/material/Card";
import { css } from "@emotion/react";

import { racconsThemes } from "../../styles/theme";

export type AllCardProps = CardProps & {
  Image?: ReactNode;
  Content?: ReactNode;
  Bottom?: ReactNode;
};

export const Card = forwardRef<HTMLDivElement, AllCardProps>((props, ref) => {
  const { Image, Content, Bottom } = props;
  const theme = racconsThemes.defaultTheme;

  return (
    <MuiCard
      {...props}
      ref={ref}
      sx={{ maxWidth: 345, maxHeight: 500 }}
      css={css`
        @media screen and (max-width: 1799px) {
          /* 모바일 가로, 타블렛 세로 */
          flex-grow: 0;
          flex-shrink: 0;
          flex-basis: 100%;
        }
        @media screen and (max-width: 899px) {
          /* 모바일 가로, 타블렛 세로 */
          flex-grow: 0;
          flex-shrink: 0;
          flex-basis: 80%;
        }
        @media screen and (max-width: 599px) {
          flex-grow: 0;
          flex-shrink: 0;
          flex-basis: 45%;
        }
        margin-left: 0.5rem;
        margin-right: 0.5rem;
        &:hover {
          background-color: ${theme.color.text.hover};
          cursor: pointer;
        }
        margin-bottom: 3rem;
      `}
    >
      {Image}
      <CardContent>{Content}</CardContent>
      <CardActions
        disableSpacing
        css={css`
          display: flex;
          justify-content: space-between;
          @media screen and (max-width: 599px) {
            font-size: 0.6rem;
          }
        `}
      >
        {Bottom}
      </CardActions>
    </MuiCard>
  );
});
