const withTM = require("next-transpile-modules")(["@common/components"]);
/** @type {import('next').NextConfig} */

const nextConfig = {
  reactStrictMode: true,
  compiler: {
    emotion: true,
  },
  images: {
    domains: ["nuguri-bucket.s3.ap-northeast-2.amazonaws.com", "uniqon-bucket.s3.ap-northeast-2.amazonaws.com"],
  },
};

module.exports = withTM(nextConfig);
