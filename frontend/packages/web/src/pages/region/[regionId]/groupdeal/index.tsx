import { Text } from "@common/components";
import { css } from "@emotion/react";
import styled from "@emotion/styled";

const GroupDealListPage = () => {
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
        공구목록
      </Text>
      <ListWrapper></ListWrapper>
    </MainWrapper>
  );
};
export default GroupDealListPage;

const MainWrapper = styled.div`
  max-width: 1799px;
  display: flex;
  flex-direction: column;
  padding: 1rem 2rem;
`;

const ListWrapper = styled.div`
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  margin-top: 0.5rem;
`;
