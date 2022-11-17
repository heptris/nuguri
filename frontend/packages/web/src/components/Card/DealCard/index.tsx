import Link from "@/components/Link";
import { Card, Text } from "@common/components";
import { racconsThemes } from "@common/components/src/styles/theme";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import Image from "next/image";

import { ROUTES } from "@/constant";
import { DealItemDetailType } from "@/types";
import { IconButton } from "@mui/material";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
const { REGION, HOBBY, DEAL, GROUP_DEAL } = ROUTES;

export const DealCard = ({ dealList, categoryId, localId }) => {
  return (
    <>
      {dealList?.map(({ dealId, dealImage, price, title, hit, deal }: DealItemDetailType) => {
        return (
          <Link
            href={REGION + `/${localId}` + DEAL + `/${dealId}`}
            noLinkStyle
            css={css`
              display: contents;
            `}
          >
            <Card
              Image={<Image src={dealImage} width={500} height={300} />}
              Content={
                <>
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
                  <div>
                    <IconButton aria-label="add to favorites">
                      <FavoriteBorderIcon />
                    </IconButton>
                    <Text as="span">{hit}</Text>
                  </div>
                  <ButtonDiv
                    css={css`
                      border-radius: 0.5rem;
                      background-color: ${racconsThemes.defaultTheme.color.text.sub};
                    `}
                  >
                    <Text
                      css={css`
                        color: ${racconsThemes.defaultTheme.color.background.submain};
                        padding: 0.2rem 0.5rem;
                        @media screen and (max-width: 599px) {
                          font-size: 0.6rem;
                          padding: 0.1rem;
                        }
                      `}
                    >
                      {deal ? "판매" : "마감"}완료
                    </Text>
                  </ButtonDiv>
                </>
              }
            />
          </Link>
        );
      })}
    </>
  );
};

const ButtonDiv = styled.div`
  display: inline-flex;
  width: auto;
  padding: 0.3rem;
  background-color: ${racconsThemes.defaultTheme.color.background.card};
  border: 1px solid ${racconsThemes.defaultTheme.color.text.hover};
  border-radius: 1.5rem;
  box-sizing: border-box;
`;
