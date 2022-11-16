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

import { useHeader, useHome, useUser } from "@/hooks";
import { Card, Menu, Text } from "@common/components";
import Link from "@/components/Link";
import { ROUTES } from "@/constant";
import { hobbyState } from "@/store";
import { DealItemDetailType, HobbyRoomType } from "@/types";
import Image from "next/image";
import { racconsThemes } from "@common/components/src/styles/theme";

const { REGION, HOBBY, DEAL, GROUP_DEAL } = ROUTES;
const options = ["전체", "문화, 예술", "운동, 액티비티", "푸드, 드링크", "여행, 나들이", "창작", "성장, 자기계발"];

const HomePage = () => {
  useHeader({ mode: "MAIN", headingText: undefined });
  const [hobby, setHobby] = useRecoilState(hobbyState);
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
  const [selectedIndex, setSelectedIndex] = useState(0);
  const open = Boolean(anchorEl);
  const handleClickListItem = (selectedMenu: HTMLElement) => {
    setAnchorEl(selectedMenu);
  };
  const handleMenuItemClick = (index: number) => {
    setSelectedIndex(index);
    setHobby(options[index]);
    setAnchorEl(null);
    console.log(options[index]);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
  const {
    userInfo: { localId },
  } = useUser();
  const { dealDatas, hobbyDatas } = useHome({});

  // useEffect(() => {
  //   console.log(dealDatas?.pages[0].content, hobbyDatas);
  // }, [dealDatas, hobbyDatas]);

  return (
    <MainWrapper>
      <Menu open={open} anchorEl={anchorEl} onCloseHandler={handleClose} handleClickListItem={handleClickListItem} handleMenuItemClick={handleMenuItemClick} selectedIndex={selectedIndex} />
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
          {hobbyDatas?.map(({ highAgeLimit, categoryId, curNum, endDate, hobbyId, hobbyImage, maxNum, title, sexLimit }: HobbyRoomType) => {
            return (
              <Link
                href={REGION + `/${localId}` + HOBBY + `/${hobbyId}`}
                noLinkStyle
                css={css`
                  display: contents;
                `}
              >
                <Card
                  Image={<Image src={hobbyImage} width={500} height={300} />}
                  Content={
                    <>
                      <ButtonDiv
                        css={css`
                          margin-bottom: 1rem;
                        `}
                      >
                        <Text
                          css={css`
                            color: ${racconsThemes.defaultTheme.color.background.submain};
                            padding: 0.3rem 0.5rem;
                            @media screen and (max-width: 599px) {
                              font-size: 0.6rem;
                              padding: 0.1rem;
                            }
                          `}
                        >
                          {categoryId}
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
                        {title}
                      </Text>

                      {!!endDate && (
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
                            {endDate.slice(0, 10)}
                          </Text>
                          <PeopleAltIcon color="disabled" />
                          <Text as="span">{curNum + "/" + maxNum}</Text>
                        </DateDiv>
                      )}
                    </>
                  }
                  Bottom={
                    <>
                      <div>
                        <IconButton aria-label="add to favorites">
                          <WcIcon />
                        </IconButton>
                        <Text as="span">{sexLimit === "f" ? "여자만" : sexLimit === "m" ? "남자만" : "성별무관"}</Text>
                        <IconButton aria-label="share">
                          <RemoveCircleOutlineIcon />
                        </IconButton>
                        <Text as="span">{highAgeLimit}세 이하</Text>
                      </div>
                    </>
                  }
                />
              </Link>
            );
          })}
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
          {dealDatas?.pages[0].content.map(({ dealId, dealImage, price, title, hit, deal }: DealItemDetailType) => {
            return (
              <Link
                href={REGION + `/${localId}` + DEAL + `/${dealId}`}
                noLinkStyle
                css={css`
                  display: contents;
                `}
              >
                <Card
                  Image={<Image src={dealImage} width={500} height={300} />}
                  Content={
                    <>
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
                        {title}
                      </Text>
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
                        {price.toLocaleString("ko-KR")}원
                      </Text>
                    </>
                  }
                  Bottom={
                    <>
                      <div>
                        <IconButton aria-label="add to favorites">
                          <FavoriteBorderIcon />
                        </IconButton>
                        <Text as="span">{hit}</Text>
                      </div>
                      <ButtonDiv
                        css={css`
                          border-radius: 0.5rem;
                          background-color: ${racconsThemes.defaultTheme.color.text.sub};
                        `}
                      >
                        <Text
                          css={css`
                            color: ${racconsThemes.defaultTheme.color.background.submain};
                            padding: 0.2rem 0.5rem;
                            @media screen and (max-width: 599px) {
                              font-size: 0.6rem;
                              padding: 0.1rem;
                            }
                          `}
                        >
                          {deal ? "판매" : "마감"}완료
                        </Text>
                      </ButtonDiv>
                    </>
                  }
                />
              </Link>
            );
          })}
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
  justify-content: space-between;
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
