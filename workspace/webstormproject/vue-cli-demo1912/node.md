advance:
  1.怎么让mode:history 不刷新页面
target:
  1. 动态工具栏
  2. 图片识别需求
  3. excel导出导入
  4. 登录手机短信验证
  5. 国际化
  6. 网页的跳转权限控制,前+后
  7. 尝试做支付

Tips & BUG
  1. 路径@无法识别  :IDE 默认无法识别 Webpack 的 alias，需要配置一下。IDEA 的话在 Settings > Languages & Frameworks > JavaScript > Webpack 里，WebStorm 应该差不多，把 webpack.base.conf.js 的路径填进去就行，可能需要重启 IDE。
  2. router路径跳转无效: 在router下指定mode:'history',解决非最优 
  默认hash模式会在路径后加# ,绝对路径不对,但是这么做以后，你的服务器就不再返回 404 错误页面
  ，因为对于所有路径都会返回 index.html 文件。为了避免这种情况，你应该在 Vue 应用里面覆盖所有的路由情况，然后在给出一个 404 页面,或者在服务端配置fallback的页面
 总结对于一般的 Vue + Vue-Router + Webpack + XXX 形式的 Web 开发场景，用 history 模式即可，只需在后端（Apache 或 Nginx）进行简单的路由配置，同时搭配前端路由的 404 页面支持。
  
DevStep
  1. 安装模块(项目目录下)
    x.npm i element-ui -s
    x.cnpm install axios -s
  2. 
  
  
