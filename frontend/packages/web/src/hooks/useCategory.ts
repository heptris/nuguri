import { apiInstance, ENDPOINT_API } from "@/api";


export const useCategory = () => {
    const getCategory = async () => {
        return await apiInstance
            .get(ENDPOINT_API + "/category/list")
            .then(({ data }) => {
                const List = data.data.filter(option => option.parentId === null);
                return List
            })
            .catch(e => {
                console.log(e);
            });
    };
    return { getCategory }

}


