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
  imgUrl?: string;
};

export const Card = forwardRef<HTMLDivElement, AllCardProps>((props, ref) => {
  const theme = racconsThemes.defaultTheme;
  const promisedate = props.promiseDate;
  const week = new Array("일", "월", "화", "수", "목", "금", "토");
  const DayOfWeek = week[promisedate?.getDay()];
  const Month = promisedate?.getMonth() + 1;
  const Day = promisedate?.getDate();
  const hours = promisedate?.getHours();
  let hour;
  if (hours > 12) hour = "오후 " + (hours - 12);
  else if (hours === 12) hour = "오후 " + hours;
  else if (hours === 24) hour = "오전 " + (hours - 12);
  else hour = "오전 " + hours;

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
      <CardMedia component="img" height="194" image={props.imgUrl} alt="Coding" />
      <CardContent>
        <ButtonDiv
          css={css`
            margin-bottom: 1rem;
          `}
        >
          <Text
            css={css`
              color: ${theme.color.background.submain};
              padding: 0.3rem 0.5rem;
              @media screen and (max-width: 599px) {
                font-size: 0.6rem;
                padding: 0.1rem;
              }
            `}
          >
            성장,자기계발
          </Text>
        </ButtonDiv>
        <Text
          as="h1"
          css={css`
            font-size: 1.2rem;
            @media screen and (max-width: 599px) {
              font-size: 1rem;
              flex-basis: 45%;
            }
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            margin-bottom: 0.5rem;
          `}
        >
          홍대카페에서 모각코 하실분 구해요
        </Text>
        {props.price && (
          <Text
            as="h1"
            css={css`
              font-size: 1.2rem;
              @media screen and (max-width: 599px) {
                font-size: 1rem;
              }

              font-weight: 700;
              margin-bottom: 1rem;
            `}
          >
            {props.price.toLocaleString("ko-KR")}원
          </Text>
        )}
        {props.promiseDate && (
          <DateDiv
            css={css`
              @media screen and (max-width: 599px) {
                font-size: 0.8rem;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
              }
            `}
          >
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
        )}
      </CardContent>
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
        <ButtonDiv
          css={css`
            border-radius: 0.5rem;
            background-color: ${theme.color.text.sub};
          `}
        >
          <Text
            css={css`
              color: ${theme.color.background.submain};
              padding: 0.2rem 0.5rem;
              @media screen and (max-width: 599px) {
                font-size: 0.6rem;
                padding: 0.1rem;
              }
            `}
          >
            {props.price ? "판매" : "마감"}완료
          </Text>
        </ButtonDiv>
      </CardActions>
    </MuiCard>
  );
});

const DateDiv = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  box-sizing: border-box;
`;

const ButtonDiv = styled.div`
  display: inline-flex;
  width: auto;
  padding: 0.3rem;
  background-color: ${racconsThemes.defaultTheme.color.background.card};
  border: 1px solid ${racconsThemes.defaultTheme.color.text.hover};
  border-radius: 1.5rem;
  box-sizing: border-box;
`;
