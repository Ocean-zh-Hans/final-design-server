import { defineStore } from 'pinia'         // 导入 pinia 库中的 defineStore 函数
import router, {setRoutes} from "@/router"; // 导入路由和设置路由的函数

// 定义一个名为 useAccountStore 的状态存储库
export const useAccountStore = defineStore('manager', {
    
    // 初始化状态，其中managerInfo为当前管理员信息
    state: () => ({
        managerInfo: {}
    }),
    
    // 定义 getter 方法，用于获取状态，例如：获取管理员 ID、获取 token 等
    getters: {
        getUserId() {
            return this.managerInfo.user ? this.managerInfo.user.id : 0
        },
        getUser() {
            return this.managerInfo.user || {}
        },
        getBearerToken() {
            return this.managerInfo.token ? 'Bearer ' + this.managerInfo.token : ''
        },
        getToken() {
            return this.managerInfo.token || ""
        },
        getMenus() {
            return this.managerInfo.menus || []
        },
        getAuths() {
            return this.managerInfo.auths.length ? this.managerInfo.auths.map(v => v.auth) : []
        }
    },
    
    // 定义 action 方法，用于改变状态，例如：设置管理员信息、设置用户信息、退出登录等
    actions: {
        setManagerInfo(managerInfo) {
            this.managerInfo = managerInfo
            
            // 根据管理员菜单设置动态路由
            //setRoutes(managerInfo.menus)
            setRoutes([

            ])
        },
        setUser(user) {
            // 将传入的 user 对象进行深拷贝并赋值给 managerInfo 中的 user 字段
            this.managerInfo.user = JSON.parse(JSON.stringify(user))
        },
        logout() {
            // 移除 localStorage 中的 manager 字段
            localStorage.removeItem('manager')
            // 跳转到登录页
            router.push('/login')
        }
    },
    
    // 开启持久化存储，刷新页面后状态依然存在
    persist: true
})