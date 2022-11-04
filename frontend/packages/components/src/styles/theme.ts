/* 테마 모듈 */

import { createTheme, ThemeProvider } from "@mui/material";

const defaultTheme = {
  name: "light",
  palette: {
    primary: {
      main: "#5a3d1c",
    },
  },
  color: {
    background: {
      main: "var(--main)",
      submain: "var(--white)",
      emphasis: "var(--vividBlue)",
      page: "var(--ink900)",
      card: "var(--ink800)",
      item: "var(--ink700)",
    },
    hover: {
      main: "var(--purple500)",
      emphasis: "var(--blue900)",
    },
    text: {
      main: "var(--black)",
      sub: "var(--ink300)",
      hover: "var(--ink200)",
    },
    status: {
      success: "var(--vividGreen)",
      fail: "var(--vividPink)",
      disabled: "var(--disabled)",
    },
    border: {
      main: "var(--ink600)",
    },
  },
};

const racconsThemes = { defaultTheme };

const theme = createTheme(defaultTheme);

export { theme, ThemeProvider, racconsThemes };
