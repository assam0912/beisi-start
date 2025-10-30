import axios from 'axios';
import { ElMessage } from 'element-plus'

// 根据环境决定baseURL：开发时走/api（vite代理），生产时走VITE_APP_API_URL（构建时注入）
const baseURL = import.meta.env.DEV ? '/api' : (import.meta.env.SERVICE_URL + '/api');
const instance = axios.create({ baseURL })

import { useTokenStore } from '@/stores/token';
// 添加请求拦截器
instance.interceptors.request.use(
    config => {
        // 请求前的回调
        // 添加token
        const tokenStore = useTokenStore();
        // 判断有没有token，有就加到请求头中
        if (tokenStore.token) {
            config.headers.Authorization = tokenStore.token;
        }
        return config;
    },
    err => {
        // 请求错误的回调
        return Promise.reject(err);
    }
)

import router from '@/router';

// 添加响应拦截器
instance.interceptors.response.use(
    result => {
        // 判断业务状态码
        if (result.data.code === 0) {
            ElMessage.success(result.data.message ? result.data.message : '操作成功')
            return result.data;
        }
        // 操作失败
        ElMessage.error(result.data.message ? result.data.message : '操作失败')
        // 异步操作的状态转换为失败
        return Promise.reject(result.data);
    },
    err => {
        // 判断响应状态码，如果是401，证明未登录，提示用户请先登录，并跳转到登录页面
        if (err.response.status === 401) {
            ElMessage.error('请先登录')
            router.push('/login')
        } else {
            ElMessage.error('服务异常')
        }
        return Promise.reject(err);
    }
)

export default instance;