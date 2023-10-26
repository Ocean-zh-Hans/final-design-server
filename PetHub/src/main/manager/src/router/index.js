// 导入 vue-router 中的 createRouter 和 createWebHistory 函数
import { createRouter, createWebHistory } from 'vue-router'

// 导入 useAccountStore 状态存储库
import {useAccountStore} from "@/stores/account"

// 使用 import.meta.glob 方法导入 views 目录下的所有vue文件
const modules = import.meta.glob('../views/*.vue')

// 使用 import.meta.glob 方法导入 layout 目录下的 CommonLayout.vue 文件
const layoutModules = import.meta.glob('../layout/CommonLayout.vue')

// 创建路由对象
const router = createRouter({

  // 指定历史模式
  history: createWebHistory(import.meta.env.BASE_URL),

  // 路由配置
  routes: [

    // 默认路由，指向 CommonLayout.vue 组件，并且包含一个子路由
    {
      path: '/',
      name: 'Layout',
      component: () => import('../layout/CommonLayout.vue'),
      children: [
        { path: '/home', name: 'Home', component: () => import('../views/HomeView.vue') },
      ]
    },

    // 登录路由，指向 LoginView.vue 组件
    {
      path: '/login',
      name: 'Login',
      component: () => import('../views/LoginView.vue')
    },

    // 404 路由，指向 404.vue 组件
    {
      path: '/404',
      name: '404',
      component: () => import('../views/404.vue')
    }
  ]
})

// 定义 setRoutes 函数，根据传入的 menus 动态添加路由
export const setRoutes = (menus) => {

  // 如果没有传入 menus，从 localStorage 中获取
  if (!menus || !menus.length) {
    const manager = localStorage.getItem('manager')
    if (!manager) {
      return
    }
    menus = JSON.parse(manager).managerInfo.menus
  }

  // 遍历 menus 中的每一项，添加对应的路由信息
  if (menus.length) {
    menus.forEach(item => {
      if (item.path) {
        router.addRoute('CommonLayout', { path: item.path, name: item.page, component: modules['../views/' + item.page + '.vue'] })
      } else {
        if (item.children && item.children.length) {
          item.children.forEach(sub => {
            if (sub.path) {
              router.addRoute('CommonLayout', { path: sub.path, name: sub.page, component: modules['../views/' + sub.page + '.vue'] })
            }
          })
        }
      }
    })
  }
}

// 执行setRoutes函数，添加动态路由
setRoutes()

// 定义全局路由守卫，用于鉴权和404页面跳转
router.beforeEach((to, from, next) => {

  // 获取useAccountStore状态存储库
  const store = useAccountStore()

  // 获取管理员信息中的用户信息
  const user = store.managerInfo.user

  // 判断管理员是否登录
  const hasUser = user && user.id

  // 不需要鉴权的路径列表
  const noPermissionPaths = ['/login', '/404']

  // 如果管理员未登录并且当前路径不在noPermissionPaths中，则跳转到登录页
  if (!hasUser && !noPermissionPaths.includes(to.path)) {
    next("/login")
  } else {
    // 如果当前路径没有匹配到路由，则跳转到404页面，否则放行
    if (!to.matched.length) {
      next('/404')
    } else {
      next()
    }
  }
})

// 导出路由对象
export default router