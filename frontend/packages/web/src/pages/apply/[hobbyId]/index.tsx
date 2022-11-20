import withAuth from "@/utils/withAuth";
import { Button, LabelInput, Text } from "@common/components";
import { racconsThemes } from "@common/components/src/styles/theme";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import LooksOneIcon from "@mui/icons-material/LooksOne";
import LooksTwoIcon from "@mui/icons-material/LooksTwo";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import { useAlert, useAuth, useBottom, useHeader } from "@/hooks";
import { ChangeEvent, useEffect, useState } from "react";
import { useRecoilState } from "recoil";
import { applyHobbyIdState } from "@/store";
import { apiInstance, ENDPOINT_API } from "@/api";
import { useRouter } from "next/router";
import { ROUTES } from "@/constant";

const { HOME } = ROUTES;
const ApplyPage = () => {
  useHeader({ mode: "ITEM" });
  const { isLogined } = useAuth();
  const { handleAlertOpen } = useAlert();
  const { setBottom } = useBottom(<></>);
  const router = useRouter();
  const { hobbyId } = router.query;
  const data = {
    hobbyId,
  };
  const [userConfirm, setUserConfirm] = useState<boolean>(false);
  const { replace, push } = useRouter();
  const onApplyHobby = () => {
    apiInstance
      .post(ENDPOINT_API + "/hobby/history/regist", data)
      .then(res => {
        console.log(res);
        handleAlertOpen("취미모임 참여에 성공했습니다. 방장의 승인을 기다려주세요.", true, 2000);
        push(HOME);
      })
      .catch(err => {
        console.log(err);
        handleAlertOpen("취미모임 참여에 실패해습니다.", false, 5000);
      });
  };

  useEffect(() => {
    isLogined &&
      setBottom({
        children: (
          <div
            css={css`
              display: flex;
              width: 100%;
              justify-content: center;
              align-items: center;
              padding: 0.5rem;
            `}
          >
            <Button
              css={css`
                width: 80%;
                border-radius: 2rem;
              `}
              onClick={() => onApplyHobby()}
              disabled={!userConfirm}
            >
              <Text
                css={css`
                  color: white;
                `}
              >
                참여 신청하기
              </Text>
            </Button>
          </div>
        ),
      });
  }, [isLogined, userConfirm]);
  return (
    <ContainerWrapper>
      <Text
        as="h1"
        css={css`
          font-size: 1.5rem;
          font-weight: 600;
        `}
      >
        모두가 즐거운 취미모임이
      </Text>
      <Text
        as="h1"
        css={css`
          font-size: 1.5rem;
          font-weight: 600;
          margin-bottom: 3rem;
        `}
      >
        될 수 있도록 함께 지켜주세요.
      </Text>
      <IconTextWrapper>
        <LooksOneIcon
          css={css`
            font-size: 2rem;
            margin-right: 1rem;
            color: ${racconsThemes.defaultTheme.color.background.main};
          `}
        />
        <div>
          <Text as="h1">모임 시작 전 부득이하게 참여가 어려워진 경우,</Text>
          <Text as="h1">반드시 호스트에게 미리 알려주세요.</Text>
        </div>
      </IconTextWrapper>
      <IconTextWrapper>
        <LooksTwoIcon
          css={css`
            font-size: 2rem;
            margin-right: 1rem;
            color: ${racconsThemes.defaultTheme.color.background.main};
          `}
        />
        <div>
          <Text as="h1">나와 다른 의견에도 귀 기울이며, 함께하는</Text>
          <Text as="h1">멤버들을 존중하는 태도를 지켜주세요. </Text>
        </div>
      </IconTextWrapper>
      <BoxWrapper>
        <Text
          as="h1"
          css={css`
            color: ${racconsThemes.defaultTheme.color.background.main};
          `}
        >
          무단으로 불참하거나, 함께하는 멤버들을
        </Text>
        <Text
          as="h1"
          css={css`
            color: ${racconsThemes.defaultTheme.color.background.main};
          `}
        >
          존중하지 않고 피해를 주는 경우
        </Text>
        <Text
          as="h1"
          css={css`
            color: ${racconsThemes.defaultTheme.color.background.main};
          `}
        >
          너구리 이용에 제재를 받게 됩니다.{" "}
        </Text>
        <IconTextWrapper
          css={css`
            align-items: center;
            margin-top: 1rem;
          `}
        >
          <div>
            <input
              id="infoAgree"
              type="checkbox"
              name="isChecked"
              onChange={event => setUserConfirm(event.target.checked)}
              css={css`
                display: none;
                &:checked ~ ${LabelSytle} {
                  &::after {
                    content: "✔";
                    font-size: 25px;
                    width: 30px;
                    height: 30px;
                    text-align: center;
                    position: absolute;
                    left: 0;
                    top: 0;
                  }
                }
              `}
            />
            <LabelSytle htmlFor="infoAgree"></LabelSytle>
          </div>
          <Text
            as="h1"
            css={css`
              font-size: 1.2rem;
              font-weight: 600;
              color: ${racconsThemes.defaultTheme.color.background.main};
            `}
          >
            취미모임 이용 규칙을 지키겠습니다!
          </Text>
        </IconTextWrapper>
      </BoxWrapper>
    </ContainerWrapper>
  );
};

export default withAuth(ApplyPage);

const ContainerWrapper = styled.div`
  display: flex;
  width: 100%;
  height: 85vh;
  flex-direction: column;
  padding: 2rem;
`;

const LabelSytle = styled.label`
  display: inline-block;
  width: 30px;
  height: 30px;
  border: 3px solid ${racconsThemes.defaultTheme.color.background.main};
  position: relative;
  margin-right: 0.5rem;
`;
const IconTextWrapper = styled.div`
  display: flex;
  margin-bottom: 1rem;
`;
const BoxWrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding: 1rem;
  border: 1px solid ${racconsThemes.defaultTheme.color.background.main};
  border-radius: 0.5rem;
  margin-top: 2rem;
`;
