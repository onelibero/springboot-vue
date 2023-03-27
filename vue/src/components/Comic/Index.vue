<template>
    <div class="hello">
      <div class="all-wrapper">
    <!-- 创建外层容器 -->
    <div class="out-wrapper">
        
        <!-- 创建导航栏容器 -->
        <div class="header-wrapper">
           
            <!-- 创建导航栏 -->
            <div class="nav-bar">
                <ul class="navBar">
                    <li>
                        <div @click="getcomic('')">首页</div>
                    </li>
                    <li>
                        <div @click="getcomic('日本')">日本动漫</div>
                    </li>
                    <li>
                        <div @click="getcomic('大陆')">国产动漫</div>
                    </li>
                    <li>
                        <div @click="getcomic('热血')">热血动漫</div>
                    </li>
                    <li>
                        <div @click="getcomic('冒险')">冒险动漫</div>
                    </li>
                    <li>
                        <div @click="getcomic('奇幻')">奇幻动漫</div>
                    </li>
                </ul>
            </div>
           
        </div>
        <!-- 创建搜索容器 -->
        <div class="search-wrapper">
            <!-- 创建搜索框 -->
            <div class="search-in">
                <i class="iconfont icon-search"></i>
                <input type="text" v-model="searchtext" name="keywords" placeholder="请输入关键词">
                <button class="searchbutton" @click="search()">搜索</button>
                <!--  登录 -->
            <div class="buju">
    <el-button @click="show3 = !show3" v-if="user.userName==''" class="username">你好，陌生人</el-button>
    <el-button @click="show3 = !show3" v-if="user.userName!=''" class="username">{{user.userName}}</el-button>
    <div style="height: 100px;" class="buju">
      <el-collapse-transition>
        <div v-show="show3" class="buju">
          <div class="buju01"><router-link to="/login"  v-if="user.userName==''">登录</router-link></div>
          <div class="buju01"> <button @click="loginout()" v-if="user.userName!=''" class="loginbutton">注销</button></div>
          <div class="buju01"> <router-link to="/user" v-if="user.userName!=''">个人中心</router-link></div>
        </div>
      </el-collapse-transition>
    </div>
            </div>
            </div>
        </div>

        <!-- 创建轮播图容器 -->
        <div class="swiper">
            <div class="swiper-wrapper">
              <el-carousel :interval="4000" type="card">
                      <el-carousel-item v-for="item in indexcomic" :key="item.id">
                                  <div class="medium">
                                    <img :src="item.cover" class="fengmian" @click="todetail(item)">
                                  </div>
                        </el-carousel-item>
              </el-carousel>
            </div>
        </div>
           
        
        </div>
  
    </div>
   <!--主体内容-->
   <div class="zongti" >
             <div v-for="item in comic.list" :key="item.id">
            <!--每个盒子-->
                  <div class="one" @click="todetail(item)" >
                    <!--图片-->
                    <img :src="item.cover"  class="oneimg">
                    <!--文本-->
                    <h1 class="comicname">{{ item.comicName }}</h1>
                  </div>
                </div>
          </div>
<div class="juli"></div>

          <div class="block">
 
  <el-pagination
  @current-change="handleCurrentChange"
  background
  :current-page="comic.pageNum"
  :page-size="comic.pageSize"
    layout="prev, pager, next"
    :total="comic.total"
    >
  </el-pagination>
              </div>
</div>


    
  </template>
  
  <script>
  

  export default {
    name: 'Index',
    props: {
      msg: String
    },
    
    data(){
			return {
                which:'',
                show3:false,
                indexcomic:[],
                user:this.$TestData.yonghu,
               get_comic: {
				currentPage: 1,
				pageSize: 10,
				keyword: "",
				sort: "popularity",
				direction: "desc",
			        },
                    comic: {
				total: 30, // 总数据量
				pageNum: 1, // 当前页
				pageSize: 10, // 页长
				pages: 1, // 总页数
				list: [], // 当前页数据集合
			        },
            searchtext:''
			}
		},
  created(){
    this.getindexcomic();
    this.getcomic();
    console.log(this.$TestData.yonghu.avatar)
  },
		methods:{
      todetail:function(item){
        if(this.user.id!=''){
          this.$router.push({path: '/detail',query:{item}});
        }else{
          this.$message.info("先登录先登录")
        }
        
      },
      handleCurrentChange:function(val) {
       this.get_comic.currentPage=val;
       this.getcomic()
        console.log(val)
      },
      loginout:function() {
			// 没有后台方法，由前端清除 token
			this.$VuexStore.commit("setToken", "");
			this.$router.push("/login");
		},
    getindexcomic:function(){
      console.log("用户")
      console.log(this.user)
      this.$Request
				.fetch_("/comic/getComicByPopularity", "get", )
				.then((result) => {
          console.log("主要的")
					console.log(result)
          this.indexcomic=result.list;
          this.indexcomic.map((item) => {
						if (item.cover) {
							item.cover = this.$Request.domain + item.cover;
						}
						return item;
					});
				})
				.catch((error) => {
					this.$message.error(error);
				});
    },
		 getcomic: function(e){
            console.log(e);
            this.get_comic.keyword=e;
            this.$Request
				.fetch_("/comic/getComicBySearch", "post", this.get_comic)
				.then((result) => {
          this.searchtext=''
					console.log(result)
          this.comic=result;
          this.comic.list.map((item) => {
						if (item.cover) {
							item.cover = this.$Request.domain + item.cover;
						}
						return item;
					});
				})
				.catch((error) => {
					this.$message.error(error);
				});
         },
         search:function(){
          console.log(this.searchtext)
          this.get_comic.keyword=this.searchtext;
          this.$Request
				.fetch_("/comic/getComicBySearch", "post", this.get_comic)
				.then((result) => {
					console.log(result)
          this.searchtext=''
          this.comic=result;
          this.comic.list.map((item) => {
						if (item.cover) {
							item.cover = this.$Request.domain + item.cover;
						}
						return item;
					});
				})
				.catch((error) => {
					this.$message.error(error);
				});
         }
		},
  }
  </script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  .block{
    overflow:auto;
  }
  .juli{
    margin-top: 30px;
  }
  .username{
    font-weight: 1000;
    margin-left: 10px;
  }
  .comicname{
    font-size: 20px;
    font-weight: 1000;
  }
  .buju01{
    margin-top: 10px;
    margin-left: 10px;
  }
  .buju{
    display: flex;
    flex-direction: row;
  }
  .searchbutton{
    width: 100px;
    height: 50px;
    background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
  }
  .loginbutton{
    margin-top: 1px;
   color: #f61010;
   font-size: 15px;
  }
  button{
    cursor: pointer;
    background-color: unset;
   border: 0;
  font-size: 15px;
  }
  .zongti{
    height: 850px;
    width: 85%;
    margin-left: 7%;
    margin-top: 500px;
    background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
    display: flex;
    flex-direction: row;
    flex-wrap:wrap;
  }
  .one{
    width: 200px;
    height: 350px;
    margin-top: 50px;
    margin-left: 40px;
  }
  .oneimg{
    width:200px;
    height:300px;
  }
  .medium{
    width: 600px;
    height: 300px;
    display: flex;
  justify-content: center;
  align-items: center;
  }
  *{
    margin: 0;
    padding: 0;
}

.all-wrapper{
    margin-top: 50px;
    width: 100%;
    height: 100%;
    background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
}

/* 设置所有a */
a,
a:hover,
a:active,
a:visited{
    text-decoration: none;
    color: #eeddff;
}

/* 设置外层容器 */
.out-wrapper{
    width: 1200px;
    height: 60px;
    margin: 0 auto; 
}

/* 设置导航栏容器 */
.out-wrapper .header-wrapper{
    height: 60px;
    width: 100%;
    background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
    display: flex;
    justify-content: space-around;
    text-align: center;
}

/* 设置logo */

/* 设置导航栏 */
.nav-bar{
    position: absolute;
    width: 900px;
    height: 60px;
    background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
}
.nav-bar .navBar{
    height: 60px;
    list-style: none;
    display: flex;
    justify-content: space-around;
    text-align: center;
}
.navBar li{
    line-height: 60px;
    cursor: pointer;
    flex-grow: 1;
}
.navBar li:hover{
    background-image: linear-gradient(to top, #fdcbf1 0%, #fdcbf1 1%, #e6dee9 100%);
}
.navBar li a{
    font-size: 20px;
}


/* 设置搜索框 */
.out-wrapper .search-wrapper{
    width: 1200px;
    height: 50px;
    background-image: linear-gradient(to bottom, #fbc2eb 0%, #a6c1ee 100%);
}

.search-wrapper .search-in{
    width: 800px;
    height: 50px;
    display: flex;
    flex-direction: row;
}
.search-in i{ 
    width: 50px;
    height: 50px;
    color: #ff9a9e;
    font-size: 30px !important;
    cursor: pointer;
}
.search-in input{
    width: 250px;
    height: 50px;
    border: 0;
    background-image: linear-gradient(to bottom, #fbc2eb 0%, #a6c1ee 100%);
    outline: none; 
}
/* 设置轮播图容器 */
.swiper{
    width: 1200px;
    height: 400px;
    background-color: #a6c1ee;
}
.swiper-slide{
    position: absolute;
}
.swiper-slide img{
    width: 100%;
    height: 300px;
    position: relative;
}
/* 设置轮播图下方的文字信息 */
.swiper-slide .remark{
    position: relative;
    left: 100px;
    top: -20px;
    color: aliceblue;
    font-size: 14px;
    overflow: hidden;
}
.swiper-slide .comicName{
    height: 30px;
    width: 180px;
    position: relative;
    top: 10px;
    color: #FFFFF2;
    font-size: 20px;
    overflow: hidden;
}
.swiper-slide .description{
    height: 30px;
    width: 180px;
    position: relative;
    top: 20px;
    color: #928E90;
    font-size: 14px;
    overflow: hidden;
}


</style>