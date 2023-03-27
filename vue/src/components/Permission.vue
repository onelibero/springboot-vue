<template>
	
	<el-container class="vue-container">
		<el-header class="vue-header"><Header/><button @click="loginout()">注销</button></el-header>
		<el-container>
			<el-aside
				:style="{
					width: this.$VuexStore.state.collapse ? '65px' : '200px',
				}"
				class="vue-sidebar"
			>
				<Sidebar :menus="menus" />
			</el-aside>
			<el-container>
				<el-main>
					<!-- 面包屑导航 -->
					<BreadCrumb />
					<!--
						内容区
						将 user 传递给子组件，接受数据并进行修改
					-->
					<router-view/>
				</el-main>
				<el-footer class="vue-footer" height="40px;">
					<Footer :webSiteInfo="webSiteInfo" />
				</el-footer>
			</el-container>
		</el-container>
	</el-container>
</template>
<script>
import Header from "@/components/fragements/Header.vue";
import Sidebar from "@/components/fragements/Sidebar.vue";
import Footer from "@/components/fragements/Footer.vue";
import BreadCrumb from "@/components/fragements/BreadCrumb.vue";
export default {
	name: "Permission",
	components: {
		Header,
		Sidebar,
		Footer,
		BreadCrumb,
	},
	data() {
		return {
			menus: this.$TestData.permission,
		};
	},
	methods: {
		mei: function () {
			console.log(this.$TestData.permission);
		},
		loginout:function() {
			// 没有后台方法，由前端清除 token
			this.$VuexStore.commit("setToken", "");
			this.$router.push("/login");
		},
	}
};
</script>
<style scoped>
.el-header,
.el-footer {
	background-color: #b3c0d1;
	color: #333;
	text-align: center;
	line-height: 60px;
}

.el-aside {
	background-color: #d3dce6;
	color: #333;
	text-align: center;
	line-height: 200px;
}

.el-main {
	background-color: #e9eef3;
	color: #333;
	/* text-align: center; */
	/* line-height: 160px; */
}

body > .el-container {
	margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
	line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
	line-height: 320px;
}
/* ======== 自定义 ======== */
.vue-container {
	/* font-family: "monaco";
	font-size: 13px; */
	position: absolute;
	width: 100%;
	top: 0px;
	left: 0;
	bottom: 0;
}
.vue-header {
	padding: 0;
	z-index: 1000;
}
.vue-footer {
	line-height: 40px;
	background: #dcddd8;
	display: block;
	float: right;
}
</style>
