import Axios from "axios";
import {Message} from "element-ui";
import da from "element-ui/src/locale/lang/da";

// 拦截器封装返回的axios处理
Axios.interceptors.response.use(success => {
    // 服务端特地返回了的错误类型
    if (success.status && success.status === 200 && success.data.status === 500) {
        Message.error(success.data.message);
        return
    }
    if(success.data.message){
        Message.success(success.data.message)
    }
    // 成功返回后端返回的respBean对象
    return success.data

}, error => {
    console.log('error:' + error)

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

// 封装请求方法,并加入到Vue.prototype中供直接调用

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
            //console.log("keyValueRequest转换结果:" + res)
            return res
        }]
    })
}

// 提交JSON数据格式

export const postRequest = (url, params)=>
{
    return Axios({
        method: 'post',
        url: `${base}${url}`,
        data: params
    })
}
export const getRequest = (url, params)=>
{
    return Axios({
        method: 'get',
        url: `${base}${url}`,
        data: params
    })
}
export const putRequest = (url, params)=>
{
    return Axios({
        method: 'put',
        url: `${base}${url}`,
        data: params
    })
}
export const deleteRequest = (url, params)=>
{
    return Axios({
        method: 'delete',
        url: `${base}${url}`,
        data: params
    })
}



