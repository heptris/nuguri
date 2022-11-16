import React from "react";
import { Text } from "@common/components";
import { css } from "@emotion/react";
import styled from "@emotion/styled";

const DealListPage = () => {
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
