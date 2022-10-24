import { GlobalStyle } from "@common/components";
import createCache from "@emotion/cache";
import { CacheProvider } from "@emotion/react";

const cache = createCache({ key: "next" });

const App = ({ Component, pageProps }) => (
  <CacheProvider value={cache}>
    <GlobalStyle />
    <Component {...pageProps} />
  </CacheProvider>
);

export default App;