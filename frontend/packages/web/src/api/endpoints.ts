/* 백엔드 엔드포인트 스키마 */

const PROTOCOL = "http://";
const HOST = "k7a702.p.ssafy.io:8080";
const DOMAIN = PROTOCOL + HOST;

export const ENDPOINT_API = DOMAIN + "/app";
export const ENDPOINT_AUTH = DOMAIN + "/auth";
export const ENDPOINT_BFF = "/api";
export const ENDPOINT_WS = DOMAIN + "/ws-connection";
export const ENDPOINT_SSE = DOMAIN + "/sse/sub";
