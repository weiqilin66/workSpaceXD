import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import locale from 'element-ui/lib/locale/lang/en'
import store from './store'

/* 配置axios */
Vue.prototype.$axios = axios
axios.defaults.baseURL = '/api' // 这样每次请求url带一个域名端口后的的/api前缀
axios.defaults.headers.post['Content-Type'] = 'application/json'

Vue.use(ElementUI)
Vue.use(ElementUI, { locale }) // 国际化
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
