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
  async rewrites() {
    return [
      {
        source: "/app/:path*",
        destination: "http://k7a702.p.ssafy.io:8080/app/:path*",
      },
      {
        source: "/auth/:path*",
        destination: "http://k7a702.p.ssafy.io:8080/auth/:path*",
      },
    ];
  },
};

module.exports = withTM(nextConfig);
