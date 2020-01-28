import Axios from "axios";
import {Message} from "element-ui";
import da from "element-ui/src/locale/lang/da";

// 统一封装返回的处理
Axios.interceptors.response.use(success => {
    console.log('success:'+success)
    // 服务端特地返回了的错误类型
    if (success.status && success.status === 200 && success.data.status === 500) {
        Message.error(success.data.message);
        return
    }
    return success.data

}, error => {
    console.log('error:'+error)

    if (error.response.status === 504 || error.response.status === 404) {
        Message.error("找不到服务器")
    } else if (error.response.status === 403) {
        Message.error("权限不足,请联系管理员")
    } else if (error.response.status === 401) {
        Message.error("尚未登录或登录过期,请重新登录")
    } else {
        if (error.response.data.message) {
            Message.error(error.response.data.message)
        } else {
            Message.error("未知错误!")
        }
    }

})

// 定义base 灵活定义url
let base = ''

//springSecurity默认登录只接受keyValue不接受json
export const postKeyValueRequest = (url, params) => {
    return Axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},// 设置响应头
        transformRequest: [function (data) { //转换格式
            let res = ''

            for (let i in data) {
                res += encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&'
            }
            console.log("keyValue转换:" + res)
            return res
        }]
    })
}



