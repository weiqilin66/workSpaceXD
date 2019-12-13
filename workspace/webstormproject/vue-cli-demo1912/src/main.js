// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
/*
import Element from 'element-ui'; //配置全局按钮弹窗大小
Vue.use(Element, { size: 'small', zIndex: 3000 });
*/
Vue.use(ElementUI)
/* main相当于全局配置文件,use(x) x所有文件可以用 x除了axios,axios使用在单独页面引用 */
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
