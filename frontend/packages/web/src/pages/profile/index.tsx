import { useHeader, useAuth, useUser } from "@/hooks";
import withAuth from "@/utils/withAuth";
import { Card, Text } from "@common/components";
import styled from "@emotion/styled";
import { Avatar } from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import { css } from "@emotion/react";
import { useState } from "react";
import * as React from "react";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import StandardImageList from "@/components/StandardImageList";
import SelectTab from "@/components/SelectTab";
import Link from "@/components/Link";
import { ROUTES } from "@/constant";

const { EDITPROFILE } = ROUTES;

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

function TabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div role="tabpanel" hidden={value !== index} id={`simple-tabpanel-${index}`} aria-labelledby={`simple-tab-${index}`} {...other}>
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography
            css={css`
              display: flex;
              flex-direction: column;
            `}
          >
            {children}
          </Typography>
        </Box>
      )}
    </div>
  );
}

function a11yProps(index: number) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}

const ProfilePage = () => {
  useHeader({ mode: "ITEM", headingText: undefined });
  const { userInfo } = useUser();
  const { temperature, nickname, profileImage } = userInfo;
  const [value, setValue] = useState(0);

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
  };

  const hobbyMenus = ["대기", "참여", "운영", "찜"];
  const [selectedHobbyMenu, setSelectedHobbyMenu] = useState(hobbyMenus[0]);
  const onSelectHobbyHandler = (selectedMenu: string) => {
    setSelectedHobbyMenu(selectedMenu);
  };

  const dealMenus = ["판매중", "판매완료", "구매완료", "찜"];
  const [selectedDealMenu, setSelectedDealMenu] = useState(dealMenus[0]);
  const onSelecDealHandler = (selectedMenu: string) => {
    setSelectedDealMenu(selectedMenu);
  };

  const groupDealMenus = ["구매완료", "운영", "찜"];
  const [selectedGroupDealMenu, setSelectedGroupDealMenu] = useState(groupDealMenus[0]);
  const onSelectGroupDealHandler = (selectedMenu: string) => {
    setSelectedGroupDealMenu(selectedMenu);
  };

  return (
    <div>
      <TopWrapper>
        <ProfileWrapper>
          <div
            css={css`
              position: relative;
            `}
          >
            <Avatar alt="Remy Sharp" src={profileImage} sx={{ width: 100, height: 100 }} />
            <Link href={EDITPROFILE}>
              <EditBtn>
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
              </EditBtn>
            </Link>
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

        <TempBtn>
          <Text
            css={css`
              color: white;
            `}
          >
            {temperature}
          </Text>
        </TempBtn>
      </TopWrapper>

      <BottomWrapper>
        <Box sx={{ width: "100%" }}>
          <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
            <Tabs value={value} onChange={handleChange} centered>
              <Tab label="피드" {...a11yProps(0)} />
              <Tab label="취미거리" {...a11yProps(1)} />
              <Tab label="중고거래" {...a11yProps(2)} />
              <Tab label="공구거래" {...a11yProps(3)} />
            </Tabs>
          </Box>
          <TabPanel value={value} index={0}>
            <div
              css={css`
                display: flex;
                justify-content: center;
              `}
            >
              <StandardImageList />
            </div>
          </TabPanel>
          <TabPanel value={value} index={1}>
            <SelectTab menus={hobbyMenus} onSelectHandler={onSelectHobbyHandler} />
            <ListWrapper>
              {selectedHobbyMenu === hobbyMenus[0] && <Card />}
              {selectedHobbyMenu === hobbyMenus[1] && <Card />}
              {selectedHobbyMenu === hobbyMenus[2] && <Card />}
              {selectedHobbyMenu === hobbyMenus[3] && <Card />}
            </ListWrapper>
          </TabPanel>
          <TabPanel value={value} index={2}>
            <SelectTab
              menus={dealMenus}
              onSelectHandler={onSelecDealHandler}
              css={css`
                margin-top: 1rem;
              `}
            />
            <ListWrapper>
              {selectedDealMenu === dealMenus[0] && <Card />}
              {selectedDealMenu === dealMenus[1] && <Card />}
              {selectedDealMenu === dealMenus[2] && <Card />}
              {selectedDealMenu === dealMenus[3] && <Card />}
            </ListWrapper>
          </TabPanel>
          <TabPanel value={value} index={3}>
            <SelectTab
              menus={groupDealMenus}
              onSelectHandler={onSelectGroupDealHandler}
              css={css`
                margin-top: 1rem;
              `}
            />
            <ListWrapper>
              {selectedGroupDealMenu === groupDealMenus[0] && <Card />}
              {selectedGroupDealMenu === groupDealMenus[1] && <Card />}
              {selectedGroupDealMenu === groupDealMenus[2] && <Card />}
            </ListWrapper>
          </TabPanel>
        </Box>
      </BottomWrapper>
    </div>
  );
};

export default withAuth(ProfilePage);

const ListWrapper = styled.div`
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  margin-top: 0.5rem;
  box-sizing: border-box;
  margin-top: 2rem;
`;

const TopWrapper = styled.div`
  display: flex;
  width: 100%;
  height: 30vh;
  top: 0rem;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
`;
const ProfileWrapper = styled.div`
  display: flex;
  flex-direction: row;
  align-items: flex-end;
`;

const EditBtn = styled.div`
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
`;

const TempBtn = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 1rem;
  width: 4rem;
  height: 2rem;
  background-color: #5a3d1c;
`;

const BottomWrapper = styled.div`
  display: flex;
  width: 100%;
  height: 70vh;
  flex-direction: column;
  align-items: center;
  bottom: 0rem;
`;

const GridWrapper = styled.div`
  width: 100%;
  display: grid;
  gap: 3vw;
  grid-template-columns: repeat(2, 1fr);

  @media screen and (max-width: 1799px) {
    grid-template-columns: repeat(2, 1fr);
    gap: 1vw;
  }
  @media screen and (max-width: 599px) {
    grid-template-columns: repeat(4, 1fr);
  }
`;
