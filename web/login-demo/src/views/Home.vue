<template>
    <el-row>
        <el-col :span="6" offset="3" class="user">
            欢迎,{{ userInfoStore.info.username }}
        </el-col>
        <el-col :span="3" offset="3" class="content">
            <el-button type="primary" @click="logout">退出登录</el-button>
        </el-col>
    </el-row>
    <el-row>
        <el-col :span="6" offset="3" class="content">
            主页内容
        </el-col>
    </el-row>
</template>

<script setup>
import { userInfoService } from '@/api/user.js'
import { useUserInfoStore } from '@/stores/userinfo.js'
import { useTokenStore } from '@/stores/token.js'

// 用户token管理
const tokenStore = useTokenStore();
// 用户信息管理
const userInfoStore = useUserInfoStore();

// 获取用户信息
const getUserInfo = async () => {
    let result = await userInfoService();
    // 如果成功获取用户信息，更新到pinia
    userInfoStore.setInfo(result.data);
}
getUserInfo();

import { useRouter } from 'vue-router'
const router = useRouter();

import { ElMessage } from 'element-plus'
// 退出登录
const logout = async () => {
    // 清楚pinia中存储的token和userInfo
    tokenStore.removeToken();
    userInfoStore.removeInfo();

    // 跳转到登录页面
    router.push('/login');

    ElMessage({
        type: 'success',
        message: '退出登录成功'
    })
}

</script>

<style scoped>

</style>