import { atom, useRecoilState } from "recoil";

type AlertType = {
  message?: string;
  isSuccess?: boolean;
  isOpened: boolean;
};
const alertState = atom<AlertType>({
  key: "alertState",
  default: {
    isOpened: false,
  },
});

export const useAlert = () => {
  const [alertInfo, setAlertInfo] = useRecoilState(alertState);

  const handleAlertOpen = (message = "404 에러 잘못된 요청입니다", isSuccess = false, time = 2000) => {
    setAlertInfo({ message, isSuccess, isOpened: true });
    setTimeout(() => setAlertInfo({ isOpened: false }), time);
  };

  return { alertInfo, handleAlertOpen };
};
