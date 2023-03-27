<template>
    <div class="hello">
      <el-collapse  accordion>
  <el-collapse-item title="用户名" name="1">
    <div class="kuandu"><el-input v-model="user.userName" placeholder="user.userName"></el-input></div>
  </el-collapse-item>
  <el-collapse-item title="邮箱" name="2">
    <div class="kuandu"><el-input v-model="user.email" placeholder="user.email"></el-input></div>
  </el-collapse-item>
  <el-collapse-item title="密码" name="3">
    <div class="kuandu"><el-input placeholder="user.password" v-model="user.password" show-password></el-input></div>
  </el-collapse-item>
  <el-collapse-item title="电话号码" name="4">
    <div class="kuandu"><el-input v-model="user.phone" placeholder="user.phone"></el-input></div>
  </el-collapse-item>
</el-collapse>
<el-button type="submit" @click="update" round class="ti">提交修改</el-button>

    </div>
  </template>
  
  <script>
  export default {
    name: 'Setting',
    props: {
      msg: String
    },
    data() {
      return {
        avatar:'',
        user:this.$TestData.yonghu,
      };
    },
    methods: {
      getuser(){
      var url = "/user/getUserById/" + this.$TestData.yonghu.id;
      this.$Request
        .fetch(url)
        .then((result) => {
          console.log("成功了")
          console.log(result)
          this.avatar=result.avatar
        })
        .catch((error) => {
        });
    },
      update(){
        this.user.avatar=this.avatar
        console.log(this.user)
        this.$Request
				.fetch_("/user/updateUser", "put", this.user)
				.then((result) => {
					if(result.code==200){
            this.$message.info("修改成功");
          }
				})
				.catch((error) => {
					this.$message.error(error);
				});
      }
    },
    created(){
     this.getuser();
    }
  }
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  .ti{
    margin-top: 10px;
  }
 .kuandu{
 margin-left: 10%;
    width: 50%;
  }
  </style>
  