import { forwardRef } from "react";

import * as React from "react";
import { styled } from "@mui/material/styles";
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

export const Card = forwardRef<HTMLDivElement, CardProps>((props, ref) => {
  return (
    <MuiCard {...props} ref={ref} sx={{ maxWidth: 345 }}>
      <CardMedia component="img" height="194" image="/assets/hobby/coding.jpg" alt="Coding" />
      <Button variant="contained" color="inherit" size="small">
        성장,자기계발
      </Button>
      <CardHeader
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title="홍대카페에서 모각코 하실분 구해요"
      />

      <CardContent>
        <Typography variant="body2" color="text.secondary">
          <div>
            <CalendarMonthIcon color="disabled" />
            <span>10.15(토) 오후 2시</span>
            <PeopleAltIcon color="disabled" />
            <span>2/5</span>
          </div>
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="add to favorites">
          <FavoriteBorderIcon />
        </IconButton>
        <span>5</span>
        <IconButton aria-label="share">
          <ChatBubbleOutlineIcon />
        </IconButton>
        <span>5</span>
        <Button variant="contained" color="inherit" size="small">
          마감완료
        </Button>
      </CardActions>
    </MuiCard>
  );
});
