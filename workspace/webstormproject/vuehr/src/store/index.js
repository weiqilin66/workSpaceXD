import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        routes: []
    },
    mutations: {// 操作state数据
        initRoutes(state, data) {
            state.routes = data;
        }
    },
    // 做异步 提交mutations
    actions: {}
})