import Vue from 'vue'
import App from './App.vue'
import router from './router'

// 引入 ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);
Vue.config.productionTip = false

// 引入封装完的axios 所有.vue文件通过this.xx引用 其他文件例如.js不行,需要额外import
import {postKeyValueRequest, postRequest, getRequest, putRequest, deleteRequest} from "./utils/api";

Vue.prototype.postKeyValueRequest = postKeyValueRequest
Vue.prototype.postRequest = postRequest
Vue.prototype.getRequest = getRequest
Vue.prototype.putRequest = putRequest
Vue.prototype.deleteRequest = deleteRequest

// 引入 vuex
import store from './store'

// 导航守卫 拦截方法如果不是登录页的页面跳转,调用initMenu方法

import {initMenu} from "./utils/menu";

router.beforeEach((to, from, next)=>{
    if(to.name==='登录'){
        next() // 放行
    }else {
        initMenu(store,router)
        next()
    }
})

// 导入 font-awesome 小图标
import 'font-awesome/css/font-awesome.min.css'

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
