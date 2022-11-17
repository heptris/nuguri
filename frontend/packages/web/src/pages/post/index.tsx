import Link from "@/components/Link";
import { ROUTES } from "@/constant";
import { useHeader, useCategory } from "@/hooks";
import { postState } from "@/store";
import withAuth from "@/utils/withAuth";
import { Button, Text } from "@common/components";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import { Box, FormControl, InputLabel, MenuItem, Select, SelectChangeEvent } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useRecoilState } from "recoil";

const { POST } = ROUTES;

const PostPage = () => {
  const { hobbyDatas } = useCategory();
  const [, setPostState] = useRecoilState(postState);
  const [categoryId, setCategoryId] = useState(null);

  return (
    <Container>
      <div
        css={css`
          display: flex;
          flex-direction: column;
          justify-content: center;
          align-items: center;
        `}
      >
        <Text
          as="h1"
          css={css`
            font-size: 1.5rem;
            font-weight: 500;
          `}
        >
          취미 카테고리 선택 후
        </Text>
        <Text
          as="h1"
          css={css`
            font-size: 1.5rem;
            font-weight: 500;
          `}
        >
          서비스 종류 선택이 가능합니다.
        </Text>
      </div>

      <Box
        sx={{ minWidth: 120 }}
        css={css`
          width: 60%;
          margin-top: 2rem;
          @media screen and (max-width: 899px) {
            width: 80%;
          }
          @media screen and (max-width: 599px) {
            width: 90%;
          }
        `}
      >
        <FormControl fullWidth>
          <InputLabel>취미카테고리를 선택해주세요.</InputLabel>
          <Select
            value={categoryId}
            label="취미카테고리"
            onChange={(event: SelectChangeEvent) => {
              setCategoryId(event.target.value);
            }}
          >
            {hobbyDatas?.map(option => (
              <MenuItem key={option.categoryId} value={option.categoryId}>
                {option.categoryName}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      </Box>

      <div
        css={css`
          display: flex;
          width: 60%;
          margin-top: 2rem;
          flex-direction: row;
          justify-content: space-around;
          align-items: center;
          @media screen and (max-width: 899px) {
            width: 80%;
          }
          @media screen and (max-width: 599px) {
            width: 90%;
          }
        `}
      >
        {categoryId ? (
          <Link href={POST + "/hobby"}>
            {
              <ButtonStyle
                onClick={() => {
                  setPostState(categoryId);
                }}
              >
                <Text
                  css={css`
                    color: white;
                    @media screen and (max-width: 599px) {
                      font-size: 0.7rem;
                    }
                  `}
                >
                  취미모임
                </Text>
              </ButtonStyle>
            }
          </Link>
        ) : (
          <ButtonStyle disabled>
            <Text
              css={css`
                @media screen and (max-width: 599px) {
                  font-size: 0.7rem;
                }
              `}
            >
              취미모임
            </Text>
          </ButtonStyle>
        )}
        {categoryId ? (
          <Link href={POST + "/deal"}>
            <ButtonStyle
              onClick={() => {
                setPostState(categoryId);
              }}
            >
              <Text
                css={css`
                  color: white;
                  @media screen and (max-width: 599px) {
                    font-size: 0.7rem;
                  }
                `}
              >
                중고거래
              </Text>
            </ButtonStyle>
          </Link>
        ) : (
          <ButtonStyle disabled>
            <Text
              css={css`
                @media screen and (max-width: 599px) {
                  font-size: 0.7rem;
                }
              `}
            >
              중고거래
            </Text>
          </ButtonStyle>
        )}
        {categoryId ? (
          <Link href={POST + "/groupdeal"}>
            <ButtonStyle
              onClick={() => {
                setPostState(categoryId);
              }}
            >
              <Text
                css={css`
                  color: white;
                  @media screen and (max-width: 599px) {
                    font-size: 0.7rem;
                  }
                `}
              >
                공구거래
              </Text>
            </ButtonStyle>
          </Link>
        ) : (
          <ButtonStyle disabled>
            <Text
              css={css`
                @media screen and (max-width: 599px) {
                  font-size: 0.7rem;
                }
              `}
            >
              공구거래
            </Text>
          </ButtonStyle>
        )}
      </div>
    </Container>
  );
};

export default withAuth(PostPage);

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 2rem;
`;

const ButtonStyle = styled(Button)`
  width: 10rem;
  height: 15rem;
  @media screen and (max-width: 599px) {
    /* 모바일 세로 */
    width: 5rem;
    height: 10rem;
  }
`;
