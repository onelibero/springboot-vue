<template>
  <div class="hello">
        <ul
class="pagelist"
v-infinite-scroll="loadMore"
infinite-scroll-disabled="loading"
infinite-scroll-distance="5" >
<li v-for="item in list" style="list-style:none">
  <!-- 动漫展示 -->
  <div class="one" @click="todetail(item)">        
          <img :src="item.cover" class="comicimg">
            <div class="fudong">
              <p class="name">{{item.comicName}}</p>
              <p class="miaoshu">{{item.description}}</p>
            </div>
         
            <div class="you">
              <p class="label">{{item.region}}</p>
              <p class="time">{{item.updateTime}}</p>
            </div>
            <div  class="shanchu">
              <button @click="shanchu(item)">删除</button>
            </div>
           
        </div>
      </li>
</ul>
  </div>  
</template>

<script>
export default {
  name: 'Detail',
  props: {
    msg: String
  },
  data(){
    return{
      url:"",
      loading:false,
      list:[],
      likejson:{
        userId:'',
        comicId:''
      }
    };
  },
 created(){
    this.getlike();
 },
  methods: {
    shanchu(item){
      this.likejson.userId=this.$TestData.yonghu.id
				this.likejson.comicId=item.id
          this.$Request
				.fetch_("/user/deleteCollection", "delete", this.likejson)
				.then((result) => {
					if(result.code==200)this.$message.info("删除成功")
          this.getlike()
				})
				.catch((error) => {
					this.$message.error(error);
				});
    },
      loadMore() {
        this.loading = true
      },
      todetail:function(item){
          this.$router.push({path: '/detail',query:{item}});
      },
      getlike(){
        var url = "/user/selectCollectionByUserId/" + this.$TestData.yonghu.id;
      this.$Request
        .fetch(url)
        .then((result) => {
          console.log(result)
          this.list=result.data
          this.list.map((item) => {
						if (item.cover) {
							item.cover = this.$Request.domain + item.cover;
						}
						return item;
					});
        })
        .catch((error) => {
          this.$message.info("没有数据。");
        });
      }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.shanchu{
  margin-top: 5px;
  margin-left: 10px;
  font-weight: 100;
  color: #eb0a0af7;
}
.you{
  margin-left: 20px;
}
.label{
  font-weight: 1000;
  margin-top: 10px;
  color: #3c3a3a;
  background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
}
.time{
  margin-top: 50px;
  font-size: 10px;
  color: #1d8ccc;
}
.fudong{
  
  text-align: left;
  margin-left: 10px;
}
.name{
  font-weight: 1000;
}
.pagelist{
  height: 300px;
  overflow-y: auto;
}
.miaoshu{
width: 300px;
font-size: 15px;
font-weight: 500;
overflow: hidden;
text-overflow: ellipsis;
white-space: nowrap;
}

.zong{
  display: flex;
  flex-direction: column;
}
.one{
  cursor: pointer;
    background-color: unset;

  height: 100px;
  border: 1px solid rgb(202, 200, 200);
  display: flex;
  flex-direction: row;
}
.comicimg{
  margin-top: 5px;
  margin-left: 5px;
  width: 80px;
  height: 90px;
  border-radius: 2%;
}
</style>
