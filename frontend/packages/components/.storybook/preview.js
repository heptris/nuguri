import { GlobalStyle } from "../src/components";
import { theme, ThemeProvider } from "../src/styles/theme";
import { racconsThemes } from "../src/styles/theme";

export const decorators = [
  Story => {
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
