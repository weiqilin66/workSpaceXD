import Vue from 'vue'
import App from './App.vue'
import router from './router'
/*main.js中配置 不能使用this.$xxx 因为this就是Vue实例本身,所有this.$xx都是在main.js配置后再.vue文件中使用的*/
// 引入 ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI,{ size: 'small', zIndex: 1800 });//弹窗大小默认2000

Vue.config.productionTip = false

// 引入封装完的axios 所有.vue文件通过this.xx引用 其他文件例如.js不行,需要额外import
import {postKeyValueRequest, postRequest, getRequest, putRequest, deleteRequest} from "./utils/api";

Vue.prototype.postKeyValueRequest = postKeyValueRequest
Vue.prototype.postRequest = postRequest
Vue.prototype.getRequest = getRequest
Vue.prototype.putRequest = putRequest
Vue.prototype.deleteRequest = deleteRequest


import {initMenu} from "./utils/menu";
import {Message} from "element-ui";
// 导航守卫 拦截方法如果不是登录页的页面跳转,调用initMenu方法
router.beforeEach((to, from, next)=>{
    if(to.name==='登录'){
        next() // 放行
    }else {
        if (window.sessionStorage.getItem('user')) {
            initMenu(store,router)
            next()
        }else {
            // 未登录&访问其他url: ,/先跳转到/login,?redirect携带登录成功后跳转的页面,在Login页判断要跳转的页面
            next('/?redirect='+to.path)
            Message.warning('sir,请先登录')
        }

    }
})

// 导入 font-awesome 小图标
import 'font-awesome/css/font-awesome.min.css'
// 引入 vuex
import store from './store'

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
