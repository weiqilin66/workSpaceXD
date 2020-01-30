1. 菜单该何时加载:
    * 注意当用户f5刷新页面时,菜单不能消失
    * so: 路由导航守卫
        * router.beforeach(to,from,next=>{})相当于java的过滤器,当跳转的页面不是Login时要执行initMenu方法
        * initMenu不能每次到后台取数据,所以存储到vuex中,当state中有routes时,不需要请求后台
    * 注销时删除vuex中菜单的数据
    
2. 
