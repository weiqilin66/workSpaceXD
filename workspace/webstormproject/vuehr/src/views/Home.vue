<template>
    <div>
        <el-container>
            <!--header-->
            <el-header class="homeHeader">
                <div class="title">灼猪</div>
                <el-dropdown class="userInfo" @command="dropdownHandler">
                    <span class="el-dropdown-link">
                {{user.name}}<i><img :src="user.userface"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="center">个人中心</el-dropdown-item>
                        <el-dropdown-item command="setting">设置</el-dropdown-item>
                        <el-dropdown-item divided command="logout">注销登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-header>
            <el-container>
                <!--侧边栏-->
                <el-aside width="200px">
                    <el-menu router>
                        <el-submenu index="1" v-for="(item,index) in this.$router.options.routes"
                                    v-if="!item.hidden" :key="index">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>{{item.name}}</span>
                            </template>
                                <el-menu-item :index="child.path" v-for="(child,index2) in item.children" :key="index2">
                                    {{child.name}}</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <!--中间栏-->
                <el-main>
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Home",
        data() {
            return {
                user: JSON.parse(window.sessionStorage.getItem('user'))

            }
        },
        methods: {
            dropdownHandler(cmd) {
                if (cmd == 'logout') {
                    this.$confirm('是否确认注销?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.getRequest('/logout')
                        window.sessionStorage.removeItem('user')
                        this.$router.replace('/')
                        // 消息弹窗由axios封装统一处理后台message

                        //this.$message.success("注销成功")
                        /*this.$message({
                              type: 'success',
                              message: '注销成功!'
                          });*/
                    }).catch(() => {
                    });
                } else if (cmd == 'center') {

                } else {

                }
            }
        }
    }
</script>


<style>
    .homeRouterView {
        margin-top: 10px;
    }

    .homeWelcome {
        text-align: center;
        font-size: 30px;
        font-family: 华文行楷;
        color: #409eff;
        padding-top: 50px;
    }

    .homeHeader {
        background-color: #409eff;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0px 15px;
        box-sizing: border-box;
    }

    .homeHeader .title {
        font-size: 30px;
        font-family: 华文行楷;
        color: #ffffff
    }

    .homeHeader .userInfo {
        cursor: pointer;
    }

    .el-dropdown-link img {
        width: 48px;
        height: 48px;
        border-radius: 24px;
        margin-left: 8px;
    }

    .el-dropdown-link {
        display: flex;
        align-items: center;
    }
</style>