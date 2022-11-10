import { apiInstance } from "./../api/index";
import axios from "axios";
import { ENDPOINT_API } from "@/api";

export const useUser = () => {
    const postProfile = () => {
        axios.
            post(ENDPOINT_API + "/member")
            .then(res => {
                console.log(res)
            })
            .catch(e => {
                console.log(e)
            })
    }

    return { postProfile }
};
