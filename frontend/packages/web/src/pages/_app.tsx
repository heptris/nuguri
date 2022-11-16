import { GlobalStyle, ThemeProvider, theme } from "@common/components";
import createCache from "@emotion/cache";
import { CacheProvider } from "@emotion/react";
import { RecoilRoot } from "recoil";
import { AppProps } from "next/app";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

import Layout from "@/components/Layout";

const cache = createCache({ key: "next" });
const queryClient = new QueryClient();

const App = ({ Component, pageProps }: AppProps) => (
  <CacheProvider value={cache}>
    <ThemeProvider theme={theme}>
      <RecoilRoot>
        <QueryClientProvider client={queryClient}>
          <GlobalStyle />
          <Layout>
            <Component {...pageProps} />
          </Layout>
          <ReactQueryDevtools initialIsOpen={false} position={"top-right"} />
        </QueryClientProvider>
      </RecoilRoot>
    </ThemeProvider>
  </CacheProvider>
);

export default App;
