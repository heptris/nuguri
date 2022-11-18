import { ENDPOINT_API } from "@/api";
import { HobbyCardList } from "@/components/List/HobbyCardList";
import { useHeader } from "@/hooks";
import { menuCategoryState } from "@/store";
import { HobbyRoomType } from "@/types";
import { Card, Text } from "@common/components";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import axios from "axios";
import { useEffect, useState } from "react";
import { useRecoilState } from "recoil";

export async function getServerSideProps({ params }) {
  const { regionId } = params;

  const postHobbyList = async () => {
    const { data } = await axios.post(ENDPOINT_API + "/hobby/list", { localId: regionId });
    return data.data;
  };

  const defaultHobbyList = await postHobbyList();

  return {
    props: {
      defaultHobbyList,
      localId: regionId,
    },
  };
}

const HobbyListPage = ({ defaultHobbyList, localId }: { defaultHobbyList: HobbyRoomType[]; localId: number }) => {
  useHeader({ mode: "LIST" });
  const [hobbyList, setHobbyList] = useState(defaultHobbyList);
  const [categoryId] = useRecoilState(menuCategoryState);
  useEffect(() => {
    setHobbyList(hobbyList.filter(item => item.categoryId === categoryId));
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
        취미모임
      </Text>
      <ListWrapper>
        <HobbyCardList hobbyList={hobbyList} />
      </ListWrapper>
    </MainWrapper>
  );
};
export default HobbyListPage;

const MainWrapper = styled.div`
  max-width: 1799px;
  display: flex;
  flex-direction: column;
  padding: 1rem;
`;

const ListWrapper = styled.div`
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  margin-top: 0.5rem;
  box-sizing: border-box;
`;
