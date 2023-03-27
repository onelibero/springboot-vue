<template>
    <div class="zong">

  <el-descriptions class="margin-top"  :column="2"  border>
    <template slot="extra">
    </template>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        头像
      </template>
      <img :src="url" class="touxiang" v-if="avatar==null">
      <img :src="avatar" class="touxiang" v-if="avatar!=null">
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        手机号
      </template>
     {{user.phone}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        昵称
      </template>
     {{user.userName}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        密码
      </template>
     ******
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        账号
      </template>
      {{user.id}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        用户权限
      </template>
      <el-tag size="small">普通用户</el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-office-building"></i>
        邮箱地址
      </template>
      {{user.email}}
    </el-descriptions-item>
    <el-descriptions-item>
      
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-office-building"></i>
        创建时间
      </template>
      {{user.createTime}}
    </el-descriptions-item>
  </el-descriptions>

    </div>
  </template>
  
  <script>
  export default {
    name: 'Detail',
    props: {
      msg: String
    },
    
    data() {
		return {
			menus: this.$TestData.user,
			url:"https://tse1-mm.cn.bing.net/th/id/OIP-C.l9T-NQJt5y8YF4iMTcbuSgAAAA?w=148&h=150&c=7&r=0&o=5&dpr=1.3&pid=1.7",
      user:this.$TestData.yonghu,
      avatar:''
		};
    
	},
  methods:{
    getuser(){
      var url = "/user/getUserById/" + this.$TestData.yonghu.id;
      this.$Request
        .fetch(url)
        .then((result) => {
          console.log("成功了")
          console.log(result)
          
            this.avatar=this.$Request.domain +result.avatar
          q
        
          
        })
        .catch((error) => {
        });
    }
  },
  created () {
    this.getuser()
    }
  }
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  .zong{
    width: 100%;
    height: 100%;
    margin-top: 5%;
  }
  .touxiang{
    margin-left: 30%;
    width: 50px;
    height: 50px;
  }
  </style>
  