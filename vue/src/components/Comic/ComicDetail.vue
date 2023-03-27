<template>
    <div>
		<p @click="prev()" class="ssss">>>></p>
		<!-- 动漫展示 -->
		<div class="zong">
			<div class="zuo">
				<img :src="comic.cover" class="img">
				<div class="you">
					 <p>昵称:{{comic.comicName}}</p>
					 <p>地区:{{ comic.region }}</p>
					 <p>标签:{{ comic.label }}</p>
					 <p>年份:{{ comic.year }}</p>
					 <p>热度:{{ comic.popularity }}</p>
					 <p>最近更新:{{ comic.updateTime }}</p>
					 <p>总集数:{{ comic.number }}</p>
					 <p>更新到多少集:{{ comic.remark }}</p>
					 <p @click="like()" class="xuan">☆</p>
   			</div>			
  			</div>
			<div class="bianjie"></div>
			
				<ul
class="pagelist"
v-infinite-scroll="loadMore"
infinite-scroll-disabled="loading"
infinite-scroll-distance="5" >
<li v-for="item in commentlist" style="list-style:none">
  <!-- 评论展示 -->
  <div class="one">        
          <img :src="item.avatar" class="comicimg" v-if="item.avatar!=null">
		  <img :src="imgurl" class="comicimg" v-if="item.avatar==null">
            <div class="fudong">
              <p class="name">{{item.userName}}</p>
              <p class="miaoshu">{{item.comment}}</p>
			  <!-- 二级评论 -->
			  <div v-for="k in item.commentReplyContentList" class="fu" v-if="item.commentReplyContentList.length>0 && item.commentId==itemid && show2">
						<img :src="k.replyAvatar" class="kimg" v-if="k.replyAvatar!=null">
		  <img :src="imgurl" class="kimg" v-if="k.replyAvatar==null">
            <div class="kfudong">
				<div class="fudong">
					<p class="kname">{{k.replyUserName}}</p>
              <p class="kmiaoshu">回复{{k.repliedUserName}}:{{k.comment}}</p>
				</div>
             
				<!-- 右边 -->
			  <div class="kyou">
              <p class="time">{{item.createTime}}</p>
			  <div class="khhe">
				<p class="kreply" @click="replya(k)" v-if="user.id!=k.replyId">回复</p>
				<p class="kreply" @click="deletecommenta(k)" v-if="user.id==k.replyId">删除</p>


			</div>
				</div>
            </div>
				</div>
            </div>
         
            <div class="youu">
              <p class="time">{{item.createTime}}</p>
			  <div class="hhe">
				<p class="reply" @click="reply(item)" v-if="user.id!=item.userId">回复</p>
				<p class="reply" @click="deletecomment(item)" v-if="user.id==item.userId">删除</p>
				<div class="sss">
					<el-button @click="show3(item)" class="reply01">查看更多</el-button>
					
				</div>
			  </div>			 
            </div>
       </div>
		<div :style="{height:divHeight}" v-if="item.commentReplyContentList.length>0 && item.commentId==itemid && show2"></div>
		<div class="bianjie01"></div>
      </li>

</ul>
	<!-- 输入评论 -->
	<div class="shuru">
		<textarea type="text" class="shuru01" v-model="comment" :placeholder="placeholder"/>
		<button type="submit" @click="addcomment()">提交</button>
		<button type="submit" @click="clear()">清空</button>
	</div>
	<div></div>

		</div>
   
   <!-- 评论显示 -->
   <div>
		
   </div>

    </div>
  </template>
  
  <script>
  export default {
    name: 'HelloWorld',
    props: {
      msg: String
    },
	data(){
			return {
				user:this.$TestData.yonghu,
				divHeight:'',
				show2:'',
				itemid:'',
				imgurl:'https://tse1-mm.cn.bing.net/th/id/OIP-C.l9T-NQJt5y8YF4iMTcbuSgAAAA?w=148&h=150&c=7&r=0&o=5&dpr=1.3&pid=1.7',
				placeholder:'',
				comic:this.$TestData.comic,
				commentlist:[],
				historyjson:{
					userId:'',
					comicId:''
				},
				comment:'',
				commenta:{
					comicId:'',
					userId:'',
					comment:''
				},
				commentb:{
					comicCommentId:'',
					replyId:'',
					repliedId:'',
					comment:'',
					
				},
				likeuser:{
					userId:'',
					comicId:''
				},
				replyid:''
			}
		},
		created(){
			this.comic=this.$route.query.item,
			this.getcomment(),
			this.addhistory(),
			this.user.avatar=this.$TestData.u+this.user.avatar
			console.log(this.$TestData.yonghu.avatar)
		},
		methods:{
			prev(){
				this.$router.go(-1)
			},
			like(){
				this.likeuser.userId=this.user.id
				this.likeuser.comicId=this.comic.id
				this.$Request
				.fetch_("/user/insertCollection", "post", this.likeuser)
				.then((result) => {
					if(result.code==200)this.$message.info("收藏成功")
					else this.$message.info("已经收藏过了")
				})
				.catch((error) => {
					this.$message.error(error);
				});
			},
			clear(){
				this.comment='',
				this.placeholder='',
				this.replyid=''
			},
			deletecomment(item){
				var url="/comment/deleteComicCommentByComicCommentId/"+ item.id
				this.$Request
				.fetch_(url, "delete",)
				.then((result) => {
          
					console.log(result)
					this.getcomment()
				})
				.catch((error) => {
					this.$message.error(error);
				});
			},
			deletecommenta(k){
				var url="/commentReplyContent/deleteCommentReplyContentByCommentReplyContentId/"+ k.id
				this.$Request
				.fetch_(url, "delete",)
				.then((result) => {
          
					console.log(result)
					this.getcomment()
				})
				.catch((error) => {
					this.$message.error(error);
				});
			},
			show3(item){
				this.divHeight=item.commentReplyContentList.length*120+"px"
				this.show2=!this.show2
				this.itemid=item.commentId
				if(item.commentReplyContentList.length==0)this.$message.info("没有人回复他，你回复吗");
			},
			loadMore() {
        this.loading = true
      },
	  reply:function(item){
		this.placeholder="回复"+item.userName+":"
		this.replyid=item.userId
		this.commentb.comicCommentId=item.id
	  },
	  replya(k){
			this.placeholder="回复"+k.replyUserName+":"
			this.replyid=k.replyId
			this.commentb.comicCommentId=k.id

	  },
	  
			addhistory:function(){
				this.historyjson.userId=this.$TestData.yonghu.id
				this.historyjson.comicId=this.comic.id
				this.$Request
				.fetch_("/user/insertHistory", "post", this.historyjson)
				.then((result) => {
					console.log(result)
				})
				.catch((error) => {
					this.$message.error(error);
				});
			},
			getcomment:function(){
				var url = "/comment/selectCommentByComicId/" + this.comic.id;
      this.$Request
        .fetch(url)
        .then((result) => {
			this.show2=!this.show2
			console.log(result)
          this.commentlist=result.data
		  this.commentlist.map((item) => {
						if (item.avatar) {
							item.avatar = this.$Request.domain + item.avatar;
						}
						if(item.commentReplyContentList.map((a)=>{
							if (a.replyAvatar) {
							a.replyAvatar = this.$Request.domain + a.replyAvatar;
							a.repliedAvatar = this.$Request.domain + a.repliedAvatar;
						}
						return a;
						}))
						return item;
        })
		// this.commentlist.commentReplyContentList.map((a) => {
		// 	console.log("sss")
		// 				if (a.replyAvatar) {
		// 					a.replyAvatar = this.$Request.domain + a.replyAvatar;
		// 				}
		// 				return a;
				
        // })
		
        })
		
        .catch((error) => {
          
        });
			},
			addcomment:function(){
				if(this.replyid==''){
					this.commenta.userId=this.$TestData.yonghu.id
				this.commenta.comicId=this.comic.id
				this.commenta.comment=this.comment
					
				// this.commentb.comicCommentId=this.
				this.$Request
				.fetch_("/comment/insertComicComment", "post", this.commenta)
				.then((result) => {
					if(result.code==200){
						this.$message.info("评论成功");
						this.comment=''
						this.placeholder='',
						this.replyid=''
						this.getcomment();
						
					}else{
						this.$message.info("有问题捏");
					}
				})
				.catch((error) => {
					this.$message.error(error);
				});
				}else{
					this.commentb.replyId=this.$TestData.yonghu.id
				this.commentb.repliedId=this.replyid
				this.commentb.comment=this.comment
				this.$Request
				.fetch_("/commentReplyContent/insertCommentReplyContent", "post", this.commentb)
				.then((result) => {
					if(result.code==200){
						console.log(result)
						console.log("二级")
						this.$message.info("评论成功");
						this.comment='',
						this.replyid='',
						this.placeholder='',
						this.getcomment();
					}else{
						this.$message.info("有问题捏");
					}
				})
				.catch((error) => {
					this.$message.error(error);
				});
			}
		}
	}}
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  .ssss{
	cursor: pointer;
    background-color: unset;
  }
  .xuan{
	cursor: pointer;
    background-color: unset;
  }
  .kreply{
	cursor: pointer;
    background-color: unset;
	color: #0cbcf2;
	margin-left: 10px;
	font-size: 12px;
	font-weight: 1000;
  }
  
.shanchu{
	margin-left: 10px;
}
.kmiaoshu{
	font-size: 12px;
}
  .kname{
	color: red;
	margin-top: 10px;
	font-size: 15px;
	font-weight: 1000;
  }
  .kimg{
	margin-top: 20px;
	height: 60px;
	width: 60px;
	border-radius: 50%;
  }
  
  .sss{
	
	width: 100px;
	height: 100px;
	display: flex;
	flex-direction: column;
  }
 .fu{
	display: flex;
	flex-direction:row ;
 }
  .shuru01{
	margin-left: 150px;
	height: 100px;
	width: 300px;
  }
  .shuru{
	width: 100%;
	display: flex;
	flex-direction: column;
	margin-bottom: 200px;
  }
  input{
	width: 400px;
	height: 100px;
	
  }
  button{
	margin-top: 10px;
	margin-left: 250px;
	width: 100px;
  }
  .hhe{
	display: flex;
	flex-direction: row;
  }
  .reply01{
	cursor: pointer;
    background-color: unset;
	font-size: 10px;
	width: 20px;
	height: 5px;
	margin-left: 10px;
	margin-top: 25px;
	border: 0px;
  }
  .reply{
	cursor: pointer;
    background-color: unset;
	color: #0cbcf2;
	margin-top: 28px;
	margin-left: 10px;
  }
  .bianjie01{
	height: 0px;
	background-color: #000;
	width: 1000px;
	border:1px solid #000;
  }
  .comicimg{
  margin-top: 15px;
  margin-left: 5px;
  width: 90px;
  height: 90px;
  border-radius: 50%;
}
  .one{
	width: 1200px;
  height: 120px;
  margin-top: 10px;
  display: flex;
  flex-direction: row;
}
 .youu{
  margin-left: 20px;
}
.label{
  font-weight: 1000;
  margin-top: 10px;
  color: #3c3a3a;
  background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
}
.time{
	width: 100px;
  margin-top: 30px;
  font-size: 10px;
  color: #1d8ccc;
}
.kfudong{
  width: 700px;
  text-align: left;
  margin-left: 10px;
	display: flex;
	flex-direction: row;
}
.fudong{
  width: 700px;
  text-align: left;
  margin-left: 10px;
}
.name{
	color: red;
	margin-top: 30px;
  font-weight: 1000;
}
.miaoshu{
	font-size: 15px;
}
.pagelist{
  height: 300px;
  overflow-y: auto;
}
  .you{
	border:1px solid #e7edec;
	margin-left: 10px;
	width: 300px;
	height: 400px;
	background-color: #ffffff;
  }
  .bianjie{
	width: 1400px;
	border:1px solid #000;
  }
  .zong{
	display: flex;
	flex-direction: column;
	width: 100%;
	height: 100%;
	justify-content: center; /*水平居中*/
    align-items: Center; /*垂直居中*/
  }
 .zuo{
	margin-top: 30px;
	width: 500px;
	height: 500px;
	display: flex;
	flex-direction: row;
 }
.img{
	margin-top: 30px;
	width: 250px;
	height: 350px;
	border-radius: 2%;
}
  </style>
  