// 导入request.js请求工具
import request from '@/utils/request.js'

// 登录接口
export const userLoginService = loginData => {
    const params = new URLSearchParams();
    return request.post('/login', loginData);
}

// 获取用户详细信息接口
export const userInfoService = () => {
    return request.get('/userInfo');
}