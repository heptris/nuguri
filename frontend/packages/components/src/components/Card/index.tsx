/** @jsxImportSource @emotion/react */

import { forwardRef } from "react";

import * as React from "react";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import { red } from "@mui/material/colors";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ShareIcon from "@mui/icons-material/Share";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";

import { CardProps, default as MuiCard } from "@mui/material/Card";
import { Avatar } from "../Avatar";
import { Button } from "@mui/material";

import { css } from "@emotion/react";
import styled from "@emotion/styled";
import Text from "../Text";

export const Card = forwardRef<HTMLDivElement, CardProps>((props, ref) => {
  return (
    <MuiCard {...props} ref={ref} sx={{ maxWidth: 345 }}>
      <CardMedia component="img" height="194" image="/assets/hobby/coding.jpg" alt="Coding" />
      <CardContent>
        <Button variant="contained" color="inherit" size="small" disabled>
          성장,자기계발
        </Button>
        <Text
          as="h1"
          css={css`
            font-size: 2rem;
            font-weight: 700;
          `}
        >
          홍대카페에서 모각코 하실분 구해요
        </Text>
        <Typography variant="body2" color="text.secondary">
          <DateDiv>
            <CalendarMonthIcon color="disabled" />
            <Text
              as="span"
              css={css`
                margin-right: 1rem;
              `}
            >
              10.15(토) 오후 2시
            </Text>
            <PeopleAltIcon color="disabled" />
            <Text as="span">2/5</Text>
          </DateDiv>
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="add to favorites">
          <FavoriteBorderIcon />
        </IconButton>
        <Text as="span">5</Text>
        <IconButton aria-label="share">
          <ChatBubbleOutlineIcon />
        </IconButton>
        <Text as="span">5</Text>
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
