import { apiInstance, ENDPOINT_API } from "@/api";
import { QUERY_KEYS } from "@/constant";
import { useQuery } from "@tanstack/react-query";

const { HOBBY_READY_LIST, HOBBY_PARTICIPATION_LIST, HOBBY_MANAGE_LIST, HOBBY_FAVORITE_LIST,
  DEAL_SOLDOUT_LIST, DEAL_PURCHASE_LIST, DEAL_ONSALE_LIST, DEAL_FAVORITE_LIST,
} = QUERY_KEYS;

export const useProfile = (nickname: string) => {

  const postHobbyReady = async () => {
    const { data } = await apiInstance
      .post(ENDPOINT_API + `/member/hobby/ready`, { nickname })
    return data.data;
  };
  const hobbyReadyData = useQuery([HOBBY_READY_LIST, nickname], postHobbyReady, {
    onSuccess: (data) => {
      console.log(data)
    },
  })
  const hobbyReadyList = hobbyReadyData.data

  const postHobbyParticipation = async () => {
    const { data } = await apiInstance
      .post(ENDPOINT_API + `/member/hobby/participation`, { nickname })
    return data.data;
  };
  const hobbyParticipationData = useQuery([HOBBY_PARTICIPATION_LIST, nickname], postHobbyParticipation, {
    onSuccess: (data) => {
      console.log(data)
    },
  })
  const hobbyParticipationList = hobbyParticipationData.data

  const postHobbyManage = async () => {
    const { data } = await apiInstance
      .post(ENDPOINT_API + `/member/hobby/manage`, { nickname })
    return data.data;
  };
  const hobbyManageData = useQuery([HOBBY_MANAGE_LIST, nickname], postHobbyManage, {
    onSuccess: (data) => {
      console.log(data)
    },
  })
  const hobbyManageList = hobbyManageData.data

  const postHobbyFavorite = async () => {
    const { data } = await apiInstance
      .post(ENDPOINT_API + `/member/hobby/favorite`, { nickname })
    return data.data;

  };
  const hobbyFavoriteData = useQuery([HOBBY_FAVORITE_LIST, nickname], postHobbyFavorite, {
    onSuccess: (data) => {
      console.log(data)
    },
  })
  const hobbyFavoriteList = hobbyFavoriteData.data


  const postDealSoldOut = async () => {
    const { data } = await apiInstance
      .post(ENDPOINT_API + `/member/deal/sold-out`, { nickname })
    return data.data;
  };
  const dealSoldOutData = useQuery([DEAL_SOLDOUT_LIST, nickname], postDealSoldOut, {
    onSuccess: (data) => {
      console.log(data)
    },
  })
  const dealSoldOutList = dealSoldOutData.data

  const postDealPurchase = async () => {
    const { data } = await apiInstance
      .post(ENDPOINT_API + `/member/deal/purchase`, { nickname })
    return data.data;
  };
  const dealPurchaseData = useQuery([DEAL_PURCHASE_LIST, nickname], postDealPurchase, {
    onSuccess: (data) => {
      console.log(data)
    },
  })
  const dealPurchaseList = dealPurchaseData.data

  const postDealOnSale = async () => {
    const { data } = await apiInstance
      .post(ENDPOINT_API + `/member/deal/on-sale`, { nickname })
    return data.data;
  };
  const dealOnSaleData = useQuery([DEAL_ONSALE_LIST, nickname], postDealOnSale, {
    onSuccess: (data) => {
      console.log(data)
    },
  })
  const dealOnSaleList = dealOnSaleData.data

  const postDealFavorite = async () => {
    const { data } = await apiInstance
      .post(ENDPOINT_API + `/member/deal/favorite`, { nickname })
    return data.data;
  };
  const dealFavoriteData = useQuery([DEAL_FAVORITE_LIST, nickname], postDealFavorite, {
    onSuccess: (data) => {
      console.log(data)
    },
  })
  const dealFavoriteList = dealFavoriteData.data

  return {
    hobbyReadyList, hobbyParticipationList, hobbyManageList,
    hobbyFavoriteList, dealSoldOutList, dealPurchaseList,
    dealOnSaleList, dealFavoriteList,
  };
};
