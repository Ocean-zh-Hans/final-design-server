import { ElMessage } from 'element-plus'        // 导入Element Plus的ElMessage组件
import { useAccountStore } from "@/stores/account";   // 导入用户状态存储库
import router from '../router'                  // 导入路由
import config from "/config";                   // 导入配置文件
import axios from "axios";                      // 导入axios库

// 创建一个axios实例
const request = axios.create({
    baseURL: `http://${config.serverUrl}`, // 设置请求的基础URL
    timeout: 5000 // 设置请求超时时间
})

// 请求拦截器，自请求发送前对请求做一些处理
request.interceptors.request.use(config => {
    // 设置请求头的Content-Type为application/json;charset=utf-8
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    // 获取用户状态存储库中的Bearer Token并设置到请求头的Authorization字段中
    config.headers['Authorization'] = useAccountStore().getBearerToken;

    return config
}, error => {
    return Promise.reject(error)
});

// 响应拦截器，在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;

        // 如果响应的数据类型是blob，则直接返回数据
        if (response.config.responseType === 'blob') {
            return res
        }

        // 如果响应的数据类型是字符串，则将其转换为JSON对象
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }

        // 如果响应的数据中的code为'401'，说明未授权，显示错误提示信息，并跳转到登录页
        if (res.code === '401') {
            ElMessage.error(res.msg);
            router.push("/login")
        }

        return res;
    },
    error => {
        console.log('err' + error)
        return Promise.reject(error)
    }
)

// 导出axios实例
export default request

