// 跨域解决->配置代理 开发环境由nodejs转发请求,生产环境用Nginx转发
let proxyObj = {}

proxyObj['/'] = {
    ws: false,
    target: 'http://localhost:8081',
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
    }
}
