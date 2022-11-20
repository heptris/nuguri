import { Card, IconButton } from "@mui/material";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import PaidIcon from "@mui/icons-material/Paid";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import Image from "next/image";
import axios from "axios";

import { Avatar, Button, Text } from "@common/components";

import { ACCESS_TOKEN, apiInstance, ENDPOINT_API } from "@/api";
import { useAuth, useBottom, useHeader, useHobbyRoom, useUser } from "@/hooks";
import { HobbyMemberType, HobbyRoomType } from "@/types";
import { useEffect, useState } from "react";
import { racconsThemes } from "@common/components/src/styles/theme";
import { ROUTES } from "@/constant";
import { useRouter } from "next/router";
import Link from "@/components/Link";
import { useFavoriteHobby } from "@/hooks/useFavoriteHobby";
import { useFavHobbyRegist } from "@/hooks/useFavHobbyRegist";
import { useMemberHobby } from "@/hooks/useMemberHobby";
import { useRecoilState } from "recoil";

const { APPLY, ADMIN } = ROUTES;

export async function getServerSideProps({ params, query, req }) {
  const { regionId, hobbyId } = query;
  const accessToken = req.cookies[ACCESS_TOKEN];
  const hobbyRoomDefaultInfo = await axios.get(ENDPOINT_API + `/hobby/${hobbyId}/detail`, { headers: { authorization: `Bearer ${accessToken}` } }).then(({ data }) => {
    return data.data;
  });
  return {
    props: {
      hobbyRoomDefaultInfo,
    },
  };
}
const categoryName = ["성장, 자기계발", "운동, 액티비티", "문화, 예술", "푸드, 드링크", "취미", "여행, 나들이"];
const HobbyDetailPage = ({ hobbyRoomDefaultInfo }: { hobbyRoomDefaultInfo: HobbyRoomType }) => {
  const { hobbyRoomInfo, refetchHobbyRoomInfo } = useHobbyRoom(hobbyRoomDefaultInfo);
  const { highAgeLimit, rowAgeLimit, categoryId, closed, content, curNum, endDate, fee, hobbyId, imageurl, localId, maxNum, meetingPlace, sexLimit, title, nickname, profileImage } = hobbyRoomInfo;
  useHeader({ mode: "ITEM" });
  const { setBottom } = useBottom(<></>);
  const { replace, push } = useRouter();
  const { userInfo } = useUser();
  const { isLogined } = useAuth();
  const [favorite, setFavorite] = useState<boolean>();
  const getHobbyFavorite = async () => {
    const { data } = await apiInstance.get(ENDPOINT_API + "/hobby/favorite/favoritecheck" + `/${hobbyId}`);
    return data.data;
  };

  useEffect(() => {
    getHobbyFavorite().then(data => {
      setFavorite(data);
    });
  }, []);
  const { hobbyMemberList } = useMemberHobby(hobbyId);

  const { postFavoriteHobbyRegist } = useFavHobbyRegist(hobbyId);
  const week = new Array("일", "월", "화", "수", "목", "금", "토");
  const getDate = new Date(endDate);
  const DayOfWeek = week[getDate.getDay()];
  const Month = getDate.getMonth() + 1;
  const Day = getDate.getDate();
  const hours = getDate.getHours();
  const minutes = getDate.getMinutes();
  let hour;
  if (hours > 12) hour = "오후 " + (hours - 12);
  else if (hours === 12) hour = "오후 " + hours;
  else if (hours === 24) hour = "오전 " + (hours - 12);
  else hour = "오전 " + hours;

  useEffect(() => {
    isLogined &&
      setBottom({
        children: (
          <div
            css={css`
              position: fixed;
              bottom: 0;
              display: flex;
              width: 100%;
              justify-content: center;
              align-items: center;
              padding: 0.5rem;
            `}
          >
            {favorite && (
              <FavoriteBtn
                onClick={() => {
                  postFavoriteHobbyRegist();
                  setFavorite(prev => !prev);
                }}
                css={css`
                  &:hover {
                    cursor: pointer;
                    background-color: ${racconsThemes.defaultTheme.color.text.hover};
                  }
                `}
              >
                <FavoriteIcon
                  fontSize="large"
                  sx={{ fontSize: "2rem" }}
                  color="action"
                  css={css`
                    transform: translate(0.2rem, 0.3rem);
                  `}
                />
              </FavoriteBtn>
            )}
            {!favorite && (
              <FavoriteBtn
                onClick={() => {
                  postFavoriteHobbyRegist();
                  setFavorite(prev => !prev);
                }}
                css={css`
                  &:hover {
                    cursor: pointer;
                    background-color: ${racconsThemes.defaultTheme.color.text.hover};
                  }
                `}
              >
                <FavoriteBorderIcon
                  fontSize="large"
                  sx={{ fontSize: "2rem" }}
                  color="action"
                  css={css`
                    transform: translate(0.2rem, 0.3rem);
                  `}
                />
              </FavoriteBtn>
            )}

            {userInfo.nickname !== nickname ? (
              <Button
                css={css`
                  width: 80%;
                  border-radius: 2rem;
                `}
                onClick={() => {
                  push(`/apply/${hobbyId}`);
                }}
              >
                <Text
                  css={css`
                    color: white;
                  `}
                >
                  참여 신청하기
                </Text>
              </Button>
            ) : (
              <Button
                css={css`
                  width: 80%;
                  border-radius: 2rem;
                `}
              >
                <Link href={ADMIN + `/${hobbyId}`} noLinkStyle>
                  <Text
                    css={css`
                      color: white;
                    `}
                  >
                    운영 관리하기
                  </Text>
                </Link>
              </Button>
            )}
          </div>
        ),
      });
  }, [isLogined, favorite]);

  return (
    <div
      css={css`
        width: 100%;
        height: 100vh;
        position: absolute;
      `}
    >
      <ButtonDiv
        css={css`
          margin: 1rem;
          position: absolute;
          z-index: 1;
        `}
      >
        <Text
          css={css`
            color: ${racconsThemes.defaultTheme.color.background.submain};
            padding: 0.3rem 0.5rem;
            @media screen and (max-width: 599px) {
              padding: 0.1rem;
            }
          `}
        >
          {categoryName[categoryId - 1]}
        </Text>
      </ButtonDiv>

      <Image src={imageurl} alt={title} height={1500} width={2000} />

      <div
        css={css`
          position: relative;
          top: -3rem;
          display: flex;
          flex-direction: column;
          width: 90%;
          height: 100%;
          align-items: center;
          margin: auto;
        `}
      >
        <Card
          css={css`
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 2rem 1rem 1rem 1rem;
            width: inherit;
          `}
        >
          <Avatar
            src={profileImage}
            sx={{ width: 100, height: 100 }}
            css={css`
              position: absolute;
              top: -3rem;
              border: 1px solid ${racconsThemes.defaultTheme.color.text.sub};
            `}
          />
          <Text
            css={css`
              margin-top: 1.5rem;
              display: block;
              font-size: 1.2rem;
            `}
          >
            {nickname}
          </Text>
          <Text
            css={css`
              display: block;
              font-weight: bold;
              font-size: 1.5rem;
            `}
          >
            {title}
          </Text>
        </Card>
        <DivWrapper>
          <CalendarMonthIcon color="action" sx={{ fontSize: "1.2rem", marginRight: "0.2rem" }} />
          <Text as="span">{`${Month}.${Day}(${DayOfWeek}) ${hour}시 ${minutes}분`}</Text>
        </DivWrapper>
        <MainWrapper>
          <Text
            as="h1"
            css={css`
              padding: 1rem;
            `}
          >
            {content}
          </Text>
          <div>
            <Text
              as="h1"
              css={css`
                color: red;
                font-size: 1.2rem;
                margin: 1rem 0rem;
              `}
            >
              안내사항
            </Text>
            <div>
              <IconTextWrapper>
                <PeopleAltIcon sx={{ marginRight: "0.5rem" }} />
                <Text>
                  {curNum}/{maxNum}명
                </Text>
              </IconTextWrapper>
              <IconTextWrapper>
                <PaidIcon sx={{ marginRight: "0.5rem" }} />
                <Text>{fee === 0 ? "무료" : fee.toLocaleString("ko-KR")}원</Text>
              </IconTextWrapper>
              <IconTextWrapper>
                <CheckCircleIcon sx={{ marginRight: "0.5rem" }} />
                <Text>
                  {rowAgeLimit + "세 이상 "}
                  {highAgeLimit + "세 이하 "}
                  {sexLimit === "f" ? "여자만" : sexLimit === "m" ? "남자만" : "누구나"}
                </Text>
              </IconTextWrapper>
              <IconTextWrapper>
                <CalendarMonthIcon sx={{ marginRight: "0.5rem" }} />
                <Text>{`${Month}.${Day}(${DayOfWeek}) ${hour}시 ${minutes}분`}</Text>
              </IconTextWrapper>
              <IconTextWrapper>
                <LocationOnIcon sx={{ marginRight: "0.5rem" }} />
                <Text>{meetingPlace}</Text>
              </IconTextWrapper>
            </div>
          </div>
          <div>
            <Text
              as="h1"
              css={css`
                color: red;
                font-size: 1.2rem;
                margin: 1rem 0rem;
              `}
            >
              참여멤버
            </Text>
            {hobbyMemberList?.map(({ imageUrl, nickname }: HobbyMemberType) => (
              <div
                css={css`
                  display: flex;
                  flex-direction: row;
                  align-items: center;
                `}
              >
                <Avatar
                  src={imageUrl}
                  sx={{ width: 70, height: 70 }}
                  css={css`
                    border: 1px solid ${racconsThemes.defaultTheme.color.text.sub};
                    margin-right: 1rem;
                  `}
                />
                <Text
                  css={css`
                    display: block;
                    font-size: 1.2rem;
                  `}
                >
                  {nickname}
                </Text>
              </div>
            ))}
          </div>
        </MainWrapper>
      </div>
    </div>
  );
};

export default HobbyDetailPage;

const MainWrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding: 1rem;
  width: inherit;
`;

const IconTextWrapper = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 1rem;
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

const FavoriteBtn = styled.div`
  width: 2.5rem;
  height: 2.5rem;
  border: 1px solid #bcbcbc;
  border-radius: 1.5rem;
  background-color: white;
  margin-right: 1rem;
  &:hover {
    cursor: pointer;
  }
`;

const DivWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  box-sizing: border-box;
  margin-top: 1rem;
`;
