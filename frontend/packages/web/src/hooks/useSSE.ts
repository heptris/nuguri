import { ENDPOINT_SSE } from "@/api";

import { useEffect, useState } from "react";
import { useAlert } from "./useAlert";

import { useUser } from "./useUser";

const useSSE = () => {
  const {
    userInfo: { memberId },
  } = useUser();
  const [meventSource, msetEventSource] = useState(undefined);
  const [data, setData] = useState(null);
  const [newAlarm, setNewAlarm] = useState(null);
  const [listening, setListening] = useState(false);
  const { handleAlertOpen } = useAlert();
  let eventSource: EventSource | undefined = undefined;
  useEffect(() => {
    const connectFunc = event => {
      console.log("연결 후", event);
      setData(event.data);
    };
    const connectNewMessage = event => {
      console.log("새 메세지", JSON.parse(event.data));
      handleAlertOpen("새로운 메세지가 있어요", true, 2000);
    };
    const connectAlarm = event => {
      console.log("새 알람", JSON.parse(event.data));
      setNewAlarm(JSON.parse(event.data));
    };
    if (!listening) {
      memberId > 0 &&
        (() => {
          try {
            eventSource = new EventSource(ENDPOINT_SSE + `/${memberId}`, {
              // headers: {
              //   Authorization: `Bearer ${getCookie(ACCESS_TOKEN)}`,
              // },
              withCredentials: true,
            });
            eventSource.addEventListener("connect", connectFunc);
            eventSource.addEventListener("messageAlarm", connectNewMessage);
            eventSource.addEventListener("alarm", connectAlarm);
            msetEventSource(eventSource);
          } catch (e) {
            console.error(e);
          }
        })();
    }
    return () => {
      eventSource?.removeEventListener("connect", connectFunc);
      eventSource?.removeEventListener("messageAlarm", connectNewMessage);
      eventSource?.removeEventListener("alarm", connectAlarm);
      eventSource?.close();
    };
  }, [memberId]);
  useEffect(() => {
    if (!listening) {
      meventSource &&
        (() => {
          try {
            console.log("들어는 오니", meventSource);
            meventSource.onopen = function (event) {
              console.log(event);
              console.log("연결 확인");
            };
            meventSource.onmessage = function (event) {
              let message = event.data;
              console.log("알람 확인", event);
            };
            meventSource.onerror = function (event) {
              console.log("onerror", event);
              // if (event.target.readyState === mEventSource.CLOSED) {
              //   console.log("SSE closed (" + event.target.readyState + ")");
              // }
              // meventSource.close();
            };
            setListening(true);
          } catch (err) {
            console.error(err);
          }
        })();
    }
  }, [meventSource]);
};

export { useSSE };
