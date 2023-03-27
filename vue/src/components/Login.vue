<template>
    <!-- 创建封装容器 -->
   
   
  
    <div class="zongti">
     

<div style="display: flex; margin-top: 20px; height: 100px;">
 
</div>
<!-- 注册页面 -->
<div class="left" v-if="id=='1'">
    
        <h1>sign up</h1>
        <input type="text"  v-model="user.userName" placeholder="userName...">
        <input type="email"  v-model="user.email" placeholder="Email...">
        <input type="password"  v-model="user.password" placeholder="Password...">
        <input type="text"  v-model="user.phone" placeholder="Phone...">
        <el-button :plain="true" class="tebie" @click="regist()">sign up</el-button>
    
</div>
<!-- 登录页面 -->
<div class="left" v-if="id=='0'">
    
        <h1>sign in</h1>
        <input type="text"  v-model="user.userName" placeholder="userName...">
        <input type="password"  v-model="user.password" placeholder="Password...">
        <button :plain="true" type="text" @click="open" class="forget">forget your password</button>
        <el-button :plain="true" class="tebie" @click="login()">sign in</el-button>
    
</div>
    <div class="right" v-if="id=='0'">
        <div class="right01">
            <h1>Hello Friend!</h1>
            <p>
                Enter your personal details and start journey with us
            </p>
            <el-button :plain="true" class="ghost" @click="toregist()">sign up</el-button>
        </div>
    </div>
    <div class="right" v-if="id=='1'">
        <div class="right01">
            <h1>welcome back!</h1>
            <p>
              o keep connected with us please login with your personal info
            </p>
            <el-button :plain="true" class="ghost" @click="tologin()">sign in</el-button>
        </div>
    </div>
</div>
 
  </template>
  
<script>

  export default {
    name: "Login",
    data(){
			return {
				id:0,
        user:{
          userName:'',
          email:'',
          password:'',
          phone:''
        }
      
			}
		},
    methods: {
      open() {
        this.$alert('重新注册一个吧', 'why you forget your password?', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
      },
     tologin:function(){
        this.$data.id=0;
     },
     toregist:function(){
      this.$data.id=1;
     },
      login: function(){
        this.$Request
            .fetch_("/user/login","post",this.user)
            .then((result)=>{
              console.log(result)
              if(result.code==200){
                this.$message.info('登录成功'),
                this.$TestData.yonghu.userName=result.data.userName;
                this.$TestData.yonghu.createTime=result.data.createTime;
                this.$TestData.yonghu.email=result.data.email;
                this.$TestData.yonghu.password=result.data.password;
                this.$TestData.yonghu.phone=result.data.phone;
                this.$TestData.yonghu.avatar=result.data.avatar;
                this.$TestData.yonghu.isAdmin=result.data.isAdmin;
                this.$TestData.yonghu.id=result.data.id;
                console.log(this.$TestData.yonghu.avatar)
                this.$VuexStore.commit("setToken", result.data);
                if(result.data.isAdmin==0){
                  this.$router.push("/");
                }else{
                  this.$router.push("/permission/user");
                }
               
							
              }else{
                this.$message.info('账号密码错误'),
                this.user.userName='',
                this.user.password=''
              }
              
            })
            .catch(() => {});
						
      },
      regist:function(){
        console.log(this.user.userName)
        if(this.user.userName==''){
          this.$message('没有输入完')
        }else{
          this.$Request
            .fetch_("/user/insertUser","post",this.user)
            .then((result)=>{
              console.log(result)
              if(result.code==200){
                this.$message('注册成功')
                this.id=0
              }else{
                this.$message('注册失败')
                this.user.userName='',
                this.user.password='',
                this.user.email='',
                this.user.phone=''
              }
            })
            .catch(() => {});
        }

        
      }
    }
  }
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style>
  .tebie{
    width: 200px;
    margin-top: 10px;
    outline: none;
    border: 1px solid #83cdf8;
    border-radius: 20px;
    background-color: #36a4ff;
    padding: 10px 40px;
    font-size: 12px;
    text-transform: uppercase;
    color: white;
    transition: transform 85ms ease-in;
  }
 .forget{
  font-size: 10px;
  margin-top: 20px;
 }
  .right01{
    margin-top: 100px;
  }
  .tebie{
    
    margin-left: 130px;
    width: 140px;
  }
  input{
    margin-left: 100px;
    margin-top: 50px;
    width: 200px;
    height: 20px;
   
  }
  button{
    cursor: pointer;
    background-color: unset;
   border: 0;
  }
body{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #F6F5F7;
}
.ghost {
  margin-top: 10px;
    outline: none;
    border: 1px solid #83cdf8;
    border-radius: 20px;
    background-color: #36a4ff;
    padding: 10px 40px;
    font-size: 12px;
    text-transform: uppercase;
    color: white;
    transition: transform 85ms ease-in;
}
h1 {
    text-transform: capitalize;
    font-size: 30px;
}
p{
    margin: 10px 0 30px;
}
button:active {
    transform: scale(.95);
}



.zongti{
  margin-top: 20%;
    position: relative;
    width: 768px;
    max-width: 100%;
    min-height: 480px;
    background-color: white;
    box-shadow: 0 14px 28px rgba(0, 0, 0, .25), 0 10px 10px rgba(0, 0, 0, .22);
    border-radius: 10px;
    overflow: hidden;
    display: flex;
    flex-direction: row;
}
.left{
  display: flex;
  flex-direction: column;
  background-color: aliceblue;
  right: 50%;
  width: 50%;
}
.right {
    position: absolute;
    display: flex;
    left: 50%;
    flex-direction: column;
    width: 50%;
    height: 100%;
    background: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
}



  </style>
  