import { useHeader,  useUser, useCategory } from "@/hooks";
import * as React from "react";
import { Card, Menu, Text } from "@common/components";
import styled from "@emotion/styled";
import { css } from "@emotion/react";
import Link from "@/components/Link";
import { ROUTES } from "@/constant";
import { useRecoilState } from "recoil";
import { menuCategoryState } from "@/store";

const { REGION, HOBBY, DEAL, GROUP_DEAL } = ROUTES;

const HomePage = () => {
  const { options } = useCategory();
  useHeader({ mode: "MAIN", headingText: undefined });
  const [categoryId, setCategoryId] = useRecoilState(menuCategoryState);
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);
  const handleClickListItem = (selectedMenu: HTMLElement) => {
    setAnchorEl(selectedMenu);
  };
  const handleMenuItemClick = (categoryId: number) => {
    setCategoryId(categoryId);
    setAnchorEl(null);
    console.log(categoryId);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
  const {
    userInfo: { localId },
  } = useUser();

  const newDate = new Date("2022-10-15 15:00:37");
  return (
    <MainWrapper>
      <Menu
        open={open}
        anchorEl={anchorEl}
        onCloseHandler={handleClose}
        handleClickListItem={handleClickListItem}
        handleMenuItemClick={handleMenuItemClick}
        categoryId={categoryId}
        options={options}
      />
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
          <Link href={REGION + `/${localId}` + HOBBY}>
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
          <Link
            href={REGION + `/${localId}` + HOBBY + "/1"}
            noLinkStyle
            css={css`
              display: contents;
            `}
          >
            <Card promiseDate={newDate} nowPeople={2} maxPeople={5} imgUrl={"/public/coding.jpg"} />
          </Link>
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
          <Link href={REGION + `/${localId}` + DEAL}>
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
          <Link
            href={REGION + `/${localId}` + DEAL + `/1`}
            noLinkStyle
            css={css`
              display: contents;
            `}
          >
            <Card price={1000000} imgUrl={"/public/coding.jpg"} />
          </Link>
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
          <Link href={REGION + `/${localId}` + GROUP_DEAL}>
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
  );
};
export default HomePage;

const MainWrapper = styled.div`
  max-width: 1799px;
  display: flex;
  flex-direction: column;
  padding: 1rem;
  background-color: #f2decb;
`;

const CategorytWrapper = styled.div`
  display: inline-block;
  max-width: 1599px;
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
  -ms-overflow-style: none;
  &::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
  }
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
