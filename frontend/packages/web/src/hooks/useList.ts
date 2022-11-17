import { apiInstance, ENDPOINT_API } from "@/api";
import { QUERY_KEYS } from "@/constant";
import { useQuery } from "@tanstack/react-query";

const { DEAL_LIST, HOBBY_LIST } = QUERY_KEYS;

export const useList = ({ categoryId, localId }: { categoryId?: number; localId?: number }) => {
    const postHobbyList = async () => {
        const { data } = await apiInstance.post(ENDPOINT_API + "/hobby/list", { categoryId, localId });
        return data.data;
    };
    const getDealList = async (page = 0, size = 4) => {
        const { data } = await apiInstance
            .get(ENDPOINT_API + "/deal/list?" + (!!localId ? `localId=${localId}&` : "") + (!!categoryId ? `categoryId=${categoryId}&` : "") + `size=${size}&page=${page}`);
        const { content } = data.data;
        return content;
    };
    const queryfetchHobbyList = useQuery([HOBBY_LIST, categoryId, localId], postHobbyList, {
        onSuccess: (data) => {
            console.log(data)
        },
        // staleTime: 10000
    })
    const queryfetchDealList = useQuery([DEAL_LIST, categoryId, localId], () => getDealList(), {
        onSuccess: (data) => {
            console.log(data)
        },
        onError(err) {
            console.log(err)
        },
        // staleTime: 10000
    })
    const hobbyList = queryfetchHobbyList.data
    const dealList = queryfetchDealList.data
    console.log(dealList)
    return { hobbyList, dealList };
}
