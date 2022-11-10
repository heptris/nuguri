import { Button, Text } from "@common/components";
import styled from "@emotion/styled";

const mainPage = () => {
  return (
    <ContainerWrapper>
      <MainWrapper>
        <Text>너의 구역의 취미거리 너구리</Text>
        <Text>내 구역을 설정하고 너구리를 시작해보세요.</Text>
      </MainWrapper>
      <SubmainWrapper>
        <Button>회원가입</Button>
        <Text>이미 계정이 있나요? 로그인</Text>
      </SubmainWrapper>
    </ContainerWrapper>
  );
};
export default mainPage;

const ContainerWrapper = styled.div`
  display: flex;
  width: 100%;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const MainWrapper = styled.div`
  display: flex;
  flex-direction: column;
`;
const SubmainWrapper = styled.div`
  display: flex;
  flex-direction: column;
`;
