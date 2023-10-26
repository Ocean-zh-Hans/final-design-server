import { createApp } from 'vue'         // 导入Vue的createApp函数
import App from './App.vue'             // 导入App组件
import { createPinia } from 'pinia'     // 导入Pinia的createPinia函数
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate' // 导入Pinia插件，用于持久化状态
import * as ElementPlusIconsVue from '@element-plus/icons-vue'      // 导入Element Plus的图标组件
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'               // 导入Element Plus的中文语言包
import ElementPlus from 'element-plus'  // 导入Element Plus库
import 'element-plus/dist/index.css'    // 导入Element Plus的CSS样式文件
import router from './router'           // 导入路由
import './assets/main.css'              // 导入自定义的CSS样式文件

// 创建Vue应用实例
const app = createApp(App)
// 创建Pinia实例
const pinia = createPinia()
// 注册Element Plus的图标组件
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

// 使用Pinia的持久化状态插件
pinia.use(piniaPluginPersistedstate)

// 将Pinia实例应用到Vue应用实例
app.use(pinia)
// 将路由应用到Vue应用实例
app.use(router)
// 将Element Plus库应用到Vue应用实例，并设置为中文语言
app.use(ElementPlus, {locale: zhCn})

// 挂载Vue应用实例到HTML页面的#app元素上
app.mount('#app')
