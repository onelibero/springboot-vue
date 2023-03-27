<template>
	<!-- 
		default-active="1" ---- 设定默认选中菜单
		:default-active="this.$route.path" ---- 当前路由为默认默认选中
	-->
	<el-menu
		class="header-menu"
		mode="horizontal"
		@select="handleSelect"
		background-color="#545c64"
		text-color="#fff"
		active-text-color="#ffd04b"
		router
	>
		<!-- 左侧导航栏折叠按钮 -->
		<span class="btn-collapse" @click="collapseCharge" :title="collapse ? '展开侧边栏' : '收起侧边栏'">
			<i class="el-icon-menu"></i>
		</span>
		<!-- 系统标题 -->
		<h3 class="header-title margin-l-10">SFAC_Admin</h3>
		<div class="fr">
			<el-submenu class="fr" index="">
				<template slot="title">
					<!--
						展示头像
						头像大小：["large", "medium", "small"]
						方形头像：shape="square"
					-->
					<el-avatar size="medium" :src="user.userImage"></el-avatar>
					<!-- 展示用户名 -->
					<!-- {{ user.userName }} -->
				</template>

				<el-menu-item index="/account/profile">Profile</el-menu-item>
				<el-menu-item @click="logout">Logout</el-menu-item>
			</el-submenu>
			<!-- <el-menu-item class="fr" index="/account/info">消息中心</el-menu-item> -->
		</div>
	</el-menu>
</template>
<script>
export default {
	name: "Header",
	props: {
		user: {
			type: Object,
		},
	},
	data() {
		return {
			collapse: false,
		};
	},
	methods: {
		handleSelect(key, keyPath) {
			console.log(key, keyPath);
		},
		collapseCharge() {
			this.collapse = !this.collapse;
			// 通过 VuexStore 改变 collapse 状态，在 layout 和 sideBar 中取值
			this.$VuexStore.commit("changeCollapse", this.collapse);
			console.log(this.$VuexStore.state.collapse);
		},
		logout() {
			// 没有后台方法，由前端清除 token
			this.$VuexStore.commit("setToken", "");
			this.$router.push("/login");
		},
	},
};
</script>
<style lang="scss" rel="stylesheet/scss">
[class*="el-icon"] {
	cursor: pointer;
}
.btn-collapse {
	padding-top: 24px;
	line-height: 1;
}
.btn-collapse {
	float: left;
	padding-left: 10px;
	color: rgb(130, 194, 189);
}
.header-title {
	float: left;
	padding-left: 10px;
	color: rgb(130, 194, 189);
	line-height: 30px;
}
.header-right {
	float: right;
	margin-left: 10px;
}
.el-header .fr {
	float: right;
}

// 去掉 el-submenu 箭头
.el-submenu > .el-submenu__title .el-submenu__icon-arrow {
	display: none;
}
</style>
