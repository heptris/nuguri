import { QUERY_KEYS } from "./../constant/index";
import { useQuery } from "@tanstack/react-query";
import { apiInstance, ENDPOINT_API } from "@/api";

const { MY_ALARM } = QUERY_KEYS;
export const useAlarm = () => {
  const getAlarm = async () => await apiInstance.get(ENDPOINT_API + "/alarm").then(({ data }) => data);
  const { data: notiList } = useQuery([MY_ALARM], () => getAlarm());
  return { notiList };
};
