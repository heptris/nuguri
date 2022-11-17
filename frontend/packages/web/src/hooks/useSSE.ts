import { ENDPOINT_SSE } from "@/api";
import { bottomState } from "@/store";
import { useEffect, useState } from "react";
import { useRecoilState } from "recoil";
import { useUser } from "./useUser";

const useSSE = () => {
  const {
    userInfo: { memberId },
  } = useUser();
  const [meventSource, msetEventSource] = useState(undefined);
  const [data, setData] = useState(null);
  const [bottom, setBottom] = useRecoilState(bottomState);
  const [newAlarm, setNewAlarm] = useState(null);
  const [listening, setListening] = useState(false);
  let eventSource: EventSource | undefined = undefined;
  useEffect(() => {
    const connectFunc = event => {
      console.log("연결 후", event);
      setData(event.data);
    };
    const connectNewMessage = event => {
      console.log("새 메세지", JSON.parse(event.data));
      setBottom({ ...bottom, newMessage: true });
    };
    const connectAlarm = event => {
      console.log("새 알람", JSON.parse(event.data));
      setNewAlarm(JSON.parse(event.data));
    };
    if (!listening) {
      memberId &&
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
              console.log(event.target);
              // if (event.target.readyState === mEventSource.CLOSED) {
              //   console.log("SSE closed (" + event.target.readyState + ")");
              // }
              meventSource.close();
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
