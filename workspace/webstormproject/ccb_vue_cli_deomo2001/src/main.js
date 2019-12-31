// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import locale from 'element-ui/lib/locale/lang/en'
import store from './store/index'

Vue.config.productionTip = false

Vue.prototype.$axios = axios

axios.defaults.baseURL = '/api' // 这样每次请求url带一个域名端口后的的/api前缀 + 配置config/index.js中proxyTable
axios.defaults.headers.post['Content-Type'] = 'application/json' // post方式的请求头

Vue.use(ElementUI)

Vue.use(ElementUI, { locale }) // 国际化

// 菜单权限控制
// 用户手动刷新页面，这是路由会被重设，要重新新增
if (sessionStorage.getItem('user')) {
  let routes = JSON.parse(sessionStorage.getItem('routes'))
  store.dispatch('add_Routes', routes)
}
// 登录状态判断
router.beforeEach((to, from, next) => {
  if (!sessionStorage.getItem('user') && to.path !== '/login') {
    next({
      path: '/login',
      query: {redirect: to.fullPath}
    })
  } else {
    next()
  }
})

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
