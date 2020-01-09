<template>
  <div>
    <el-container style="height: 500px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '3']">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>导航一</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>

              <el-menu-item index="1-1"> <el-link type="primary" > <router-link to="/login">注册</router-link></el-link></el-menu-item>

             <!-- <el-menu-item index="1-2" @click="getList">交互</el-menu-item>
              <el-menu-item index="1-2" @click="getList"><router-link to="/login">交互&跳转</router-link></el-menu-item>-->
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>导航二</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>导航三</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>查看</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>王小虎</span>
        </el-header>
        <router-view/>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'Menu',
  data () {
    return {
      // 左侧菜单栏数据
      menuItems: [
        {
          name: 'home', // 要跳转的路由名称 不是路径
          size: 18, // icon大小
          type: 'md-home', // icon类型
          text: '主页' // 文本内容
        },
        {
          text: '二级菜单',
          type: 'ios-paper',
          children: [
            {
              type: 'ios-grid',
              name: 't1',
              text: '表格'
            },
            {
              text: '三级菜单',
              type: 'ios-paper',
              children: [
                {
                  type: 'ios-notifications-outline',
                  name: 'msg',
                  text: '查看消息'
                },
                {
                  type: 'md-lock',
                  name: 'password',
                  text: '修改密码'
                },
                {
                  type: 'md-person',
                  name: 'userinfo',
                  text: '基本资料'
                }
              ]
            }
          ]
        }
      ]
    }
  },
  methods: {
    getList () {
      const _this = this
      _this.$axios.get('/hi')
        .then(response => {
          alert(response.data)
        // _this.$router.push(" ") // router编程式导航 <router-link>声明式导航
        }).catch(error => {
          console.log(error)
        })
    }
  }
}
</script>
<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>
