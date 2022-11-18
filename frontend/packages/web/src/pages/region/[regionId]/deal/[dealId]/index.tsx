import axios from "axios";

import { ACCESS_TOKEN, ENDPOINT_API } from "@/api";
import { useAuth, useBottom, useDealItem, useHeader } from "@/hooks";
import { DealItemDetailType } from "@/types";
import { Avatar, Button, Text } from "@common/components";
import { css } from "@emotion/react";
import { Card, IconButton } from "@mui/material";
import FavoriteIcon from "@mui/icons-material/Favorite";
import Image from "next/image";
import { useEffect } from "react";

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
const DealDetailPage = (props: { regionId: number; dealDetailInfo: DealItemDetailType }) => {
  const { regionId, dealDetailInfo } = props;
  const { sellerId, dealId, deal, dealImage, description, dong, hit, price, title, favorite } = dealDetailInfo;
  console.log(dealDetailInfo);
  const { mutateToGetDealHistoryId } = useDealItem({ sellerId, dealId });
  const { isLogined } = useAuth();
  useHeader({ mode: "ITEM" });
  const { setBottom } = useBottom(<></>);

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
            <IconButton disabled={!isLogined}>
              <FavoriteIcon fontSize="large" />
            </IconButton>
            <Text
              css={css`
                font-size: 1.5rem;
                font-weight: bold;
              `}
            >
              {price} 원
            </Text>
            {isLogined ? (
              <Button size="large" onClick={() => mutateToGetDealHistoryId()}>
                채팅하기
              </Button>
            ) : (
              <Button disabled>로그인이 필요합니다</Button>
            )}
          </div>
        ),
      });
  }, [isLogined]);
  return (
    <div
      css={css`
        width: 100%;
        position: absolute;
      `}
    >
      <Image src={dealImage} alt={title} layout={"fill"} />
      <div
        css={css`
          position: relative;
          top: 10rem;
          display: flex;
          flex-direction: column;
          width: 90%;
          align-items: center;
          margin: auto;
        `}
      >
        <Card
          css={css`
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 1rem;
            width: inherit;
          `}
        >
          <Avatar
            css={css`
              position: absolute;
              top: -2rem;
            `}
          />
          <Text
            css={css`
              display: block;
              font-size: 1.5rem;
            `}
          >
            {"사용자 이름"}
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
        <Text>{description}</Text>
      </div>
    </div>
  );
};

export default DealDetailPage;
