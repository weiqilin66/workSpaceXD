import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: '登录',
        component: Login,
        hidden: true
        // component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    },
    {
        path: '/home',
        name: 'Home',
        component: () => import( '../views/Home'),
        hidden: true
    },
    {
        path: '/home',
        name: '导航一',
        component: () => import( '../views/Home'),
        children: [
            {
                path: '/test1',
                name: '选项1',
                component: () => import( '../views/Test1')
            },
            {
                path: '/test2',
                name: '选项2',
                component: () => import( '../views/Test2')
            }
        ]
    }

]

const router = new VueRouter({
    routes
})

export default router
