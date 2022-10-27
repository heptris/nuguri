import { css } from "@emotion/react";
import { Animated, Basic, bounce, Combined, Pink, BasicExtended, ComponentSelectorsExtended } from "../shared/styles";
import { AppBar } from "../../components/src/components/AppBar";
import { BottomNavbar } from "../../components/src/components/BottomNavbar";
import { Menu } from "../../components/src/components/Menu";

import Box from "@mui/material/Box";
const Home = () => {
  return (
    <Box>
      <AppBar />
      <Box
        css={css`
          display: flex;
          height: 100rem;
          position: relative;
          padding: 6rem 2rem;
        `}
      >
        <Menu open={false} />
      </Box>
      <BottomNavbar />
    </Box>
  );
};
export default Home;
