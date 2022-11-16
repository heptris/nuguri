import { apiInstance, ENDPOINT_API } from "@/api";
import { QUERY_KEYS } from "@/constant";
import { useInfiniteQuery, useQuery } from "@tanstack/react-query";

const { DEAL_LIST, HOBBY_LIST } = QUERY_KEYS;
export const useHome = ({ categoryId, localId }: { categoryId?: number; localId?: number }) => {
  const postHobbyList = async () => {
    return await apiInstance.post(ENDPOINT_API + "/hobby/list", { categoryId, localId }).then(({ data }) => data.data);
  };
  const getDealList = async (page = 0, size = 4) => {
    return await apiInstance
      .get(ENDPOINT_API + "/deal/list?" + (!!localId ? `localId=${localId}&` : "") + (!!categoryId ? `categoryId=${categoryId}&` : "") + `size=${size}&page=${page}`)
      .then(({ data }) => data.data);
  };
  const {
    data: dealDatas,
    fetchNextPage,
    fetchPreviousPage,
    hasNextPage,
  } = useInfiniteQuery([DEAL_LIST], ({ pageParam = 0 }) => getDealList(pageParam), {
    getNextPageParam: last => {
      return !last.last && last.pageable.pageNumber + 1;
    },
    getPreviousPageParam: first => !first.first && first - 1,
  });
  const { data: hobbyDatas } = useQuery([HOBBY_LIST], postHobbyList);
  return { dealDatas, hobbyDatas, fetchNextPage, fetchPreviousPage, hasNextPage };
};
