import Link from "@/components/Link";
import { HobbyRoomType } from "@/types";
import { Button, Card, Text } from "@common/components";
import { racconsThemes } from "@common/components/src/styles/theme";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import { IconButton } from "@mui/material";
import Image from "next/image";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";
import WcIcon from "@mui/icons-material/Wc";
import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";
import { ROUTES } from "@/constant";
import { useUser } from "@/hooks";

const { REGION, HOBBY, DEAL, GROUP_DEAL } = ROUTES;

const getDate = endDate => {};

const HobbyCardList = ({ hobbyList }) => {
  return (
    <>
      {hobbyList?.map(({ hobbyId, localId, categoryId, title, curNum, endDate, maxNum, wishlistNum, chatNum, highAgeLimit, imageurl, sexLimit, closed, approveStatus }: HobbyRoomType) => {
        const week = new Array("일", "월", "화", "수", "목", "금", "토");
        const getDate = new Date(endDate);
        const DayOfWeek = week[getDate.getDay()];
        const Month = getDate.getMonth() + 1;
        const Day = getDate.getDate();
        const hours = getDate.getHours();
        let hour;
        if (hours > 12) hour = "오후 " + (hours - 12);
        else if (hours === 12) hour = "오후 " + hours;
        else if (hours === 24) hour = "오전 " + (hours - 12);
        else hour = "오전 " + hours;
        const categoryName = ["성장, 자기계발", "운동, 액티비티", "문화, 예술", "푸드, 드링크", "취미", "여행, 나들이"];

        return (
          <Link
            href={REGION + `/${localId}` + HOBBY + `/${hobbyId}`}
            noLinkStyle
            css={css`
              display: contents;
            `}
          >
            <Card
              Image={<Image src={imageurl} width={500} height={400} />}
              Content={
                <>
                  <ButtonDiv
                    css={css`
                      margin: 0.5rem 0;
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
                      {categoryName[categoryId]}
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
                    <>
                      <DivWrapper css={css``}>
                        <CalendarMonthIcon color="action" sx={{ fontSize: "1.2rem", marginRight: "0.2rem" }} />
                        <Text as="span">{`${Month}.${Day}(${DayOfWeek}) ${hour}시`}</Text>
                        <IconButton>
                          <PeopleAltIcon color="action" sx={{ fontSize: "1.2rem" }} />
                        </IconButton>
                        <Text as="span">{curNum + "/" + maxNum}</Text>
                      </DivWrapper>
                      {/* <DivWrapper>
                        <WcIcon color="action" sx={{ fontSize: "20px", marginRight: "5px" }} />
                        <Text as="span">{sexLimit === "f" ? "여자만" : sexLimit === "m" ? "남자만" : "성별무관"}</Text>
                        <IconButton>
                          <RemoveCircleOutlineIcon sx={{ fontSize: "20px" }} />
                        </IconButton>
                        <Text as="span">{highAgeLimit}세 이하</Text>
                      </DivWrapper> */}
                    </>
                  )}
                </>
              }
              Bottom={
                <>
                  <DivWrapper
                    css={css`
                      justify-content: space-between;
                    `}
                  >
                    <IconWrapper>
                      <FavoriteBorderIcon color="action" sx={{ fontSize: "1.3rem", marginRight: "0.3rem" }} />
                      <Text as="span">{wishlistNum}</Text>
                      <ChatBubbleOutlineIcon color="action" sx={{ fontSize: "1.3rem", marginRight: "0.3rem", marginLeft: "0.5rem" }} />
                      <Text as="span">{chatNum}</Text>
                    </IconWrapper>
                    {closed && (
                      <Button
                        disabled
                        css={css`
                          padding: 0.5rem;
                          min-width: 6rem;
                          @media screen and (max-width: 599px) {
                            padding: 0.3rem;
                            min-width: 4rem;
                          }
                        `}
                      >
                        <Text
                          as="span"
                          css={css`
                            font-size: 1rem;
                            @media screen and (max-width: 599px) {
                              font-size: 0.8rem;
                            }
                          `}
                        >
                          마감완료
                        </Text>
                      </Button>
                    )}
                    {!closed && maxNum === curNum && (
                      <Button
                        disabled
                        css={css`
                          padding: 0.5rem;
                          min-width: 6rem;
                          @media screen and (max-width: 599px) {
                            padding: 0.3rem;
                            min-width: 4rem;
                          }
                        `}
                      >
                        <Text
                          as="span"
                          css={css`
                            font-size: 1rem;
                            @media screen and (max-width: 599px) {
                              font-size: 0.8rem;
                            }
                          `}
                        >
                          인원마감
                        </Text>
                      </Button>
                    )}
                  </DivWrapper>
                </>
              }
            />
          </Link>
        );
      })}
    </>
  );
};

export { HobbyCardList };

const DivWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  box-sizing: border-box;
  @media screen and (max-width: 599px) {
    font-size: 0.6rem;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
`;

const IconWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

const ButtonDiv = styled.div`
  display: inline-flex;
  width: auto;
  padding: 0.3rem 0.5rem;
  background-color: ${racconsThemes.defaultTheme.color.background.card};
  border: 1px solid ${racconsThemes.defaultTheme.color.text.hover};
  border-radius: 1.5rem;
  box-sizing: border-box;
`;
