import axiosClient from "axios";

/**
 * Creates an initial 'axios' instance with custom settings.
 */

const axios = axiosClient.create({
    baseURL: import.meta.env.VITE_BACKEND_URL as string,
    withCredentials: true
});

axios.interceptors.response.use(
    (res) => res.data,
    async (error) => {
        return error?.response?.data ?? Promise.reject(error);
    }
);

export default axios;