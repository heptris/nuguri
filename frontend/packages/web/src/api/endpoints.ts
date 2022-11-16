/* 백엔드 엔드포인트 스키마 */

const PROTOCOL = "https://";
const HOST = "k7a702.p.ssafy.io";
const DOMAIN = PROTOCOL + HOST;

export const ENDPOINT_API = DOMAIN + "/app";
export const ENDPOINT_AUTH = DOMAIN + "/auth";
export const ENDPOINT_BFF = "/api";
export const ENDPOINT_WS = ENDPOINT_API + "/ws-connection";
export const ENDPOINT_SSE = ENDPOINT_API + "/sse/sub";
