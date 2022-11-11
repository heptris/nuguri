import { ACCESS_TOKEN, ENDPOINT_API } from "@/api";
import { useDealItem } from "@/hooks";
import { DealItemDetailType } from "@/types";
import { Button } from "@common/components";
import axios from "axios";

import React from "react";

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
  const { sellerId, dealId } = dealDetailInfo;
  console.log(dealDetailInfo);
  const { mutateToGetDealHistoryId } = useDealItem({ sellerId, dealId });
  return (
    <>
      <div>DealDetailPage</div>
      <Button onClick={() => mutateToGetDealHistoryId()}>채팅방으로 이동</Button>
    </>
  );
};

export default DealDetailPage;
