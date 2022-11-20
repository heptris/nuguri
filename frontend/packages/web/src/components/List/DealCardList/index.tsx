import Link from "@/components/Link";
import { Button, Card, Text } from "@common/components";
import { racconsThemes } from "@common/components/src/styles/theme";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import Image from "next/image";
import { useRecoilState } from "recoil";
import { dealDetailCategoryId } from "@/store";

import { ROUTES } from "@/constant";
import { DealItemDetailType } from "@/types";
import { IconButton } from "@mui/material";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
const { REGION, HOBBY, DEAL, GROUP_DEAL } = ROUTES;

export const DealCardList = ({ dealList }) => {
  const categoryName = ["성장, 자기계발", "운동, 액티비티", "문화, 예술", "푸드, 드링크", "취미", "여행, 나들이"];
  const [dealCategoryId, setDealCategoryId] = useRecoilState(dealDetailCategoryId);
  return (
    <>
      {dealList?.map(({ dealId, dealImage, price, title, hit, deal, categoryId, localId }: DealItemDetailType) => {
        return (
          <Link
            href={REGION + `/${localId}` + DEAL + `/${dealId}`}
            noLinkStyle
            css={css`
              display: contents;
            `}
            onClick={() => {
              setDealCategoryId(categoryId);
            }}
          >
            <Card
              Image={<Image src={dealImage} width={500} height={400} />}
              Content={
                <>
                  <ButtonDiv
                    css={css`
                      margin: 0.5rem 0;
                    `}
                  >
                    <Text
                      css={css`
                        color: ${racconsThemes.defaultTheme.color.background.submain};
                        padding: 0.3rem 0.5rem;
                        @media screen and (max-width: 599px) {
                          font-size: 0.6rem;
                          padding: 0.1rem;
                        }
                      `}
                    >
                      {categoryName[categoryId - 1]}
                    </Text>
                  </ButtonDiv>
                  <Text
                    as="h1"
                    css={css`
                      font-size: 1.2rem;
                      @media screen and (max-width: 599px) {
                        font-size: 1rem;
                        flex-basis: 45%;
                      }
                      overflow: hidden;
                      text-overflow: ellipsis;
                      white-space: nowrap;
                      margin-bottom: 0.5rem;
                    `}
                  >
                    {title}
                  </Text>
                  <Text
                    as="h1"
                    css={css`
                      font-size: 1.2rem;
                      @media screen and (max-width: 599px) {
                        font-size: 1rem;
                      }
                      font-weight: 700;
                      margin-bottom: 1rem;
                    `}
                  >
                    {price.toLocaleString("ko-KR")}원
                  </Text>
                </>
              }
              Bottom={
                <>
                  <DivWrapper
                    css={css`
                      justify-content: space-between;
                    `}
                  >
                    <IconWrapper>
                      <FavoriteBorderIcon color="action" sx={{ fontSize: "1.3rem", marginRight: "0.3rem" }} />
                      <Text as="span">{hit}</Text>
                    </IconWrapper>
                    {deal && (
                      <Button
                        disabled
                        css={css`
                          padding: 0.5rem;
                          min-width: 6rem;
                          @media screen and (max-width: 599px) {
                            padding: 0.3rem;
                            min-width: 4rem;
                          }
                        `}
                      >
                        <Text
                          as="span"
                          css={css`
                            font-size: 1rem;
                            @media screen and (max-width: 599px) {
                              font-size: 0.8rem;
                            }
                          `}
                        >
                          판매완료
                        </Text>
                      </Button>
                    )}
                  </DivWrapper>
                </>
              }
            />
          </Link>
        );
      })}
    </>
  );
};
const DivWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  box-sizing: border-box;
  @media screen and (max-width: 599px) {
    font-size: 0.6rem;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
`;
const IconWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
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
