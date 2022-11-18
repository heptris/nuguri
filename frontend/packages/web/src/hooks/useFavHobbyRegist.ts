import { apiInstance, ENDPOINT_API } from "@/api";
import { useMutation } from "@tanstack/react-query";
import axios from "axios";
import { useAlert } from "./useAlert";

export const useFavHobbyRegist = (hobbyId: number) => {
  const { handleAlertOpen } = useAlert();
  const postFavoriteHobbyRegist = async () => {
    await apiInstance.post(ENDPOINT_API + "/hobby/favorite/regist" + `/${hobbyId}`, hobbyId);
  };

  const { mutate: mutateFavoriteHobby } = useMutation(postFavoriteHobbyRegist, {
    onSettled: () => {
      handleAlertOpen("즐겨찾기 해제/등록에 성공했습니다.", true, 1000);
    },
  });

  const handleFavoriteHobby = async () => {
    mutateFavoriteHobby();
  };
  return { handleFavoriteHobby };
};
