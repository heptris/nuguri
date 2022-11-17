import { useEffect, useState } from "react";
import styled from "@emotion/styled";
import { css } from "@emotion/react";
import { useRecoilState } from "recoil";

import IconButton from "@mui/material/IconButton";
import ShareIcon from "@mui/icons-material/Share";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";
import WcIcon from "@mui/icons-material/Wc";
import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";

import { useHeader, useHome, useUser, useCategory } from "@/hooks";
import { Card, Menu, Text } from "@common/components";
import Link from "@/components/Link";
import { ROUTES } from "@/constant";
import { menuCategoryState } from "@/store";
import { DealItemDetailType, HobbyRoomType } from "@/types";
import Image from "next/image";
import { racconsThemes } from "@common/components/src/styles/theme";
import { apiInstance, ENDPOINT_API } from "@/api";
import HobbyCard from "@/components/Card/HobbyCard";
import { DealCard } from "@/components/Card/DealCard";
import { useList } from "@/hooks/useList";

const { REGION, HOBBY, DEAL, GROUP_DEAL } = ROUTES;

const HomePage = () => {
  const { options } = useCategory();

  useHeader({ mode: "MAIN", headingText: undefined });
  const [categoryId, setCategoryId] = useRecoilState(menuCategoryState);
  const {
    userInfo: { localId },
  } = useUser();

  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);
  const handleClickListItem = (selectedMenu: HTMLElement) => {
    setAnchorEl(selectedMenu);
  };
  const handleMenuItemClick = (categoryId: number) => {
    setCategoryId(categoryId);
    setAnchorEl(null);
    console.log(categoryId);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  // const { dealDatas } = useHome({ categoryId, localId });
  const { hobbyList, dealList } = useList({ categoryId, localId });

  return (
    <MainWrapper>
      <Menu
        open={open}
        anchorEl={anchorEl}
        onCloseHandler={handleClose}
        handleClickListItem={handleClickListItem}
        handleMenuItemClick={handleMenuItemClick}
        categoryId={categoryId}
        options={options}
      />
      <CategorytWrapper>
        <TitleWrapper>
          <Text
            css={css`
              font-size: 1.2rem;
              font-weight: 700;
            `}
          >
            취미모임
          </Text>
          <Link href={REGION + `/${localId}` + HOBBY}>
            <Text
              css={css`
                color: #5e6272;
                &:hover {
                  color: #999daf;
                  cursor: pointer;
                }
              `}
            >
              더보기
            </Text>
          </Link>
        </TitleWrapper>
        <CardWapper>
          <HobbyCard hobbyList={hobbyList} categoryId={categoryId} localId={localId} />
        </CardWapper>
      </CategorytWrapper>
      <CategorytWrapper>
        <TitleWrapper>
          <Text
            as="p"
            css={css`
              text-align: left;
              font-size: 1.2rem;
              font-weight: 700;
            `}
          >
            중고거래
          </Text>
          <Link href={REGION + `/${localId}` + DEAL}>
            <Text
              css={css`
                color: #5e6272;
                &:hover {
                  color: #999daf;
                  cursor: pointer;
                }
              `}
            >
              더보기
            </Text>
          </Link>
        </TitleWrapper>
        <CardWapper>
          <DealCard dealList={dealList} categoryId={categoryId} localId={localId} />
        </CardWapper>
      </CategorytWrapper>
      <CategorytWrapper>
        <TitleWrapper>
          <Text
            as="p"
            css={css`
              text-align: left;
              font-size: 1.2rem;
              font-weight: 700;
            `}
          >
            공구목록
          </Text>
          <Link href={REGION + `/${localId}` + GROUP_DEAL}>
            <Text
              css={css`
                color: #5e6272;
                &:hover {
                  color: #999daf;
                  cursor: pointer;
                }
              `}
            >
              더보기
            </Text>
          </Link>
        </TitleWrapper>
        <CardWapper>
          {/* <Card promiseDate={newDate} nowPeople={2} maxPeople={5} price={1000000} imgUrl={"/public/coding.jpg"} /> */}
          {/* <Card promiseDate={newDate} nowPeople={2} maxPeople={5} price={1000000} imgUrl={"/public/coding.jpg"} /> */}
          {/* <Card promiseDate={newDate} nowPeople={2} maxPeople={5} price={1000000} imgUrl={"/public/coding.jpg"} /> */}
          {/* <Card promiseDate={newDate} nowPeople={2} maxPeople={5} price={1000000} imgUrl={"/public/coding.jpg"} /> */}
        </CardWapper>
      </CategorytWrapper>
    </MainWrapper>
  );
};
export default HomePage;

const MainWrapper = styled.div`
  max-width: 1799px;
  display: flex;
  flex-direction: column;
  padding: 1rem;
  background-color: #f2decb;
`;

const CategorytWrapper = styled.div`
  display: inline-block;
  max-width: 1599px;
  flex-direction: column;
`;

const TitleWrapper = styled.div`
  display: flex;
  box-sizing: border-box;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
`;

const CardWapper = styled.div`
  max-width: 1799px;
  display: flex;
  flex-direction: row;
  -ms-overflow-style: none;
  &::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
  }
  @media screen and (max-width: 1799px) {
    width: 100%;
    overflow-x: scroll;
    overflow-y: hidden;
  }

  @media screen and (max-width: 899px) {
    /* 모바일 가로, 타블렛 세로 */
    width: 100%;
    overflow-x: scroll;
    overflow-y: hidden;
  }

  @media screen and (max-width: 599px) {
    /* 모바일 세로 */
    width: 100%;
    overflow-x: scroll;
    overflow-y: hidden;
  }
`;
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
