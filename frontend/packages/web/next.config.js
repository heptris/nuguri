const withTM = require("next-transpile-modules")(["@common/components"]);
/** @type {import('next').NextConfig} */

const nextConfig = {
  reactStrictMode: true,
  compiler: {
    emotion: true,
  },
};

module.exports = withTM(nextConfig);
