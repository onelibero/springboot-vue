<template>
	<el-breadcrumb class="vue-breadcrumb" separator-class="el-icon-arrow-right">
		<!-- 去掉 :to="item.path" 跳转属性 -->
		<el-breadcrumb-item v-for="item in breadcrumbList" :key="item.path">{{ item.name }}</el-breadcrumb-item>
	</el-breadcrumb>
</template>

<script>
export default {
	name: "BreadCrumb",
	data: function () {
		return {
			breadcrumbList: [],
		};
	},
	watch: {
		// 监听路由变化，重获面包屑数组
		$route: {
			handler: function (route) {
				this.getBreadCrumb();
			},
			immediate: true,
		},
	},
	methods: {
		// 根据路由获取访问路径
		getBreadCrumb() {
			/**
			 * $route 路由信息对象，只读对象
			 * $router 路由操作对象 ，只写对象
			 * this.$route.matched.filter((item) => item.path != "") ---- 路由拆分
			 * /test/helloWorld ---- 拆分成两个路由对象
			 * [{"name": "测试模块","path":"/test"},{"name": "你好世界","path":"/test/helloWorld"}]			 *
			 */
			this.breadcrumbList = this.$route.matched.filter((item) => item.path != "");
			console.log(this.breadcrumbList);
		},
	},
	created: function () {
		this.getBreadCrumb();
	},
};
</script>

<style>
.vue-breadcrumb {
	line-height: 30px !important;
	border-bottom: 1px solid gray;
}
</style>
