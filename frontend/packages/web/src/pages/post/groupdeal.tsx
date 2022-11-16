import Fileupload from "@/components/Fileupload";
import { useAlert, useHeader, useUser } from "@/hooks";
import { Button, LabelInput, Text } from "@common/components";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import { Box, FormControl, InputLabel, MenuItem, Select, SelectChangeEvent, Slider, TextField } from "@mui/material";
import React, { useEffect, useState } from "react";
import { apiInstance, ENDPOINT_API } from "@/api";
import { useRouter } from "next/router";
import { ROUTES } from "@/constant";
import { postState } from "@/store";
import { useRecoilState } from "recoil";

const { HOME } = ROUTES;

const GroupDealePage = () => {
  const [categoryId, setCategoryId] = useRecoilState(postState);
  const [file, setFile] = useState(null);
  const [title, setTitle] = useState("");
  const [productUrl, setProductUrl] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState(null);
  const [endDate, setEndDate] = useState("");
  const [goalNumber, setGoalNumber] = useState(null);

  const { replace } = useRouter();
  const { handleAlertOpen } = useAlert();

  //취미방 생성 함수
  const onClickPostButton = async () => {
    if (file && title && description && price && endDate && goalNumber && productUrl) {
      const formData = new FormData();
      formData.append("file", file);

      const config = {
        headers: {
          "Content-Type": "multipart/form-data;charset=UTF-8;",
        },
      };

      const data = {
        categoryId,
        title,
        description,
        price,
        endDate: endDate + ":59.999Z",
        goalNumber,
        productUrl,
      };

      formData.append("groupPurchaseRegistRequestDto", new Blob([JSON.stringify(data)], { type: "application/json" }));

      await apiInstance
        .post(`${ENDPOINT_API}/group/regist`, formData, config)
        .then(res => {
          console.log(res);
          handleAlertOpen("취미방 생성이 완료되었습니다.", true, 1000);
          replace(HOME);
          console.log(formData);
        })
        .catch(err => {
          console.log(err);
          handleAlertOpen("취미방 생성이 실패했습니다.", false, 5000);
          console.log(formData);
        });
    } else {
      handleAlertOpen("모든칸을 채워주세요.", false, 5000);
    }
  };

  return (
    <ContainerWrapper>
      <Fileupload onFileSelectSuccess={(file: any) => setFile(file)} onFileSelectError={({ error }) => handleAlertOpen("이미지 등록에 실패했습니다.", false, 5000)} />
      <LabelInput
        label={"제목"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        name={"title"}
        value={title}
        placeholder={"2~15 글자로 작성해주세요."}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
          setTitle(event.target.value);
        }}
      />
      <TextField
        id="outlined-multiline-static"
        label="상세내용"
        multiline
        rows={4}
        value={description}
        placeholder={"상세 내용을 작성해주세요."}
        css={css`
          width: 80%;
          margin-top: 2rem;
        `}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
          setDescription(event.target.value);
        }}
      />
      <LabelInput
        label={"가격"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        type={"number"}
        name={"price"}
        value={price}
        placeholder={"숫자만 입력 가능합니다."}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
          setPrice(event.target.value);
        }}
      />

      <TextField
        label={"마감기한"}
        InputLabelProps={{
          shrink: true,
        }}
        type="datetime-local"
        name={"endDate"}
        value={endDate}
        placeholder={"숫자만 입력 가능합니다."}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
          setEndDate(event.target.value);
        }}
        css={css`
          width: 80%;
          margin-top: 2rem;
        `}
      />

      <LabelInput
        label={"목표금액"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        type={"number"}
        name={"goalNumber"}
        value={goalNumber}
        placeholder={"숫자만 입력 가능합니다."}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
          setGoalNumber(event.target.value);
        }}
      />
      <LabelInput
        label={"구매링크"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        name={"productUrl"}
        value={productUrl}
        placeholder={"구매관련 링크를 작성해주세요."}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
          setProductUrl(event.target.value);
        }}
      />
      <Button
        onClick={onClickPostButton}
        css={css`
          width: 80%;
          margin-top: 2rem;
        `}
      >
        등록
      </Button>
    </ContainerWrapper>
  );
};

export default GroupDealePage;

const ContainerWrapper = styled.div`
  display: flex;
  width: 100%;
  flex-direction: column;
  align-items: center;
`;
