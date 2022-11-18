import { useEffect, useState } from "react";
import { Text } from "@common/components";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import axios from "axios";
import { ENDPOINT_API } from "@/api";
import { DealItemType } from "@/types";
import { DealCardList } from "@/components/List/DealCardList";
import { useRecoilState } from "recoil";
import { menuCategoryState } from "@/store";
import { useHeader } from "@/hooks";

export async function getServerSideProps({ params }) {
  const { regionId } = params;

  const getDealList = async ({ regionId }) => {
    const { data } = await axios.get(ENDPOINT_API + `/deal/list?localId=${regionId}&page=0&size=10`);
    console.log(data);
    return data.data.content;
  };

  const defaultDealList = await getDealList({ regionId });

  return {
    props: {
      defaultDealList,
      localId: regionId,
    },
  };
}

const DealListPage = ({ defaultDealList, localId }: { defaultDealList: DealItemType[]; localId: number }) => {
  useHeader({ mode: "LIST" });
  const [dealList, setDealList] = useState(defaultDealList);
  const [categoryId, setCategoryId] = useRecoilState(menuCategoryState);
  useEffect(() => {
    setDealList(dealList.filter(item => item.categoryId === categoryId));
  }, []);
  return (
    <MainWrapper>
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
      <DealCardList categoryId={categoryId} dealList={dealList} localId={localId} />
    </MainWrapper>
  );
};
const MainWrapper = styled.div`
  max-width: 1799px;
  display: flex;
  flex-direction: column;
  padding: 1rem 2rem;
`;

export default DealListPage;
