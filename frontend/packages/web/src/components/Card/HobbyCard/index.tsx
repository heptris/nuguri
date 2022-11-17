import Link from "@/components/Link";
import { HobbyRoomType } from "@/types";
import { Card, Text } from "@common/components";
import { racconsThemes } from "@common/components/src/styles/theme";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import { IconButton } from "@mui/material";
import Image from "next/image";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import WcIcon from "@mui/icons-material/Wc";
import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";
import { ROUTES } from "@/constant";
import { useUser } from "@/hooks";

const { REGION, HOBBY, DEAL, GROUP_DEAL } = ROUTES;

const HobbyCard = ({ hobbyList, categoryId, localId }) => {
  return (
    <>
      {hobbyList?.map(({ highAgeLimit, categoryId, curNum, endDate, hobbyId, hobbyImage, maxNum, title, sexLimit }: HobbyRoomType) => {
        return (
          <Link
            href={REGION + `/${localId}` + HOBBY + `/${hobbyId}`}
            noLinkStyle
            css={css`
              display: contents;
            `}
          >
            <Card
              Image={<Image src={hobbyImage} width={500} height={300} />}
              Content={
                <>
                  <ButtonDiv
                    css={css`
                      margin-bottom: 1rem;
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
                      {categoryId}
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

                  {!!endDate && (
                    <DateDiv
                      css={css`
                        @media screen and (max-width: 599px) {
                          font-size: 0.8rem;
                          overflow: hidden;
                          text-overflow: ellipsis;
                          white-space: nowrap;
                        }
                      `}
                    >
                      <CalendarMonthIcon color="disabled" />
                      <Text
                        as="span"
                        css={css`
                          margin-right: 1rem;
                        `}
                      >
                        {endDate.slice(0, 10)}
                      </Text>
                      <PeopleAltIcon color="disabled" />
                      <Text as="span">{curNum + "/" + maxNum}</Text>
                    </DateDiv>
                  )}
                </>
              }
              Bottom={
                <>
                  <div>
                    <IconButton aria-label="add to favorites">
                      <WcIcon />
                    </IconButton>
                    <Text as="span">{sexLimit === "f" ? "여자만" : sexLimit === "m" ? "남자만" : "성별무관"}</Text>
                    <IconButton aria-label="share">
                      <RemoveCircleOutlineIcon />
                    </IconButton>
                    <Text as="span">{highAgeLimit}세 이하</Text>
                  </div>
                </>
              }
            />
          </Link>
        );
      })}
    </>
  );
};

export default HobbyCard;

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
