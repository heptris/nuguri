import axios from "axios";
import { NextApiRequest, NextApiResponse } from "next";

import { ENDPOINT_AUTH } from "@/api";
import { REFRESH_TOKEN, ACCESS_TOKEN } from "@/api";

import { setCookie } from "cookies-next";

export default async (req: NextApiRequest, res: NextApiResponse) => {
  const { method, body } = req;
  if (method !== "POST" && method !== "GET") {
    res.status(404).end();
  }

  const onLoginProcess = (data: { accessToken: string; refreshToken: string; accessTokenExpiresIn: number; nickname: string }) => {
    const { accessToken, refreshToken, accessTokenExpiresIn, nickname } = data;
    const clientData = {
      accessTokenExpiresIn,
      nickname,
    };
    console.log(new Date(accessTokenExpiresIn), new Date(Date.now()));

    setCookie(ACCESS_TOKEN, accessToken, {
      req,
      res,
      secure: true,
      sameSite: "strict",
      maxAge: (accessTokenExpiresIn - Date.now()) / 1000,
    });
    setCookie(REFRESH_TOKEN, refreshToken, {
      req,
      res,
      httpOnly: true,
      secure: true,
      sameSite: "strict",
    });

    res.status(200).json(clientData);
  };

  switch (method) {
    // 로그인 케이스
    case "POST":
      await axios
        .post(`${ENDPOINT_AUTH}/login`, body)
        .then(response => {
          const {
            data: { data: resData },
          } = response;
          onLoginProcess(resData);
        })
        .catch(e => {
          const { response } = e;
          // const { status, data } = response;
          console.error(e);
          res.status(response?.status ?? 404).json(response?.data ?? "잘못된 요청입니다.");
        });
      break;
    // 토큰 재발행 케이스
    case "GET":
      const arr = req.headers.cookie?.split(";").map<[string, string]>(el => {
        const tmp = el.trim().split("=");
        return [tmp[0], tmp[1]];
      });

      if (arr === undefined) {
        res.status(404).json("로그인 정보가 없습니다. 다시 로그인하세요");
        break;
      }

      const cookieMap = new Map<string, string>(arr);
      const accessToken = cookieMap.get(ACCESS_TOKEN);
      const refreshToken = cookieMap.get(REFRESH_TOKEN);
      console.log(cookieMap);
      await axios
        .post(`${ENDPOINT_AUTH}/reissue`, {
          accessToken,
          refreshToken,
        })
        .then(response => {
          const {
            data: { data: resData },
          } = response;
          console.log("재발행이후", resData);
          onLoginProcess(resData);
        })
        .catch(e => {
          const { response } = e;
          console.error(e);
          res.status(response?.status ?? 404).json("다시 로그인하세요.");
        });
      break;
  }
};
