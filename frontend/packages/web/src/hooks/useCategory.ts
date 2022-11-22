import { apiInstance, ENDPOINT_API } from "@/api";
import { useEffect, useState } from "react";

export const allLocal = [{
  parentId: null,
  categoryName: "전체"
}]

const getCategory = async () => {
  return await apiInstance
    .get(ENDPOINT_API + "/category/list")
    .then(({ data }) => {
      const list = data.data.filter(option => option.parentId === null);
      return list
    })
    .catch(e => {
      console.log(e);
    });
};
export const useCategory = () => {
  const [options, setOptions] = useState<any>(allLocal);
  const [hobbyDatas, setHobbyDatas] = useState<any>();
  useEffect(() => {
    const List = getCategory();
    //promise 객체에서 배열로 바꿔주는 과정
    const getData = () => {
      List.then(data => {
        setOptions([...options, ...data]);
        setHobbyDatas(data)
      });
    };
    getData();
  }, []);
  console.log(options)
  return { options, hobbyDatas }
}


