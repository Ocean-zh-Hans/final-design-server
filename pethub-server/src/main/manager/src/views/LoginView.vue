<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue"
import { User, Lock } from '@element-plus/icons-vue'
import request from "@/utils/request";
import {ElMessage} from "element-plus";
import { useAccountStore } from "@/stores/account";
import router, {setRoutes} from "@/router";

const loginData = reactive({
  username: '',
  password: ''
});

const rules = reactive({
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' }
  ],
})

const ruleFormRef = ref()
const submitLogin = () => {
    ruleFormRef.value.validate(valid => {
      if (valid) {
        // 发送表单数据给后台
        request.post('/user/login.do', loginData).then(res => {
          if (res.status === 0) {
            ElMessage.success('登录成功')
            const userStore = useAccountStore()
            userStore.setManagerInfo(res.data)
            router.push('/')
          } else {
            ElMessage.error(res.msg)
          }
        })
      }
    })
  }

</script>

<template>
  <div class="login-layout">

    <div
        class="shaw-layout"
        :style="{
          borderRadius: 'var(--el-border-radius-round)',
          boxShadow: 'var(--el-box-shadow-lighter)'
        }"
    >
      <h1 style="padding-bottom: 20px;font-weight: bold;text-align: center;color: #409EFF;">
        登&nbsp;&nbsp;录
      </h1>

      <el-form
          ref="ruleFormRef"
          size="large"
          status-icon
          :rules="rules"
          :model="loginData"
      >
        <el-form-item prop="username">
        <el-input
            v-model="loginData.username"
            placeholder="请输入用户名"
            :prefix-icon="User"/>
        </el-form-item>

        <el-form-item prop="password">
        <el-input
            v-model="loginData.password"
            show-password
            placeholder="请输入密码"
            :prefix-icon="Lock"/>
        </el-form-item>

        <el-form-item prop="submit">
          <el-button
              type="primary"
              style="width: 100%"
              @click="submitLogin"
              plain>
            登&nbsp;&nbsp;录
          </el-button>
        </el-form-item>

      </el-form>
    </div>

  </div>
</template>

<style>
.login-layout {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  margin: 0;
  padding: 0;
  background-color: #ecf5ff;
}

.shaw-layout {
  background-color: #ffffff;
  width: 400px;
  padding: 30px;
  border: 1px solid var(--el-border-color);
  margin: 150px auto;
}

</style>