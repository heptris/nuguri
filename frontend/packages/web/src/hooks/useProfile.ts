import { apiInstance, ENDPOINT_API } from "@/api";

export const useProfile = (nickname) => {
  const postHobbyReady = async () => {
    await apiInstance
      .post(ENDPOINT_API + `/member/hobby/ready`, { nickname })
      .then(res => {
        console.log(res);
        return res;
      })
      .catch(err => console.log(err));
  };

  const postHobbyParticipation = async () => {
    await apiInstance
      .post(ENDPOINT_API + `/member/hobby/participation`, { nickname })
      .then(res => {
        console.log(res);
      })
      .catch(err => console.log(err));
  };
  const postHobbyManage = async () => {
    await apiInstance
      .post(ENDPOINT_API + `/member/hobby/manage`, { nickname })
      .then(res => {
        console.log(res);
      })
      .catch(err => console.log(err));
  };
  const postHobbyFavorite = async () => {
    await apiInstance
      .post(ENDPOINT_API + `/member/hobby/favorite`, { nickname })
      .then(res => {
        console.log(res);
      })
      .catch(err => console.log(err));
  };
  const postDealSoldOut = async () => {
    await apiInstance
      .post(ENDPOINT_API + `/member/deal/sold-out`, { nickname })
      .then(res => {
        console.log(res);
      })
      .catch(err => console.log(err));
  };
  const postDealPurchase = async () => {
    await apiInstance
      .post(ENDPOINT_API + `/member/deal/purchase`, { nickname })
      .then(res => {
        console.log(res);
      })
      .catch(err => console.log(err));
  };
  const postDealOnSale = async () => {
    await apiInstance
      .post(ENDPOINT_API + `/member/deal/on-sale`, { nickname })
      .then(res => {
        console.log(res);
      })
      .catch(err => console.log(err));
  };
  const postDealFavorite = async () => {
    await apiInstance
      .post(ENDPOINT_API + `/member/deal/favorite`, { nickname })
      .then(res => {
        console.log(res);
      })
      .catch(err => console.log(err));
  };
  return { postHobbyReady, postHobbyParticipation, postHobbyManage, postHobbyFavorite, postDealSoldOut, postDealPurchase, postDealOnSale, postDealFavorite };
};
