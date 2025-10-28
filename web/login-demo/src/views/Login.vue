<template>
    <el-row class="login-page">
        <el-col :span="6" offset="3" class="form">
            <!-- 登录表单 -->
             <el-form ref="form" size="large" autocomplete="off" :model="loginData" :rules="rules">
                <el-form-item>
                    <h1>登录</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="loginData.username">aaa</el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="loginData.password"></el-input>
                </el-form-item>
                 <el-form-item>
                    <!-- 登录按钮 -->
                    <el-button type="primary" auto-insert-space :loading="loading" @click="login">{{loading ? '登陆中' : '登录'}}</el-button>
                 </el-form-item>
             </el-form>
        </el-col>
    </el-row>
</template>

<script setup>
import { User, Lock } from '@element-plus/icons-vue'
import { ref } from 'vue';

// 定义数据模型
const loginData = ref({
    username: '',
    password: '',
})

// 定义表单校验规则
const rules = {
    username: [
        {required: true, message: '用户名不能为空', trigger: 'blur'}
    ],
    password: [
        {required: true, message: '密码不能为空', trigger: 'blur'}
    ]
}

// 定义登录按钮加载状态
const loading = ref(false)

import { userLoginService } from '@/api/user.js'
import { useRouter } from 'vue-router'
import { useTokenStore } from '@/stores/token.js'

const router = useRouter()
const tokenStore = useTokenStore()
// 定义登录函数
const login = async () => {
    loading.value = true;       // 设置登录按钮样式为登录中
    try {
        // 调用接口，完成登录
        let result = await userLoginService(loginData.value);

        // 如果上面的登录接口调用返回成功结果，才会继续执行下面的代码，否则会抛出异常，下面的代码就不执行了
        // 把得到的token存储到pinia中
        tokenStore.setToken(result.data.token)

        // 跳转到首页
        router.push('/')
    } finally {
        loading.value = false;  // 恢复登录按钮样式
    }    
}

</script>

<style scoped>
.login-page {
    height: 100vh;
    display: flex;
    justify-content: center;  /* 水平居中 */
    align-items: center;      /* 垂直居中 */
}

.form {
    padding: 40px;
    border-radius: 10px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
    width: 100%;              /* 确保宽度自适应 */
    max-width: 400px;         /* 最大宽度限制 */
}
</style>