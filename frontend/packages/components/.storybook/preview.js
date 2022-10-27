import { GlobalStyle } from "../src";
import { ThemeProvider, useTheme } from "@emotion/react";

export const decorators = [
  Story => {
    const theme = useTheme();
    return (
      <ThemeProvider theme={theme}>
        <GlobalStyle />
        <Story />
      </ThemeProvider>
    );
  },
];
export const parameters = {
  actions: { argTypesRegex: "^on[A-Z].*" },
  controls: {
    matchers: {
      color: /(background|color)$/i,
      date: /Date$/,
    },
  },
};
