import { apiInstance, ENDPOINT_API } from "@/api";
import Fileupload from "@/components/Fileupload";
import { ROUTES } from "@/constant";
import { useAlert, useHeader, useUser } from "@/hooks";
import { Button, LabelInput, Text } from "@common/components";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import { useRouter } from "next/router";
import { useState } from "react";

const { PROFILE } = ROUTES;

const EditPage = () => {
  const { replace } = useRouter();
  const { userInfo } = useUser();
  const { nickname } = userInfo;
  useHeader({ mode: "LOGIN", headingText: "프로필 수정" });
  const { handleAlertOpen } = useAlert();

  const [file, setFile] = useState(null);
  const [nickName, setNickname] = useState(nickname);

  const onClickEditButton = async () => {
    const formData = new FormData();
    formData.append("file", file);

    const config = {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    };

    const data = {
      nickname: nickName,
    };

    formData.append("requestDto", new Blob([JSON.stringify(data)], { type: "application/json" }));

    await apiInstance
      .post(`${ENDPOINT_API}/member/modify`, formData, config)
      .then(res => {
        console.log(res);
        handleAlertOpen("프로필 수정이 완료되었습니다.", true, 1000);
        replace(PROFILE);
        console.log(data);
      })
      .catch(err => {
        console.log(err);
        handleAlertOpen("프로필 수정이 실패했습니다.", false, 5000);
        console.log(data);
      });
  };

  return (
    <ContainerWrapper>
      <Fileupload onFileSelectSuccess={(file: any) => setFile(file)} onFileSelectError={({ error }) => handleAlertOpen("이미지 등록에 실패했습니다.", false, 5000)} type={"profile"} />
      <LabelInput
        label={"닉네임"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        name={"nickName"}
        value={nickName}
        placeholder={"2~15 글자로 작성해주세요."}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
          setNickname(event.target.value);
        }}
      />
      <Button
        onClick={onClickEditButton}
        css={css`
          width: 80%;
          margin-top: 2rem;
        `}
      >
        수정
      </Button>
    </ContainerWrapper>
  );
};

export default EditPage;

const ContainerWrapper = styled.div`
  display: flex;
  width: 100%;
  height: 85vh;
  flex-direction: column;
  align-items: center;
`;
