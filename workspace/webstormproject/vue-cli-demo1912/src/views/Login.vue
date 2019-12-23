<template>
 <div>
   <el-form :model="user" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
     <el-form-item label="用户名" prop="username">
       <el-input type="username" v-model="user.username" autocomplete="off"></el-input>
     </el-form-item>
     <el-form-item label="确认密码" prop="password">
       <el-input type="password" v-model="user.password" autocomplete="off"></el-input>
     </el-form-item>
     <el-form-item>
       <el-button type="primary" @click="submitForm('user')">提交</el-button>
       <el-button @click="resetForm('user')">重置</el-button>
     </el-form-item>
   </el-form>
 </div>
</template>

<script>
import {mapActions} from 'vuex'
export default {
  data () {
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.user.password !== '') {
          this.$refs.ruleForm.validateField('password')
        }
        callback()
      }
    }
    let validateUsername = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'))
      } else {
        if (this.user.username !== '') {
          this.$refs.ruleForm.validateField('username')
        }
        callback()
      }
    }
    return {
      rules: {
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        username: [
          { validator: validateUsername, trigger: 'blur' }
        ]
      },
      user: {
        username: '',
        password: ''
      },
      loading: false
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
          this.$router.push('/homePage')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    ...mapActions({add_Routes: 'add_Routes'}), // '...'将数组转化成逗号分隔的参数序列

    handleLogin () {
      // 这里为了方便就设置两个用户，其它不允许
      if (this.user.username !== 'common' && this.user.username !== 'admin') {
        return
      }
      this.loading = true
      this.$api.login(this.user.username, this.user.password).then((res) => {
        if (res.status === 200) {
          // 将路由信息，菜单信息，用户信息存到sessionStorage里
          sessionStorage.setItem('menuData', JSON.stringify(res.data.navData))
          sessionStorage.setItem('user', this.user.username)
          sessionStorage.setItem('routes', JSON.stringify(res.data.routerData))
          this.add_Routes(res.data.routerData) // 触发vuex里的增加路由
        }
      })
    }
  }

}
</script>
