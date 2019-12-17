import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
// 利用 vue-router 的 addRoutes 方法可以动态添加路由
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
      path: '/homePage',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/Dashboard')
      // redirect:'子组件名' redirect到某个children组件可以默认显示哪个子组件
    },
    {
      path: '/404',
      component: () => import('@/views/404')
    }
  ]
})
/*// 从服务器获取路由,进行拼接
export const makeRoute = function (items) {
  let routes = []
  for (let i = 0; i < items.length; i++) {
    console.log(items[i])
    items[i] = formatRoute(items[i])
    if (items[i].children) {
      // 递归处理子路由的component
      items[i].children = makeRoute(items[i].children)
    }
  }
  return items
}

// 格式化路由,使component挂载到路由上,生成addRoutes可用的格式
const formatRoute = (item) => {
  let route = item
  route.component = eval(route.component)
  return route
}*/
