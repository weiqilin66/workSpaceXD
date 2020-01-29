import Vue from 'vue'
import App from './App.vue'
import router from './router'

// 引入 ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);
Vue.config.productionTip = false

// 引入封装完的axios请求
import {postKeyValueRequest, postRequest, getRequest, putRequest, deleteRequest} from "./utils/api";

Vue.prototype.postKeyValueRequest = postKeyValueRequest
Vue.prototype.postRequest = postRequest
Vue.prototype.getRequest = getRequest
Vue.prototype.putRequest = putRequest
Vue.prototype.deleteRequest = deleteRequest

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
