import { apiInstance, ENDPOINT_API } from "@/api";
import { HobbyMemberType } from "@/types";
import { useEffect, useState } from "react";

export const useMemberHobby = (hobbyId: number) => {
  const getHobbyMemberList = async () => {
    const { data } = await apiInstance.get(ENDPOINT_API + "/hobby/history/" + `${hobbyId}` + "/participant");
    return data.data;
  };
  const [hobbyMemberList, setHobbyMemberList] = useState<HobbyMemberType>();
  useEffect(() => {
    getHobbyMemberList().then(data => {
      setHobbyMemberList(data);
      console.log(data);
    });
  }, []);

  return { hobbyMemberList };
};
