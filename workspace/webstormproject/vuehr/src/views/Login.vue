<template>
    <div>
        <el-form :rules="rules" ref="loginForm" :model="loginForm" class="loginContainer">
            <h3 class="loginTitle">系统登录</h3>
            <el-form-item prop="username">
                <el-input type="text" auto-complete="off" placeholder="请输入用户名" v-model="loginForm.username"/>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" auto-complete="off" placeholder="请输入密码" v-model="loginForm.password"/>
            </el-form-item>
            <font size="2px">Remember</font>
            <el-checkbox class="loginRemember" v-model="checked"/>
            <el-button type="primary" style="width:100%" @click="submitForm">登录</el-button>
        </el-form>
    </div>
</template>

<script>
    import {postKeyValueRequest} from "../utils/api";

    export default {
        name: "Login",
        methods: {
            submitForm() {
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        postKeyValueRequest('/doLogin', this.loginForm).then(data => {
                            // Axios封装已经判断了状态,此时的response就是data
                            if (data) {
                                alert(JSON.stringify(data))
                                //new ObjectMapper().writeValueToString()
                            }
                        })
                    } else {
                        this.$message.error('请确认输入用户名及密码')
                        return false;
                    }
                });
            },
        },
        data() {
            return {
                loginForm: {
                    username: 'admin',
                    password: '123'
                },
                checked: true,
                rules: {
                    username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
                    password: [{required: true, message: '请输入密码', trigger: 'blur'}]
                }
            }
        }
    }
</script>

<style>
    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 15px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .loginTitle {
        margin: 15px auto 20px auto;
        text-align: center;
        color: #505458;
    }

    .loginRemember {
        text-align: left;
        margin: 0px 0px 15px 0px;

    }
</style>