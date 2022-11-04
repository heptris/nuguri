import { GlobalStyle, ThemeProvider, theme } from "@common/components";
import createCache from "@emotion/cache";
import { CacheProvider } from "@emotion/react";
import { RecoilRoot } from "recoil";
import { AppProps } from "next/app";

import Layout from "@/components/Layout";

const cache = createCache({ key: "next" });

const App = ({ Component, pageProps }: AppProps) => (
  <CacheProvider value={cache}>
    <ThemeProvider theme={theme}>
      <RecoilRoot>
        <GlobalStyle />
        <Layout>
          <Component {...pageProps} />
        </Layout>
      </RecoilRoot>
    </ThemeProvider>
  </CacheProvider>
);

export default App;
