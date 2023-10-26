<script setup>
import {RouterView} from 'vue-router'
import {useAccountStore} from "@/stores/account";
import request from "@/utils/request";
import {ElMessage} from "element-plus";
import {
  DataBoard,
  Document,
  Setting,
  User
} from "@element-plus/icons-vue";


const userStore = useAccountStore()
const user = userStore.getUser

const logout = () => {
  request.get('/logout/' + user.uid).then(res => {
    if (res.code === '200') {
      userStore.logout()
    } else {
      ElMessage.error(res.msg)
    }
  })
}
const menus = userStore.getMenus
</script>

<template>
  <div>
    <el-container>
      <!-- 头部 -->
      <el-header>
        <div class="header-layout">
          <!-- LOGO -->
          <div>后台管理</div>
          <!--  -->
          <div>

          </div>
        </div>
      </el-header>
      <el-container>
        <!-- 边栏 -->
        <el-aside width="200px" style="height: 100%">
          <div class="aside-layout">
            <!-- 菜单 -->
            <el-menu
                router
                class="menu-layout"
                default-active="2"
            >
              <!-- 子菜单 -->
              <el-sub-menu index="1">
                <template #title>
                  <el-icon><DataBoard /></el-icon>
                  <span>数据</span>
                </template>

                <el-menu-item index="1-4-1">item one</el-menu-item>

              </el-sub-menu>
              <el-menu-item index="2">
                <el-icon>
                  <User/>
                </el-icon>
                <span>用户</span>
              </el-menu-item>
              <el-menu-item index="3" disabled>
                <el-icon>
                  <document/>
                </el-icon>
                <span>日志</span>
              </el-menu-item>
              <el-menu-item index="4">
                <el-icon>
                  <setting/>
                </el-icon>
                <span>设置</span>
              </el-menu-item>
            </el-menu>
          </div>
        </el-aside>
        <!-- 内容 -->
        <el-main>
          <div>
            <RouterView/>
          </div>
        </el-main>
      </el-container>
    </el-container>


  </div>
</template>

<style>
.header-layout {
  display: flex;
  width: 100%;
  height: 100%;
  border-bottom: 1px solid #ddd;
  background-color: #FAFCFF;
}

.aside-layout {
  height: 100%;
}

.menu-layout {

  min-height: calc(100vh - 60px);
}


.header-layout > div:first-child {
  width: 180px;
  height: 60%;
  margin: auto 0;
  color: #409EFF;
  font-size: x-large;
  font-weight: bold;
  text-align: center;
}

.header-layout > div:last-child {
  flex: 1;
}
</style>
