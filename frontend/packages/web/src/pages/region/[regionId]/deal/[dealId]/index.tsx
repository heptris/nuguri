import axios from "axios";

import { ACCESS_TOKEN, apiInstance, ENDPOINT_API } from "@/api";
import { useAlert, useAuth, useBottom, useDealItem, useHeader } from "@/hooks";
import { DealItemDetailType } from "@/types";
import { Avatar, Button, Text } from "@common/components";
import { css } from "@emotion/react";
import { Card, IconButton } from "@mui/material";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";
import Image from "next/image";
import { useEffect, useState } from "react";
import { racconsThemes } from "@common/components/src/styles/theme";
import styled from "@emotion/styled";
import { useRecoilState } from "recoil";
import { dealDetailCategoryId } from "@/store";

export async function getServerSideProps(context) {
  const { query } = context;
  const accessToken = context.req.cookies[ACCESS_TOKEN];

  const { regionId, dealId } = query;
  const getUnloginedInfo = async () => await axios.get(ENDPOINT_API + `/deal/${dealId}/detail`).then(({ data }: { data: { data: DealItemDetailType } }) => data.data);
  const getLoginedInfo = async () =>
    await axios.get(ENDPOINT_API + `/deal/${dealId}/login/detail`, { headers: { authorization: `Bearer ${accessToken}` } }).then(({ data }: { data: { data: DealItemDetailType } }) => data.data);
  const dealDetailInfo = accessToken ? await getLoginedInfo() : await getUnloginedInfo();
  return {
    props: {
      regionId,
      dealDetailInfo,
    },
  };
}
const categoryName = ["성장, 자기계발", "운동, 액티비티", "문화, 예술", "푸드, 드링크", "취미", "여행, 나들이"];

const DealDetailPage = (props: { regionId: number; dealDetailInfo: DealItemDetailType }) => {
  const { regionId, dealDetailInfo } = props;
  const { sellerId, dealId, deal, dealImage, description, dong, hit, price, title, favorite, sellerNickname } = dealDetailInfo;
  const [categoryId, setCategoryId] = useRecoilState(dealDetailCategoryId);
  console.log(categoryId);
  console.log(dealDetailInfo);
  const { mutateToGetDealHistoryId } = useDealItem({ sellerId, dealId });
  const { isLogined } = useAuth();
  const { handleAlertOpen } = useAlert();
  useHeader({ mode: "ITEM" });
  const { setBottom } = useBottom(<></>);
  const [dealFavorite, setDealFavorite] = useState<boolean>(favorite);

  const postFavoriteDealRegist = async () => {
    await apiInstance
      .post(ENDPOINT_API + "/deal" + `/${dealId}` + "/favorite", dealId)
      .then(res => {
        console.log(res);
        handleAlertOpen("즐겨찾기 해제/등록에 성공했습니다.", true, 2000);
      })
      .catch(err => {
        console.log(err);
        handleAlertOpen("즐겨찾기 해제/등록에 실패했습니다.", false, 2000);
      });
  };

  const [favCnt, setFavCnt] = useState<number>();
  const [chatCnt, setChatCnt] = useState<number>();

  const getFavoriteChatCnt = async () => {
    const { data } = await apiInstance.get(ENDPOINT_API + "/deal-history/" + `${dealId}` + "/count");
    return data.data;
  };
  useEffect(() => {
    getFavoriteChatCnt().then(data => {
      const { chatCnt, favCnt } = data;
      setChatCnt(chatCnt);
      setFavCnt(favCnt);
    });
  });
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
              justify-content: space-evenly;
              align-items: center;
              padding: 0.5rem;
            `}
          >
            {dealFavorite && (
              <FavoriteBtn
                onClick={() => {
                  postFavoriteDealRegist();
                  setDealFavorite(prev => !prev);
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
            {!dealFavorite && (
              <FavoriteBtn
                onClick={() => {
                  postFavoriteDealRegist();
                  setDealFavorite(prev => !prev);
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
            <Text
              css={css`
                font-size: 1.5rem;
                font-weight: bold;
              `}
            >
              {price.toLocaleString("ko-KR")} 원
            </Text>
            {isLogined ? (
              <Button
                css={css`
                  width: 30%;
                  border-radius: 2rem;
                `}
                onClick={() => mutateToGetDealHistoryId()}
                disabled={deal}
              >
                {deal && <Text>판매완료</Text>}
                {!deal && (
                  <Text
                    css={css`
                      color: white;
                    `}
                  >
                    채팅하기
                  </Text>
                )}
              </Button>
            ) : (
              <Button disabled>로그인이 필요합니다</Button>
            )}
          </div>
        ),
      });
  }, [isLogined, dealFavorite]);
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

      <Image src={dealImage} alt={title} height={1500} width={2000} />

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
            {sellerNickname}
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
        <DivLocationWrapper>
          <LocationOnIcon color="action" sx={{ fontSize: "1.2rem", marginRight: "0.2rem" }} />
          <Text as="span">{dong}</Text>
        </DivLocationWrapper>
        <MainWrapper>
          <Text
            as="h1"
            css={css`
              padding: 1rem;
            `}
          >
            {description}
          </Text>
        </MainWrapper>
        <DivWrapper>
          <FavoriteIcon color="action" sx={{ fontSize: "1.2rem", marginRight: "0.2rem" }} />
          <Text as="span">{favCnt}</Text>
          <IconButton>
            <ChatBubbleOutlineIcon color="action" sx={{ fontSize: "1.2rem" }} />
          </IconButton>
          <Text as="span">{chatCnt}</Text>
        </DivWrapper>
      </div>
    </div>
  );
};

export default DealDetailPage;

const MainWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: inherit;
  padding: 1rem 0rem;
`;

const FavoriteBtn = styled.div`
  width: 2.5rem;
  height: 2.5rem;
  border: 1px solid #bcbcbc;
  border-radius: 1.5rem;
  background-color: white;
  &:hover {
    cursor: pointer;
  }
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

const DivWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 0rem 2rem;
  box-sizing: border-box;
  margin-top: 2rem;
`;

const DivLocationWrapper = styled(DivWrapper)`
  justify-content: center;
`;
