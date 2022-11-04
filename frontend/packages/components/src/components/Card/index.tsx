/** @jsxImportSource @emotion/react */

import { forwardRef } from "react";

import * as React from "react";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ShareIcon from "@mui/icons-material/Share";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";

import { CardProps, default as MuiCard } from "@mui/material/Card";
import { Button } from "@mui/material";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import Text from "../Text";
import { racconsThemes } from "../../styles/theme";

export type AllCardProps = CardProps & {
  promiseDate?: Date;
  maxPeople?: number;
  nowPeople?: number;
  price?: number;
};

export const Card = forwardRef<HTMLDivElement, AllCardProps>((props, ref) => {
  const theme = racconsThemes.defaultTheme;
  const promiseDate = props.promiseDate;
  const week = new Array("일", "월", "화", "수", "목", "금", "토");
  const DayOfWeek = week[promiseDate.getDay()];
  const Month = promiseDate.getMonth() + 1;
  const Day = promiseDate.getDate();
  const hours = promiseDate.getHours();
  let hour;
  if (hours > 12) hour = "오후 " + (hours - 12);
  else if (hours === 12) hour = "오후 " + hours;
  else if (hours === 24) hour = "오전 " + (hours - 12);
  else hour = "오전 " + hours;

  return (
    <MuiCard {...props} ref={ref} sx={{ maxWidth: 345, maxHeight: 500 }}>
      <CardMedia component="img" height="194" image="/assets/hobby/coding.jpg" alt="Coding" />
      <CardContent>
        <span
          css={css`
            background-color: ${theme.color.background.card};
            border: 1px solid ${theme.color.text.hover};
            border-radius: 10px;
          `}
        >
          <Text
            css={css`
              color: ${theme.color.background.submain};
              padding: 0.5rem;
            `}
          >
            성장,자기계발
          </Text>
        </span>
        <Text
          as="h1"
          css={css`
            font-size: 2rem;
            font-weight: 700;
          `}
        >
          홍대카페에서 모각코 하실분 구해요
        </Text>
        <DateDiv>
          <CalendarMonthIcon color="disabled" />
          <Text
            as="span"
            css={css`
              margin-right: 1rem;
            `}
          >
            {Month}.{Day}({DayOfWeek}) {hour}시
          </Text>
          <PeopleAltIcon color="disabled" />
          <Text as="span">{props.nowPeople + "/" + props.maxPeople}</Text>
        </DateDiv>
      </CardContent>
      <CardActions
        disableSpacing
        css={css`
          display: flex;
          justify-content: space-between;
        `}
      >
        <div>
          <IconButton aria-label="add to favorites">
            <FavoriteBorderIcon />
          </IconButton>
          <Text as="span">5</Text>
          <IconButton aria-label="share">
            <ChatBubbleOutlineIcon />
          </IconButton>
          <Text as="span">5</Text>
        </div>
        <Button variant="contained" color="inherit" size="small" disabled>
          마감완료
        </Button>
      </CardActions>
    </MuiCard>
  );
});

const DateDiv = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
`;
