import { forwardRef } from "react";
import * as React from "react";
import Box from "@mui/material/Box";
import BottomNavigationAction from "@mui/material/BottomNavigationAction";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import ChatBubbleOutlineOutlinedIcon from "@mui/icons-material/ChatBubbleOutlineOutlined";
import AddBoxOutlinedIcon from "@mui/icons-material/AddBoxOutlined";
import PersonOutlineOutlinedIcon from "@mui/icons-material/PersonOutlineOutlined";

import { BottomNavigationProps, default as MuiBottomNavigation } from "@mui/material/BottomNavigation";

export const BottomNavbar = forwardRef<HTMLDivElement, BottomNavigationProps>((props, ref) => {
  const [value, setValue] = React.useState(0);
  return (
    <Box sx={{ width: 500 }}>
      <MuiBottomNavigation
        {...props}
        ref={ref}
        showLabels
        value={value}
        onChange={(event, newValue) => {
          setValue(newValue);
        }}
      >
        <BottomNavigationAction label="홈" icon={<HomeOutlinedIcon />} />
        <BottomNavigationAction label="채팅" icon={<ChatBubbleOutlineOutlinedIcon />} />
        <BottomNavigationAction label="라운지" icon={<AddBoxOutlinedIcon />} />
        <BottomNavigationAction label="프로필" icon={<PersonOutlineOutlinedIcon />} />
      </MuiBottomNavigation>
    </Box>
  );
});
