import Fileupload from "@/components/Fileupload";
import { useAlert, useHeader } from "@/hooks";
import { Button, LabelInput, Text } from "@common/components";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import { Box, FormControl, InputLabel, MenuItem, Select, SelectChangeEvent, Slider, TextField } from "@mui/material";
import React, { useState } from "react";
import { apiInstance, ENDPOINT_API } from "@/api";
import { useRouter } from "next/router";
import { ROUTES } from "@/constant";

const PostButton = ({ disabled, onClickPostButton }) => {
  return (
    <Button disabled={disabled} onClick={onClickPostButton}>
      등록
    </Button>
  );
};
const { HOME } = ROUTES;

const HobbyPage = () => {
  const { replace } = useRouter();
  const { handleAlertOpen } = useAlert();
  const [disabled, setDisabled] = useState(true);
  const onClickPostButton = () => {
    const formData = new FormData();
    file && formData.append("file", file);

    const config = {
      headers: {
        "content-type": "multipart/form-data;charset=UTF-8;",
      },
    };

    const data = {
      title,
      content,
      maxNum,
      endDate,
      meetingPlace,
      ageLimit,
      sexLimit,
      fee,
    };

    formData.append("hobbyCreateRequestDto", new Blob([JSON.stringify(data)], { type: "application/json" }));

    apiInstance
      .post(`${ENDPOINT_API}/hobby/regist`, formData, config)
      .then(res => {
        console.log(res);
        handleAlertOpen("취미방 생성이 완료되었습니다.", true, 1000);
        replace(HOME);
      })
      .catch(err => {
        console.log(err);
        handleAlertOpen("취미방 생성이 실패했습니다.", false, 5000);
      });
  };
  useHeader({ mode: "POST", HeaderRight: <PostButton {...{ disabled, onClickPostButton }} /> });

  const [file, setFile] = useState(null);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [maxNum, setMaxNum] = useState(null);
  const [endDate, setEndDate] = useState("");
  const [meetingPlace, setMeetingPlace] = useState("");
  // const [ageLimit, setAgeLimit] = useState(null);
  const [sexLimit, setSexLimit] = useState("");
  const [fee, setFee] = useState(null);
  const [ageLimit, setAgeLimit] = React.useState<number[]>([20, 37]);

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
        value={content}
        placeholder={"상세 내용을 작성해주세요."}
        css={css`
          width: 80%;
          margin-top: 2rem;
        `}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
          setContent(event.target.value);
        }}
      />
      <LabelInput
        label={"모집인원"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        type={"number"}
        name={"maxNum"}
        value={maxNum}
        placeholder={"숫자만 입력 가능합니다."}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
          setMaxNum(event.target.value);
        }}
      />

      <TextField
        label={"마감기한"}
        InputLabelProps={{
          shrink: true,
        }}
        type="date"
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
        label={"모임장소"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        name={"meetingPlace"}
        value={meetingPlace}
        placeholder={"모임장소를 입력해주세요"}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
          setMeetingPlace(event.target.value);
        }}
      />

      <Box
        sx={{ width: 330 }}
        css={css`
          align-self: start;
          margin-left: 10%;
        `}
      >
        <Text
          css={css`
            margin-top: 2rem;
            color: #4e5968;
            font-size: 1.1rem;
            font-weight: 300;
          `}
          as="h1"
        >
          나이제한
        </Text>
        <Slider
          getAriaLabel={() => "Temperature range"}
          value={ageLimit}
          onChange={(event: Event, newValue: number | number[]) => {
            setAgeLimit(newValue as number[]);
          }}
          valueLabelDisplay="auto"
        />
      </Box>
      <Box
        sx={{ minWidth: 120 }}
        css={css`
          width: 80%;
          margin-top: 2rem;
        `}
      >
        <FormControl fullWidth>
          <InputLabel>성별제한</InputLabel>
          <Select
            value={sexLimit}
            label="성별제한"
            onChange={(event: SelectChangeEvent) => {
              setSexLimit(event.target.value as string);
            }}
          >
            <MenuItem value={"M"}>남자</MenuItem>
            <MenuItem value={"W"}>여자</MenuItem>
            <MenuItem value={"W"}>누구나</MenuItem>
          </Select>
        </FormControl>
      </Box>
      <LabelInput
        label={"참가회비"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        type={"number"}
        name={"fee"}
        value={fee}
        placeholder={"숫자만 입력 가능합니다. 0원 입력시 무료"}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
          setFee(event.target.value);
        }}
      />
    </ContainerWrapper>
  );
};

export default HobbyPage;

const ContainerWrapper = styled.div`
  display: flex;
  width: 100%;
  height: 85vh;
  flex-direction: column;
  align-items: center;
`;
