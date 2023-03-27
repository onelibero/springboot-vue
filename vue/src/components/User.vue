<template>
	
		<div  class="beijing">
			<el-header class="header">
				<el-popover
    placement="top-start"
    title="点一下就行"
    width="60"
    trigger="hover"
	background-color="#d3dce6">
    <el-button slot="reference"><router-link to="/" class="tocomic">进入动漫世界</router-link></el-button>
  </el-popover>
				
			</el-header>
		<div class="top">
			<!-- 头像 -->
			<div>
				<el-upload
  class="avatar-uploader"
  :action="targetAction"
  :show-file-list="false"
  :on-success="handleAvatarSuccess">
  <img v-if="avatar==null" :src="url" class="img01">
  <img :src= "avatar" class="img01" v-if="avatar!=null">
      </el-upload>
				
				<!-- <img :src="url" class="img01" v-if="user.avatra==null"> -->
				
				
			</div>
			<!-- 文本 -->
			<div class="wenben">
				<h1  class="wenben01">{{user.userName}}</h1>
				<h2  class="wenben02">普通用户</h2>
				<h3  class="wenben03">{{user.createTime}}</h3>
			</div>
			<!-- 点赞和浏览记录 -->
			<div class="like">
				<p>点赞</p>
				<p>{{likenum}}</p>
			</div>
			<div class="jilu">
				<p>浏览记录</p>
				<p>{{jilunum}}</p>
			</div>
		</div>
		<div class="kuan">
		<el-container>
  <el-container>
	<div class="sidebar">
		<el-aside width="200px"><Sidebar :menus="menus" /></el-aside>
	</div>
    
    <el-container>
		<div class="main">
			<el-main><router-view/></el-main>
		</div>
      
    </el-container>
  </el-container>
</el-container>
		</div>
	</div>

</template>
<script>
import Sidebar from "@/components/fragements/Usersidebar.vue";
import BreadCrumb from "@/components/fragements/BreadCrumb.vue";
export default {
	name: "User",
	components: {
		Sidebar,
		BreadCrumb,
	},
	data() {
		return {
			menus: this.$TestData.user,
			url:"https://tse1-mm.cn.bing.net/th/id/OIP-C.l9T-NQJt5y8YF4iMTcbuSgAAAA?w=148&h=150&c=7&r=0&o=5&dpr=1.3&pid=1.7",
			user:this.$TestData.yonghu,
			targetAction:'',
			likenum:'',
			jilunum:'',
			avatar:'',
		};
	},
	created(){
		this.targetAction=this.$TestData.u+"/api/common/image/user";
		this.getlike();
		this.getjilu();
		this.getuser();
		// this.user.avatar=this.$TestData.yonghu.avatar
		// 
	},
	methods: {
		getuser(){
      var url = "/user/getUserById/" + this.$TestData.yonghu.id;
      this.$Request
        .fetch(url)
        .then((result) => {
			this.avatar=this.$Request.domain +result.avatar
        })
        .catch((error) => {
          
        });
    },
		getlike(){
			var url = "/user/selectCollectionByUserId/" + this.$TestData.yonghu.id;
      this.$Request
        .fetch(url)
        .then((result) => {
          
          this.likenum=result.data.length
          
        })
        .catch((error) => {
          
        });
		},

		getjilu(){
			var url = "/user/selectHistoryByUserId/" + this.$TestData.yonghu.id;
      this.$Request
        .fetch(url)
        .then((result) => {
         this.jilunum=result.data.length
        })
        .catch((error) => {
          this.$message.info("没有数据。");
        });
		},	
		mei: function () {
			console.log(this.$TestData.user);
		},
		handleAvatarSuccess(res, file) {
			this.url = res.data;
			this.user.avatar=res.data
			console.log(this.user)
			this.$Request
				.fetch_("/user/updateUser", "put", this.user)
				.then((result) => {
					if (result.data.avatar==null) {
            this.avatar=null
          				}else{
							this.avatar=this.$TestData.u+result.data.avatar
						}
						
						this.$TestData.yonghu.userName=result.data.userName;
                this.$TestData.yonghu.createTime=result.data.createTime;
                this.$TestData.yonghu.email=result.data.email;
                this.$TestData.yonghu.password=result.data.password;
                this.$TestData.yonghu.phone=result.data.phone;
                this.$TestData.yonghu.avatar=result.data.avatar;
                this.$TestData.yonghu.isAdmin=result.data.isAdmin;
                this.$TestData.yonghu.id=result.data.id;
				this.$TestData.yonghu.userName=result.data.userName;
						this.user=this.$TestData.yonghu
						this.$message.info("上传成功");
						//this.user.avatar=this.$TestData.u+this.user.avatar
						console.log("这是user")
						console.log(this.user)
					
				})
				.catch((error) => {
					
				});
 		
},
	}
};
</script>
<style scoped>

.tocomic{
	font-weight: 1000;
	text-decoration: none;
}
.like{
	font-weight: 1000;
	margin-top: 5%;
	margin-left: 40%;
}
.jilu{
	font-weight: 1000;
	margin-left: 1%;
	margin-top: 5%;
}
.wenben{
	margin-left: 10px;
	margin-top: 30px;
}
.wenben01{
	margin-left: 10px;
	font-weight: 1000;
	font-size: 24px;
}
.wenben02{
	font-size: 15px;
	color: #51c5ef;
}
.wenben03{
	font-size: 12px;
}
.beijing{
	height: 800px;
	z-index: 999;
  right: 0px;
	width: 1450px;
	background-color: #d3dce6;
}
.img01{
	border-radius: 2%;
	margin-top: 50px;
	margin-left: 20px;
	width: 100px;
	height: 100px;
}
.kuan{
	width: 90%;
}
.top{
	display: flex;
	flex-direction: row;
	border-radius: 1%;
	width: 60%;
	height: 200px;
	margin-left: 300px;
	background-color: rgb(255, 255, 255);
}


.sidebar{
	margin-left: 320px;
	margin-top: 20px;
	background-color: #d3dce6;
	color: #333;
	text-align: center;
	line-height: 500px;
	border: 2px;
}

.main{
	margin-left: 20px;
	margin-top: 20px;
	background-color: rgb(255, 255, 255);
	color: #333;
	height: 400px;
	width: 620px;
	border: 2px;
	/* text-align: center; */
	/* line-height: 160px; */
}




</style>
