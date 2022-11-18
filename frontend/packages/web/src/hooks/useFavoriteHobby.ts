import { apiInstance, ENDPOINT_API } from "@/api";
import { useEffect, useState } from "react";

export const useFavoriteHobby = (hobbyId: number) => {
  const getHobbyFavorite = async () => {
    const { data } = await apiInstance.get(ENDPOINT_API + "/hobby/favorite/favoritecheck" + `/${hobbyId}`);
    console.log(data);
    return data.data;
  };
  const [isFavoriteHobby, setIsFavoriteHobby] = useState<boolean>();
  useEffect(() => {
    //promise 객체에서 배열로 바꿔주는 과정
    getHobbyFavorite().then(data => {
      setIsFavoriteHobby(data);
    });
  }, []);

  return { isFavoriteHobby };
};
