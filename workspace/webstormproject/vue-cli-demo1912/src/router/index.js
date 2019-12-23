import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
/*
初始路由只配置login 404
利用 vue-router 的 addRoutes 方法可以动态添加路由
*/
export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/Login')
    },
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '*',
      name: '404',
      component: () => import('@/views/404')
    }
    /*{
      path: '/homePage',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/Dashboard')
      // redirect:'子组件名' redirect到某个children组件可以默认显示哪个子组件
    }*/
  ]
})
