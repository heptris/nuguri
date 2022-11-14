import Link from "@/components/Link";
import { ROUTES } from "@/constant";
import { Button, Text } from "@common/components";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import Image from "next/image";
const { LOGIN, SIGNUP } = ROUTES;
import mainImg from "../../public/assets/main.png";

const mainPage = () => {
  return (
    <>
      <ContainerWrapper>
        <MainWrapper>
          <Image src={mainImg} width={120} height={120} />
          <Text
            css={css`
              font-size: 1.5rem;
              font-weight: 700;
              margin: 2rem 1rem 1rem;
              color: #e4bc96;
            `}
          >
            <Text
              css={css`
                color: white;
              `}
            >
              너
            </Text>
            의{" "}
            <Text
              css={css`
                color: white;
              `}
            >
              구
            </Text>
            역의 취미거
            <Text
              css={css`
                color: white;
              `}
            >
              리
            </Text>{" "}
          </Text>

          <Text
            css={css`
              font-size: 1.5rem;
              font-weight: 700;
              margin-bottom: 1rem;
              color: white;
            `}
          >
            너구리
          </Text>
          <TextWrapper>
            <Text
              css={css`
                color: #f2decb;
              `}
            >
              내 구역을 설정하고{" "}
            </Text>
            <Text
              css={css`
                color: #f2decb;
              `}
            >
              너구리를 시작해보세요.
            </Text>
          </TextWrapper>
        </MainWrapper>
      </ContainerWrapper>
      <SubmainWrapper>
        <div
          css={css`
            width: 100%;
            display: flex;
            justify-content: center;
          `}
        >
          <Link href={SIGNUP}>
            <Button
              css={css`
                background-color: #a77c4e;
                width: 20rem;
                margin-bottom: 1rem;
                border-radius: 1rem;
                &:hover {
                  background-color: #d3bea7;
                }
              `}
            >
              <Text>회원가입</Text>
            </Button>
          </Link>
        </div>

        <Text>
          이미 계정이 있나요?{" "}
          <Link href={LOGIN}>
            <Text
              css={css`
                text-decoration: none;
                color: #5a3d1c;
                font-weight: 500;
              `}
            >
              로그인
            </Text>
          </Link>
        </Text>
      </SubmainWrapper>
    </>
  );
};
export default mainPage;

const ContainerWrapper = styled.div`
  display: flex;
  width: 100%;
  height: 80vh;
  position: relative;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #5a3d1c;
`;

const MainWrapper = styled.div`
  display: flex;
  height: 50vh;
  position: absolute;
  flex-direction: column;
  justify-content: cneter;
  align-items: center;
`;

const TextWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const SubmainWrapper = styled.div`
  display: flex;
  width: 100%;
  height: 25vh;
  position: absolute;
  border-top-left-radius: 1rem;
  border-top-right-radius: 1rem;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: white;
  bottom: 0rem;
`;
