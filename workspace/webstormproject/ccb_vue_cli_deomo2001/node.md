advance:

target:
  1. 动态权限导航栏
      * “动态添加路由”和“导航守卫"
      * 后端控制路由数据
  2. 图片识别需求
  3. excel导出导入
  4. 登录手机短信验证
  5. 国际化
  6. 网页的跳转权限控制,前+后
  7. 尝试做支付
  8. 数据库事务控制
  9. cookie的使用
  10. nginx的使用
  11. 国际化
  12. 封装axios

Tips & BUG
  1. @无法解析为src文件夹
     * IDE 默认无法识别WebStorm ，把 webpack.base.conf.js 的路径填进去就行，可能需要重启 IDE。
     Settings > Languages & Frameworks > JavaScript > Webpack
  2. router路径跳转
     * 默认hash模式会在路径后加/#/ ,使用model:history的话可避免加#，但是如此你的服务器就不再返回 404 错误页面，因为对于所有路径都会返回 index.html 文件。为了避免这种情况，你应该在 Vue 应用里面前端覆盖所有的路由情况，包括404 页面,或者在服务端配置fallback的页面
     * 支付页面由于不能含有# 和部分小程序会自动删除掉# 必须使用histroy，只需在后端（Apache 或 Nginx）进行简单的路由配置，同时搭配前端路由的 404 页面支持。
  3. 跨域问题:
     * 当前端页面与后台运行在不同的服务器时，就必定会出现跨域这一问题(前端起了8080 后端测试端口8763),当协议、子域名、主域名、端口号中任意一个不相同时，都算作不同域
     * 跨域问题的根本原因：当页面在执行一个脚本时会检查访问的资源是否同源，如果非同源，那么在请求数据时，浏览器会在控制台中报一个异常，提示拒绝访问
     * 跨域，指的是从一个域名去请求另外一个域名的资源
     * 解决跨域的原理:绕过同源策略(http协议,www.xx.com 或者localhost域名 :8080端口  /img/xx.jpg 请求资源)
     * 跨域并不是请求发不出去，请求能发出去，服务端能收到请求并正常返回结果，只是结果被浏览器拦截了
     * 但是有三个标签是允许跨域加载资源：
          * img
          * link
          * script src=XXX
     * 跨域解决方案
        * 配置proxyTable 拦截url请求转向了zuul ,每次请求再拼接zuul.routers的路径 参考https://blog.csdn.net/yuanlaijike/article/details/80522621
        * 使用Nginx 




