import { apiInstance, ENDPOINT_API } from "@/api"

export const useProfile = () => {
    const postHobbyReady = async () => {
        await apiInstance.post(ENDPOINT_API + `/member/hobby/ready`,).
            then(res => console.log(res))
            .catch((err) => console.log(err))
    }

    const postHobbyParticipation = async () => {
        await apiInstance.post(ENDPOINT_API + `/member/hobby/participation`,).
            then(res => console.log(res))
            .catch((err) => console.log(err))
    }
    const postHobbyManage = async () => {
        await apiInstance.post(ENDPOINT_API + `/member/hobby/manage`,).
            then(res => console.log(res))
            .catch((err) => console.log(err))
    }
    const postHobbyFavorite = async () => {
        await apiInstance.post(ENDPOINT_API + `/member/hobby/favorite`,).
            then(res => console.log(res))
            .catch((err) => console.log(err))
    }
    const postDealSoldOut = async () => {
        await apiInstance.post(ENDPOINT_API + `/member/deal/sold-out`,).
            then(res => console.log(res))
            .catch((err) => console.log(err))
    }
    const postDealPurchase = async () => {
        await apiInstance.post(ENDPOINT_API + `/member/deal/purchase`,).
            then(res => console.log(res))
            .catch((err) => console.log(err))
    }
    const postDealOnSale = async () => {
        await apiInstance.post(ENDPOINT_API + `/member/deal/on-sale`,).
            then(res => console.log(res))
            .catch((err) => console.log(err))
    }
    const postDealFavorite = async () => {
        await apiInstance.post(ENDPOINT_API + `/member/deal/favorite`,).
            then(res => console.log(res))
            .catch((err) => console.log(err))
    }
    return { postHobbyReady, postHobbyParticipation, postHobbyManage, postHobbyFavorite, postDealSoldOut, postDealPurchase, postDealOnSale, postDealFavorite }
}
