import { useHeader, useAuth, useUser } from "@/hooks";
import withAuth from "@/utils/withAuth";
import { Button, Text } from "@common/components";
import styled from "@emotion/styled";
import { Avatar } from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import { css } from "@emotion/react";

const ProfilePage = () => {
  useHeader({ mode: "ITEM", headingText: undefined });
  const { userInfo } = useUser();
  const { temperature, nickname, profileImage } = userInfo;
  console.log(profileImage);
  const { handleLogout } = useAuth();

  return (
    <div>
      <TopWrapper>
        {/* <Button onClick={() => handleLogout()}>로그아웃</Button> */}
        <ProfileWrapper>
          <div
            css={css`
              position: relative;
            `}
          >
            <Avatar alt="Remy Sharp" src={profileImage} sx={{ width: 100, height: 100 }} />
            <div
              css={css`
                position: absolute;
                top: 4.3rem;
                left: 4.3rem;
                width: 2rem;
                height: 2rem;
                border: 1px solid #bcbcbc;
                border-radius: 1.5rem;
                background-color: white;
                &:hover {
                  cursor: pointer;
                }
              `}
            >
              <EditIcon
                sx={{ fontSize: "20px" }}
                color="action"
                css={css`
                  transform: translate(0.35rem, 0.35rem);

                  &:hover {
                    cursor: pointer;
                  }
                `}
              />
            </div>
          </div>
          <Text
            css={css`
              font-size: 1.2rem;
              font-weight: 500;
              margin-left: 1rem;
            `}
          >
            {nickname}
          </Text>
        </ProfileWrapper>

        <div
          css={css`
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: 1rem;
            width: 4rem;
            height: 2rem;
            margin-left: 5rem;
            background-color: #5a3d1c;
          `}
        >
          <Text
            css={css`
              color: white;
            `}
          >
            {temperature}
          </Text>
        </div>
      </TopWrapper>

      <BottomWrapper></BottomWrapper>
    </div>
  );
};

export default withAuth(ProfilePage);

const TopWrapper = styled.div`
  display: flex;
  width: 100%;
  height: 30vh;
  top: 0rem;
  flex-direction: row;
  justify-content: center;
  align-items: center;
`;
const ProfileWrapper = styled.div`
  display: flex;
  flex-direction: row;
  align-items: flex-end;
`;

const BottomWrapper = styled.div`
  display: flex;
  width: 100%;
  height: 55vh;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  bottom: 0rem;
  background-color: yellow;
`;
