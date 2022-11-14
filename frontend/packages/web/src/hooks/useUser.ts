import { apiInstance } from "./../api/index";
import axios from "axios";
import { ENDPOINT_API } from "@/api";
import { useRecoilState } from "recoil";
import { profileState, regionState } from "@/store";

export const useUser = () => {
    const [, setLocation] = useRecoilState(regionState);
    const [, setProfile] = useRecoilState(profileState);
    const data = {};
    const postProfile = () => {
        apiInstance.
            post(ENDPOINT_API + "/member", data)
            .then(res => {
                const { localId, baseAddress, nickName, temperature } = res.data.data;
                console.log(baseAddress)
                setLocation({ localId, baseAddress })
                setProfile({ nickName, temperature })
            })
            .catch(e => {
                console.log(e)
            })
    }

    return { postProfile }
};
