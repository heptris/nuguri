import { useState } from "react";
import styled from "@emotion/styled";
import { css } from "@emotion/react";
import { useRecoilState } from "recoil";

import { useHeader, useUser, useCategory } from "@/hooks";
import { Menu, Text } from "@common/components";
import Link from "@/components/Link";
import { ROUTES } from "@/constant";
import { menuCategoryState } from "@/store";
import { racconsThemes } from "@common/components/src/styles/theme";
import { HobbyCardList } from "@/components/List/HobbyCardList";
import { DealCardList } from "@/components/List/DealCardList";
import { useList } from "@/hooks/useList";

const { REGION, HOBBY, DEAL, GROUP_DEAL } = ROUTES;

const HomePage = () => {
  const { options } = useCategory();

  useHeader({ mode: "MAIN", headingText: undefined });
  const [categoryId, setCategoryId] = useRecoilState(menuCategoryState);
  const {
    userInfo: { localId },
  } = useUser();

  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);
  const handleClickListItem = (selectedMenu: HTMLElement) => {
    setAnchorEl(selectedMenu);
  };
  const handleMenuItemClick = (categoryId: number) => {
    setCategoryId(categoryId);
    setAnchorEl(null);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  const { hobbyList, dealList } = useList({ categoryId, localId });

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
          <Link href={REGION + `/${localId}` + HOBBY} noLinkStyle>
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
          <HobbyCardList hobbyList={hobbyList} />
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
          <Link href={REGION + `/${localId}` + DEAL} noLinkStyle>
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
          <DealCardList dealList={dealList} />
        </CardWapper>
      </CategorytWrapper>
      {/* <CategorytWrapper>
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
          <Link href={REGION + `/${localId}` + GROUP_DEAL} noLinkStyle>
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
          
        </CardWapper>
      </CategorytWrapper> */}
    </MainWrapper>
  );
};
export default HomePage;

const MainWrapper = styled.div`
  max-width: 1799px;
  display: flex;
  flex-direction: column;
  padding: 1rem;
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
const DateDiv = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  box-sizing: border-box;
`;

const ButtonDiv = styled.div`
  display: inline-flex;
  width: auto;
  padding: 0.3rem;
  background-color: ${racconsThemes.defaultTheme.color.background.card};
  border: 1px solid ${racconsThemes.defaultTheme.color.text.hover};
  border-radius: 1.5rem;
  box-sizing: border-box;
`;
