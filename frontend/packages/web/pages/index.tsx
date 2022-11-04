import { css } from "@emotion/react";
import { Animated, Basic, bounce, Combined, Pink, BasicExtended, ComponentSelectorsExtended } from "../shared/styles";
import styled from "@emotion/styled";
import { AppBar } from "../../components/src/components/AppBar";
import { BottomNavbar } from "../../components/src/components/BottomNavbar";
import { Menu } from "../../components/src/components/Menu";
import { HobbyCard } from "@common/components/src/components/Card/HobbyCard";

import Box from "@mui/material/Box";
const Home = () => {
  return (
    <Box>
      <AppBar />
      <CardWrapper
        css={css`
          display: flex;
          height: 100rem;
          position: relative;
          padding: 6rem 2rem;
        `}
      >
        <Menu open={false} />
        <HobbyCard />
      </CardWrapper>
      <BottomNavbar />
    </Box>
  );
};
export default Home;

const CardWrapper = styled.div`
  display: flex;
  position: relative;
  padding: 6rem 2rem;
  flex-direction: column;
`;
