// 跨域解决->配置代理 开发环境由nodejs转发请求,生产环境用Nginx转发
let proxyObj = {}

proxyObj['/'] = {
    ws: false,
    // target: 'http://localhost:1111', // zuul
    target: 'http://localhost:8081',
    changeOrigin: true,
    pathRewrite: {
        '/': ''
    }
}
let proxyObj2={};
proxyObj2['/'] = {
    ws: false,
    target: 'http://192.168.45.131:8081',
    changeOrigin: true,
    pathRewrite: {
        '/': ''
    }
}

module.exports = {
    devServer: {
        host: 'localhost',
        port: 8080,
        proxy: proxyObj
        // proxy: proxyObj2 //本地前台连ubutun后台使用
    },
    // vue-echarts
    transpileDependencies: [
        'vue-echarts',
        'resize-detector'
    ]
}
