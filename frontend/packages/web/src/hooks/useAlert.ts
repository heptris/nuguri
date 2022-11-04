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
  const [, setAlert] = useRecoilState(alertState);

  const handleAlertOpen = (time = 2000, message = "404 에러 잘못된 요청입니다", isSuccess = false) => {
    setAlert({ message, isSuccess, isOpened: true });
    setTimeout(() => setAlert({ isOpened: false }), time);
  };

  return { handleAlertOpen };
};
