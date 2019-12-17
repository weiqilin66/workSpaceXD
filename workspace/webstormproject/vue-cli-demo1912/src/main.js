import Vue from 'vue'
import App from './App'
import router, from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import locale from 'element-ui/lib/locale/lang/en'
import store from './store'

/*
import Element from 'element-ui'; //配置全局按钮弹窗大小
Vue.use(Element, { size: 'small', zIndex: 3000 });
*/
/* 配置axios */
Vue.prototype.$axios = axios
axios.defaults.baseURL = '/api' // 这样每次请求url带一个域名端口后的的/api前缀
axios.defaults.headers.post['Content-Type'] = 'application/json'

/* main相当于全局配置文件,use(x) 所有类可以使用 axios的用法不同 */
Vue.use(ElementUI)
Vue.use(ElementUI, { locale }) // 国际化
Vue.config.productionTip = false

/* router 配置 */
router.beforeEach((to, from, next) => {
  // 如果目标路由为登陆时,恢复用户原始状态
  if (to.path === '/login') {
    window.clearInterval(window.interval)
    store.commit('logOut')
  }
  let allRoutes = store.getters.allRoutes
  let loginStatus = store.getters.loginStatus

  // 登录成功时加载路由及模块
  if (from.path === '/login' && allRoutes !== '' && loginStatus) {
    let routesObj = makeRoute(store.getters.allRoutes)
    router.addRoutes(routesObj)
  }
  // 没有登录时自动跳转,开发环境免登陆时注释
  if (to.path !== '/login' && (allRoutes === '' || !loginStatus)) {
    if (sessionStorage.getItem('userInfo') === null) {
      next({ path: '/login' })
    } else {
      // 刷新当前页面
      // 重置store参数
      let userInfo = JSON.parse(sessionStorage.getItem('userInfo'))
      store.commit('setUser', userInfo)
      if (userInfo.routes) {
        // 重新加载路由及模块
        let routesObj = makeRoute(userInfo.routes)
        router.addRoutes(routesObj)
      }
      next({path: to.path, query: to.query})
    }
  } else {
    // 路由的next必须存在,否则无法进入下一页
    next()
  }
})
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
