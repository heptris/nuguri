import { headerState } from "@/store";
import * as React from "react";
import { Card, Menu } from "@common/components";
import { useEffect } from "react";
import { useRecoilState } from "recoil";

const HomePage = () => {
  const [, setHeader] = useRecoilState(headerState);
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);
  const handleClick = (selectedMenu: HTMLElement) => {
    setAnchorEl(selectedMenu);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
  useEffect(() => {
    setHeader({ mode: "MAIN", headingText: undefined });
  }, [anchorEl]);
  const newDate = new Date("2022-10-15 15:00:37");
  return (
    <div>
      <Menu open={open} anchorEl={anchorEl} onCloseHandler={handleClose} onClickHandler={handleClick} />
      <Card promiseDate={newDate} nowPeople={2} maxPeople={5} />
    </div>
  );
};
export default HomePage;
