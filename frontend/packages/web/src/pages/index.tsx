import { headerState } from "@/store";
import * as React from "react";
import { Card, Menu, Text } from "@common/components";
import { useEffect } from "react";
import { useRecoilState } from "recoil";
import styled from "@emotion/styled";
import { css } from "@emotion/react";
import Link from "@/components/Link";
import { ROUTES } from "@/constant";
const { HOBBYLIST, DEALLIST, GROUPDEALLIST } = ROUTES;

const HomePage = () => {
  const [, setHeader] = useRecoilState(headerState);
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);
  const handleClick = (selectedMenu: HTMLElement) => {
    setAnchorEl(selectedMenu);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
  useEffect(() => {
    setHeader({ mode: "MAIN", headingText: undefined });
  }, [anchorEl]);
  const newDate = new Date("2022-10-15 15:00:37");
  return (
    <ContainerWrapper>
      <MainWrapper>
        <Menu open={open} anchorEl={anchorEl} onCloseHandler={handleClose} onClickHandler={handleClick} />
        <CategorytWrapper>
          <TitleWrapper>
            <Text
              css={css`
                font-size: 1.2rem;
                font-weight: 700;
              `}
            >
              취미모임
            </Text>
            <Link href={HOBBYLIST}>
              <Text
                css={css`
                  color: #5e6272;
                  &:hover {
                    color: #999daf;
                    cursor: pointer;
                  }
                `}
              >
                더보기
              </Text>
            </Link>
          </TitleWrapper>
          <CardWapper>
            <Card promiseDate={newDate} nowPeople={2} maxPeople={5} imgUrl={"/public/coding.jpg"} />
            <Card promiseDate={newDate} nowPeople={2} maxPeople={5} imgUrl={"/public/coding.jpg"} />
            <Card promiseDate={newDate} nowPeople={2} maxPeople={5} imgUrl={"/public/coding.jpg"} />
            <Card promiseDate={newDate} nowPeople={2} maxPeople={5} imgUrl={"/public/coding.jpg"} />
          </CardWapper>
        </CategorytWrapper>
        <CategorytWrapper>
          <TitleWrapper>
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
            <Link href={DEALLIST}>
              <Text
                css={css`
                  color: #5e6272;
                  &:hover {
                    color: #999daf;
                    cursor: pointer;
                  }
                `}
              >
                더보기
              </Text>
            </Link>
          </TitleWrapper>
          <CardWapper>
            <Card price={1000000} imgUrl={"/public/coding.jpg"} />
            <Card price={1000000} imgUrl={"/public/coding.jpg"} />
            <Card price={1000000} imgUrl={"/public/coding.jpg"} />
            <Card price={1000000} imgUrl={"/public/coding.jpg"} />
          </CardWapper>
        </CategorytWrapper>
        <CategorytWrapper>
          <TitleWrapper>
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
            <Link href={GROUPDEALLIST}>
              <Text
                css={css`
                  color: #5e6272;
                  &:hover {
                    color: #999daf;
                    cursor: pointer;
                  }
                `}
              >
                더보기
              </Text>
            </Link>
          </TitleWrapper>
          <CardWapper>
            <Card promiseDate={newDate} nowPeople={2} maxPeople={5} price={1000000} imgUrl={"/public/coding.jpg"} />
            <Card promiseDate={newDate} nowPeople={2} maxPeople={5} price={1000000} imgUrl={"/public/coding.jpg"} />
            <Card promiseDate={newDate} nowPeople={2} maxPeople={5} price={1000000} imgUrl={"/public/coding.jpg"} />
            <Card promiseDate={newDate} nowPeople={2} maxPeople={5} price={1000000} imgUrl={"/public/coding.jpg"} />
          </CardWapper>
        </CategorytWrapper>
      </MainWrapper>
    </ContainerWrapper>
  );
};
export default HomePage;
const ContainerWrapper = styled.div`
  display: flex;
  justify-content: center;
  @media screen and (max-width: 899px) {
    justify-content: left;
  }
`;
const MainWrapper = styled.div`
  max-width: 1799px;
  display: flex;
  flex-direction: column;
  padding: 1rem;
`;

const CategorytWrapper = styled.div`
  display: inline-block;
  max-width: 1799px;
  flex-direction: column;
`;

const TitleWrapper = styled.div`
  display: flex;
  box-sizing: border-box;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
`;

const CardWapper = styled.div`
  max-width: 1799px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  @media screen and (max-width: 1799px) {
    width: 100%;
    overflow-x: scroll;
    overflow-y: hidden;
  }

  @media screen and (max-width: 899px) {
    /* 모바일 가로, 타블렛 세로 */
    width: 100%;
    overflow-x: scroll;
    overflow-y: hidden;
  }

  @media screen and (max-width: 599px) {
    /* 모바일 세로 */
    width: 100%;
    overflow-x: scroll;
    overflow-y: hidden;
  }
`;
